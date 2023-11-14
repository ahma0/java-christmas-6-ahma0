package christmas.model;

import christmas.model.value.Beverage;
import christmas.model.value.EventDate;
import christmas.model.value.Menu;

import java.time.LocalDate;
import java.util.Optional;

public class DecemberEvent extends Event {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;
    private static final int GIVEAWAY_CRITERIA = 120_000;
    private static final int MENU_DISCOUNT = 2_023;

    private final LocalDate reservationDate;
    private final OrderDetails orderDetails;
    private final int totalPrice;

    public DecemberEvent(Reservation reservation) {
        super(EventDate.DECEMBER_EVENT);
        this.reservationDate = reservation.getReservationDate();
        this.orderDetails = reservation.getOrderDetails();
        this.totalPrice = reservation.getTotalPrice();
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

    public Optional<Menu> getGiveaway() {
        if(didTotalPriceMeetCriteria()) {
            Optional.of(GIVEAWAY);
        }

        return Optional.empty();
    }

    public Optional<Benefit> getGiveawayWithBenefitDto() {
        if (didTotalPriceMeetCriteria()) {
            return Optional.of(new Benefit("증정 이벤트", GIVEAWAY.getPrice()));
        }
        return Optional.empty();
    }

    private boolean isPossibleGetSpecialDiscount() {
        return reservationDate.getDayOfWeek().getValue() == 7 || reservationDate.getDayOfMonth() == 25;
    }

    public boolean didTotalPriceMeetCriteria() {
        return totalPrice >= GIVEAWAY_CRITERIA;
    }

    private boolean isWeekend() {
        int dayOfWeek = reservationDate.getDayOfWeek().getValue();
        return dayOfWeek == 5 || dayOfWeek == 6;
    }
}
