package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.cqrs.command.ICommand;
import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.AccountFundsAddedEvent;
import gryc.bank.demo.cqrs.event.AccountFundsWithdrawnEvent;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.repository.EventStore;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountBalanceChangeCommandHandler implements IHandler<ICommand> {
    private EventStore eventStore;
    @Override
    public boolean isApplicable(ICommand command) {
        return command instanceof AccountBalanceChangeCommand;
    }

    @Override
    public List<Event> handle(AccountAggregate accountAggregate, ICommand command) {
        AccountBalanceChangeCommand accountBalanceChangeCommand = (AccountBalanceChangeCommand) command;
        accountAggregate.getWriteRepository().changeBalance(accountBalanceChangeCommand);
        eventStore = accountAggregate.getEventStore();
        return new ArrayList<>(isPositiveBalanceChange(accountBalanceChangeCommand)
                ? handlePositiveBalanceChange(accountBalanceChangeCommand)
                : handleNegativeBalanceChange(accountBalanceChangeCommand));
    }

    private boolean isPositiveBalanceChange(AccountBalanceChangeCommand command) {
        return command.getBalanceChange().compareTo(BigInteger.ZERO) >= 0;
    }

    private List<Event> handlePositiveBalanceChange(AccountBalanceChangeCommand command) {
        AccountFundsAddedEvent accountFundsAddedEvent = new AccountFundsAddedEvent(command);
        eventStore.addEvent(accountFundsAddedEvent.getAccountId(), accountFundsAddedEvent);
        return Arrays.asList(accountFundsAddedEvent);
    }

    private List<Event> handleNegativeBalanceChange(AccountBalanceChangeCommand command) {
        AccountFundsWithdrawnEvent accountFundsWithdrawnEvent = new AccountFundsWithdrawnEvent(command);
        eventStore.addEvent(accountFundsWithdrawnEvent.getAccountId(), accountFundsWithdrawnEvent);
        return Arrays.asList(accountFundsWithdrawnEvent);
    }


}
