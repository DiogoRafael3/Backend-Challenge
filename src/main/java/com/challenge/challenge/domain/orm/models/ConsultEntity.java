package com.challenge.challenge.domain.orm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CONSULT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private DoctorEntity doctor;
    @OneToOne
    private SpecialtyEntity specialty;
    @ManyToOne
    private PatientEntity patient;
}
