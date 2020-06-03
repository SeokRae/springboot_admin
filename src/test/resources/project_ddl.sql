-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema admin_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `admin_db` ;

-- -----------------------------------------------------
-- Schema admin_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `admin_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `admin_db` ;

-- -----------------------------------------------------
-- Table `admin_db`.`admin_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`admin_user` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`admin_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'index',
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `role` VARCHAR(50) NOT NULL,
  `last_login_at` DATETIME NULL DEFAULT NULL,
  `login_fail_count` INT(11) NULL DEFAULT NULL,
  `password_updated_at` datetime DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `admin_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`category` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`category` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `admin_db`.`partner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`partner` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`partner` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `call_center` VARCHAR(13) NULL DEFAULT NULL,
  `partner_number` VARCHAR(13) NULL DEFAULT NULL,
  `business_number` VARCHAR(16) NULL DEFAULT NULL,
  `ceo_name` VARCHAR(20) NULL DEFAULT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `category_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------user
-- Table `admin_db`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`item` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`item` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NULL DEFAULT NULL,
  `name` VARCHAR(45) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `price` DECIMAL(12,4) NOT NULL,
  `brand_name` VARCHAR(45) NULL DEFAULT NULL,
  `registered_at` VARCHAR(45) NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(20) NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `partner_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `admin_db`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`user` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'index',
  `account` VARCHAR(12) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `phone_number` VARCHAR(13) NOT NULL,
  `registered_at` DATETIME NULL DEFAULT NULL,
  `unregistered_at` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `admin_db`.`order_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `admin_db`.`order_group` ;

CREATE TABLE IF NOT EXISTS `admin_db`.`order_group` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(50) NOT NULL,
  `order_type` VARCHAR(50) NOT NULL,
  `rev_address` VARCHAR(100) NULL DEFAULT NULL,
  `rev_name` VARCHAR(50) NULL DEFAULT NULL,
  `payment_type` VARCHAR(50) NULL DEFAULT NULL,
  `total_price` DECIMAL(12,4) NULL DEFAULT NULL,
  `total_quantity` INT(11) NULL DEFAULT NULL,
  `order_at` DATETIME NULL DEFAULT NULL,
  `arrival_date` DATETIME NULL DEFAULT NULL,
  `created_at` DATETIME NULL DEFAULT NULL,
  `created_by` VARCHAR(20) NULL DEFAULT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `user_id` BIGINT(20) NOT NULL,
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
  `status` VARCHAR(50) NOT NULL,
  `arrival_date` DATETIME NOT NULL,
  `quantity` INT(11) NULL DEFAULT NULL,
  `total_price` DECIMAL(12,4) NULL DEFAULT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) NULL DEFAULT NULL,
  `order_group_id` BIGINT(20) NOT NULL,
  `item_id` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
