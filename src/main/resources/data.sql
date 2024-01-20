-- Insert specialties
INSERT INTO "SpecialtyEntity" ("specialtyName") VALUES
('Dermatology'),
('Ophthalmology'),
('Radiology'),
('Family Medicine'),
('Pediatrics');

-- Insert doctors with their specialties
INSERT INTO "DoctorEntity" ("name", "specialty_id") VALUES
('Antonio', 1),  -- Dermatology
('Maria', 2),    -- Ophthalmology
('Carlos', 3),   -- Radiology
('Gabriela', 4), -- Family Medicine
('Paulo', 5);    -- Pediatrics

-- Insert patients
INSERT INTO "PatientEntity" ("name", "age") VALUES
('Manuel', 53),
('Joana', 32),
('Ana', 25),
('Miguel', 40),
('Diogo', 33),
('Catarina', 33);