package gryc.bank.demo.cqrs.domain;

import gryc.bank.demo.cqrs.command.ICommand;
import gryc.bank.demo.cqrs.domain.strategy.HandlerStrategyHelper;
import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.query.IQuery;
import gryc.bank.demo.cqrs.repository.AccountWriteRepository;
import gryc.bank.demo.cqrs.repository.EventStore;
import gryc.bank.demo.utils.AccountIdHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountAggregate {
    private final EventStore eventStore;
    private final AccountWriteRepository writeRepository;
    private final AccountIdHandler idHandler;
    private final HandlerStrategyHelper strategyHelper;

    @Autowired
    public AccountAggregate(EventStore eventStore, AccountWriteRepository writeRepository, AccountIdHandler idHandler,
                            HandlerStrategyHelper handlerStrategyHelper) {
        this.eventStore = eventStore;
        this.writeRepository = writeRepository;
        this.idHandler = idHandler;
        this.strategyHelper = handlerStrategyHelper;
    }

    public List<Event> handleCommand(ICommand command) {
        return strategyHelper.getCommandStrategies()
                .stream().filter(strategy -> strategy.isApplicable(command))
                .findAny()
                .orElseThrow(RuntimeException::new)
                .handle(this, command);
    }

    public List<Event> handleQuery(IQuery query) {
        return strategyHelper.getQueryStrategies()
                .stream().filter(strategy -> strategy.isApplicable(query))
                .findAny()
                .orElseThrow(RuntimeException::new)
                .handle(this, query);
    }

    public EventStore getEventStore() {
        return eventStore;
    }

    public AccountWriteRepository getWriteRepository() {
        return writeRepository;
    }

    public AccountIdHandler getIdHandler() {
        return idHandler;
    }
}
