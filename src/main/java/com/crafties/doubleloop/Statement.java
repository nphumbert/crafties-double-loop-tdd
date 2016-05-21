package com.crafties.doubleloop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Statement {

    private static final String HEADER = "date | credit | debit | balance";
    private List<Transaction> transactions = new ArrayList<>();

    public String print() {
        Map<Transaction, BigDecimal> balancesByTransaction = computeBalancesByTransaction();

        return new StringBuilder(HEADER).append(
                transactions.stream()
                        .sorted(comparing(Transaction::date).reversed())
                        .map(transaction -> "\n" + transaction.print(balancesByTransaction.get(transaction)))
                        .collect(joining())
        ).toString();
    }

    private Map<Transaction, BigDecimal> computeBalancesByTransaction() {
        BigDecimal balance = BigDecimal.ZERO;
        Map<Transaction, BigDecimal> balancesByTransaction = new HashMap<>();
        for (Transaction transaction : sortByDateAsc(transactions)) {
            balance = balance.add(transaction.amount());
            balancesByTransaction.put(transaction, balance);
        }
        return balancesByTransaction;
    }

    private List<Transaction> sortByDateAsc(List<Transaction> transactions) {
        return transactions.stream().sorted(comparing(Transaction::date)).collect(toList());
    }

    public void add(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
