package gryc.bank.demo.cqrs.command;

import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;

public class AccountNotEnoughMoneyCommand implements ICommand{
    private AccountBalanceChangeDto accountBalanceChangeDto;

    public AccountNotEnoughMoneyCommand(AccountBalanceChangeDto accountBalanceChangeDto) {
        this.accountBalanceChangeDto = accountBalanceChangeDto;
    }

    public AccountBalanceChangeDto getAccountBalanceChangeDto() {
        return accountBalanceChangeDto;
    }

}
