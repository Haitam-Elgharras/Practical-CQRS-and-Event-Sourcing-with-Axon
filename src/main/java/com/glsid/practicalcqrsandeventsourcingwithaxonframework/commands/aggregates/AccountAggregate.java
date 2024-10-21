package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commands.aggregates;

import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.CreateAccountCommand;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.CreditAccountCommand;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.DebitAccountCommand;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.enums.AccountStatus;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events.AccountActivatedEvent;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events.AccountCreatedEvent;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events.AccountCreditedEvent;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events.AccountDebitedEvent;
import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.exceptions.AmountException;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;


@NoArgsConstructor
@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    // 2/ the CommandHandler Listen to the command bus and when a command is received, it does some validation and then sends an event to the event bus
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        if (command.getInitialBalance() < 0) {
            throw new IllegalArgumentException("Initial balance cannot be less than 0");
        }

        // send the event to the event bus
        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        command.getId(),
                        command.getInitialBalance(),
                        command.getCurrency(),
                        AccountStatus.CREATED
                )
        );
    }

    // 3/ EventSourcingHandler Listen to the event bus and when the event is received, update the state of the aggregate
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getId();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = AccountStatus.CREATED;

        // the event sourcing handler can send events to the event bus (side effects)
        AggregateLifecycle.apply(
                new AccountActivatedEvent(
                        this.accountId,
                        AccountStatus.ACTIVATED
                )
        );
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent event) {
        this.status = event.getAccountStatus();
    }


    // Account Operations
    @CommandHandler
    public void handleCreditAccountCommand(CreditAccountCommand creditCommand) {
        if(creditCommand.getAmount() <= 0)
            throw new AmountException("Amount should be greater than 0");

        AggregateLifecycle.apply(
                new AccountCreditedEvent(
                        creditCommand.getId(),
                        creditCommand.getAmount(),
                        creditCommand.getCurrency()
                )
        );
    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent creditEvent) {
        balance += creditEvent.getAmount();
    }

    // Account Operations
    @CommandHandler
    public void handleDebitAccountCommand(DebitAccountCommand debitCommand) {
        if(debitCommand.getAmount() > balance || debitCommand.getAmount() <= 0)
            throw new AmountException("Amount should be greater than 0 and less than balance");;

        AggregateLifecycle.apply(
                new AccountDebitedEvent(
                        debitCommand.getId(),
                        debitCommand.getAmount(),
                        debitCommand.getCurrency()
                )
        );
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent debitEvent) {
        balance -= debitEvent.getAmount();
    }
}