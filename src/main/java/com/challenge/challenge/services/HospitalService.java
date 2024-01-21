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
import com.challenge.challenge.domain.orm.specification.PatientSpecification;
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;
import com.challenge.challenge.mappers.HospitalEntityMapper;
import com.challenge.challenge.services.interfaces.IHospitalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class HospitalService implements IHospitalService {

    private final HospitalEntityMapper entityMapper;
    private final ConsultRepository consultRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final PatientRepository patientRepository;
    private final PathologyRepository pathologyRepository;
    private final SymptomRepository symptomRepository;

    @Override
    public Consult createConsult(ConsultCommandDto consult) {
        log.debug("[HospitalService] Creating consult object...");
        DoctorEntity doctor = doctorRepository.findByName(consult.getDoctor())
                .orElseThrow(() -> {
                    String message = String.format("[HospitalService] Doctor %s not found!", consult.getDoctor());
                    log.error(message);
                    return new RuntimeException(message);
                });
        SpecialtyEntity specialty = specialtyRepository.findBySpecialtyName(consult.getSpecialty())
                .orElseThrow(() -> {
                    String message = String.format("[HospitalService] Specialty %s not found!", consult.getSpecialty());
                    log.error(message);
                    return new RuntimeException(message);
                });

        PatientEntity patient = createPatient(consult.getPatient());

        log.info("[HospitalService] Saving consult to database...");
        ConsultEntity savedEntity = consultRepository.save(generateConsult(consult, doctor, specialty, patient));

        log.info("[HospitalService] Successfully saved consult to database!");
        return entityMapper.toConsult(savedEntity);
    }

    @Override
    public Response getPatientConsultsAndSymptoms(Long patientId) {
        log.info("[HospitalService] Fetching for consults and symptoms of patient with id {}...", patientId);
        List<ConsultEntity> patientConsults = consultRepository.findAllByPatientId(patientId)
                .orElseThrow(() -> {
                    String message = String.format("[HospitalService] Patient with id %s has been to no consults!", patientId);
                    log.error(message);
                    return new RuntimeException(message);
                });

        Response response = new Response();
        response.setConsults(entityMapper.toConsultResponseList(patientConsults));

        List<SymptomEntity> patientSymptoms = new ArrayList<>();
        addSymptomsFromPathologies(patientId, patientSymptoms);

        response.setSymptoms(entityMapper.toSymptomList(patientSymptoms));

        log.info("[HospitalService] Successfully obtained patient data!");
        return response;
    }

    @Override
    public List<TopSpecialtyResponse> getTopSpecialties() {
        log.info("[HospitalService] Getting hospital's top specialties...");
        return consultRepository.findSpecialtiesWithMoreThanConsults(2L)
                .orElseGet(Collections::emptyList);
    }

    @Override
    public Page<Patient> getAllPatients(PatientFilters filters, Pageable pageable) {
        log.info("[HospitalService] Getting all patients...");
        Specification<PatientEntity> spec = PatientSpecification.filterBy(filters);
        Page<PatientEntity> patientEntities = patientRepository.findAll(spec, pageable)
                .orElseGet(Page::empty);

        log.info("[HospitalService] Successfully obtained all patients!");
        return patientEntities.map(entityMapper::toPatient);
    }

    private void addSymptomsFromPathologies(Long patientId, List<SymptomEntity> patientSymptoms) {
        List<PathologyEntity> patientPathologies = pathologyRepository.findAllByPatientId(patientId)
                .orElseThrow(() -> {
                    String message = "[HospitalService] Patient information may be corrupted, no pathologies found";
                    log.error(message);
                    return new RuntimeException(message);
                });
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
