-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema argos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema argos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `inventory` DEFAULT CHARACTER SET utf8 ;
USE `inventory` ;

-- -----------------------------------------------------
-- Table `inventory`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  `authority_id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`authority_id`),
  UNIQUE INDEX `IX_AUTH_USERNAME` (`username` ASC, `authority` ASC),
  INDEX `FK_AUTHORITIES_USERS_idx` (`username` ASC),
  CONSTRAINT `FK_AUTHORITIES_USERS`
    FOREIGN KEY (`username`)
    REFERENCES `inventory`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `description` VARCHAR(60) NULL DEFAULT NULL,
  `parent_category` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = MyISAM
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`unit_of_measure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`unit_of_measure` (
  `unit_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `description` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`unit_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`item` (
  `item_id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(30) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  `detail` TEXT NULL DEFAULT NULL,
  `unit_of_measure` INT(11) NULL DEFAULT NULL,
  `cost` DECIMAL(6,2) NULL DEFAULT NULL,
  `sale_price` DECIMAL(6,2) NULL DEFAULT NULL,
  `rent_price` DECIMAL(6,2) NULL DEFAULT NULL,
  `existence` INT(11) NULL DEFAULT NULL,
  `default_picture` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC),
  INDEX `FK_ITEM_UNIT_OF_MEASURE_idx` (`unit_of_measure` ASC))
ENGINE = MyISAM
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`transaction_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`transaction_type` (
  `transaction_type_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`transaction_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`inventory_transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`inventory_transactions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `transaction_item` INT(11) NOT NULL,
  `transaction_type` INT(11) NOT NULL,
  `quantity` DECIMAL(5,3) NOT NULL,
  `transaction_date` DATETIME NOT NULL,
  `transaction_user` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_TRANSACTION_ITEM_idx` (`transaction_item` ASC),
  INDEX `FK_TRANSACTION_TYPE_idx` (`transaction_type` ASC),
  CONSTRAINT `FK_TRANSACTION_TYPE`
    FOREIGN KEY (`transaction_type`)
    REFERENCES `inventory`.`transaction_type` (`transaction_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`item_classification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`item_classification` (
  `classification_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  PRIMARY KEY (`classification_id`),
  INDEX `FK_CLASSIFIED_ITEM_idx` (`item_id` ASC),
  INDEX `FK_CLASSIFICATION_CATEGORY_idx` (`category_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`production`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`production` (
  `production_id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(20) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  PRIMARY KEY (`production_id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`item_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`item_location` (
  `location_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` INT(11) NOT NULL,
  `quantity` INT(11) NOT NULL,
  `section` VARCHAR(35) NULL DEFAULT NULL,
  `hall` VARCHAR(35) NULL DEFAULT NULL,
  `rack` VARCHAR(35) NULL DEFAULT NULL,
  `box` VARCHAR(35) NULL DEFAULT NULL,
  `production` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  INDEX `FK_LOCATION_ITEM_idx` (`item_id` ASC),
  INDEX `FP_LOCATION_PRODUCTION_idx` (`production` ASC),
  CONSTRAINT `FP_LOCATION_PRODUCTION`
    FOREIGN KEY (`production`)
    REFERENCES `inventory`.`production` (`production_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`item_picture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`item_picture` (
  `picture_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` INT(11) NOT NULL,
  `file_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`picture_id`),
  INDEX `FK_PICTURE_ITEM_idx` (`item_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`persistent_logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`persistent_logins` (
  `username` VARCHAR(64) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `inventory`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `inventory`.`status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `inventory`;

DELIMITER $$

DROP TRIGGER IF EXISTS inventory.item_location_AFTER_INSERT$$
USE `inventory`$$
CREATE TRIGGER `inventory`.`item_location_AFTER_INSERT` AFTER INSERT ON `inventory`.`item_location`
FOR EACH ROW
begin
    update inventory.item set existence = (select sum(quantity) from inventory.item_location where item_id = new.item_id) where item_id = new.item_id;
end;$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
