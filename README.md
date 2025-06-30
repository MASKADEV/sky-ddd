# Typical Banking Service Application

A simple banking system implementation following Domain-Driven Design (DDD) principles that allows deposits, withdrawals, and statement printing.

## Features

- **Account Management**:
  - Deposit money
  - Withdraw money
  - View transaction history

- **Core Banking Operations**:
  - Maintain account balance
  - Record all transactions
  - Generate formatted statements

## Technical Overview

### Architecture
- **Domain Layer**: Contains all business logic and rules
- **Application Layer**: Coordinates operations and handles use cases
- **Infrastructure Layer**: Provides implementations for repositories and printers

### Key Components
- `Account`: The aggregate root managing all transactions
- `AccountService`: Application service handling banking operations
- `InMemoryAccountRepository`: Simple repository implementation
- `ConsoleStatementPrinter`: Displays formatted statements

## Usage

When running the application, you'll see a menu with these options:

```
=== Banking Application ===
1. Deposit
2. Withdraw
3. Print Statement
4. Exit
```

- Select an option by entering the corresponding number
- Follow prompts to enter amounts when making deposits or withdrawals
- Statements show transactions in format: `Date || Amount || Balance`

## Design Principles

- **Domain-Driven Design**: Clear separation of domain, application, and infrastructure layers
- **Immutable Objects**: Value objects are immutable for thread safety
- **Clean Architecture**: Dependencies point inward toward domain logic
- **SOLID Principles**: Each class has single responsibility

## Example Output

```
=== BANK STATEMENT ===
Account: ACC123
Date: 2023-05-20

Date || Amount || Balance
============================
2023-05-20 || 1000 || 1000
2023-05-21 || 2000 || 3000
2023-05-22 || -500 || 2500
============================
Current Balance: 2500
============================
```
