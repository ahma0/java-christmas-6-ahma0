package christmas.model;

import christmas.model.value.EventDate;

import java.time.LocalDate;
import java.util.Optional;

public class ChristmasEvent extends Event {

    private final LocalDate reservationDate;

    public ChristmasEvent(LocalDate reservationDate) {
        super(EventDate.CHRISTMAS_EVENT);
        this.reservationDate = reservationDate;
    }

    public Optional<Benefit> calculateDDayDiscountAndGet() {
        if (isBetweenChristmasEvent()) {
            return Optional.of(new Benefit("크리스마스 디데이 할인", 1000 + (reservationDate.getDayOfMonth() * 100) - 100));
        }

        return Optional.empty();
    }

    private boolean isBetweenChristmasEvent() {
        return !reservationDate.isBefore(getEventDate().getStartDate())
                && !reservationDate.isAfter(getEventDate().getEndDate());
    }

}
