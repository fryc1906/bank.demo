package gryc.bank.demo.cqrs.query;

public class AccountExistQuery {
    private long accountId;

    public AccountExistQuery(long accountId) {
        this.accountId = accountId;
    }

    public long getAccountId() {
        return accountId;
    }

}
