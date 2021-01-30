package gryc.bank.demo.utils;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AccountIdHandler {
    AtomicLong id = new AtomicLong();

    public long getId() {
        return id.incrementAndGet();
    }
}
