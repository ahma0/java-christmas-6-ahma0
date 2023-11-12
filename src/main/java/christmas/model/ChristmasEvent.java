package christmas.model;

import christmas.model.value.EventDate;

import java.time.LocalDate;

public class ChristmasEvent extends Event {

    public ChristmasEvent() {
        super(EventDate.CHRISTMAS_EVENT);
    }

    public int calculateDDayDiscount(LocalDate reservationDate) {

        if (isBetweenChristmasEvent(reservationDate)) {
            int date = reservationDate.minusDays(getEventDate().getStartDate().getDayOfMonth()).getDayOfMonth();
            return 1000 + (date * 100);
        }

        return 0;
    }

    private boolean isBetweenChristmasEvent(LocalDate reservationDate) {
        return !reservationDate.isBefore(getEventDate().getStartDate())
                && !reservationDate.isAfter(getEventDate().getEndDate());
    }

}
