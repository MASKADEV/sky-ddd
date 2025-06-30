package com.domain.aggregation;

import com.domain.exceptions.DomainException;

public record Amount(int value) {
    public Amount {
        if (value <= 0) {
            throw new DomainException("Amount must be positive");
        }
    }

    public boolean isNegativeOrZero() {
        return value <= 0;
    }
}
