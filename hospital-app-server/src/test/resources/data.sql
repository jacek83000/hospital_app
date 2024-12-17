

-- -----------------------------------------------------
-- Table `public`.`person`
-- -----------------------------------------------------
INSERT INTO `public`.`person` (`id`, `first_name`, `last_name`, `contact_number`, `email`) VALUES
 (10001, 'firstName1', 'lastName1', '+48 111 111 111', 'doctor1@hospital.com'),
 (10002, 'firstName2', 'lastName2', '+48 111 111 112', 'doctor2@hospital.com'),
 (10003, 'firstName3', 'lastName3', '+48 111 111 113', 'patient1@hospital.com'),
 (10004, 'firstName4', 'lastName4', '+48 111 111 114', 'patient2@hospital.com');


-- -----------------------------------------------------
-- Table `public`.`doctor`
-- -----------------------------------------------------
INSERT INTO `public`.`doctor` (`id`, `specialization`, `years_of_experience`) VALUES
 (10001, 'specialization1', 19),
 (10002, 'specialization2', 24);


-- -----------------------------------------------------
-- Table `public`.`patient`
-- -----------------------------------------------------
INSERT INTO `public`.`patient` (`id`, `age`, `sex`, `address`) VALUES
 (10003, 30, 'male', 'address1'),
 (10004, 35, 'female', 'address2');


-- -----------------------------------------------------
-- Table `public`.`medication`
-- -----------------------------------------------------
INSERT INTO `public`.`medication` (`id`, `name`, `price`, `description`, `company_id`) VALUES
 (10001, 'name1', 39.99, 'description1', 1),
 (10002, 'name2', 45.99, NULL, 2),
 (10003, 'name3', 50.00, 'description3', 3);


-- -----------------------------------------------------
-- Table `public`.`visit`
-- -----------------------------------------------------
INSERT INTO `public`.`visit` (`id`, `date`, `assurance`, `price`, `doctor_id`, `patient_id`) VALUES
 (10001, '2024-12-01 20:00:00.000000', 0, 150.0, 10001, 10003),
 (10002, '2024-12-01 20:00:00.000000', 1, 100.0, 10002, 10004);


-- -----------------------------------------------------
-- Table `public`.`receipt`
-- -----------------------------------------------------
INSERT INTO `public`.`receipt` (`id`, `created_at`, `expiration_date`, `visit_id`) VALUES
 (10001, '2024-12-01 20:00:00.000000', '2024-12-15 00:00:00.000000', 10001),
 (10002, '2024-12-01 20:00:00.000000', '2024-12-15 00:00:00.000000', 10002);


-- -----------------------------------------------------
-- Table `public`.`receipt_medication`
-- -----------------------------------------------------
INSERT INTO `public`.`receipt_medication` (`medication_id`, `receipt_id`) VALUES
 (10001, 10001),
 (10002, 10002),
 (10002, 10001);


-- SECURITY----------------------------------------------------------------------------------------------------
USE `public`;

DROP TABLE IF EXISTS `public`.`authorities`;
DROP TABLE IF EXISTS `public`.`users`;

-- -----------------------------------------------------
-- Table `public`.`users`
-- -----------------------------------------------------
CREATE TABLE `public`.`users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `public`.`users` VALUES
('doctor','{bcrypt}$2a$10$Z8hCTojIwc0k2Wac79xuEOsU5laK8XdtrKw0Ik9YJIefpb2f5SdJq',1),
('receptionist','{bcrypt}$2a$10$0qndOQYDUJ38azKfOstY4uQ7WyvC6hEGxZDCgdTfGlFsc/Kp83dcW',1),
('admin','{bcrypt}$2a$10$Hb8TY3gBoKR1XpTRGPNG1uw6vot9QAf.sc4M9u82ZIJeL7W9/h8pO',1);


-- -----------------------------------------------------
-- Table `public`.`users`
-- -----------------------------------------------------
CREATE TABLE `public`.`authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `public`.`authorities`  VALUES
('receptionist','ROLE_RECEPTIONIST'),
('doctor','ROLE_DOCTOR'),
('admin','ROLE_ADMIN');

