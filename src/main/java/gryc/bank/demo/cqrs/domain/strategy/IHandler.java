package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.domain.AccountAggregate;
import gryc.bank.demo.cqrs.event.Event;

import java.util.List;

public interface IHandler<T> {
    boolean isApplicable(T t);
    List<Event> handle(AccountAggregate accountAggregate, T t);
}
