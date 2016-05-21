package com.crafties.doubleloop;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private final Statement statement;

    public Account(Statement statement) {
        this.statement = statement;
    }

    public String printStatement() {
        return statement.print();
    }

    public void deposit(BigDecimal amount, LocalDate date) {
        statement.add(new Transaction(amount, date));
    }

    public void withdraw(BigDecimal amount, LocalDate date) {
        statement.add(new Transaction(amount.negate(), date));
    }
}
