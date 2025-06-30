package com.domain.records;

import com.domain.aggregation.AccountId;
import com.domain.aggregation.Balance;
import com.domain.enums.TransactionType;

import java.time.LocalDate;
import java.util.List;

public record Statement(
        AccountId accountId,
        LocalDate generatedOn,
        List<Transaction> transactions,
        Balance currentBalance
) {
    public String toFormattedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== BANK STATEMENT ===\n");
        sb.append("Account: ").append(accountId.value()).append("\n");
        sb.append("Generated on: ").append(generatedOn).append("\n\n");
        sb.append("Date || Amount || Balance\n");
        sb.append("============================\n");

        for (Transaction transaction : transactions) {
            String amountDisplay = (transaction.type() == TransactionType.WITHDRAWAL)
                    ? "-" + transaction.amount().value()
                    : String.valueOf(transaction.amount().value());

            sb.append(transaction.date())
                    .append(" || ")
                    .append(amountDisplay)
                    .append(" || ")
                    .append(transaction.balanceAfter().value())
                    .append("\n");
        }

        sb.append("============================\n");
        sb.append("Current Balance: ").append(currentBalance.value()).append("\n");
        sb.append("============================");
        return sb.toString();
    }
}
