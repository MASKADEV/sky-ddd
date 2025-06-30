package com.infrastructure;

import com.domain.records.Statement;
import com.infrastructure.repos.StatementPrinter;

public class ConsoleStatementPrinter implements StatementPrinter {
    @Override
    public void print(Statement statement) {
        System.out.println(statement.toFormattedString());
    }
}
