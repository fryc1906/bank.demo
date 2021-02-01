package gryc.bank.demo.cqrs.projection.strategy;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.cqrs.domain.Account;
import gryc.bank.demo.cqrs.event.AccountFundsAddedEvent;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;

public class ApplyAccountFundsAddedEvent implements IApply {

    @Override
    public boolean isApplicable(Event event) {
        return event instanceof AccountFundsAddedEvent;
    }

    @Override
    public void apply(Event event, AccountReadRepository repository) {
        AccountFundsAddedEvent accountFundsAddedEvent = (AccountFundsAddedEvent) event;
        AccountBalanceChangeCommand accountBalanceChangeCommand =
                new AccountBalanceChangeCommand(accountFundsAddedEvent.getAccountBalanceChangeDto());
        Account accountById = repository.getAccountById(accountBalanceChangeCommand.getId());
        accountById.changeBalance(accountBalanceChangeCommand.getBalanceChange());
    }
}
