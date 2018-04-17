

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `37_1` DEFAULT CHARACTER SET utf8 ;
USE `37_1` ;

-- -----------------------------------------------------
-- Table `37_1`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `37_1`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NULL,
  `Surname` VARCHAR(45) NULL,
  `Login` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `37_1`.`area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `37_1`.`area` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_of_area` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `37_1`.`Category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `37_1`.`Category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name_of_category` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `37_1`.`news`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `37_1`.`news` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `area_id` INT NOT NULL,
  `Category_id` INT NOT NULL,
  PRIMARY KEY (`id`, `area_id`, `Category_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_news_area_idx` (`area_id` ASC),
  INDEX `fk_news_Category1_idx` (`Category_id` ASC),
  CONSTRAINT `fk_news_area`
    FOREIGN KEY (`area_id`)
    REFERENCES `37_1`.`area` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_news_Category1`
    FOREIGN KEY (`Category_id`)
    REFERENCES `37_1`.`Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `37_1`.`smart_bottle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `37_1`.`smart_bottle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Users_id` INT NOT NULL,
  PRIMARY KEY (`id`, `Users_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_smart_bottle_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_smart_bottle_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `37_1`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `37_1`.`Users_has_news`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `37_1`.`Users_has_news` (
  `Users_id` INT NOT NULL,
  `news_id` INT NOT NULL,
  PRIMARY KEY (`Users_id`, `news_id`),
  INDEX `fk_Users_has_news_news1_idx` (`news_id` ASC),
  INDEX `fk_Users_has_news_Users1_idx` (`Users_id` ASC),
  CONSTRAINT `fk_Users_has_news_Users1`
    FOREIGN KEY (`Users_id`)
    REFERENCES `37_1`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_news_news1`
    FOREIGN KEY (`news_id`)
    REFERENCES `37_1`.`news` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
