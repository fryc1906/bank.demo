package gryc.bank.demo.cqrs.command;

import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;

import java.math.BigInteger;

public class AccountBalanceChangeCommand {
    private final AccountBalanceChangeDto accountBalanceChangeDto;

    public AccountBalanceChangeCommand(AccountBalanceChangeDto dto) {
        this.accountBalanceChangeDto = dto;
    }

    public long getId() {
        return accountBalanceChangeDto.getAccountId();
    }

    public BigInteger getBalanceChange() {
        return accountBalanceChangeDto.getChangeAmount();
    }

    public AccountBalanceChangeDto getAccountBalanceChangeDto() {
        return accountBalanceChangeDto;
    }

}
