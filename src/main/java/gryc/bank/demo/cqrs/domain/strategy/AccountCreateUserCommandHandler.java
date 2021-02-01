package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.cqrs.command.ICommand;
import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.AccountCreatedEvent;
import gryc.bank.demo.cqrs.event.Event;

import java.util.Arrays;
import java.util.List;

public class AccountCreateUserCommandHandler implements IHandler<ICommand>{
    @Override
    public boolean isApplicable(ICommand command) {
        return command instanceof AccountCreateCommand;
    }

    @Override
    public List<Event> handle(AccountAggregate accountAggregate, ICommand command) {
        AccountCreateCommand accountCreateCommand = (AccountCreateCommand) command;
        final long newId = accountAggregate.getIdHandler().getId();
        accountAggregate.getWriteRepository().createAccount(newId, accountCreateCommand);
        AccountCreatedEvent event = new AccountCreatedEvent(newId, accountCreateCommand);
        accountAggregate.getEventStore().addEvent(newId, event);
        return Arrays.asList(event);
    }
}
