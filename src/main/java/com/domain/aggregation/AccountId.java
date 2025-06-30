package com.domain.aggregation;

import com.domain.exceptions.DomainException;

public record AccountId(String value) {
    public AccountId {
        if (value == null || value.isBlank()) {
            throw new DomainException("Account ID cannot be empty");
        }
    }
}
