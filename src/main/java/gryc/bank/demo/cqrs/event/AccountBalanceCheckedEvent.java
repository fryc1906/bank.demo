package gryc.bank.demo.cqrs.event;

import gryc.bank.demo.cqrs.query.AccountBalanceQuery;

public class AccountBalanceCheckedEvent extends Event {
    private final long accountId;

    public AccountBalanceCheckedEvent(AccountBalanceQuery query) {
        this.accountId = query.getAccountId();
    }

}
