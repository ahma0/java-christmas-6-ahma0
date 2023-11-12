package christmas.model.value;

import java.time.LocalDate;

public enum EventDate {

    CHRISTMAS_EVENT(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25)),
    DecemberEvent(LocalDate.of(2023, 12, 01), LocalDate.of(2023, 12, 31));

    private final LocalDate startDate;
    private final LocalDate endDate;

    EventDate(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
