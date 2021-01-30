package gryc.bank.demo.cqrs.command;

import gryc.bank.demo.restservice.dto.CreateAccountDto;

import java.math.BigInteger;

public class AccountCreateCommand {
    private final CreateAccountDto createAccountDto;
    private final BigInteger initialBalance = BigInteger.ZERO;

//    public CreateAccountCommand(long id, String name, String surname) {
//        this.id = id;
//        this.name = name;
//        this.surname = surname;
//    }

    public AccountCreateCommand(CreateAccountDto createAccountDto) {
        this.createAccountDto = createAccountDto;
    }

    public CreateAccountDto getCreateAccountDto() {
        return createAccountDto;
    }

    public String getName() {
        return createAccountDto.getName();
    }

    public String getSurname() {
        return createAccountDto.getSurname();
    }

    public BigInteger getInitialBalance() {
        return initialBalance;
    }
}
