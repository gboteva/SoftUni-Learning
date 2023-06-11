SET sql_mode = '';
SET sql_mode = 'ONLY_FULL_GROUP_BY';

CREATE TABLE `players`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(10) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `age` INT NOT NULL DEFAULT 0,
    `position` CHAR(1) NOT NULL,
    `salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
    `hire_date` DATETIME,
    `skills_data_id` INT NOT NULL,
    `team_id` INT
);

CREATE TABLE `coaches`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(10) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
    `coach_level` INT NOT NULL DEFAULT 0
);

CREATE TABLE `players_coaches`(
	`player_id` INT,
    `coach_id` INT,
    CONSTRAINT fk_pc_players
    FOREIGN KEY (`player_id`)
    REFERENCES `players`(`id`),
    
    CONSTRAINT fk_pc_coaches
    FOREIGN KEY (`coach_id`)
    REFERENCES `coaches`(`id`),
    
    PRIMARY KEY(`player_id`, `coach_id`)
);

CREATE TABLE `skills_data`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `dribbling` INT DEFAULT 0,
    `pace` INT DEFAULT 0,
    `passing` INT DEFAULT 0,
    `shooting` INT DEFAULT 0,
    `speed` INT DEFAULT 0,
    `strength` INT DEFAULT 0
);

CREATE TABLE `teams`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `established` DATE,
    `fan_base` BIGINT NOT NULL DEFAULT 0,
    `stadium_id` INT NOT NULL
);

CREATE TABLE `stadiums` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `capacity` INT NOT NULL,
    `town_id` INT NOT NULL
);

CREATE TABLE `towns` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    `country_id` INT NOT NULL
);

CREATE TABLE `countries`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL
);

ALTER TABLE `players`
ADD CONSTRAINT fk_players_skills
FOREIGN KEY (`skills_data_id`)
REFERENCES `skills_data`(`id`),
ADD CONSTRAINT fk_players_temas
FOREIGN KEY (`team_id`)
REFERENCES `teams` (`id`);

ALTER TABLE `teams`
ADD CONSTRAINT fk_teams_stadiums
FOREIGN KEY (`stadium_id`)
REFERENCES `stadiums`(`id`);

ALTER TABLE `stadiums`
ADD CONSTRAINT fk_stadiums_towns
FOREIGN KEY (`town_id`)
REFERENCES `towns`(`id`);

ALTER TABLE `towns`
ADD CONSTRAINT fk_towns_countries
FOREIGN KEY (`country_id`)
REFERENCES `countries`(`id`);

#INSERT
INSERT INTO `coaches` (`first_name`, `last_name`, `salary`, `coach_level`)
(
	SELECT `first_name`, `last_name`, 2 * `salary` ,
		char_length(`first_name`)
    FROM `players`
    WHERE `age` >=45
);

#UPDATE
UPDATE `coaches` 
SET `coach_level` = `coach_level` + 1
WHERE 
LEFT(`first_name`,1) = 'A'
AND (SELECT COUNT(*) 
	FROM players_coaches AS pc
    WHERE pc.`coach_id` = id
) >=1;

#Delete
DELETE FROM players
WHERE `age` >=45;

#5.	Players 
SELECT `first_name`, `age`, `salary`
FROM `players`
ORDER BY `salary` DESC;

#06. Young offense players without contract
SELECT p.`id`,
CONCAT(p.`first_name`, ' ', p.`last_name`) AS 'full_name',
p.`age`, p.`position`, p.`hire_date`
FROM `players` AS p
JOIN `skills_data` AS sp
ON p.`skills_data_id` = sp.`id`
WHERE p.`position` = 'A' AND
	 p.`hire_date` IS NULL AND
     sp.`strength` >50 AND
     p.`age` < 23
ORDER BY p.`salary`, p.`age`;

#07. Detail info for all teams
SELECT t.`name` AS 'team_name',
 t.`established` AS 'established', 
 t.`fan_base` AS 'fan_base', 
COUNT(p.`id`) AS 'players_count'
FROM `teams` AS t
LEFT JOIN `players` AS p
ON t.`id` = p.`team_id`
GROUP BY t.`id`
ORDER BY `players_count` DESC, `fan_base` DESC;

#08. The fastest player by towns
SELECT MAX(sd.speed) AS 'max_speed', t.`name` AS town_name
FROM `skills_data` AS sd
RIGHT JOIN  `players` AS p ON p.skills_data_id = sd.id
RIGHT JOIN `teams` AS tm ON p.team_id = tm.id
RIGHT JOIN `stadiums` AS s ON s.`id` = tm.`stadium_id`
RIGHT JOIN `towns` AS t ON t.`id` = s.`town_id`
WHERE tm.`name` != 'Devify'
GROUP BY t.`id`
ORDER BY `max_speed` DESC, town_name;

#9.	Total salaries and players by country
SELECT 
c.`name`, 
COUNT(p.`id`) AS 'total_count_of_players',
SUM(p.`salary`) AS 'total_sum_of_salaries'
FROM  `players` AS p 
RIGHT JOIN `teams` AS tm ON p.team_id = tm.id
RIGHT JOIN `stadiums` AS s ON s.`id` = tm.`stadium_id`
RIGHT JOIN `towns` AS t ON t.`id` = s.`town_id`
RIGHT JOIN `countries` AS c ON t.`country_id` = c.`id`
GROUP BY c.`name`
ORDER BY  `total_count_of_players` DESC, c.name;

#10.	Find all players that play on stadium
DELIMITER $$
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE count INT;
    SET count := ( SELECT COUNT(p.id) 
	FROM  `players` AS p 
	RIGHT JOIN `teams` AS tm ON p.team_id = tm.id
	RIGHT JOIN `stadiums` AS s ON s.`id` = tm.`stadium_id`
	WHERE s.name = stadium_name
    );
    RETURN count;
END $$

#11.	Find good playmaker by teams
DELIMITER $$
CREATE PROCEDURE udp_find_playmaker (min_dribble_points INT, team_name VARCHAR(45) )
BEGIN
	SELECT CONCAT(p.`first_name`,' ', p.`last_name`) AS 'full_name',
	p.`age`, p.`salary`, sd.`dribbling`, sd.`speed`, tm.`name` AS 'team_name'
	FROM `skills_data` AS sd
	RIGHT JOIN  `players` AS p ON p.skills_data_id = sd.id
	RIGHT JOIN `teams` AS tm ON p.team_id = tm.id
	WHERE tm.name = team_name
	AND
	sd.speed > (SELECT AVG(s.speed) FROM `skills_data` AS s)
	AND
	sd.dribbling > min_dribble_points
	ORDER BY sd.speed DESC
	LIMIT 1;
END$$

