package com.example.productapi.filtering;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

// This class builds database query specifications for filtering products
public class ProductSpecification {
    
    // Static method that takes a list of filter criteria and returns a Specification
    // Specification is a functional interface that defines database queries
    public static Specification withFilters(List<FilterCriteria> criteria) {
        
        // This is a lambda that implements the Specification interface
        // root: Represents the entity we're querying (like Product)
        // query: The query we're building
        // cb: CriteriaBuilder - helps create query conditions
        return (root, query, cb) -> {
            
            // List to hold all our filter conditions (predicates)
            List<Predicate> predicates = new ArrayList<>();
            
            // Loop through each filter criteria provided
            for (FilterCriteria filter : criteria) {
                
                // Switch based on what type of comparison we're doing
                switch (filter.getOperator()) {
                    
                    // Case: equals comparison (exact match)
                    case "eq":
                        predicates.add(
                            cb.equal(
                                root.get(filter.getField()),  // Get the field we're comparing
                                filter.getValue()             // The value to compare against
                            )
                        );
                        break;
                    
                    // Case: greater than comparison
                    case "gt":
                        predicates.add(
                            cb.greaterThan(
                                root.get(filter.getField()),        // Field to compare
                                (Comparable) filter.getValue()       // Value to compare against
                            )
                        );
                        break;
                    
                    // Case: less than comparison
                    case "lt":
                        predicates.add(
                            cb.lessThan(
                                root.get(filter.getField()),        // Field to compare
                                (Comparable) filter.getValue()       // Value to compare against
                            )
                        );
                        break;
                    
                    // Case: LIKE comparison (contains search)
                    case "like":
                        predicates.add(
                            cb.like(
                                cb.lower(root.get(filter.getField())),    // Convert field to lowercase
                                "%" + filter.getValue().toString().toLowerCase() + "%"    // Add wildcards and convert to lowercase
                            )
                        );
                        break;
                }
            }
            
            // Combine all predicates with AND logic
            // This means ALL conditions must be true
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
