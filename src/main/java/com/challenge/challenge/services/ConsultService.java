package com.challenge.challenge.services;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.domain.dto.command.PathologyCommandDto;
import com.challenge.challenge.domain.dto.command.PatientCommandDto;
import com.challenge.challenge.domain.dto.command.SymptomCommandDto;
import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.orm.models.DoctorEntity;
import com.challenge.challenge.domain.orm.models.PathologyEntity;
import com.challenge.challenge.domain.orm.models.PatientEntity;
import com.challenge.challenge.domain.orm.models.SpecialtyEntity;
import com.challenge.challenge.domain.orm.models.SymptomEntity;
import com.challenge.challenge.domain.orm.repository.ConsultRepository;
import com.challenge.challenge.domain.orm.repository.DoctorRepository;
import com.challenge.challenge.domain.orm.repository.PathologyRepository;
import com.challenge.challenge.domain.orm.repository.PatientRepository;
import com.challenge.challenge.domain.orm.repository.SpecialtyRepository;
import com.challenge.challenge.domain.orm.repository.SymptomRepository;
import com.challenge.challenge.mappers.HospitalEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsultService implements IHospitalService {

    HospitalEntityMapper entityMapper;
    ConsultRepository consultRepository;
    DoctorRepository doctorRepository;
    SpecialtyRepository specialtyRepository;
    PatientRepository patientRepository;
    PathologyRepository pathologyRepository;
    SymptomRepository symptomRepository;


    @Autowired
    public ConsultService(HospitalEntityMapper entityMapper,
                          ConsultRepository consultRepository,
                          DoctorRepository doctorRepository,
                          SpecialtyRepository specialtyRepository,
                          PatientRepository patientRepository,
                          PathologyRepository pathologyRepository,
                          SymptomRepository symptomRepository) {
        this.entityMapper = entityMapper;
        this.consultRepository = consultRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.patientRepository = patientRepository;
        this.pathologyRepository = pathologyRepository;
        this.symptomRepository = symptomRepository;
    }

    @Override
    public Consult createConsult(ConsultCommandDto consult) {

        DoctorEntity doctor = doctorRepository.findByName(consult.getDoctor())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        SpecialtyEntity specialty = specialtyRepository.findBySpecialtyName(consult.getSpecialty())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));

        PatientEntity patient = createPatient(consult.getPatient());

        ConsultEntity savedEntity = consultRepository.save(generateConsult(consult, doctor, specialty, patient));


        return entityMapper.toConsult(savedEntity);
    }


    private PatientEntity createPatient(PatientCommandDto patient) {
        PatientEntity patientEntity = patientRepository.findByNameAndAge(patient.getName(), patient.getAge())
                .orElseGet(() -> patientRepository.save(new PatientEntity(null, patient.getName(), patient.getAge())));

        createPathology(patient.getPathology(), patientEntity);

        return patientEntity;
    }

    private void createPathology(PathologyCommandDto pathologyCommandDto, PatientEntity entity) {
        PathologyEntity pathology = new PathologyEntity();

        List<SymptomEntity> symptoms = pathologyCommandDto.getSymptoms().stream()
                .map(this::createSymptom)
                .collect(Collectors.toList());

        pathology.setPatient(entity);
        pathology.setSymptoms(symptoms);

        pathologyRepository.save(pathology);
    }

    private SymptomEntity createSymptom(SymptomCommandDto symptomCommandDto) {
        SymptomEntity symptom = new SymptomEntity();
        symptom.setDescription(symptomCommandDto.getDescription());
        return symptomRepository.save(symptom);
    }

    private ConsultEntity generateConsult(ConsultCommandDto consult, DoctorEntity doctor, SpecialtyEntity specialty, PatientEntity patient) {
        ConsultEntity consultEntity = entityMapper.toConsultEntity(consult);
        consultEntity.setDoctor(doctor);
        consultEntity.setSpecialty(specialty);
        consultEntity.setPatient(patient);
        return consultEntity;
    }
}
