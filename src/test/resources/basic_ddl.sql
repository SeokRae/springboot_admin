-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema admin_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema admin_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `admin_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `admin_db` ;

-- -----------------------------------------------------
-- Table `admin_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`user` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `phone_number` VARCHAR(45) NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(45) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `admin_db`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`item` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`item` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  `content` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `admin_db`.`order_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`order_detail` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`order_detail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `order_at` DATETIME NULL,
  `user_id` BIGINT(20) NOT NULL,
  `item_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
