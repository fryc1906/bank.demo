package gryc.bank.demo.cqrs.projection.strategy;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ApplyStrategyHelper {
    public List<IApply> getStrategies(){
        return Arrays.asList(
                new ApplyAccountCreatedEvent(),
                new ApplyAccountFundsAddedEvent(),
                new ApplyAccountFundsWithdrawnEvent()
        );
    }
}
