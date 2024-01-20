package com.challenge.challenge.services;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.dto.command.ConsultCommandDto;
import com.challenge.challenge.domain.dto.command.PathologyCommandDto;
import com.challenge.challenge.domain.dto.command.PatientCommandDto;
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
        verifyEntitiesExists(consult);

        PatientCommandDto patientCommand = consult.getPatient();
        PathologyCommandDto pathologyCommand = patientCommand.getPathology();

        DoctorEntity doctor = new DoctorEntity(null, consult.getDoctor());
        SpecialtyEntity specialty = new SpecialtyEntity(null, consult.getSpecialty(), doctor);
        PatientEntity patient = new PatientEntity(null, patientCommand.getName(), patientCommand.getAge(), null, null);
        ConsultEntity savedEntity = consultRepository.save(generateConsult(consult, doctor, specialty, patient));

        createPathology(patient, pathologyCommand);
        createSymptoms(pathologyCommand);

        return entityMapper.toConsult(savedEntity);
    }

    private void createSymptoms(PathologyCommandDto pathologyCommand) {
        List<SymptomEntity> symptoms = entityMapper.toSymptomEntityList(pathologyCommand.getSymptoms());
        symptomRepository.saveAll(symptoms);
    }

    private void createPathology(PatientEntity patient, PathologyCommandDto pathologyCommand) {
        PathologyEntity pathology = entityMapper.toPathologyEntity(pathologyCommand);
        pathology.setPatient(patient);
        pathologyRepository.save(pathology);
    }

    private ConsultEntity generateConsult(ConsultCommandDto consult, DoctorEntity doctor, SpecialtyEntity specialty, PatientEntity patient) {
        ConsultEntity consultEntity = entityMapper.toConsultEntity(consult);
        consultEntity.setDoctor(doctor);
        consultEntity.setSpecialty(specialty);
        consultEntity.setPatient(patient);
        return consultEntity;
    }

    private void verifyEntitiesExists(ConsultCommandDto consult) {
        PatientCommandDto patient = consult.getPatient();
        doctorRepository.findByName(consult.getDoctor())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        specialtyRepository.findBySpecialtyName(consult.getSpecialty())
                .orElseThrow(() -> new RuntimeException("Specialty not found"));
        patientRepository.findByNameAndAge(patient.getName(), patient.getAge())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
}
