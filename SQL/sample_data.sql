USE hospital_db;

-- Insert Employees (base info)
INSERT INTO Employee (employee_id, first_name, last_name, address, phone) VALUES
--(101, 'John', 'Doe', '123 Main St', '555-1234'),
(102, 'Jane', 'Smith', '456 Maple Ave', '555-5678'),
(103, 'Emily', 'White', '789 Oak Blvd', '555-9012'),
(104, 'Michael', 'Brown', '321 Cedar St', '555-3456');

-- Insert Doctors
INSERT INTO Doctor (employee_id, specialization, department_id) VALUES
(101, 'Cardiology',1),
(102, 'Neurology', 2);

-- Insert Nurses
INSERT INTO Nurse (employee_id, rotation, salary, department_id) VALUES
(103, 'Day', 60000.00, 1),
(104, 'Night', 62000.00, 2);

-- Insert Patients
INSERT INTO Patient (patient_id, first_name, last_name, address, phone, ward_id, bed_number, diagnosis, doctor_id) VALUES
(201, 'Alice', 'Green', '22 Pine Ln', '555-6789', 5, 101, 'Asthma', 101),
(202, 'Bob', 'Johnson', '88 Elm Rd', '555-7890', 3, 202, 'Migraine', 102);
