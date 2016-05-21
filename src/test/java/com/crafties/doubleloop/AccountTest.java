package com.crafties.doubleloop;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AccountTest {

    @Test
    public void should_print_statement() {
        // given
        Statement statement = mock(Statement.class);
        when(statement.print()).thenReturn("printed statement");
        Account account = new Account(statement);

        // when
        String printedStatement = account.printStatement();

        // then
        assertThat(printedStatement).isEqualTo("printed statement");
    }

    @Test
    public void should_deposit_amount() {
        // given
        Statement statement = mock(Statement.class);
        Account account = new Account(statement);

        // when
        account.deposit(new BigDecimal("100"), LocalDate.of(2012, 12, 11));

        // then
        verify(statement).add(new Transaction(new BigDecimal("100"), LocalDate.of(2012, 12, 11)));
    }
    
    @Test
    public void should_withdraw_amount() {
        // given
        Statement statement = mock(Statement.class);
        Account account = new Account(statement);

        // when
        account.withdraw(new BigDecimal("100"), LocalDate.of(2012, 12, 11));

        // then
        verify(statement).add(new Transaction(new BigDecimal("100").negate(), LocalDate.of(2012, 12, 11)));
    }

}