package gryc.bank.demo.restservice.dto;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

public class AccountBalanceChangeDto {
    @NotEmpty
    private long accountId;
    @NotEmpty
    private BigInteger changeAmount;

    public AccountBalanceChangeDto(@NotEmpty long accountId, @NotEmpty BigInteger changeAmount) {
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
