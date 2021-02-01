package gryc.bank.demo.cqrs.query;

public class AccountBalanceQuery implements IQuery{
    private final long accountId;

    public AccountBalanceQuery(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }

}
