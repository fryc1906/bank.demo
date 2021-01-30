package gryc.bank.demo.restservice.dto;

import java.math.BigInteger;

public class AccountBalanceDto {
    private final BigInteger balance;

    public AccountBalanceDto(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getBalance() {
        return balance;
    }
}
