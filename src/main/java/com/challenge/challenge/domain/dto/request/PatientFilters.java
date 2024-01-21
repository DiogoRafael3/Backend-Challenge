package com.challenge.challenge.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientFilters {
    String nameFilter;
    Integer ageFilter;
    boolean sortByName = false;
    boolean sortByAge = false;
}
