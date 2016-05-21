package com.crafties.doubleloop;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class PrintStatementTest {

    @Test
    public void should_print_statement() {
        // given
        Account account = new Account(new Statement());
        account.deposit(new BigDecimal("1000"), LocalDate.of(2012, Month.JANUARY, 10));
        account.deposit(new BigDecimal("2000"), LocalDate.of(2012, Month.JANUARY, 13));
        account.withdraw(new BigDecimal("500"), LocalDate.of(2012, Month.JANUARY, 14));

        // when
        String statement = account.printStatement();

        // then
        assertThat(statement).isEqualTo(
                "date | credit | debit | balance\n" +
                "14/01/2012 | | 500.00 | 2500.00\n" +
                "13/01/2012 | 2000.00 | | 3000.00\n" +
                "10/01/2012 | 1000.00 | | 1000.00"
        );
    }
}
