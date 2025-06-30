package com.domain.records;

import com.domain.aggregation.Amount;
import com.domain.aggregation.Balance;
import com.domain.aggregation.TransactionId;
import com.domain.enums.TransactionType;

import java.time.LocalDate;

public record Transaction(
        TransactionId id,
        LocalDate date,
        Amount amount,
        TransactionType type,
        Balance balanceAfter
) {}
