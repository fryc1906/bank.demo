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
    private final AccountReadRepository repository;
    private final ApplyStrategyHelper strategyHelper;

    @Autowired
    public AccountProjector(AccountReadRepository repository, ApplyStrategyHelper strategyHelper) {
        this.repository = repository;
        this.strategyHelper = strategyHelper;
    }

    public void project(long accountId, List<Event> events) {
            events.forEach(event ->
                strategyHelper.getStrategies().stream()
                    .filter(strategy -> strategy.isApplicable(event))
                    .findAny()
                    .orElseGet(ApplyNonMutatingEmptyEvent::new)
                    .apply(event, repository));
    }

}
