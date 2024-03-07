-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema wisebite
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wisebite
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wisebite` DEFAULT CHARACTER SET utf8 ;
USE `wisebite` ;

-- -----------------------------------------------------
-- Table `wisebite`.`Recipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Recipe` (
  `name` VARCHAR(45) NOT NULL,
  `description` TEXT NOT NULL,
  `calorie_amount` INT NOT NULL,
  `instructions` TEXT NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`User` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `infix` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `usertype` ENUM('ADMIN', 'CLIENT', 'COACH', 'DIETITIAN') NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Dietitian`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Dietitian` (
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  INDEX `verzinzelf11_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `verzinzelf11`
    FOREIGN KEY (`username`)
    REFERENCES `wisebite`.`User` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Coach`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Coach` (
  `username` VARCHAR(45) NOT NULL,
  `dietitian` VARCHAR(45) NULL,
  PRIMARY KEY (`username`),
  INDEX `verzinzelf12_idx` (`username` ASC) VISIBLE,
  INDEX `verzinzelf16_idx` (`dietitian` ASC) VISIBLE,
  CONSTRAINT `verzinzelf12`
    FOREIGN KEY (`username`)
    REFERENCES `wisebite`.`User` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf16`
    FOREIGN KEY (`dietitian`)
    REFERENCES `wisebite`.`Dietitian` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Client` (
  `username` VARCHAR(45) NOT NULL,
  `weight` DOUBLE NOT NULL,
  `height` DOUBLE NOT NULL,
  `start_date` DATE NOT NULL,
  `dietitian` VARCHAR(45) NULL,
  `coach` VARCHAR(45) NULL,
  PRIMARY KEY (`username`),
  INDEX `verzinzelf15_idx` (`dietitian` ASC) VISIBLE,
  INDEX `verzinzelf17_idx` (`coach` ASC) VISIBLE,
  CONSTRAINT `verzinzelf2`
    FOREIGN KEY (`username`)
    REFERENCES `wisebite`.`User` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf15`
    FOREIGN KEY (`dietitian`)
    REFERENCES `wisebite`.`Dietitian` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `verzinzelf17`
    FOREIGN KEY (`coach`)
    REFERENCES `wisebite`.`Coach` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Plan` (
  `weight_loss_goal` INT NOT NULL,
  `plan_goal` TINYINT(1) NOT NULL,
  `duration` INT NOT NULL,
  `client` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`client`),
  CONSTRAINT `verzinzelf1`
    FOREIGN KEY (`client`)
    REFERENCES `wisebite`.`Client` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Workout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Workout` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `duration` INT NOT NULL,
  `burned_calories` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Diet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Diet` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `calorie_amount` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`DailyTask`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`DailyTask` (
  `date` DATE NOT NULL,
  `daily_goal` TINYINT(100) NOT NULL,
  `client` VARCHAR(45) NOT NULL,
  `workout_id` INT NULL,
  `diet_id` INT NULL,
  INDEX `verzinzelf3_idx` (`client` ASC) VISIBLE,
  INDEX `verzinzelf7_idx` (`workout_id` ASC) VISIBLE,
  INDEX `verzinzelf5_idx` (`diet_id` ASC) VISIBLE,
  PRIMARY KEY (`date`, `client`),
  CONSTRAINT `verzinzelf3`
    FOREIGN KEY (`client`)
    REFERENCES `wisebite`.`Plan` (`client`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf7`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wisebite`.`Workout` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `verzinzelf5`
    FOREIGN KEY (`diet_id`)
    REFERENCES `wisebite`.`Diet` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Meal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Meal` (
  `recipe` VARCHAR(45) NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`recipe`, `id`),
  INDEX `verzinzelf6_idx` (`recipe` ASC) VISIBLE,
  INDEX `verzinzelf4_idx` (`id` ASC) VISIBLE,
  CONSTRAINT `verzinzelf6`
    FOREIGN KEY (`recipe`)
    REFERENCES `wisebite`.`Recipe` (`name`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf4`
    FOREIGN KEY (`id`)
    REFERENCES `wisebite`.`Diet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Exercise`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Exercise` (
  `name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `reps` INT NULL,
  `weight_amount` INT NULL,
  `duration` INT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Appointment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Appointment` (
  `date_time` DATETIME NOT NULL,
  `subject` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `attendee` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`date_time`, `username`),
  INDEX `verzinzelf13_idx` (`username` ASC) VISIBLE,
  INDEX `verzinzelf14_idx` (`attendee` ASC) VISIBLE,
  CONSTRAINT `verzinzelf13`
    FOREIGN KEY (`username`)
    REFERENCES `wisebite`.`User` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf14`
    FOREIGN KEY (`attendee`)
    REFERENCES `wisebite`.`User` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`ExerciseWorkout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`ExerciseWorkout` (
  `exercise` VARCHAR(45) NOT NULL,
  `workout` INT NOT NULL,
  PRIMARY KEY (`exercise`, `workout`),
  INDEX `verzinzelf9_idx` (`workout` ASC) VISIBLE,
  INDEX `verzinzelf8_idx` (`exercise` ASC) VISIBLE,
  CONSTRAINT `verzinzelf8`
    FOREIGN KEY (`exercise`)
    REFERENCES `wisebite`.`Exercise` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `verzinzelf9`
    FOREIGN KEY (`workout`)
    REFERENCES `wisebite`.`Workout` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wisebite`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wisebite`.`Admin` (
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `verzinzelf10`
    FOREIGN KEY (`username`)
    REFERENCES `wisebite`.`User` (`username`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
