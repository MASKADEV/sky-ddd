package com.infrastructure.repos;

import com.domain.aggregation.AccountId;
import com.domain.models.Account;

public interface AccountRepository {
    Account findById(AccountId accountId);
    void save(Account account);
}
