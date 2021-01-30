package gryc.bank.demo.restservice.exception;

import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;

public class AccountNotEnoughMoneyException extends RuntimeException{
    public AccountNotEnoughMoneyException(AccountBalanceChangeDto accountBalanceChangeDto) {
        super("Withdraw operation amount exceeds total account balance");
    }
}
