package gryc.bank.demo.cqrs.projection;

import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.projection.strategy.ApplyNonMutatingEmptyEvent;
import gryc.bank.demo.cqrs.projection.strategy.ApplyStrategyHelper;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountProjector {
    private AccountReadRepository repository;
    private ApplyStrategyHelper strategyHelper;

    @Autowired
    public AccountProjector(AccountReadRepository repository, ApplyStrategyHelper strategyHelper) {
        this.repository = repository;
        this.strategyHelper = strategyHelper;
    }

    public void project(long accountId, List<Event> events) {
        for (Event event : events) {
            strategyHelper.getStrategies().stream()
                    .filter(strategy -> strategy.isApplicable(event))
                    .findAny()
                    .orElseGet(ApplyNonMutatingEmptyEvent::new)
                    .apply(event, repository);
        }
    }

//    private void apply(long accountId, AccountCreatedEvent event) {
//        AccountCreateCommand accountCreateCommand = new AccountCreateCommand(event.getCreateAccountDto());
//        repository.addAccount(accountId, accountCreateCommand);
//    }
//
//    private void apply(AccountFundsAddedEvent event) {
//        AccountBalanceChangeCommand accountBalanceChangeCommand = new AccountBalanceChangeCommand(event.getAccountBalanceChangeDto());
//        Account accountById = repository.getAccountById(accountBalanceChangeCommand.getId());
//        accountById.changeBalance(accountBalanceChangeCommand.getBalanceChange());
//    }

//    private void apply(AccountFundsWithdrawnEvent event) {
//        AccountBalanceChangeCommand accountBalanceChangeCommand = new AccountBalanceChangeCommand(event.getAccountBalanceChangeDto());
//        Account accountById = repository.getAccountById(accountBalanceChangeCommand.getId());
//        accountById.changeBalance(accountBalanceChangeCommand.getBalanceChange());
//    }

}
