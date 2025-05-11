CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

-- Employee base table
CREATE TABLE IF NOT EXISTS Employee (
    employee_id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    address VARCHAR(255),
    phone VARCHAR(20)
);

-- Doctor table
CREATE TABLE IF NOT EXISTS Doctor (
    employee_id INT PRIMARY KEY,
    specialization VARCHAR(100),
    department_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

-- Nurse table
CREATE TABLE IF NOT EXISTS Nurse (
    employee_id INT PRIMARY KEY,
    rotation VARCHAR(50),
    salary DOUBLE,
    department_id INT,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

-- Patient table
CREATE TABLE IF NOT EXISTS Patient (
    patient_id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    address VARCHAR(255),
    phone VARCHAR(20),
    ward_id INT,
    bed_number INT,
    diagnosis VARCHAR(255),
    doctor_id INT,
    FOREIGN KEY (doctor_id) REFERENCES Doctor(employee_id)
);

-- Department Table
CREATE TABLE Department (
    dept_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    building VARCHAR(100),
    director_id INT NOT NULL,
    FOREIGN KEY (director_id)
        REFERENCES Doctor(employee_id)
        ON DELETE RESTRICT
);

-- Ward Table
CREATE TABLE Ward (
    ward_id INT,                   -- local to each department
    dept_id INT,
    bed_count INT NOT NULL,
    supervisor_id INT NOT NULL,
    PRIMARY KEY (dept_id, ward_id),
    FOREIGN KEY (dept_id)
        REFERENCES Department(dept_id)
        ON DELETE CASCADE,
    FOREIGN KEY (supervisor_id)
        REFERENCES Nurse(employee_id)
        ON DELETE RESTRICT
);
