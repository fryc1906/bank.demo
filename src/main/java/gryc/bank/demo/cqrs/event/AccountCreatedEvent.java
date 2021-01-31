package gryc.bank.demo.cqrs.event;

import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.restservice.dto.CreateAccountDto;

public class AccountCreatedEvent extends Event {
    private final long id;
    private CreateAccountDto createAccountDto;

    public AccountCreatedEvent(long id, AccountCreateCommand command) {
        this.id = id;
        this.createAccountDto = command.getCreateAccountDto();
    }

    public CreateAccountDto getCreateAccountDto() {
        return createAccountDto;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return createAccountDto.getName();
    }

    public String getSurname() {
        return createAccountDto.getSurname();
    }

}
