-- Create SPECIALTY table
CREATE TABLE SPECIALTY (
    id SERIAL PRIMARY KEY,
    specialty_name VARCHAR(255) NOT NULL
);

-- Insert specialties
INSERT INTO SPECIALTY (specialty_name) VALUES
('Dermatology'),
('Ophthalmology'),
('Radiology'),
('Family Medicine'),
('Pediatrics');

-- Create DOCTOR table
CREATE TABLE DOCTOR (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialty_id INTEGER REFERENCES SPECIALTY(id)
);

-- Insert doctors with their specialties
INSERT INTO DOCTOR (name, specialty_id) VALUES
('Antonio', 1),  -- Dermatology
('Maria', 2),    -- Ophthalmology
('Carlos', 3),   -- Radiology
('Gabriela', 4), -- Family Medicine
('Paulo', 5);    -- Pediatrics

-- Create PATIENT table
CREATE TABLE PATIENT (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL
);

-- Insert patients
INSERT INTO PATIENT (name, age) VALUES
('Manuel', 53),
('Joana', 32),
('Ana', 25),
('Diogo', 33),
('Catarina', 33),
('Miguel', 40);

-- Create PATHOLOGY table
CREATE TABLE PATHOLOGY (
    id SERIAL PRIMARY KEY,
    patient_id INTEGER REFERENCES PATIENT(id)
);

-- Insert pathologies
INSERT INTO PATHOLOGY (patient_id) VALUES
('1'),
('1'),
('1'),
('2'),
('3'),
('4'),
('5'),
('6');

-- Create SYMPTOM table
CREATE TABLE SYMPTOM (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

-- Insert symptoms
INSERT INTO SYMPTOM (description) VALUES
('Symptom 1 Description'),
('Symptom 2 Description'),
('Symptom 3 Description'),
('Symptom 4 Description'),
('Symptom 5 Description'),
('Symptom 6 Description'),
('Symptom 7 Description'),
('Symptom 8 Description'),
('Symptom 9 Description'),
('Symptom 10 Description'),
('Symptom 11 Description'),
('Symptom 12 Description'),
('Symptom 13 Description'),
('Symptom 14 Description'),
('Symptom 15 Description');

-- Create PATHOLOGY_SYMPTOMS table
CREATE TABLE PATHOLOGY_SYMPTOMS (
    pathology_entity_id INTEGER REFERENCES PATHOLOGY(id),
    symptoms_id INTEGER REFERENCES SYMPTOM(id),
    PRIMARY KEY (pathology_entity_id, symptoms_id)
);

-- Complete pathology relations
INSERT INTO PATHOLOGY_SYMPTOMS ('pathology_entity_id', 'symptoms_id') VALUES
(1,1),
(1,2),
(2,3),
(2,4),
(2,5),
(3,6),
(3,7),
(4,8),
(5,9),
(5,10),
(5,11),
(6,12),
(6,13),
(7,14),
(7,15);