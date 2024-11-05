package com.example.productapi.filtering;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterCriteria {
    private String field;
    private String operator;
    private Object value;
}
