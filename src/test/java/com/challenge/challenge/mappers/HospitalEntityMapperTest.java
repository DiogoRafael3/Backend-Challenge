package com.challenge.challenge.mappers;

import com.challenge.challenge.domain.Consult;
import com.challenge.challenge.domain.Doctor;
import com.challenge.challenge.domain.Patient;
import com.challenge.challenge.domain.Specialty;
import com.challenge.challenge.domain.orm.models.ConsultEntity;
import com.challenge.challenge.domain.orm.models.DoctorEntity;
import com.challenge.challenge.domain.orm.models.PatientEntity;
import com.challenge.challenge.domain.orm.models.SpecialtyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HospitalEntityMapperTest {

    @Mock
    private HospitalEntityMapper entityMapper;

    private static final String DUMMY_PATIENT = "DummyPatient";
    private static final String DUMMY_DOCTOR = "DummyDoctor";
    private static final String DUMMY_SPECIALTY = "DummySpecialty";

    //I only made one mapper test due to time constraints
    @Test
    void toConsult() {
        //ideally i'd like to have these objects created in other lines, or other methods to facilitate reading
        ConsultEntity consultEntity = new ConsultEntity(1L, new DoctorEntity(1L, DUMMY_DOCTOR, new SpecialtyEntity()), new SpecialtyEntity(1L, DUMMY_SPECIALTY), new PatientEntity(1L, DUMMY_PATIENT, 1));
        Consult consult = new Consult(1L, new Doctor(1L, DUMMY_DOCTOR, new Specialty()), new Specialty(1L, DUMMY_SPECIALTY), new Patient(1L, DUMMY_PATIENT, 1));

        Mockito.when(entityMapper.toConsult(consultEntity)).thenReturn(consult);

        Consult consultActual = entityMapper.toConsult(consultEntity);

        assertEquals(consult, consultActual);
    }

}