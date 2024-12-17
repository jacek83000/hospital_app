USE `hospital_directory`;

DROP TABLE IF EXISTS `hospital_directory`.`authorities`;
DROP TABLE IF EXISTS `hospital_directory`.`users`;

-- -----------------------------------------------------
-- Table `hospital_directory`.`users`
-- -----------------------------------------------------
CREATE TABLE `hospital_directory`.`users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `hospital_directory`.`users` VALUES
('doctor','{bcrypt}$2a$10$Z8hCTojIwc0k2Wac79xuEOsU5laK8XdtrKw0Ik9YJIefpb2f5SdJq',1),
('receptionist','{bcrypt}$2a$10$0qndOQYDUJ38azKfOstY4uQ7WyvC6hEGxZDCgdTfGlFsc/Kp83dcW',1),
('admin','{bcrypt}$2a$10$Hb8TY3gBoKR1XpTRGPNG1uw6vot9QAf.sc4M9u82ZIJeL7W9/h8pO',1);


-- -----------------------------------------------------
-- Table `hospital_directory`.`users`
-- -----------------------------------------------------
CREATE TABLE `hospital_directory`.`authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `hospital_directory`.`authorities` VALUES
('receptionist','ROLE_RECEPTIONIST'),
('doctor','ROLE_DOCTOR'),
('admin','ROLE_ADMIN');

