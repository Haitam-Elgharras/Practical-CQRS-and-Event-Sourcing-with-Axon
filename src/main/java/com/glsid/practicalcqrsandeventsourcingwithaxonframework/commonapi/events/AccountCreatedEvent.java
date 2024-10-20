package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events;

import lombok.Getter;

public class AccountCreatedEvent extends BaseEvent<String> {
    @Getter
    public final String currency;
    @Getter
    public final double initialBalance;

    public AccountCreatedEvent(String id, double accountBalance, String currency) {
        super(id);
        this.initialBalance = accountBalance;
        this.currency = currency;
    }
}
