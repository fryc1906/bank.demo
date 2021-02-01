package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.AccountBalanceCheckedEvent;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.query.AccountBalanceQuery;
import gryc.bank.demo.cqrs.query.IQuery;

import java.util.Arrays;
import java.util.List;

public class AccountBalanceQueryHandler implements IHandler<IQuery> {
    @Override
    public boolean isApplicable(IQuery query) {
        return query instanceof AccountBalanceQuery;
    }

    @Override
    public List<Event> handle(AccountAggregate accountAggregate, IQuery query) {
        AccountBalanceQuery accountBalanceQuery = (AccountBalanceQuery) query;
        AccountBalanceCheckedEvent event = new AccountBalanceCheckedEvent(accountBalanceQuery);
        accountAggregate.getEventStore().addEvent(accountBalanceQuery.getAccountId(), event);
        return Arrays.asList(event);
    }
}
