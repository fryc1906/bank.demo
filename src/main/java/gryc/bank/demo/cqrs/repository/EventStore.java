package gryc.bank.demo.cqrs.repository;

import gryc.bank.demo.cqrs.event.Event;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EventStore {
    private final Map<Long, List<Event>> store = new HashMap<>();

    public void addEvent(Long id, Event event) {
        List<Event> events = getEventsById(id);
        if (events == null) {
            events = new ArrayList<>(List.of(event));
            store.put(id, events);
        } else {
            events.add(event);
        }
    }

    public List<Event> getEventsById(long id) {
        return Optional.ofNullable(store.get(id)).orElseGet(ArrayList::new);
    }

}
