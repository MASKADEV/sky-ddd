package com.domain.models;

import com.domain.aggregation.AccountId;
import com.domain.aggregation.Amount;
import com.domain.aggregation.Balance;
import com.domain.aggregation.TransactionId;
import com.domain.enums.TransactionType;
import com.domain.events.DomainEvent;
import com.domain.events.MoneyDeposited;
import com.domain.events.MoneyWithdrawn;
import com.domain.exceptions.DomainException;
import com.domain.records.Statement;
import com.domain.records.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Account {
    private final AccountId id;
    private final List<Transaction> transactions;
    private final List<DomainEvent> domainEvents;
    private Balance balance;

    public Account(AccountId id) {
        this.id = Objects.requireNonNull(id, "Account ID cannot be null");
        this.balance = Balance.ZERO;
        this.transactions = new ArrayList<>();
        this.domainEvents = new ArrayList<>();
    }

    public void deposit(Amount amount) {
        Objects.requireNonNull(amount, "Amount cannot be null");
        if (amount.isNegativeOrZero()) {
            throw new DomainException("Deposit amount must be positive");
        }

        Balance newBalance = balance.add(amount);
        Transaction transaction = new Transaction(
                TransactionId.generate(),
                LocalDate.now(),
                amount,
                TransactionType.DEPOSIT,
                newBalance
        );

        transactions.add(transaction);
        balance = newBalance;
        domainEvents.add(new MoneyDeposited(id, amount, newBalance));
    }

    public void withdraw(Amount amount) {
        Objects.requireNonNull(amount, "Amount cannot be null");
        if (amount.isNegativeOrZero()) {
            throw new DomainException("Withdrawal amount must be positive");
        }
        if (balance.isLessThan(amount)) {
            throw new DomainException("Insufficient funds for withdrawal");
        }

        Balance newBalance = balance.subtract(amount);
        Transaction transaction = new Transaction(
                TransactionId.generate(),
                LocalDate.now(),
                amount,
                TransactionType.WITHDRAWAL,
                newBalance
        );

        transactions.add(transaction);
        balance = newBalance;
        domainEvents.add(new MoneyWithdrawn(id, amount, newBalance));
    }

    public Statement generateStatement() {
        return new Statement(
                id,
                LocalDate.now(),
                Collections.unmodifiableList(new ArrayList<>(transactions)),
                balance
        );
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(new ArrayList<>(domainEvents));
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }

    public AccountId getId() {
        return this.id;
    }
}
