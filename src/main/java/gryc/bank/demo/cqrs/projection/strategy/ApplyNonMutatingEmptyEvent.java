package gryc.bank.demo.cqrs.projection.strategy;

import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;

public class ApplyNonMutatingEmptyEvent implements IApply {
    @Override
    public boolean isApplicable(Event event) {
        return false;
    }

    @Override
    public void apply(Event event, AccountReadRepository repository) {

    }
}
