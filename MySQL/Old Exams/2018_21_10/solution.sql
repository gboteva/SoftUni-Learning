drop database `colonial_journey_management_system_db`;
create database `colonial_journey_management_system_db`;
CREATE TABLE `planets`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL
);

CREATE TABLE `spaceports`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `planet_id` INT(11),
    CONSTRAINT fk_spaceports_planets
    FOREIGN KEY (`planet_id`)
    REFERENCES `planets`(`id`)
);

CREATE TABLE `spaceships`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `manufacturer` VARCHAR(30) NOT NULL,
    `light_speed_rate` INT(11) DEFAULT 0
);

CREATE TABLE `colonists`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `ucn` CHAR(10) UNIQUE NOT NULL,
    `birth_date` DATE NOT NULL
);

CREATE TABLE `journeys`(
	`id` INT (11) PRIMARY KEY AUTO_INCREMENT,
    `journey_start` DATETIME NOT NULL,
    `journey_end` DATETIME NOT NULL,
    `purpose` ENUM('Medical', 'Technical', 'Educational', 'Military'),
    `destination_spaceport_id` INT(11),
    `spaceship_id` INT(11),
    CONSTRAINT fk_journeys_spaceports
    FOREIGN KEY (`destination_spaceport_id`)
    REFERENCES `spaceports`(`id`),
    CONSTRAINT fk_journeys_spaceships
    FOREIGN KEY (`spaceship_id`)
    REFERENCES `spaceships`(`id`)
);

CREATE TABLE `travel_cards`(
	`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `card_number` CHAR(10) UNIQUE NOT NULL,
    `job_during_journey` ENUM('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook'),
    `colonist_id` INT(11),
    `journey_id` INT(11),
    CONSTRAINT fk_travelCards_colonists
    FOREIGN KEY (`colonist_id`)
    REFERENCES `colonists`(`id`),
    CONSTRAINT fk_travelCards_journeys
    FOREIGN KEY (`journey_id`)
    REFERENCES `journeys`(`id`)
);

#01.	Data Insertion
INSERT INTO `travel_cards`(`card_number`, `job_during_journey`, `colonist_id`, `journey_id`)
SELECT 
	(CASE 
    WHEN `birth_date` > '1980-01-01' THEN CONCAT(YEAR(`birth_date`), DAY(`birth_date`), LEFT(`ucn`, 4))
    ELSE CONCAT(YEAR(`birth_date`), MONTH(`birth_date`), RIGHT(`ucn`, 4) )
	END),
 
	(CASE 
		WHEN `id` MOD 2 = 0 THEN 'Pilot'
		WHEN `id` MOD 3 = 0 THEN 'Cook'
		ELSE 'Engineer'
	END)
, `id`,
LEFT(`ucn`, 1)    
FROM `colonists`
WHERE `id` BETWEEN 96 AND 100;

#02.	Data Update
	UPDATE `journeys`
    SET `purpose` = (
	CASE 
        WHEN `id` MOD 2 = 0 THEN 'Medical'
        WHEN `id` MOD 3 = 0 THEN 'Technical'
        WHEN `id` MOD 5 = 0 THEN 'Educational'
        WHEN `id` MOD 7 = 0 THEN 'Military'
        ELSE `purpose`
	END);

#03.	Data Deletion
DELETE c FROM `colonists` AS c
LEFT JOIN `travel_cards` AS tc
ON c.`id` = tc.`colonist_id`
WHERE `journey_id` IS NULL;

#04. Extract all travel cards
SELECT `card_number`, `job_during_journey`
FROM `travel_cards`
ORDER BY `card_number`;

#05. Extract all colonists
SELECT `id`, 
concat_ws(' ', `first_name`, `last_name`) AS 'full_name',
`ucn`
FROM `colonists`
ORDER BY `first_name`, `last_name`, `id`;

#06.	Extract all military journeys
SELECT `id`, `journey_start`, `journey_end`
FROM `journeys`
WHERE `purpose` = 'Military'
ORDER BY `journey_start`;

#07. Extract all pilots
SELECT c.`id`, CONCAT_WS(' ', c.`first_name`, c.`last_name`) AS 'full_name'
FROM `colonists` AS c
JOIN `travel_cards` AS tc
ON c.`id` = tc.`colonist_id`
WHERE tc.`job_during_journey` = 'Pilot'
ORDER BY c.`id`;

#08. Count all colonists
SELECT COUNT(tc.`colonist_id`) AS 'count'
FROM `travel_cards` AS tc
JOIN `journeys` AS j
ON tc.`journey_id` = j.`id`
WHERE j.`purpose` = 'Technical';

#09.Extract the fastest spaceship
SELECT ss.`name`, sp.`name`
FROM `spaceships` AS ss
JOIN `journeys` AS j
ON ss.`id` = j.`spaceship_id`
JOIN `spaceports` AS sp
ON j.`destination_spaceport_id` = sp.`id`
ORDER BY ss.`light_speed_rate` DESC
LIMIT 1;

#10. Extract - pilots younger than 30 years
SELECT ss.`name`, ss.`manufacturer`
FROM `spaceships` AS ss
JOIN `journeys` AS j
ON ss.`id` = j.`spaceship_id`
JOIN `travel_cards` AS tc
ON j.`id` = tc.`journey_id`
JOIN `colonists` AS c
ON c.`id` = tc.`colonist_id`
WHERE tc.job_during_journey = 'Pilot'
AND '2019' - YEAR(c.`birth_date`) < 30
ORDER BY ss.`name`;

#11. Extract all educational mission
SELECT p.`name` AS 'planet_name', sp.`name` AS 'spaceport_name'
FROM `planets` AS p
JOIN `spaceports` AS sp
ON p.`id` = sp.`planet_id`
JOIN `journeys` AS j
ON sp.`id` = j.`destination_spaceport_id`
WHERE j.`purpose` = 'Educational'
ORDER BY sp.`name` DESC;

#12. Extract all planets and their journey count
SELECT p.`name` AS 'planet_name', COUNT(j.`id`) AS 'journeys_count'
FROM `planets` AS p
JOIN `spaceports` AS sp
ON p.`id` = sp.`planet_id`
JOIN `journeys` AS j
ON sp.`id` = j.`destination_spaceport_id`
GROUP BY p.`name`
ORDER BY `journeys_count` DESC, p.`name`;

#13. Extract the shortest journey
SELECT j.`id`, p.`name`, sp.`name`, j.`purpose`
FROM `planets` AS p
JOIN `spaceports` AS sp
ON p.`id` = sp.`planet_id`
JOIN `journeys` AS j
ON sp.`id` = j.`destination_spaceport_id`
ORDER BY datediff(j.`journey_end`, j.`journey_start`) 
LIMIT 1;

#14. Extract the less popular job
SELECT tc.`job_during_journey` AS 'job_name'
FROM  `travel_cards` AS tc
WHERE tc.`journey_id` = (
	SELECT j.`id`
    FROM `journeys` AS j
    ORDER BY DATEDIFF(j.`journey_end`, j.`journey_start`) DESC
    LIMIT 1
)
GROUP BY tc.`job_during_journey`
ORDER BY COUNT(tc.`colonist_id`)
LIMIT 1;

#15. Get colonists count
DELIMITER $$
CREATE FUNCTION udf_count_colonists_by_destination_planet (planet_name VARCHAR (30)) 
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN(
		SELECT COUNT(tc.`colonist_id`)
		FROM `planets` AS p
		JOIN `spaceports` AS sp
		ON p.`id` = sp.`planet_id`
		JOIN `journeys` AS j
		ON sp.`id` = j.`destination_spaceport_id`
		JOIN `travel_cards` AS tc
		ON j.`id` = tc.`journey_id`
        WHERE p.`name` = planet_name
    );
END$$

#Modify spaceship
DELIMITER $$
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate(spaceship_name VARCHAR(50), light_speed_rate_increse INT(11))
BEGIN
		START TRANSACTION;
        IF ((SELECT `name` FROM `spaceships`
			WHERE `name` = spaceship_name ) IS NOT NULL) 
		THEN 
			UPDATE `spaceships`
            SET light_speed_rate = light_speed_rate + light_speed_rate_increse
            WHERE `name` = spaceship_name; 
		ELSE
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
			ROLLBACK; 
		END IF;
END$$


