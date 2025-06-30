package com.domain.aggregation;

public record TransactionId(String value) {
    public static TransactionId generate() {
        return new TransactionId(java.util.UUID.randomUUID().toString());
    }
}
