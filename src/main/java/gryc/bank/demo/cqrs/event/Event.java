package gryc.bank.demo.cqrs.event;

import java.util.Date;
import java.util.UUID;

public abstract class Event {
    private final UUID id = UUID.randomUUID();
    private final Date created = new Date();
}
