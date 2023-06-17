# 1.	Employees with Salary Above 35000
DELIMITER $$
CREATE PROCEDURE `usp_get_employees_salary_above_35000`()
BEGIN
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` > 35000
    ORDER BY `first_name`, `last_name`, `employee_id`;

END$$

#2.	Employees with Salary Above Number
DELIMITER $$
CREATE PROCEDURE `usp_get_employees_salary_above` (target_salary DECIMAL(19,4))
BEGIN
	SELECT `first_name`, `last_name` FROM `employees`
    WHERE `salary` >= target_salary
    ORDER BY `first_name`, `last_name`, `employee_id`;

END$$

#3.	Town Names Starting With
DELIMITER $$
CREATE PROCEDURE `usp_get_towns_starting_with` (letters VARCHAR(50))
BEGIN
	SELECT `name` FROM `towns`
    WHERE `name` LIKE CONCAT(letters, '%')
    ORDER BY `name`;
END$$

#4.	Employees from Town
DELIMITER $$
CREATE PROCEDURE `usp_get_employees_from_town`(town_name VARCHAR(50))
BEGIN
	SELECT e.`first_name`, e.`last_name`
    FROM `employees` AS e
    JOIN `addresses` AS a ON e.`address_id` = a.`address_id`
    JOIN `towns` AS t ON t.`town_id` = a.`town_id`
    WHERE t.`name` = town_name
    ORDER BY e.`first_name`, e.`last_name`, e.`employee_id`;
    
END$$

#5.	Salary Level Function
DELIMITER $$
CREATE FUNCTION `ufn_get_salary_level` (salary DECIMAL(19,4))
RETURNS VARCHAR(10)
deterministic
BEGIN
	DECLARE salary_level VARCHAR(10);
    IF salary < 30000 THEN SET salary_level:= 'Low';
    ELSEIF salary BETWEEN 30000 AND 50000 THEN SET salary_level:= 'Average';
    ELSE SET salary_level = 'High';
    END IF;
    RETURN salary_level;
END$$

#6.	Employees by Salary Level
DELIMITER $$
CREATE PROCEDURE `usp_get_employees_by_salary_level`(salary_level VARCHAR(50))
BEGIN
		
		SELECT `first_name`, `last_name` FROM `employees`
        WHERE (SELECT ufn_get_salary_level(`salary`)) = salary_level
        ORDER BY `first_name` DESC, `last_name` DESC;
END$$

#7.	Define Function
DELIMITER $$
CREATE FUNCTION `ufn_is_word_comprised` (set_of_letters VARCHAR(50), word VARCHAR(50)) 
RETURNS INT
DETERMINISTIC
BEGIN
	RETURN word REGEXP CONCAT('^[', set_of_letters, ']+$');
END$$


#8.	Find Full Name
DELIMITER $$
CREATE PROCEDURE `usp_get_holders_full_name`()
BEGIN	
	SELECT CONCAT_WS(' ', `first_name`, `last_name`) AS  'full_name'
    FROM `account_holders`
    ORDER BY `full_name`, `id`;
END$$

#9.	People with Balance Higher Than
DELIMITER $$
CREATE PROCEDURE `usp_get_holders_with_balance_higher_than`(target DECIMAL(19,4))
BEGIN
	SELECT ah.`first_name`, ah.`last_name` FROM `account_holders` AS ah
    JOIN `accounts` AS a ON ah.`id` = a.`account_holder_id`
    GROUP BY a.`account_holder_id`
    HAVING SUM(`balance`) > target;
END$$

#10.	Future Value Function
DELIMITER $$
CREATE FUNCTION `ufn_calculate_future_value`(sum DECIMAL(19,4), interest_rate DOUBLE(19,4), years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN
	RETURN sum * (POW((1 + interest_rate), years));
END$$

#11.	Calculating Interest
DELIMITER $$
CREATE PROCEDURE `usp_calculate_future_value_for_account`(id INT, interest DECIMAL(19,4))
BEGIN
	SELECT a.`id` AS 'account_id',
    ah.`first_name`,
    ah.`last_name`,
    a.`balance` AS 'current_balance',
	`ufn_calculate_future_value`(a.`balance`, interest, 5) AS 'balance_in_5_years'
    FROM `account_holders` AS ah 
    JOIN `accounts` AS a
    ON ah.`id` = a.`account_holder_id`
    WHERE a.`id` = id;
END$$

#12.	Deposit Money
DELIMITER $$
CREATE PROCEDURE `usp_deposit_money`(account_id INT, money_amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF money_amount <= 0 THEN ROLLBACK;
    ELSE 
    UPDATE `accounts`
    SET `balance` = `balance` + money_amount;
    END IF;
END$$ 

#13.	Withdraw Money
DELIMITER $$
CREATE PROCEDURE `usp_withdraw_money`(account_id INT, money_amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    
	IF (SELECT `balance` FROM `accounts` WHERE `id` = account_id) < money_amount
    OR money_amount <= 0 THEN ROLLBACK;
   
   ELSE UPDATE `accounts`
    SET `balance` = `balance` - money_amount;
    END IF;
END$$ 

#14.	Money Transfer
DELIMITER $$
CREATE PROCEDURE `usp_transfer_money`(from_account_id INT, to_account_id INT, amount DECIMAL(19,4))
BEGIN
	START TRANSACTION;
    IF (amount < 0) OR
		((SELECT `balance` FROM `accounts` WHERE `id` = from_account_id) < amount) OR
		(SELECT COUNT(`id`) FROM `accounts` WHERE `id` = from_account_id) <> 1 OR
		(SELECT COUNT(`id`) FROM `accounts` WHERE `id` = to_account_id) <> 1
    THEN ROLLBACK;
    ELSE 
	UPDATE `accounts`
    SET `balance` = `balance` - amount
    WHERE `id` = `from_account_id`;
    
    UPDATE `accounts`
    SET `balance` = `balance`+ amount
    WHERE `id` = `to_account_id`;
    END IF;
END$$

#15. Log Accounts Trigger
CREATE TABLE `logs` (
log_id INT PRIMARY KEY AUTO_INCREMENT, 
account_id INT NOT NULL, 
old_sum DECIMAL(19,4) NOT NULL, 
new_sum DECIMAL(19,4) NOT NULL
); 

DELIMETER $$
CREATE TRIGGER tr_update_balance
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN
	INSERT INTO `logs` (`account_id`, `old_sum`, `new_sum`)
    VALUES(OLD.`id`, OLD.`balance`, NEW.`balance`);
END$$

#16.	Emails Trigger
CREATE TABLE `notification_emails`(
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`recipient` INT NOT NULL, 
`subject` TEXT, 
`body` TEXT);

DELIMITER $$
CREATE TRIGGER tr_insert_record
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
INSERT INTO `notification_emails` (`recipient`, `subject`, `body`)
VALUES(NEW.`account_id`, 
			CONCAT('Balance change for account: ', NEW.`account_id`),
			CONCAT('On ', NOW(), ' your balance was changed from ', NEW.`old_sum`, ' to ', NEW.`new_sum`, '.')
		);
END $$