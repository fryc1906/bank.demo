package gryc.bank.demo.cqrs.domain;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.cqrs.command.AccountNotEnoughMoneyCommand;
import gryc.bank.demo.cqrs.event.*;
import gryc.bank.demo.cqrs.repository.AccountWriteRepository;
import gryc.bank.demo.cqrs.query.AccountBalanceQuery;
import gryc.bank.demo.cqrs.repository.EventStore;
import gryc.bank.demo.utils.AccountIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AccountAggregate {
    private final EventStore eventStore;
    private final AccountWriteRepository writeRepository;
    private final AccountIdHandler idHandler;

    @Autowired
    public AccountAggregate(EventStore eventStore, AccountWriteRepository writeRepository, AccountIdHandler idHandler) {
        this.eventStore = eventStore;
        this.writeRepository = writeRepository;
        this.idHandler = idHandler;
    }

    public List<Event> handleCreateUserCommand(AccountCreateCommand command) {
        final long newId = idHandler.getId();
        writeRepository.createAccount(newId, command);
        AccountCreatedEvent event = new AccountCreatedEvent(newId, command);
        eventStore.addEvent(newId, event);
        return Arrays.asList(event);
    }

    public List<Event> handleAccountBalanceQuery(AccountBalanceQuery query) {
        AccountBalanceCheckedEvent event = new AccountBalanceCheckedEvent(query);
        eventStore.addEvent(query.getAccountId(), event);
        return Arrays.asList(event);
    }

    public List<Event> handleBalanceChangeCommand(AccountBalanceChangeCommand command) {
        writeRepository.changeBalance(command);
        return new ArrayList<>(isPositiveBalanceChange(command)
                ? handlePositiveBalanceChange(command)
                : handleNegativeBalanceChange(command));
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

    public List<Event> handleAccountNotEnoughCommand(AccountNotEnoughMoneyCommand command) {
        AccountBalanceExceededEvent accountBalanceExceededEvent = new AccountBalanceExceededEvent(command);
        eventStore.addEvent(accountBalanceExceededEvent.getAccountId(), accountBalanceExceededEvent);
        return Arrays.asList(accountBalanceExceededEvent);
    }

}
