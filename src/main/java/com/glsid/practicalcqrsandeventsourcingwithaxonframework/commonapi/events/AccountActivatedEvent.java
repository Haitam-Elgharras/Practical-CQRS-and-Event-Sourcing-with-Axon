package com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.events;

import com.glsid.practicalcqrsandeventsourcingwithaxonframework.commonapi.commands.enums.AccountStatus;
import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String> {
    @Getter
    public AccountStatus accountStatus;

    public AccountActivatedEvent(String id, AccountStatus status) {
        super(id);
        this.accountStatus = status;
    }
}
