package com.domain.aggregation;

public record Balance(int value) {
    public static final Balance ZERO = new Balance(0);

    public Balance add(Amount amount) {
        return new Balance(this.value + amount.value());
    }

    public Balance subtract(Amount amount) {
        return new Balance(this.value - amount.value());
    }

    public boolean isLessThan(Amount amount) {
        return this.value < amount.value();
    }
}
