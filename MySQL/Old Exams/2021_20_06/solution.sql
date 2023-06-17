create database `stc`;
drop database `stc`;

CREATE TABLE `addresses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL
);

CREATE TABLE `categories` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL
);

CREATE TABLE `clients` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `full_name` VARCHAR(50) NOT NULL,
    `phone_number` VARCHAR(20) NOT NULL
);

CREATE TABLE `drivers` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(30) NOT NULL,
    `last_name` VARCHAR(30) NOT NULL,
    `age` INT NOT NULL,
    `rating` FLOAT DEFAULT 5.5
);

CREATE TABLE `cars`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `make` VARCHAR(20) NOT NULL,
    `model` VARCHAR(20),
    `year` INT NOT NULL DEFAULT 0,
    `mileage` INT DEFAULT 0,
    `condition` CHAR(1) NOT NULL,
    `category_id` INT NOT NULL,
    CONSTRAINT fk_cars_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories`(`id`)
);

CREATE TABLE `courses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `from_address_id` INT NOT NULL,
    `start` DATETIME NOT NULL,
    `bill` DECIMAL(10,2) DEFAULT 10,
	`car_id` INT NOT NULL,
    `client_id` INT NOT NULL,
    CONSTRAINT fk_courses_addresses
    FOREIGN KEY (`from_address_id`)
    REFERENCES `addresses`(`id`),
    CONSTRAINT fk_courses_cars
    FOREIGN KEY (`car_id`)
    REFERENCES `cars`(`id`),
    CONSTrAINT fk_courses_clients
    FOREIGN KEY (`client_id`)
    REFERENCES `clients`(`id`)
);

CREATE TABLE `cars_drivers`(
	`car_id` INT NOT NULL,
    `driver_id` INT NOT NULL,
    CONSTRAINT pk_cars_drivers
    PRIMARY KEY (`car_id`, `driver_id`),
    CONSTRAINT fk_cd_cars
    FOREIGN KEY (`car_id`)
    REFERENCES `cars`(`id`),
    CONSTRAINT fk_cd_drivers
    FOREIGN KEY (`driver_id`)
    REFERENCES `drivers`(`id`)
);

#INSERT
INSERT INTO `clients`(`full_name`, `phone_number`)
SELECT CONCAT(`first_name`, ' ', `last_name`),
CONCAT('(088) 9999', (`id` * 2))
FROM `drivers`
WHERE `id` BETWEEN 10 AND 20;

#UPDATE
UPDATE `cars`
SET `condition` = 'C'
WHERE (`mileage` >= 800000 OR `mileage` IS NULL)
AND `year` <= 2010 AND `make` <> 'Mercedes-Benz';

#DELETE
DELETE c FROM `clients` AS c
LEFT JOIN `courses` AS cr
ON c.`id` = cr.`client_id`
WHERE cr.`id` IS NULL
AND char_length(c.`full_name`) > 3;

#05.Cars
SELECT `make`, `model`, `condition`
FROM `cars`
ORDER BY `id`;

#06. Drivers and Cars
SELECT d.`first_name`, d.`last_name`, c.`make`, c.`model`,
c.`mileage`
FROM `drivers` AS d
JOIN `cars_drivers` AS cd
ON d.`id` = cd.driver_id
JOIN `cars` AS c
ON cd.car_id = c.`id`
WHERE c.`mileage` IS NOT NULL
ORDER BY c.`mileage` DESC, d.`first_name`;

#07. Number of courses
SELECT c.`id`, c.`make`, c.`mileage`,
COUNT(cr.`id`) AS 'count_of_courses', 
ROUND(AVG(cr.`bill`),2) AS 'avg_bill'
FROM `cars` AS c
LEFT JOIN `courses` AS cr
ON c.`id` = cr.`car_id`
GROUP BY c.`id`
HAVING `count_of_courses` <> 2
ORDER BY `count_of_courses` DESC, c.`id`;

#08. Regular clients
SELECT c.`full_name`, 
COUNT(cr.car_id) AS 'count_of_cars',
SUM(cr.`bill`) AS 'total_sum'
FROM `clients` AS c
JOIN `courses` AS cr
ON c.`id` = cr.`client_id`
WHERE SUBSTRING(c.`full_name`,2,1) = 'a'
GROUP BY c.`id`
HAVING `count_of_cars` > 1
ORDER BY c.`full_name`;

#09. Full info for courses
SELECT a.`name`,
	(CASE
		WHEN HOUR(cr.`start`) BETWEEN 6 AND 20 THEN 'Day'
        ELSE 'Night'
	END) AS 'day_time',
    cr.`bill`,
    c.`full_name`,
    cars.`make`,
    cars.`model`,
    cat.`name`
FROM `courses` AS cr
JOIN `addresses` AS a ON cr.`from_address_id` = a.`id`
JOIN `clients` AS c ON cr.`client_id` = c.`id`
JOIN `cars` AS cars ON cars.`id` = cr.`car_id`
JOIN `categories` AS cat ON cars.`category_id` = cat.`id`
GROUP BY cr.`id`
ORDER BY cr.`id`;

#10.	Find all courses by client’s phone number
DELIMITER $$
CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20)) 
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE count_courses INT;
    SET count_courses :=(
		SELECT COUNT(cr.`id`)
        FROM `courses` AS cr
        JOIN `clients` AS cl
        ON cr.`client_id` = cl.`id`
        WHERE cl.`phone_number` = phone_num
    );
    
    RETURN count_courses;
END;

#11. Full info for address
DELIMITER $$
CREATE PROCEDURE udp_courses_by_address (address_name VARCHAR(100))
BEGIN	
	SELECT a.`name`, c.`full_name`,
    (
		CASE 
			WHEN cr.`bill` <= 20 THEN 'Low'
            WHEN cr.`bill` <= 30 THEN 'Medium'
            ELSE 'High'
            END
    ) AS 'level_of_bill',
    cars.`make`,
    cars.`condition`,
    cat.`name`
    FROM `courses` AS cr
	JOIN `addresses` AS a ON cr.`from_address_id` = a.`id`
	JOIN `clients` AS c ON cr.`client_id` = c.`id`
	JOIN `cars` AS cars ON cars.`id` = cr.`car_id`
	JOIN `categories` AS cat ON cars.`category_id` = cat.`id`
    GROUP BY cr.`id`
    HAVING a.`name` = address_name
    ORDER BY cars.`make`, c.`full_name`;
END$$