package com.challenge.orm;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SPECIALTY")
public class SpecialtyEntity {
    @Id
    private String specialtyId;
    @OneToOne
    private DoctorEntity doctor;
}
