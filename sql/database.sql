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
CREATE SCHEMA IF NOT EXISTS `argos` DEFAULT CHARACTER SET utf8 ;
USE `argos` ;

-- -----------------------------------------------------
-- Table `argos`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  `authority_id` INT(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`authority_id`),
  UNIQUE INDEX `IX_AUTH_USERNAME` (`username` ASC, `authority` ASC),
  INDEX `FK_AUTHORITIES_USERS_idx` (`username` ASC),
  CONSTRAINT `FK_AUTHORITIES_USERS`
    FOREIGN KEY (`username`)
    REFERENCES `argos`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`category` (
  `category_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(35) NOT NULL,
  `description` VARCHAR(60) NULL DEFAULT NULL,
  `parent_category` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  INDEX `FK_CATEGORY_PARENT_idx` (`parent_category` ASC),
  CONSTRAINT `FK_CATEGORY_PARENT`
    FOREIGN KEY (`parent_category`)
    REFERENCES `argos`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`unit_of_measure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`unit_of_measure` (
  `unit_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `description` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`unit_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`item` (
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
  INDEX `FK_ITEM_UNIT_OF_MEASURE_idx` (`unit_of_measure` ASC),
  CONSTRAINT `FK_ITEM_UNIT_OF_MEASURE`
    FOREIGN KEY (`unit_of_measure`)
    REFERENCES `argos`.`unit_of_measure` (`unit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 19
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`transaction_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`transaction_type` (
  `transaction_type_id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`transaction_type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`inventory_transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`inventory_transactions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `transaction_item` INT(11) NOT NULL,
  `transaction_type` INT(11) NOT NULL,
  `quantity` DECIMAL(5,3) NOT NULL,
  `transaction_date` DATETIME NOT NULL,
  `transaction_user` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_TRANSACTION_ITEM_idx` (`transaction_item` ASC),
  INDEX `FK_TRANSACTION_TYPE_idx` (`transaction_type` ASC),
  CONSTRAINT `FK_TRANSACTION_ITEM`
    FOREIGN KEY (`transaction_item`)
    REFERENCES `argos`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TRANSACTION_TYPE`
    FOREIGN KEY (`transaction_type`)
    REFERENCES `argos`.`transaction_type` (`transaction_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`item_classification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`item_classification` (
  `classification_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` INT(11) NOT NULL,
  `category_id` INT(11) NOT NULL,
  PRIMARY KEY (`classification_id`),
  INDEX `FK_CLASSIFIED_ITEM_idx` (`item_id` ASC),
  INDEX `FK_CLASSIFICATION_CATEGORY_idx` (`category_id` ASC),
  CONSTRAINT `FK_CLASSIFICATION_CATEGORY`
    FOREIGN KEY (`category_id`)
    REFERENCES `argos`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CLASSIFIED_ITEM`
    FOREIGN KEY (`item_id`)
    REFERENCES `argos`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`production`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`production` (
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
-- Table `argos`.`item_location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`item_location` (
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
  CONSTRAINT `FK_LOCATION_ITEM`
    FOREIGN KEY (`item_id`)
    REFERENCES `argos`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FP_LOCATION_PRODUCTION`
    FOREIGN KEY (`production`)
    REFERENCES `argos`.`production` (`production_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`item_picture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`item_picture` (
  `picture_id` INT(11) NOT NULL AUTO_INCREMENT,
  `item_id` INT(11) NOT NULL,
  `file_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`picture_id`),
  INDEX `FK_PICTURE_ITEM_idx` (`item_id` ASC),
  CONSTRAINT `FK_PICTURE_ITEM`
    FOREIGN KEY (`item_id`)
    REFERENCES `argos`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`persistent_logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`persistent_logins` (
  `username` VARCHAR(64) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `argos`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `argos`.`status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `argos`;

DELIMITER $$
USE `argos`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `argos`.`item_location_AFTER_INSERT`
AFTER INSERT ON `argos`.`item_location`
FOR EACH ROW
begin
    update argos.item set existence = (select sum(quantity) from argos.item_location where item_id = new.item_id) where item_id = new.item_id;
end$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
