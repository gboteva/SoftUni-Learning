USE `minions`;
CREATE TABLE `minions`  (
	id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE `towns` (
	town_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL
);

ALTER TABLE `minions`
ADD COLUMN `town_id` INT NOT NULL;

ALTER TABLE `minions`
ADD CONSTRAINT `fk_minions_towns`
FOREIGN KEY (`town_id`) 
REFERENCES `towns`(`id`);

INSERT INTO `towns`
VALUES(1, 'Sofia'), (2, 'Plovdiv'), (3, 'Varna');

INSERT INTO `minions` 
VALUES (1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3), 
(3, 'Steward', NULL, 2);

TRUNCATE `minions`;
DROP TABLE `minions`;
DROP TABLE `towns`;

CREATE TABLE `people` (
id INT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(200) NOT NULL,
picture BLOB,
height DOUBLE(3,2),
weight DOUBLE(3,2),
gender CHAR(1) NOT NULL,
birthdate DATE NOT NULL,
biography LONGTEXT
);

INSERT INTO `people` (`id`, `name`, `gender`, `birthdate`)
VALUES (1, 'Galka', 'f', '1989-10-04'),
(2, 'Krasi', 'f', '1995-05-10'),
(3, 'Sashka', 'f', '1989-06-06'),
(4, 'Svetla', 'f', '1972-04-23'),
(5, 'Bobo', 'm', '1990-12-12');

CREATE TABLE `users`(
id INT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
username VARCHAR(30) UNIQUE NOT NULL,
password VARCHAR(26) NOT NULL,
profile_picture BLOB,
last_login_time TIMESTAMP,
is_deleted BOOLEAN
);

INSERT INTO `users` (`id`, `username`, `password`)
VALUES(1, 'galkab', '12345'), (2, 'galka', '12345'),
(3, 'gboteva', '123456'), (4, 'bobo', 'dsadad'), 
(5, 'krasi', '121212');


ALTER TABLE `users`
DROP PRIMARY KEY,
ADD PRIMARY KEY pk_users (`id`, `username`);

ALTER TABLE `users`
MODIFY `last_login_time` DATETIME DEFAULT NOW();

ALTER TABLE `users`
DROP PRIMARY KEY, 
ADD CONSTRAINT pk_users
PRIMARY KEY `users` (`id`),
MODIFY COLUMN `username` VARCHAR(30) UNIQUE;

CREATE DATABASE `movies`;
CREATE TABLE `directors` (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	director_name VARCHAR(50) NOT NULL,
	notes LONGTEXT
);
CREATE TABLE `genres` (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	genre_name VARCHAR(50) NOT NULL,
	notes LONGTEXT
);

CREATE TABLE `categories` (
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    category_name VARCHAR(50) NOT NULL,
    notes LONGTEXT
);

CREATE TABLE `movies`(
	id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(100) NOT NULL,
    director_id INT,
    copyright_year YEAR,
    length INT,
    genre_id INT,
    category_id INT,
    rating INT,
    notes LONGTEXT
);

INSERT INTO `directors` (`id`, `director_name`)
VALUES(1, 'copola'), (2, 'me'), (3, 'you'), (4,'he'), (5, 'she');

INSERT INTO `genres` (`id`, `genre_name`)
VALUES(1, 'copola'), (2, 'me'), (3, 'you'), (4,'he'), (5, 'she');

INSERT INTO `categories` (`id`, `category_name`)
VALUES(1, 'copola'), (2, 'me'), (3, 'you'), (4,'he'), (5, 'she');

INSERT INTO `movies` (`id`, `title`)
VALUES(1, 'copola'), (2, 'me'), (3, 'you'), (4,'he'), (5, 'she');

CREATE DATABASE `car_rental`;
CREATE TABLE `categories`(
	id INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(50) NOT NULL,
    daily_rate INT,
    weekly_rate INT,
    monthly_rate INT,
    weekend_rate INT
);

INSERT INTO `categories` (`category`)
VALUES('test1'), ('test2'), ('test3');

CREATE TABLE `cars` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    plate_number VARCHAR(50),
    make VARCHAR(50),
    model VARCHAR (50)  NOT NULL,
    car_year YEAR,
    category_id INT,
    doors INT,
    picture BLOB,
    car_condition BOOLEAN,
    available BOOLEAN
);

INSERT INTO `cars`  (`model`)
VALUES ('test'), ('test2'), ('test3');

CREATE TABLE `employees` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50),
    title VARCHAR(50),
    notes TEXT
);
INSERT INTO `employees` (`first_name`)
VALUES ('test'), ('test2'), ('test3');

CREATE TABLE `customers` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    driver_licence VARCHAR(20),
    full_name VARCHAR(50) NOT NULL,
    address TEXT,
    city VARCHAR(50),
    zip_code INT,
    notes TEXT
);

INSERT INTO `customers` (`full_name`)
 VALUES ('test'), ('test2'), ('test3');

CREATE TABLE `rental_orders` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    customer_id INT,
    car_id INT,
    car_condition BOOLEAN,
    tank_level DOUBLE,
    kilometrage_start DOUBLE,
    kilometrage_end DOUBLE,
    total_kilometrage DOUBLE,
    start_date DATE,
    end_date DATE,
    total_days INT,
    rate_applied INT,
    tax_rate DOUBLE,
    order_status VARCHAR(100) NOT NULL,
    notes TEXT
);

INSERT INTO `rental_orders` (`order_status`)
VALUES ('test'), ('test2'), ('test3');

CREATE DATABASE `soft_uni`;
CREATE TABLE `towns` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);

INSERT INTO `towns` (`name`)
VALUES ('Sofia'), ('Plovdiv'), ('Varna'), ('Burgas');

CREATE TABLE `addresses`(
	id INT PRIMARY KEY AUTO_INCREMENT,
    adress_text TEXT,
    town_id INT
);

CREATE TABLE `departments` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);
INSERT INTO `departments` (`name`)
VALUES ('Engineering'), ('Sales'), ('Marketing'), ('Software Development'), ('Quality Assurance');


CREATE TABLE `employees`(
	id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    middle_name VARCHAR(50),
    last_name VARCHAR(50),
    job_title VARCHAR(50),
    department_id INT,
    hire_date DATE,
    salary DOUBLE,
    address_id INT
);

INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`)
VALUES('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88);

SELECT `name` FROM `towns`
ORDER BY `name`;
SELECT `name` FROM `departments`
ORDER BY `name`;
SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees`
ORDER BY `salary` DESC;

UPDATE `employees`
SET `salary` = `salary`* 1.1;
SELECT `salary` FROM `employees`; 



