package gryc.bank.demo.cqrs.event;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;

import java.math.BigInteger;

public class AccountFundsAddedEvent extends Event {
    private final long accountId;
    private final AccountBalanceChangeDto accountBalanceChangeDto;

    public AccountFundsAddedEvent(AccountBalanceChangeCommand command) {
        this.accountId = command.getId();
        this.accountBalanceChangeDto = command.getAccountBalanceChangeDto();
    }

    public long getAccountId() {
        return accountId;
    }

    public BigInteger getBalanceChange() {
        return accountBalanceChangeDto.getChangeAmount();
    }

    public AccountBalanceChangeDto getAccountBalanceChangeDto() {
        return accountBalanceChangeDto;
    }
}
