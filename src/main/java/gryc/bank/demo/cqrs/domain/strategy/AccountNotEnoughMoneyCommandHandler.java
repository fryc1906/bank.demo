package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.command.AccountNotEnoughMoneyCommand;
import gryc.bank.demo.cqrs.command.ICommand;
import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.AccountBalanceExceededEvent;
import gryc.bank.demo.cqrs.event.Event;

import java.util.Arrays;
import java.util.List;

public class AccountNotEnoughMoneyCommandHandler implements ICommandHandler {
    @Override
    public boolean isApplicable(ICommand command) {
        return command instanceof AccountNotEnoughMoneyCommand;
    }

    @Override
    public List<Event> handle(AccountAggregate accountAggregate, ICommand command) {
        AccountNotEnoughMoneyCommand accountNotEnoughMoneyCommand = (AccountNotEnoughMoneyCommand) command;
        AccountBalanceExceededEvent accountBalanceExceededEvent = new AccountBalanceExceededEvent(accountNotEnoughMoneyCommand);
        accountAggregate.getEventStore().addEvent(accountBalanceExceededEvent.getAccountId(), accountBalanceExceededEvent);
        return Arrays.asList(accountBalanceExceededEvent);
    }
}
