package gryc.bank.demo.cqrs.query;

import java.math.BigInteger;

public class AccountBalanceQuery {
    private final long accountId;

    public AccountBalanceQuery(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }

}
