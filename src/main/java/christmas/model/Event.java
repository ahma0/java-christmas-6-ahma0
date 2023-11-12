package christmas.model;

import christmas.model.value.EventDate;

public abstract class Event {

    private EventDate eventDate;

    public Event(EventDate eventDate) {
        this.eventDate = eventDate;
    }

    public EventDate getEventDate() {
        return eventDate;
    }
}
