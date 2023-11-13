package christmas.model;

import christmas.model.value.EventDate;

import java.time.LocalDate;
import java.util.Optional;

public class DecemberEvent extends Event {

    private static final int MENU_DISCOUNT = 2023;

    private final LocalDate reservationDate;
    private final OrderDetails orderDetails;

    public DecemberEvent(LocalDate reservationDate, OrderDetails orderDetails) {
        super(EventDate.DECEMBER_EVENT);
        this.reservationDate = reservationDate;
        this.orderDetails = orderDetails;
    }

    public Optional<Benefit> getSpecialDate() {
        if (isPossibleGetSpecialDiscount()) {
            return Optional.of(new Benefit("특별 할인", 1000));
        }
        return Optional.empty();
    }

    public Optional<Benefit> getWeekDiscount() {
        if (isWeekend()) {
            return Optional.of(new Benefit("주말 할인", (orderDetails.getMainDishCount() * MENU_DISCOUNT)));
        }

        return Optional.of(new Benefit("평일 할인", (orderDetails.getDessertCount() * MENU_DISCOUNT)));
    }

    private boolean isPossibleGetSpecialDiscount() {
        return reservationDate.getDayOfWeek().getValue() == 7 || reservationDate.getDayOfMonth() == 25;
    }

    private boolean isWeekend() {
        int dayOfWeek = reservationDate.getDayOfWeek().getValue();
        return dayOfWeek == 5 || dayOfWeek == 6;
    }
}
