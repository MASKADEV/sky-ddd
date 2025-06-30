package com.application.service;

public interface AccountService {
    void deposit(String accountId, int amount);
    void withdraw(String accountId, int amount);
    void printStatement(String accountId);
}
