#1. Table design
CREATE TABLE `users` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `username` VARCHAR(30) UNIQUE NOT NULL,
    `password` VARCHAR(30) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `gender` CHAR(1) NOT NULL,
    `age` INT NOT NULL,
    `job_title` VARCHAR(40) NOT NULL,
    `ip` VARCHAR(30) NOT NULL
);

CREATE TABLE `addresses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `address` VARCHAR(30) NOT NULL,
    `town` VARCHAR(30) NOT NULL,
    `country` VARCHAR(30) NOT NULL,
    `user_id` INT NOT NULL,
    CONSTRAINT fk_addresses_users
    FOREIGN KEY (`user_id`)
    REFERENCES `users`(`id`)
);

CREATE TABLE `photos`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `description` TEXT NOT NULL,
    `date` DATETIME NOT NULL,
    `views` INT NOT NULL DEFAULT 0
);

CREATE TABLE `comments`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `comment` VARCHAR(255) NOT NULL,
    `date` DATETIME NOT NULL,
    `photo_id` INT NOT NULL,
    CONSTRAINT fk_comments_photos
    FOREIGN KEY (`photo_id`)
    REFERENCES `photos`(`id`)
);

CREATE TABLE `users_photos`(
	`user_id` INT NOT NULL,
    `photo_id` INT NOT NULL,
    CONSTRAINT fk_up_users
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`),
    CONSTRAINT fk_up_photos
    FOREIGN KEY (`photo_id`)
    REFERENCES `photos`(`id`)
);

CREATE TABLE `likes`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `photo_id` INT,
    `user_id` INT,
    CONSTRAINT fk_likes_photos
    FOREIGN KEY (`photo_id`)
    REFERENCES `photos`(`id`),
    CONSTRAINT fk_likes_users
    FOREIGN KEY (`user_id`)
    REFERENCES `users`(`id`)
);

# 5. Users
SELECT `username`, `gender`, `age`
FROM `users`
ORDER BY `age` DESC, `username`;

#06. Extract 5 most commented photos
SELECT p.`id`, p.`date` AS 'date_and_time', 
p.`description`, COUNT(c.`id`) AS 'commentsCount'
FROM `photos` AS p
JOIN `comments` AS c
ON p.`id` = c.`photo_id`
GROUP BY p.`id`
ORDER BY `commentsCount` DESC, p.id
LIMIT 5;

#07. Lucky users
SELECT CONCAT(u.`id`, ' ', u.`username`) AS 'id_username',
u.`email`
FROM `users` AS u
JOIN `users_photos` AS up
ON u.`id` = up.`user_id`
WHERE u.`id` = up.`photo_id`
ORDER BY u.`id`;

#08. Count likes and comments
SELECT p.`id`, COUNT(l.`id`) AS 'likes_count',
IF(`comments_count` IS NULL, 0, `comments_count`) AS 'comments_count'
FROM `photos` AS p
LEFT JOIN (SELECT p.`id`, COUNT(c.`id`) AS 'comments_count'
		FROM `photos` AS p
		LEFT JOIN  `comments` AS c
        ON p.`id` = c.`photo_id`
		GROUP BY p.`id`) AS com
ON com.`id` = p.`id`
LEFT JOIN `likes` AS l  ON p.`id` = l.`photo_id`
GROUP BY p.`id`
ORDER BY `likes_count` DESC, `comments_count` DESC, p.id;

#09.	The Photo on the Tenth Day of the Month
SELECT CONCAT(SUBSTRING(`description`, 1, 30), '...') AS 'summary',
`date`
FROM `photos`
WHERE DAY(`date`) = '10'
ORDER BY `date` DESC;

#10. Get user’s photos count
DELIMITER $$
CREATE FUNCTION udf_users_photos_count(username VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE count_photos INT;
    SET count_photos:= (
		SELECT COUNT(`photo_id`) 
		FROM `users` AS u
		JOIN `users_photos` AS up
		ON u.`id` = up.`user_id`
		WHERE u.`username` = username
    );
    RETURN count_photos;
END$$

#11.	Increase User Age
DELIMITER $$
CREATE PROCEDURE udp_modify_user (address VARCHAR(30), town VARCHAR(30)) 
BEGIN
	UPDATE `users` AS u
	JOIN `addresses` AS a
	ON u.`id` = a.`user_id`
	SET `age` = `age` + 10
	WHERE a.`town` = town 
	AND a.`address` = address;
END$$

#02 INSERT
INSERT INTO `addresses` (`address`, `town`, `country`, `user_id`)
SELECT `username`, `password`, `ip`, `age`
FROM `users`
WHERE `gender` = 'M';

#Update
UPDATE `addresses`
SET `country` = (
	CASE 
	  WHEN LEFT (`country`, 1) = 'B' THEN 'Blocked'
	  WHEN LEFT (`country`, 1) = 'T' THEN 'Test'
      WHEN LEFT (`country`, 1) = 'P' THEN 'In Progress'
      ELSE `country` = `country`
END);

#Delete
DELETE FROM `addresses`
WHERE `id` MOD 3 = 0;
