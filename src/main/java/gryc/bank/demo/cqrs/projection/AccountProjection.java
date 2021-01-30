package gryc.bank.demo.cqrs.projection;

import gryc.bank.demo.cqrs.repository.EventStore;
import gryc.bank.demo.cqrs.repository.AccountReadRepository;
import gryc.bank.demo.cqrs.query.AccountBalanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

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

}
