package com.challenge.challenge.domain.orm.specification;

import com.challenge.challenge.domain.dto.request.PatientFilters;
import com.challenge.challenge.domain.orm.models.PatientEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PatientSpecification {

    public static Specification<PatientEntity> filterBy(PatientFilters filters) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.getNameFilter() != null && !filters.getNameFilter().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filters.getNameFilter() + "%"));
            }

            if (filters.getAgeFilter() != null) {
                predicates.add(criteriaBuilder.equal(root.get("age"), filters.getAgeFilter()));
            }

            List<Order> orders = new ArrayList<>();
            if (filters.isSortByName()) {
                orders.add(criteriaBuilder.asc(root.get("name")));
            }
            if (filters.isSortByAge()) {
                orders.add(criteriaBuilder.asc(root.get("age")));
            }
            query.orderBy(orders);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
