-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Table `hospital_directory`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital_directory`.`person` ;

CREATE TABLE IF NOT EXISTS `hospital_directory`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `contact_number` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital_directory`.`doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital_directory`.`doctor` ;

CREATE TABLE IF NOT EXISTS `hospital_directory`.`doctor` (
  `id` INT NOT NULL,
  `specialization` VARCHAR(255) NOT NULL,
  `years_of_experience` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKop6cku1inyqn8son2ki4cgqdh`
    FOREIGN KEY (`id`)
    REFERENCES `hospital_directory`.`person` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital_directory`.`medication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital_directory`.`medication` ;

CREATE TABLE IF NOT EXISTS `hospital_directory`.`medication` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `company_id` INT NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital_directory`.`patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital_directory`.`patient` ;

CREATE TABLE IF NOT EXISTS `hospital_directory`.`patient` (
  `id` INT NOT NULL,
  `age` INT NULL DEFAULT NULL,
  `sex` VARCHAR(255) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKaxjru7sr936i3y7dy396vnon8`
    FOREIGN KEY (`id`)
    REFERENCES `hospital_directory`.`person` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital_directory`.`visit`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital_directory`.`visit` ;

CREATE TABLE IF NOT EXISTS `hospital_directory`.`visit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `doctor_id` INT NOT NULL,
  `patient_id` INT NOT NULL,
  `date` DATETIME(6) NOT NULL,
  `assurance` BIT(1) NULL DEFAULT NULL,
  `price` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKc63541y8ppkvsovm00gumv90t` (`doctor_id` ASC) VISIBLE,
  INDEX `FKrban5yeabnx30seqm69jw44e` (`patient_id` ASC) VISIBLE,
  CONSTRAINT `FKc63541y8ppkvsovm00gumv90t`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `hospital_directory`.`doctor` (`id`),
  CONSTRAINT `FKrban5yeabnx30seqm69jw44e`
    FOREIGN KEY (`patient_id`)
    REFERENCES `hospital_directory`.`patient` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital_directory`.`receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital_directory`.`receipt` ;

CREATE TABLE IF NOT EXISTS `hospital_directory`.`receipt` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `visit_id` INT NOT NULL,
  `created_at` DATETIME(6) NULL DEFAULT NULL,
  `expiration_date` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKnq0mtp1avv9xsgl7w70sf8l8a` (`visit_id` ASC) VISIBLE,
  CONSTRAINT `FKnq0mtp1avv9xsgl7w70sf8l8a`
    FOREIGN KEY (`visit_id`)
    REFERENCES `hospital_directory`.`visit` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital_directory`.`receipt_medication`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `hospital_directory`.`receipt_medication` ;

CREATE TABLE IF NOT EXISTS `hospital_directory`.`receipt_medication` (
  `medication_id` INT NOT NULL,
  `receipt_id` INT NOT NULL,
  PRIMARY KEY (`medication_id`, `receipt_id`),
  INDEX `FK3xk4rupcuxs8ff0tttn2raab5` (`receipt_id` ASC) VISIBLE,
  CONSTRAINT `FK3xk4rupcuxs8ff0tttn2raab5`
    FOREIGN KEY (`receipt_id`)
    REFERENCES `hospital_directory`.`receipt` (`id`),
  CONSTRAINT `FKhbgwwu5jf41wgpiy005l1kbor`
    FOREIGN KEY (`medication_id`)
    REFERENCES `hospital_directory`.`medication` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
