package gryc.bank.demo.restservice.dto;

import java.math.BigInteger;

public class AccountBalanceChangeDto {
    private long accountId;
    private BigInteger changeAmount;

    public AccountBalanceChangeDto(long accountId, BigInteger changeAmount) {
        this.accountId = accountId;
        this.changeAmount = changeAmount;
    }

    public long getAccountId() {
        return accountId;
    }

    public BigInteger getChangeAmount() {
        return changeAmount;
    }
}
