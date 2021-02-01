package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.query.IQuery;

import java.util.List;

public interface IQueryHandler {
    boolean isApplicable(IQuery query);
    List<Event> handle(AccountAggregate accountAggregate, IQuery query);
}
