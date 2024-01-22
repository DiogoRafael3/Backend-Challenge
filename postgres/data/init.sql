-- Insert specialties
INSERT INTO SPECIALTY ('specialty_name') VALUES
('Dermatology'),
('Ophthalmology'),
('Radiology'),
('Family Medicine'),
('Pediatrics');

-- Insert doctors with their specialties
INSERT INTO DOCTOR ('name', 'specialty_id') VALUES
('Antonio', 1),  -- Dermatology
('Maria', 2),    -- Ophthalmology
('Carlos', 3),   -- Radiology
('Gabriela', 4), -- Family Medicine
('Paulo', 5);    -- Pediatrics

-- Insert patients
INSERT INTO PATIENT ('name', 'age') VALUES
('Manuel', 53)
('Joana', 32)
('Ana', 25)
('Diogo', 33)
('Catarina', 33)
('Miguel', 40)

-- Insert pathologies
INSERT INTO PATHOLOGY ('patient_id') VALUES
('1'),
('1'),
('1'),
('2'),
('3'),
('4'),
('5'),
('6')

-- Insert symptoms
INSERT INTO SYMPTOM ('description') VALUES
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
('Symptom 13 Description')

-- Complete pathology relations
INSERT INTO PATHOLOGY_SYMPTOMS ('pathology_entity_id', 'symptoms_id') VALUES
('1', '1'),
('1', '2'),
('2','3'),
('2','4'),
('3','5'),
('3','6'),
('4','7'),
('4','8'),
('5','9'),
('5','10'),
('6','11'),
('6','12'),
('7','13'),
('7','14')