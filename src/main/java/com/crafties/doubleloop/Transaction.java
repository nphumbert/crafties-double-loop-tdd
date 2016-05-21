package com.crafties.doubleloop;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final BigDecimal amount;
    private final LocalDate date;

    public Transaction(BigDecimal amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public String print(BigDecimal balance) {
        StringBuilder builder = new StringBuilder();
        builder.append(format(date)).append(" | ");

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            builder.append("| ").append(scale(amount).abs());
        } else {
            builder.append(scale(amount).abs()).append(" |");
        }

        builder.append(" | ").append(scale(balance));
        return builder.toString();
    }

    private String format(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private BigDecimal scale(BigDecimal amount) {
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal amount() {
        return amount;
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (!amount.equals(that.amount)) return false;
        return date.equals(that.date);

    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }
}
