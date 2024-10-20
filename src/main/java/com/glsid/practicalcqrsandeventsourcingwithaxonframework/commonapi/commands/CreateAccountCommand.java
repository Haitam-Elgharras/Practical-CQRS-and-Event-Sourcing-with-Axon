package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands;

import lombok.Getter;

@Getter
public class CreateAccountCommand extends BaseCommand<String> {
    private double initialBalance;
    private String currency;

    public CreateAccountCommand(String id, double balance, String currency) {
        super(id);
        this.initialBalance = balance;
        this.currency = currency;
    }
}