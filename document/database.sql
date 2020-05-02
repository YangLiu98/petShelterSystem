-- MySQL Script generated by MySQL Workbench
-- Sat May  2 19:34:54 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `user_id` INT(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(20) NOT NULL,
  `first_name` VARCHAR(10) NOT NULL,
  `last_name` VARCHAR(10) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `phone` VARCHAR(30) NOT NULL,
  `email` VARCHAR(50) NULL,
  `address` VARCHAR(1024) NULL,
  `is_admin` TINYINT NOT NULL DEFAULT 0,
  `icon` VARCHAR(100) NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`user_name` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`phone` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`pet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`pet` (
  `pet_id` INT(10) ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` ENUM("遗失宠物", "收容宠物", "代养宠物") NOT NULL,
  `species` VARCHAR(45) NOT NULL,
  `breed` VARCHAR(45) NULL,
  `color` VARCHAR(20) NOT NULL,
  `size` VARCHAR(45) NULL,
  `sex` ENUM("公", "母", "无性别") NOT NULL,
  `birthday` DATE NULL,
  `remark` VARCHAR(1024) NULL,
  `tag` VARCHAR(100) NULL,
  `main_pic` VARCHAR(100) NULL,
  `pic` VARCHAR(1024) NULL,
  `video` VARCHAR(1024) NULL,
  PRIMARY KEY (`pet_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`sponse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`sponse` (
  `sponse_id` INT(10) ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) ZEROFILL UNSIGNED NOT NULL,
  `pet_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `from_date` DATE NOT NULL,
  `to_date` DATE NOT NULL,
  `remark` VARCHAR(1024) NULL,
  `passed` TINYINT NOT NULL DEFAULT 0,
  `pass_date` DATE NULL,
  `status` ENUM("未开始", "进行中", "已结束") NOT NULL DEFAULT '未开始',
  PRIMARY KEY (`sponse_id`),
  CONSTRAINT `FK_sponse_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_sponse_pet_id`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`pet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`find`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`find` (
  `find_id` INT(10) ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `pet_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `from_date` DATE NOT NULL,
  `to_date` DATE NOT NULL,
  `reward` TINYINT NOT NULL DEFAULT 0,
  `reward_value` INT NULL,
  `reward_others` VARCHAR(200) NULL,
  `remark` VARCHAR(1024) NULL,
  `found` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`find_id`),
  CONSTRAINT `FK_find_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_find_pet_id`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`pet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`feed`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`feed` (
  `feed_id` INT(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `pet_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `from_date` DATE NOT NULL,
  `to_date` DATE NOT NULL,
  `remark` VARCHAR(1024) NULL,
  `passed` TINYINT NOT NULL DEFAULT 0,
  `pass_date` DATE NULL,
  `contracted` TINYINT NULL DEFAULT 0,
  `contract` VARCHAR(1024) NOT NULL,
  `status` ENUM("未开始", "进行中", "已结束") NOT NULL DEFAULT '未开始',
  PRIMARY KEY (`feed_id`),
  INDEX `FK_ueser_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_pet_id_idx` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `FK_feed_ueser_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_feed_pet_id`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`pet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`appointment` (
  `appointment_id` INT(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `pass` TINYINT(1) NOT NULL DEFAULT 0,
  `type` ENUM("参观", "看望", "寄送", "取回", "领回", "其他") NOT NULL,
  `user_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `pet_id` INT(10) UNSIGNED ZEROFILL NULL,
  `date` DATE NOT NULL,
  `remark` VARCHAR(1024) NULL,
  PRIMARY KEY (`appointment_id`),
  INDEX `FK_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_pet_id_idx` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `FK_appointment_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_appointment_pet_id`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`pet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`adopt`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`adopt` (
  `adopt_id` INT(10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `pet_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `date` DATE NOT NULL,
  `contract` VARCHAR(1024) NOT NULL,
  `remark` VARCHAR(1024) NULL,
  PRIMARY KEY (`adopt_id`),
  INDEX `FK_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_pet_id_idx` (`pet_id` ASC) VISIBLE,
  CONSTRAINT `FK_adopt_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_adopt_pet_id`
    FOREIGN KEY (`pet_id`)
    REFERENCES `mydb`.`pet` (`pet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`message` (
  `message_id` INT(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `sender_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `reciever_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  `date` DATETIME NOT NULL,
  `readed` TINYINT NOT NULL DEFAULT 0,
  `content` VARCHAR(1024) NOT NULL,
  PRIMARY KEY (`message_id`),
  INDEX `FK_sender_idx` (`sender_id` ASC) VISIBLE,
  INDEX `FK_receiver_idx` (`reciever_id` ASC) VISIBLE,
  CONSTRAINT `FK_message_sender`
    FOREIGN KEY (`sender_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_message_receiver`
    FOREIGN KEY (`reciever_id`)
    REFERENCES `mydb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
