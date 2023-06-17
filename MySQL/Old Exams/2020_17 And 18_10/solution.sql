# Table design
CREATE TABLE `pictures`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `url` VARCHAR(100) NOT NULL,
    `added_on` DATETIME NOT NULL
);

CREATE TABLE `categories`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) UNIQUE NOT NULL
);

CREATE TABLE `products`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(40) UNIQUE NOT NULL,
    `best_before` DATE,
    `price` DECIMAL(10,2) NOT NULL,
    `description` TEXT,
    `category_id` INT NOT NULL,
    `picture_id` INT NOT NULL,
    
    CONSTRAINT fk_products_categories
    FOREIGN KEY (`category_id`)
    REFERENCES `categories`(`id`),
    
    CONSTRAINT fk_products_pictures
    FOREIGN KEY (`picture_id`)
    REFERENCES `pictures`(`id`)
);

CREATE TABLE `towns`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE `addresses`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) UNIQUE NOT NULL,
    `town_id` INT NOT NULL,
    CONSTRAINT fk_add_towns
    FOREIGN KEY (`town_id`)
    REFERENCES `towns`(`id`)
);

CREATE TABLE `stores`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(20) UNIQUE NOT NULL,
    `rating` FLOAT NOT NULL,
    `has_parking` BOOLEAN DEFAULT FALSE,
    `address_id` INT NOT NULL,
    CONSTRAINT fk_stores_addresses
    FOREIGN KEY (`address_id`)
    REFERENCES `addresses`(`id`)
);

CREATE TABLE `products_stores`(
	`product_id` INT NOT NULL,
    `store_id` INT NOT NULL,
    CONSTRAINT fk_ps_products
    FOREIGN KEY (`product_id`)
    REFERENCES `products`(`id`),
    
    CONSTRAINT fk_ps_stores
    FOREIGN KEY (`store_id`)
    REFERENCES `stores`(`id`),
    
    PRIMARY KEY(`product_id`, `store_id`)
);

CREATE TABLE `employees` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(15) NOT NULL,
    `middle_name` CHAR,
    `last_name` VARCHAR(20) NOT NULL,
    `salary` DECIMAL(19,2) DEFAULT 0,
    `hire_date` DATE NOT NULL,
    `manager_id` INT,
    `store_id` INT NOT NULL,
    CONSTRAINT fk_empl_empl
    FOREIGN KEY (`manager_id`)
    REFERENCES `employees`(`id`),
    CONSTRAINT fk_empl_stores
    FOREIGN KEY (`store_id`)
    REFERENCES `stores`(`id`)
);

# 5 Employees
SELECT `first_name`, `middle_name`, `last_name`,
`salary`, `hire_date` FROM `employees`
ORDER BY `hire_date` DESC;

#06. Products with old pictures
SELECT prod.`name` AS 'product_name',
	   prod.`price` AS 'price',
       prod.`best_before` AS 'best_before',
       CONCAT(SUBSTRING(prod.`description`, 1, 10), '...') AS 'short_description',
       pic.`url` AS 'url'
FROM `products` AS prod
JOIN `pictures` AS pic
ON prod.`picture_id` = pic.`id`
WHERE char_length(prod.`description`) > 100
AND YEAR(pic.`added_on`) < '2019'
AND prod.`price` > 20
ORDER BY prod.`price` DESC;

#07. Counts of products in stores
SELECT s.`name` AS `name`,
COUNT(p.`id`) AS 'product_count',
ROUND(AVG(p.`price`),2) AS 'avg'
FROM `stores` AS s
LEFT JOIN `products_stores` AS ps
ON s.`id` = ps.`store_id`
LEFT JOIN `products` AS p
ON ps.`product_id` = p.`id`
GROUP BY s.`id`
ORDER BY `product_count` DESC, `avg` DESC, s.`id`;

#08. Specific employee
SELECT CONCAT(e.`first_name`, ' ', e.`last_name`) AS 'Full_name',
s.`name` AS 'Store_name',
a.`name` AS 'address',
e.`salary` AS 'salary'
FROM `employees` AS e
JOIN `stores` AS s
ON e.`store_id` = s.`id`
JOIN `addresses` AS a
ON s.`address_id` = a.`id`
WHERE e.`salary` < 4000 AND
a.`name` LIKE ('%5%') AND
char_length(s.`name`) > 8 AND
RIGHT(e.`last_name`, 1) = 'n';

#09. Find all information of stores
SELECT REVERSE(s.`name`) AS 'reversed_name',
CONCAT(upper(t.`name`), '-', a.`name`) AS 'full_address',
COUNT(e.`id`) AS 'employes_count'
FROM `stores` AS s
JOIN `addresses` AS a
ON s.`address_id` = a.`id`
JOIN `towns` AS t
ON a.`town_id` = t.`id`
JOIN `employees` AS e
ON s.`id` = e.`store_id`
GROUP BY s.`id`
HAVING `employes_count` >= 1
ORDER BY `full_address`;

#10. Find name of top paid employee by store name
DELIMITER $$
CREATE FUNCTION `udf_top_paid_employee_by_store`(store_name VARCHAR(50)) 
RETURNS VARCHAR(100)
DETERMINISTIC
BEGIN
RETURN (
SELECT 
CONCAT(e.`first_name`,' ', e.`middle_name`,'.', ' ', e.`last_name`, ' ',
'works', ' ', 'in', ' ', 'store', ' ', 'for', ' ',
YEAR('2020-10-18')-YEAR(e.`hire_date`), ' ', 'years'
) 
FROM `employees` AS e
JOIN `stores` AS s
ON e.`store_id` = s.`id`
WHERE s.`name` = store_name
ORDER BY e.`salary` DESC
LIMIT 1
);
END$$

#11. Update product price by address
DELIMITER $$
CREATE PROCEDURE `udp_update_product_price` (address_name VARCHAR (50))
BEGIN
	UPDATE `products` AS p
	JOIN `products_stores` AS ps
	ON p.`id` = ps.`product_id`
	JOIN `stores` AS s
	ON ps.`store_id` = s.`id`
	JOIN `addresses` AS a
	ON s.`address_id` = a.`id`
	SET p.`price` = p.`price` + IF (LEFT(a.`name`, 1) = '0', 100, 200)
	WHERE a.`name` = address_name;
END$$

#Insert
INSERT INTO `products_stores` (`product_id`, `store_id`)
SELECT p.`id`, 1
FROM `products` AS p
LEFT JOIN `products_stores` AS ps
ON p.`id` = ps.`product_id`
WHERE ps.`store_id` IS NULL;

#UPDATE
UPDATE `employees`
SET `manager_id` = 3, `salary` = `salary` - 500
WHERE YEAR(`hire_date`) > 2003 AND
`store_id` <> 5 AND `store_id` <> 14;

#DELETE
DELETE e FROM `employees` AS e
WHERE `manager_id` IS NOT NULL AND
`salary` >=6000;
