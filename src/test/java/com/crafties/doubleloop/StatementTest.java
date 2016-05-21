package com.crafties.doubleloop;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class StatementTest {

    private static class FakeTransaction extends Transaction {

        private final BigDecimal expectedBalance;
        private final String printedValue;

        public FakeTransaction(BigDecimal amount, LocalDate date, BigDecimal expectedBalance, String printedValue) {
            super(amount, date);
            this.expectedBalance = expectedBalance;
            this.printedValue = printedValue;
        }

        @Override
        public String print(BigDecimal balance) {
            if (balance.equals(expectedBalance)) {
                return printedValue;
            }

            throw new IllegalStateException("Unexpected balance [expected = " + expectedBalance + ", actual = " + balance + "]");
        }
    }

    @Test
    public void should_print_only_header_when_empty() {
        // given
        Statement statement = new Statement();

        // when
        String printedStatement = statement.print();

        // then
        assertThat(printedStatement).isEqualTo("date | credit | debit | balance");
    }

    @Test
    public void should_print_all_transactions_by_date_desc() {
        // given
        Statement statement = new Statement();

        // when
        statement.add(new FakeTransaction(new BigDecimal(100), LocalDate.of(2015, Month.FEBRUARY, 11), new BigDecimal(100), "11/02/2015 | 100.00 | | 100.00"));
        statement.add(new FakeTransaction(new BigDecimal(200), LocalDate.of(2015, Month.FEBRUARY, 12), new BigDecimal(300), "12/02/2015 | 200.00 | | 300.00"));
        String printedStatement = statement.print();

        // then
        assertThat(printedStatement).isEqualTo(
                "date | credit | debit | balance\n" +
                "12/02/2015 | 200.00 | | 300.00\n" +
                "11/02/2015 | 100.00 | | 100.00"
        );
    }
}