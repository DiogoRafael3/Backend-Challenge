package com.challenge.challenge.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientFilters {
    String nameFilter;
    Long ageFilter;
    boolean sortByName;
    boolean sortByAge;
}
