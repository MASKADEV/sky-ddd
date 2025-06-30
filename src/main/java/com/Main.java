package com;

import com.application.service.AccountServiceImpli;
import com.domain.aggregation.AccountId;
import com.domain.models.Account;
import com.infrastructure.ConsoleStatementPrinter;
import com.infrastructure.InMemoryAccountRepository;
import com.infrastructure.repos.AccountRepository;
import com.infrastructure.repos.StatementPrinter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        StatementPrinter statementPrinter = new ConsoleStatementPrinter();
        AccountServiceImpli accountService = new AccountServiceImpli(accountRepository, statementPrinter);
        Scanner scanner = new Scanner(System.in);

        AccountId accountId = new AccountId("ACC123");
        accountRepository.save(new Account(accountId));

        boolean running = true;
        while (running) {
            System.out.println("\n=== Banking Application ===");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Print Statement");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1-4.");
                    scanner.nextLine();
                    continue;
                }
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleDeposit(accountService, accountId, scanner);
                        break;
                    case 2:
                        handleWithdraw(accountService, accountId, scanner);
                        break;
                    case 3:
                        accountService.printStatement(accountId.value());
                        break;
                    case 4:
                        running = false;
                        System.out.println("Thank you for using our banking service!");
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1-4.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void handleDeposit(AccountServiceImpli service, AccountId accountId, Scanner scanner) {
        try {
            System.out.print("Enter deposit amount: ");
            int amount = scanner.nextInt();
            scanner.nextLine();
            service.deposit(accountId.value(), amount);
            System.out.println("Successfully deposited " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void handleWithdraw(AccountServiceImpli service, AccountId accountId, Scanner scanner) {
        try {
            System.out.print("Enter withdrawal amount: ");
            int amount = scanner.nextInt();
            scanner.nextLine();
            service.withdraw(accountId.value(), amount);
            System.out.println("Successfully withdrew " + amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}