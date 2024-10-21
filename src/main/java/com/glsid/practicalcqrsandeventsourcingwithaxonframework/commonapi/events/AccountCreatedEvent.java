package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events;

import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.enums.AccountStatus;
import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String> {
    @Getter
    public final String currency;
    @Getter
    public final double initialBalance;
    @Getter
    public final AccountStatus status;

    public AccountCreatedEvent(String id, double accountBalance, String currency, AccountStatus status) {
        super(id);
        this.initialBalance = accountBalance;
        this.currency = currency;
        this.status = status;
    }
}
