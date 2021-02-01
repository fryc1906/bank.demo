package gryc.bank.demo.cqrs.projection.strategy;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.cqrs.domain.Account;
import gryc.bank.demo.cqrs.event.AccountFundsWithdrawnEvent;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;

public class ApplyAccountFundsWithdrawnEvent implements IApply {

    @Override
    public boolean isApplicable(Event event) {
        return event instanceof AccountFundsWithdrawnEvent;
    }

    @Override
    public void apply(Event event, AccountReadRepository repository) {
        AccountFundsWithdrawnEvent accountFundsWithdrawnEvent = (AccountFundsWithdrawnEvent) event;
        AccountBalanceChangeCommand accountBalanceChangeCommand =
                new AccountBalanceChangeCommand(accountFundsWithdrawnEvent.getAccountBalanceChangeDto());
        Account accountById = repository.getAccountById(accountBalanceChangeCommand.getId());
        accountById.changeBalance(accountBalanceChangeCommand.getBalanceChange());
    }
}
