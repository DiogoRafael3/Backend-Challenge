package com.challenge.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "PATIENT")
public class PatientEntity {
    @Id
    private String patientId;
    @Column
    private short age;
    @OneToMany
    private List<ConsultEntity> consults;
    @OneToMany
    private List<PathologyEntity> pathologies;
}
