package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.command.ICommand;
import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.Event;

import java.util.List;

public interface ICommandHandler {
    boolean isApplicable(ICommand command);
    List<Event> handle(AccountAggregate accountAggregate, ICommand command);
}
