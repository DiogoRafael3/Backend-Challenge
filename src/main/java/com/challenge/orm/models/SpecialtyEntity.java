package com.challenge.orm.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SPECIALTY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyEntity {
    @Id
    private String specialtyId;
    @OneToOne
    private DoctorEntity doctor;
}
