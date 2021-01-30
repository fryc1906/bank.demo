package gryc.bank.demo.cqrs.domain;

import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.restservice.dto.CreateAccountDto;

import java.math.BigInteger;

public class Account {
    private final String name;
    private final String surname;
    private final long id;
    private BigInteger balance = BigInteger.ZERO;


    public Account(long id, CreateAccountDto createAccountDto) {
        this.name = createAccountDto.getName();
        this.surname = createAccountDto.getSurname();
        this.id = id;
    }

    public Account(long id, AccountCreateCommand command) {
        this.id = id;
        this.name = command.getName();
        this.surname = command.getSurname();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getId() {
        return id;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void changeBalance(BigInteger balance) {
        this.balance = this.balance.add(balance);
    }
}
