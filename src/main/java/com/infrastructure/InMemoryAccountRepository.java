package com.infrastructure;

import com.domain.aggregation.AccountId;
import com.domain.exceptions.DomainException;
import com.domain.models.Account;
import com.infrastructure.repos.AccountRepository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<AccountId, Account> accounts = new HashMap<>();

    @Override
    public Account findById(AccountId accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new DomainException("Account not found");
        }
        return account;
    }

    @Override
    public void save(Account account) {
        accounts.put(account.getId(), account);
    }
}
