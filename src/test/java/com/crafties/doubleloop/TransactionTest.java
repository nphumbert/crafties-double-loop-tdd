package com.crafties.doubleloop;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionTest {
    
    @Test
    public void should_print_deposit() {
        // given
        Transaction transaction = new Transaction(new BigDecimal("100"), LocalDate.of(2012, Month.JANUARY, 14));

        // when
        String printedTransaction = transaction.print(new BigDecimal(500));
        
        // then
        assertThat(printedTransaction).isEqualTo("14/01/2012 | 100.00 | | 500.00");
    }

    @Test
    public void should_print_withdraw() {
        // given
        Transaction transaction = new Transaction(new BigDecimal("-100"), LocalDate.of(2012, Month.JANUARY, 14));

        // when
        String printedTransaction = transaction.print(new BigDecimal(500));

        // then
        assertThat(printedTransaction).isEqualTo("14/01/2012 | | 100.00 | 500.00");
    }

}