package com.challenge.orm;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {
    @Id
    private String doctorId;
}
