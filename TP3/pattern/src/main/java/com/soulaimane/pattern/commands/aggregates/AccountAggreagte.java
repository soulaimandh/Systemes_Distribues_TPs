package com.soulaimane.pattern.commands.aggregates;

import com.soulaimane.pattern.common.enums.AccountStatus;
import com.soulaimane.pattern.common.events.AccountActivatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.soulaimane.pattern.common.commands.CreateAccountCommand;
import com.soulaimane.pattern.common.events.AccountCreatedEvent;

@Aggregate
public class AccountAggreagte {

    @AggregateIdentifier
    private String id;
    private double balance;
    private String currency;
    private AccountStatus status;

    public AccountAggreagte() {
    }

    @CommandHandler
    public AccountAggreagte(CreateAccountCommand command) {
        if (command.getInitialBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        AggregateLifecycle
                .apply(new AccountCreatedEvent(command.getId(), command.getInitialBalance(), command.getCurrency()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.id = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = AccountStatus.CREATED;
        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, AccountStatus.ACTIVATED));
    }

     @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        this.status = event.getAccountStatus();
    }
}
