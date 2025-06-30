package com.domain.events;

import com.domain.aggregation.AccountId;
import com.domain.aggregation.Amount;
import com.domain.aggregation.Balance;

public record MoneyWithdrawn(
        AccountId accountId,
        Amount amount,
        Balance newBalance
) implements DomainEvent {
    @Override
    public String type() {
        return "MoneyWithdrawn";
    }
}
