

-- -----------------------------------------------------
-- Table `hospital_directory`.`person`
-- -----------------------------------------------------
INSERT INTO `hospital_directory`.`person` (`id`, `first_name`, `last_name`, `contact_number`, `email`) VALUES
(1, 'Liam', 'Carter', '+48 111 111 111', 'carter@hospital.com'),
(2, 'Emma', 'Reynolds', '+48 111 111 112', 'reynolds@hospital.com'),
(3, 'Noah', 'Bennett', '+48 111 111 113', 'bennett@hospital.com'),
(4, 'Olivia', 'Hayes', '+48 111 111 114', 'hayes@hospital.com');


-- -----------------------------------------------------
-- Table `hospital_directory`.`doctor`
-- -----------------------------------------------------
INSERT INTO `hospital_directory`.`doctor` (`id`, `specialization`, `years_of_experience`) VALUES
(1, 'dermatology', 14),
(2, 'cardiology', 7);


-- -----------------------------------------------------
-- Table `hospital_directory`.`patient`
-- -----------------------------------------------------
INSERT INTO `hospital_directory`.`patient` (`id`, `age`, `sex`, `address`) VALUES
(3, 75, 'female', '124 Maplewood Lane, Springfield, TX 78901'),
(4, 65, 'male', '58 Oceanview Drive, Clearwater, FL 33767');


-- -----------------------------------------------------
-- Table `hospital_directory`.`medication`
-- -----------------------------------------------------
INSERT INTO `hospital_directory`.`medication` (`id`, `name`, `price`, `description`, `company_id`) VALUES
(1, 'Relivexa', 24.99, 'Pain relief, fast action.', 1),
(2, 'Cardiogen', 39.99, 'Supports healthy heart function.', 2),
(3, 'Neurovex', 47.50, 'Enhances mental clarity and focus.', 5),
(4, 'Glucorin', 4.99, 'Regulates blood sugar levels.', 6),
(5, 'Dermasol', 15.00, 'Treats skin irritations effectively.', 3);


-- -----------------------------------------------------
-- Table `hospital_directory`.`visit`
-- -----------------------------------------------------
INSERT INTO `hospital_directory`.`visit` (`id`, `date`, `assurance`, `price`, `doctor_id`, `patient_id`) VALUES
(1, '2024-12-04 20:00:00.000000', 0, 200.0, 1, 3),
(2, '2024-12-05 18:00:00.000000', 1, 100.0, 1, 3),
(3, '2024-12-20 17:30:00.000000', 0, 100.0, 2, 4),
(4, '2024-12-24 20:00:00.000000', 1, 150.0, 2, 4),
(5, '2025-01-01 19:00:00.000000', 0, 70.0, 1, 4);


-- -----------------------------------------------------
-- Table `hospital_directory`.`receipt`
-- -----------------------------------------------------
INSERT INTO `hospital_directory`.`receipt` (`id`, `created_at`, `expiration_date`, `visit_id`) VALUES
(1, '2024-12-04 20:00:00.000000', '2024-12-18 00:00:00.000000', 1),
(2, '2024-12-05 18:00:00.000000', '2024-12-19 00:00:00.000000', 2),
(3, '2024-12-20 17:30:00.000000', '2025-01-03 00:00:00.000000', 3),
(4, '2024-12-24 20:00:00.000000', '2025-01-07 00:00:00.000000', 4),
(5, '2025-01-01 19:00:00.000000', '2025-01-15 00:00:00.000000', 4);


-- -----------------------------------------------------
-- Table `hospital_directory`.`receipt_medication`
-- -----------------------------------------------------
INSERT INTO `hospital_directory`.`receipt_medication` (`medication_id`, `receipt_id`) VALUES
(1, 1),
(1, 2),
(1, 4),
(2, 5),
(3, 1),
(4, 5),
(4, 2),
(5, 3),
(5, 4);

