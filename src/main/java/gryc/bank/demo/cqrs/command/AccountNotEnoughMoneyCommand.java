package gryc.bank.demo.cqrs.command;

import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;

import java.math.BigInteger;

public class AccountNotEnoughMoneyCommand {
    private AccountBalanceChangeDto accountBalanceChangeDto;

    public AccountNotEnoughMoneyCommand(AccountBalanceChangeDto accountBalanceChangeDto) {
        this.accountBalanceChangeDto = accountBalanceChangeDto;
    }

    public AccountBalanceChangeDto getAccountBalanceChangeDto() {
        return accountBalanceChangeDto;
    }
}
