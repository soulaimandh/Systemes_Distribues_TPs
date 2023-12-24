package com.soulaimane.pattern.common.events;

import com.soulaimane.pattern.common.enums.AccountStatus;

import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String> {
    @Getter
    private AccountStatus accountStatus;

    public AccountActivatedEvent(String id, AccountStatus accountStatus) {
        super(id);
        this.accountStatus = accountStatus;
    }

}
