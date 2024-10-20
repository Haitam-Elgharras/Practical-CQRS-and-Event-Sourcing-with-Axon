package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands;

import lombok.Getter;

@Getter // we use just the getter because the commands are immutable
public class DebitAccountCommand extends BaseCommand<String> {
    private double amount;
    private String currency;

    public DebitAccountCommand(String id, double amount, String currency) {
        super(id);
        this.amount = amount;
        this.currency = currency;
    }
}