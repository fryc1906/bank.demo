package gryc.bank.demo.cqrs.projection;

import gryc.bank.demo.cqrs.event.Event;
import gryc.bank.demo.cqrs.query.AccountBalanceQuery;
import gryc.bank.demo.cqrs.query.AccountExistQuery;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;
import gryc.bank.demo.cqrs.repository.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class AccountProjection {
    private AccountReadRepository readRepository;
    private AccountProjector accountProjector;
    private EventStore eventStore;

    @Autowired
    public AccountProjection(AccountReadRepository readRepository, EventStore eventStore) {
        this.readRepository = readRepository;
        this.accountProjector = new AccountProjector(readRepository);
        this.eventStore = eventStore;
    }

    public BigInteger handle(AccountBalanceQuery balanceQuery) {
        accountProjector.project(balanceQuery.getAccountId(), eventStore.getEventsById(balanceQuery.getAccountId()));
        return readRepository.getAccountById(balanceQuery.getAccountId()).getBalance();
    }

    public boolean handle(AccountExistQuery accountExistQuery) {
        List<Event> eventsById = eventStore.getEventsById(accountExistQuery.getAccountId());
        accountProjector.project(accountExistQuery.getAccountId(), eventsById);
        return readRepository.getAccountById(accountExistQuery.getAccountId()) != null;
    }

}
