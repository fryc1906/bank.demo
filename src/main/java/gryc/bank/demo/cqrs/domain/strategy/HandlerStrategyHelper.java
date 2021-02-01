package gryc.bank.demo.cqrs.domain.strategy;

import gryc.bank.demo.cqrs.command.ICommand;
import gryc.bank.demo.cqrs.query.IQuery;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class HandlerStrategyHelper {
    public List<IHandler<ICommand>> getCommandStrategies() {
        return Arrays.asList(
                new AccountCreateUserCommandHandler(),
                new AccountBalanceChangeCommandHandler(),
                new AccountNotEnoughMoneyCommandHandler()
        );
    }

    public List<IHandler<IQuery>> getQueryStrategies() {
        return Arrays.asList(
            new AccountBalanceQueryHandler()
        );
    }
}
