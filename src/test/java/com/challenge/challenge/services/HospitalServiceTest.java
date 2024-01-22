package com.challenge.challenge.services;

import com.challenge.challenge.domain.Doctor;
import com.challenge.challenge.domain.Patient;
import com.challenge.challenge.domain.Specialty;
import com.challenge.challenge.domain.Symptom;
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
import com.challenge.challenge.domain.response.ConsultResponse;
import com.challenge.challenge.domain.response.Response;
import com.challenge.challenge.domain.response.TopSpecialtyResponse;
import com.challenge.challenge.mappers.HospitalEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class HospitalServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private SpecialtyRepository specialtyRepository;

    @Mock
    private ConsultRepository consultRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PathologyRepository pathologyRepository;

    @Mock
    private SymptomRepository symptomRepository;

    @Mock
    private HospitalEntityMapper entityMapper;

    @InjectMocks
    private HospitalService hospitalService;

    private static final String DUMMY_PATIENT = "DummyPatient";
    private static final String DUMMY_DOCTOR = "DummyDoctor";
    private static final String DUMMY_SPECIALTY = "DummySpecialty";
    private static final String DUMMY_SYMPTOM = "DummySymptom";

    @Test
    void testCreateConsult_success() {
        SymptomCommandDto symptomCommand = createDummySymptomCommand();
        PathologyCommandDto pathologyCommand = createDummyPathologyCommand(symptomCommand);
        PatientCommandDto patientCommand = createDummyPatientCommand(pathologyCommand);
        ConsultCommandDto consultCommand = createDummyConsultCommand(patientCommand);

        PatientEntity patientEntity = createDummyPatientEntity();
        SpecialtyEntity specialtyEntity = createDummySpecialtyEntity();
        DoctorEntity doctorEntity = createDummyDoctorEntity(specialtyEntity);
        ConsultEntity consultEntity = createDummyConsultEntity(doctorEntity, specialtyEntity, patientEntity);

        Mockito.when(doctorRepository.findByName(DUMMY_DOCTOR)).thenReturn(Optional.of(doctorEntity));
        Mockito.when(specialtyRepository.findBySpecialtyName(DUMMY_SPECIALTY)).thenReturn(Optional.of(specialtyEntity));
        Mockito.when(patientRepository.save(Mockito.any())).thenReturn(patientEntity);
        Mockito.when(consultRepository.save(Mockito.any())).thenReturn(consultEntity);
        Mockito.when(entityMapper.toConsultEntity(Mockito.any())).thenReturn(consultEntity);

        hospitalService.createConsult(consultCommand);

        Mockito.verify(doctorRepository, Mockito.times(1)).findByName(DUMMY_DOCTOR);
        Mockito.verify(specialtyRepository, Mockito.times(1)).findBySpecialtyName(DUMMY_SPECIALTY);
        Mockito.verify(patientRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(consultRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(entityMapper, Mockito.times(1)).toConsult(Mockito.any());
    }

    @Test
    void testCreateConsult_doctorNotFound() {
        // Doctor name is not necessary as database response will not be mocked
        ConsultCommandDto consultCommand = new ConsultCommandDto();

        assertThrows(RuntimeException.class, () -> hospitalService.createConsult(consultCommand));
        Mockito.verify(specialtyRepository, Mockito.never()).findBySpecialtyName(Mockito.any());
    }

    @Test
    void testCreateConsult_specialtyNotFound() {
        ConsultCommandDto consultCommand = new ConsultCommandDto(DUMMY_DOCTOR, null, null);

        Mockito.when(doctorRepository.findByName(DUMMY_DOCTOR)).thenReturn(Optional.of(createDummyDoctorEntity(new SpecialtyEntity())));
        assertThrows(RuntimeException.class, () -> hospitalService.createConsult(consultCommand));
        Mockito.verify(patientRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void testGetPatientConsultsAndSymptoms_success() {
        Response response = new Response();

        PatientEntity patientEntity = createDummyPatientEntity();
        SpecialtyEntity specialtyEntity = createDummySpecialtyEntity();
        DoctorEntity doctorEntity = createDummyDoctorEntity(specialtyEntity);
        ConsultEntity consultEntity = createDummyConsultEntity(doctorEntity, specialtyEntity, patientEntity);
        List<ConsultEntity> consultEntities = Collections.singletonList(consultEntity);

        SymptomEntity symptomEntity = createDummySymptomEntity();
        PathologyEntity pathologyEntity = createDummyPathologyEntity(patientEntity, symptomEntity);

        List<ConsultResponse> consultResponses = Collections.singletonList(createDummyConsultResponse());
        List<Symptom> symptoms = Collections.singletonList(new Symptom(1L, DUMMY_SYMPTOM));
        response.setConsults(consultResponses);
        response.setSymptoms(symptoms);

        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patientEntity));
        Mockito.when(consultRepository.findAllByPatientId(1L)).thenReturn(consultEntities);
        Mockito.when(pathologyRepository.findAllByPatientId(1L)).thenReturn(Collections.singletonList(pathologyEntity));
        Mockito.when(entityMapper.toConsultResponseList(consultEntities)).thenReturn(consultResponses);
        Mockito.when(entityMapper.toSymptomList(Collections.singletonList(symptomEntity))).thenReturn(symptoms);

        Response responseActual = hospitalService.getPatientConsultsAndSymptoms(1L);

        Mockito.verify(patientRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(consultRepository, Mockito.times(1)).findAllByPatientId(1L);
        Mockito.verify(pathologyRepository, Mockito.times(1)).findAllByPatientId(1L);
        Mockito.verify(entityMapper, Mockito.times(1)).toConsultResponseList(consultEntities);
        Mockito.verify(entityMapper, Mockito.times(1)).toSymptomList(Collections.singletonList(symptomEntity));
        assertEquals(response, responseActual);
    }

    @Test
    void testGetPatientConsultsAndSymptoms_noPatient() {
        assertThrows(RuntimeException.class, () -> hospitalService.getPatientConsultsAndSymptoms(1L));
        Mockito.verify(consultRepository, Mockito.never()).findAllByPatientId(Mockito.any());
    }


    @Test
    void getTopSpecialties_success() {
        List<TopSpecialtyResponse> response = Collections.singletonList(new TopSpecialtyResponse(DUMMY_SPECIALTY, 3L));
        Mockito.when(consultRepository.findSpecialtiesWithMoreThanConsults(2L)).thenReturn(response);

        List<TopSpecialtyResponse> responseActual = hospitalService.getTopSpecialties();

        Mockito.verify(consultRepository, Mockito.times(1)).findSpecialtiesWithMoreThanConsults(2L);
        assertEquals(response, responseActual);
    }

    @Test
    void getTopSpecialties_empty() {
        List<TopSpecialtyResponse> responseActual = hospitalService.getTopSpecialties();

        Mockito.verify(consultRepository, Mockito.times(1)).findSpecialtiesWithMoreThanConsults(2L);
        assertEquals(Collections.emptyList(), responseActual);
    }

    @Test
    void getAllPatients_success() {
        PatientEntity patientEntity = new PatientEntity(1L, DUMMY_PATIENT, 1);
        Page<PatientEntity> patients = new PageImpl<>(Collections.singletonList(patientEntity));
        Patient patient = new Patient(1L, DUMMY_PATIENT, 1);
        Page<Patient> patientActual = new PageImpl<>(Collections.singletonList(patient));

        Mockito.when(patientRepository.findAll((Specification<PatientEntity>) ArgumentMatchers.argThat(spec -> spec instanceof Specification), ArgumentMatchers.any(Pageable.class))).thenReturn(Optional.of(patients));
        Mockito.when(entityMapper.toPatient(patientEntity)).thenReturn(patient);

        Page<Patient> response = hospitalService.getAllPatients(new PatientFilters(), PageRequest.of(1,10));
        assertEquals(patientActual, response);
    }

    @Test
    void getAllPatients_empty() {
        Page<Patient> response = hospitalService.getAllPatients(new PatientFilters(), PageRequest.of(1,10));
        assertEquals(Page.empty(), response);
    }



    private ConsultCommandDto createDummyConsultCommand(PatientCommandDto patient) {
        return new ConsultCommandDto(DUMMY_DOCTOR, DUMMY_SPECIALTY, patient);
    }

    private PatientCommandDto createDummyPatientCommand(PathologyCommandDto pathology) {
        return new PatientCommandDto(DUMMY_PATIENT, 1, pathology);
    }
    private SymptomCommandDto createDummySymptomCommand() {
        return new SymptomCommandDto(DUMMY_SYMPTOM);
    }

    private PathologyCommandDto createDummyPathologyCommand(SymptomCommandDto symptom) {
        return new PathologyCommandDto(Collections.singletonList(symptom));
    }

    private ConsultEntity createDummyConsultEntity(DoctorEntity doctorEntity, SpecialtyEntity specialtyEntity, PatientEntity patientEntity) {
        return new ConsultEntity(1L, doctorEntity, specialtyEntity, patientEntity);
    }

    private PatientEntity createDummyPatientEntity() {
        return new PatientEntity(1L, DUMMY_PATIENT, 1);
    }

    private DoctorEntity createDummyDoctorEntity(SpecialtyEntity specialty) {
        return new DoctorEntity(1L, DUMMY_DOCTOR, specialty);
    }

    private SpecialtyEntity createDummySpecialtyEntity() {
        return new SpecialtyEntity(1L, DUMMY_SPECIALTY);
    }

    private PathologyEntity createDummyPathologyEntity(PatientEntity patient, SymptomEntity symptom) {
        return new PathologyEntity(1L, patient, Collections.singletonList(symptom));
    }

    private SymptomEntity createDummySymptomEntity() {
        return new SymptomEntity(1L, DUMMY_SYMPTOM);
    }

    private ConsultResponse createDummyConsultResponse() {
        ConsultResponse consultResponse = new ConsultResponse();
        consultResponse.setId(1L);
        consultResponse.setDoctor(new Doctor(1L, DUMMY_DOCTOR, new Specialty()));
        consultResponse.setSpecialty(new Specialty(1L, DUMMY_SPECIALTY));
        return new ConsultResponse();
    }

}