package gryc.bank.demo.cqrs.projection;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.cqrs.domain.Account;
import gryc.bank.demo.cqrs.event.AccountCreatedEvent;
import gryc.bank.demo.cqrs.event.AccountFundsAddedEvent;
import gryc.bank.demo.cqrs.event.AccountFundsWithdrawnEvent;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountProjector {
    AccountReadRepository repository;

    @Autowired
    public AccountProjector(AccountReadRepository repository) {
        this.repository = repository;
    }

    public void project(long accountId, List<Event> events) {
        for (Event event : events) {
            if (event instanceof AccountCreatedEvent) {
                apply(accountId, (AccountCreatedEvent) event);
            }
            if (event instanceof AccountFundsAddedEvent) {
                apply((AccountFundsAddedEvent) event);
            }
            if (event instanceof AccountFundsWithdrawnEvent) {
                apply((AccountFundsWithdrawnEvent) event);
            }
        }
    }

    private void apply(long accountId, AccountCreatedEvent event) {
        AccountCreateCommand accountCreateCommand = new AccountCreateCommand(event.getCreateAccountDto());
        repository.addAccount(accountId, accountCreateCommand);
    }

    private void apply(AccountFundsAddedEvent event) {
        AccountBalanceChangeCommand accountBalanceChangeCommand = new AccountBalanceChangeCommand(event.getAccountBalanceChangeDto());
        Account accountById = repository.getAccountById(accountBalanceChangeCommand.getId());
        accountById.changeBalance(accountBalanceChangeCommand.getBalanceChange());
    }

    private void apply(AccountFundsWithdrawnEvent event) {
        AccountBalanceChangeCommand accountBalanceChangeCommand = new AccountBalanceChangeCommand(event.getAccountBalanceChangeDto());
        Account accountById = repository.getAccountById(accountBalanceChangeCommand.getId());
        accountById.changeBalance(accountBalanceChangeCommand.getBalanceChange());
    }


}
