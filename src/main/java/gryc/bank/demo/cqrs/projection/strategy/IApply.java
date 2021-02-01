package gryc.bank.demo.cqrs.projection.strategy;

import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;

public interface IApply {
    boolean isApplicable(Event event);
    void apply(Event event, AccountReadRepository repository);
}
