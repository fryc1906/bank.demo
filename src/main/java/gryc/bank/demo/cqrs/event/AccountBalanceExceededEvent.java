package gryc.bank.demo.cqrs.event;

import gryc.bank.demo.cqrs.command.AccountNotEnoughMoneyCommand;
import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;

import java.math.BigInteger;

public class AccountBalanceExceededEvent extends Event {
    private AccountBalanceChangeDto accountBalanceChangeDto;

    public AccountBalanceExceededEvent(AccountNotEnoughMoneyCommand command) {
        accountBalanceChangeDto = command.getAccountBalanceChangeDto();
    }

    public long getAccountId() {
        return accountBalanceChangeDto.getAccountId();
    }

    public BigInteger getWithdrawValue() {
        return accountBalanceChangeDto.getChangeAmount();
    }

}
