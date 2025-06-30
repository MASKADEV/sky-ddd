package com.application.service;

import com.domain.aggregation.AccountId;
import com.domain.aggregation.Amount;
import com.domain.exceptions.ApplicationException;
import com.domain.exceptions.DomainException;
import com.domain.models.Account;
import com.domain.records.Statement;
import com.infrastructure.repos.AccountRepository;
import com.infrastructure.repos.StatementPrinter;

public class AccountServiceImpli implements AccountService {
    private final AccountRepository accountRepository;
    private final StatementPrinter statementPrinter;

    public AccountServiceImpli(
            AccountRepository accountRepository,
            StatementPrinter statementPrinter
    ) {
        this.accountRepository = accountRepository;
        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(String accountId, int amount) {
        try {
            Account account = accountRepository.findById(new AccountId(accountId));
            account.deposit(new Amount(amount));
            accountRepository.save(account);
        } catch (DomainException e) {
            throw new ApplicationException("Deposit failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void withdraw(String accountId, int amount) {
        try {
            Account account = accountRepository.findById(new AccountId(accountId));
            account.withdraw(new Amount(amount));
            accountRepository.save(account);
        } catch (DomainException e) {
            throw new ApplicationException("Withdrawal failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void printStatement(String accountId) {
        try {
            Account account = accountRepository.findById(new AccountId(accountId));
            Statement statement = account.generateStatement();
            statementPrinter.print(statement);
        } catch (DomainException e) {
            throw new ApplicationException("Statement generation failed: " + e.getMessage(), e);
        }
    }
}
