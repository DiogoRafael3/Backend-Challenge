package com.challenge.orm;

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
