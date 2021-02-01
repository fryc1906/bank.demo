package gryc.bank.demo.cqrs.domain.strategy;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class HandlerStrategyHelper {
    public List<ICommandHandler> getCommandStrategies() {
        return Arrays.asList(
                new AccountCreateUserCommandHandler(),
                new AccountBalanceChangeCommandHandler(),
                new AccountNotEnoughMoneyCommandHandler()
        );
    }

    public List<IQueryHandler> getQueryStrategies() {
        return Arrays.asList(
            new AccountBalanceQueryHandler()
        );
    }
}
