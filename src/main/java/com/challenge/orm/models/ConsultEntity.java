package com.challenge.orm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private Long consultId;
    @OneToOne
    @JoinColumn
    private DoctorEntity doctor;
    @OneToOne
    @JoinColumn
    private SpecialtyEntity specialty;
    @ManyToOne
    @JoinColumn
    private PatientEntity patient;
}
