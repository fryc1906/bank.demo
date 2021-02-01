package gryc.bank.demo.cqrs.projection.strategy;

import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.cqrs.event.AccountCreatedEvent;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;
import org.springframework.stereotype.Component;

@Component
public class ApplyAccountCreatedEvent implements IApply {

    @Override
    public boolean isApplicable(Event event) {
        return event instanceof AccountCreatedEvent;
    }

    @Override
    public void apply(Event event, AccountReadRepository repository) {
        AccountCreatedEvent accountCreatedEvent = (AccountCreatedEvent) event;
        AccountCreateCommand accountCreateCommand = new AccountCreateCommand(accountCreatedEvent.getCreateAccountDto());
        repository.addAccount(accountCreatedEvent.getId(), accountCreateCommand);
    }
}
