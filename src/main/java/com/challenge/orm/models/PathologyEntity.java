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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "PATHOLOGY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathologyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pathologyId;
    @ManyToOne
    @JoinColumn
    private PatientEntity patient;
    @OneToMany
    private List<SymptomEntity> symptoms;
}
