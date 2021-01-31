package gryc.bank.demo.cqrs.repository;

import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.cqrs.domain.Account;
import gryc.bank.demo.restservice.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountReadRepository {
    private Map<Long, Account> accounts = new HashMap<>();

    public Account getAccountById(long id) {
        return Optional.ofNullable(accounts.get(id)).orElseThrow(() -> new AccountNotFoundException(id));
    }

    public void addAccount(long id, AccountCreateCommand command) {
        accounts.put(id, new Account(id, command.getCreateAccountDto()));
    }

}
