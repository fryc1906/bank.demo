package gryc.bank.demo.cqrs.service;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.cqrs.command.AccountNotEnoughMoneyCommand;
import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.AccountCreatedEvent;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.projection.AccountProjection;
import gryc.bank.demo.cqrs.query.AccountBalanceQuery;
import gryc.bank.demo.cqrs.query.AccountExistQuery;
import gryc.bank.demo.restservice.dto.AccountBalanceChangeDto;
import gryc.bank.demo.restservice.dto.CreateAccountDto;
import gryc.bank.demo.restservice.exception.AccountNotEnoughMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AccountService {
    private AccountAggregate accountAggregate;
    private AccountProjection accountProjection;

    @Autowired
    public AccountService(AccountAggregate accountAggregate, AccountProjection accountProjection) {
        this.accountAggregate = accountAggregate;
        this.accountProjection = accountProjection;
    }

    public long createUser(CreateAccountDto dto) {
        AccountCreateCommand accountCreateCommand = new AccountCreateCommand(dto);
        List<Event> events = accountAggregate.handleCreateUserCommand(accountCreateCommand);
        return events.stream()
                .filter(a -> a instanceof AccountCreatedEvent)
                .map(a -> (AccountCreatedEvent)a)
                .findAny()
                .orElseThrow(RuntimeException::new)
                .getId();
    }

    public BigInteger getBalance(long id) {
        AccountBalanceQuery accountBalanceQuery = new AccountBalanceQuery(id);
        accountAggregate.handleAccountBalanceQuery(accountBalanceQuery);
        return accountProjection.handle(accountBalanceQuery);
    }

    public void changeBalance(AccountBalanceChangeDto accountBalanceChangeDto) {
        AccountExistQuery accountExistQuery = new AccountExistQuery(accountBalanceChangeDto.getAccountId());
        if (isNotEnoughMoney(accountBalanceChangeDto)) {
            AccountNotEnoughMoneyCommand accountNotEnoughMoneyCommand = new AccountNotEnoughMoneyCommand(accountBalanceChangeDto);
            accountAggregate.handleAccountNotEnoughCommand(accountNotEnoughMoneyCommand);
            throw new AccountNotEnoughMoneyException(accountBalanceChangeDto);
        }
        AccountBalanceChangeCommand accountBalanceChangeCommand =
                new AccountBalanceChangeCommand(accountBalanceChangeDto);
        accountAggregate.handleBalanceChangeCommand(accountBalanceChangeCommand);
    }

    private boolean isNotEnoughMoney(AccountBalanceChangeDto accountBalanceChangeDto) {
        AccountBalanceQuery accountBalanceQuery = new AccountBalanceQuery(accountBalanceChangeDto.getAccountId());
        BigInteger currentBalance = accountProjection.handle(accountBalanceQuery);
        int compareResult = currentBalance.add(accountBalanceChangeDto.getChangeAmount()).compareTo(BigInteger.ZERO);
        return compareResult < 0;
    }

}
