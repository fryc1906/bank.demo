package gryc.bank.demo.cqrs.repository;

import gryc.bank.demo.cqrs.command.AccountBalanceChangeCommand;
import gryc.bank.demo.cqrs.command.AccountCreateCommand;
import gryc.bank.demo.cqrs.domain.Account;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AccountWriteRepository {
    private Map<Long, Account> accounts = new HashMap<>();

    public Account createAccount(long id, AccountCreateCommand command) {
        Account newAccount = new Account(id, command);
        accounts.put(newAccount.getId(), newAccount);
        return newAccount;
    }

    public void changeBalance(AccountBalanceChangeCommand command) {
        Account account = accounts.get(command.getId());
        account.changeBalance(command.getBalanceChange());
    }

}
