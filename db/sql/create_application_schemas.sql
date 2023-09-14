CREATE TABLE IF NOT EXISTS application (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  manager_id bigint NOT NULL,
  property_id bigint NOT NULL,
  applicant_id bigint NOT NULL,
  co_signer_id bigint DEFAULT NULL,
  status varchar(20) NOT NULL,
  note text DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY manager_id_idx (manager_id),
  KEY property_id_idx (property_id),
  KEY applicant_id_idx (applicant_id),
  KEY co_signer_id_idx (co_signer_id),
  INDEX status_idx (status)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS applicant (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  employer_id bigint NOT NULL,
  identification_id bigint NOT NULL,
  first_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  last_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  middle_name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  suffix varchar(4) COLLATE utf8mb4_general_ci DEFAULT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY employer_id_idx (employer_id),
  KEY identification_id_idx (identification_id),
  INDEX email_idx (email)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS primary_applicant (
  id bigint KEY NOT NULL,
  emergency_contact_id bigint,
  recent_eviction BOOL NOT NULL,
  eviction_explanation varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY emergency_contact_id_idx (emergency_contact_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS co_signer (
  id bigint KEY NOT NULL,
  address_id bigint NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY address_id_idx (address_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS residential_history (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  applicant_id bigint NOT NULL,
  address_id bigint NOT NULL,
  current_residence BOOL NOT NULL,
  resided_from_month varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  resided_from_year SMALLINT NOT NULL,
  resided_to_month varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  resided_to_year SMALLINT DEFAULT NULL,
  monthly_rent decimal(15, 2) NOT NULL,
  landlord_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  landlord_phone varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  landlord_email varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  leave_reason varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY applicant_id_idx (applicant_id),
  KEY address_id_idx (address_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS personal_reference (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  applicant_id bigint NOT NULL,
  name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  relationship varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY applicant_id_idx (applicant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS co_applicant (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  applicant_id bigint NOT NULL,
  first_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  last_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY applicant_id_idx (applicant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS occupant (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  applicant_id bigint NOT NULL,
  first_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  last_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  date_of_birth date NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY applicant_id_idx (applicant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS pet (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  applicant_id bigint NOT NULL,
  name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  breed varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  weight SMALLINT DEFAULT NULL,
  age TINYINT DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY applicant_id_idx (applicant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS identification (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  date_of_birth date NOT NULL,
  ssn varchar(11) COLLATE utf8mb4_general_ci NOT NULL,
  government_issued_id varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  issued_state_territory varchar(3) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS emergency_contact (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  name varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  relationship varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS vehicle (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  applicant_id bigint NOT NULL,
  make varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  model varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  year_made SMALLINT DEFAULT NULL,
  color varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL,
  licence_num varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY applicant_id_idx (applicant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS employer (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  address_id bigint NOT NULL,
  name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  phone varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  monthly_salary decimal(15, 2) NOT NULL,
  position_held varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  years_worked SMALLINT NOT NULL,
  supervisor_name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  supervisor_email varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY address_id_idx (address_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS additional_income_source (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  applicant_id bigint NOT NULL,
  name varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  monthly_income decimal(15, 2) NOT NULL,
  created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  KEY applicant_id_idx (applicant_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;