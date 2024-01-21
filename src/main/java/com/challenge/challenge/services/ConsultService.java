package com.challenge.challenge.services;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.Patient;
import com.challenge.challenge.domain.dto.request.PatientFilters;
import com.challenge.challenge.domain.dto.request.command.ConsultCommandDto;
import com.challenge.challenge.domain.dto.request.command.PathologyCommandDto;
import com.challenge.challenge.domain.dto.request.command.PatientCommandDto;
import com.challenge.challenge.domain.dto.request.command.SymptomCommandDto;
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
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;
import com.challenge.challenge.mappers.HospitalEntityMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ConsultService implements IHospitalService {

    private final HospitalEntityMapper entityMapper;
    private final ConsultRepository consultRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PatientRepository patientRepository;
    private final PathologyRepository pathologyRepository;
    private final SymptomRepository symptomRepository;

    @PersistenceContext
    private final EntityManager entityManager;

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

    @Override
    public Response getPatientConsultsAndSymptoms(Long patientId) {
        List<ConsultEntity> patientConsults = consultRepository.findAllByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Patient has been to no consults!"));

        Response response = new Response();
        response.setConsults(entityMapper.toConsultResponseList(patientConsults));

        List<SymptomEntity> patientSymptoms = new ArrayList<>();
        addSymptomsFromPathologies(patientId, patientSymptoms);

        response.setSymptoms(entityMapper.toSymptomList(patientSymptoms));

        return response;
    }

    @Override
    public List<TopSpecialtyResponse> getTopSpecialties() {
        return consultRepository.findSpecialtiesWithMoreThanConsults(2L)
                .orElseGet(Collections::emptyList);
    }

    @Override
    public List<Patient> getAllPatients(PatientFilters filters) {

        return null;
    }

    private void addSymptomsFromPathologies(Long patientId, List<SymptomEntity> patientSymptoms) {
        List<PathologyEntity> patientPathologies = pathologyRepository.findAllByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Patient information may be corrupted"));
        patientPathologies.stream()
                .map(PathologyEntity::getSymptoms)
                .forEach(patientSymptoms::addAll);
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
