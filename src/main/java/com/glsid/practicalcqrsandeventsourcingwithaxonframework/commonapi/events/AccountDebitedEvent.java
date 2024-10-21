package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events;

import lombok.Getter;

@Getter
public class AccountDebitedEvent extends BaseEvent<String> {
    private Double amount;
    private String currency;

    public AccountDebitedEvent(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}