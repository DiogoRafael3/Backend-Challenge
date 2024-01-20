package com.challenge.orm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "PATIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
