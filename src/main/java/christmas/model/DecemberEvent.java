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
        int benefitPrice = calculateBenefitPrice();

        if(!isBenefitPriceZero(benefitPrice)) {
            return Optional.of(new Benefit(getWeek(), benefitPrice));
        }
        return Optional.empty();
    }

    public Optional<Menu> getGiveaway() {
        if (didTotalPriceMeetCriteria()) {
            return Optional.of(GIVEAWAY);
        }

        return Optional.empty();
    }

    public Optional<Benefit> getGiveawayWithBenefitDto() {
        if (didTotalPriceMeetCriteria()) {
            return Optional.of(new Benefit("증정 이벤트", GIVEAWAY.getPrice()));
        }
        return Optional.empty();
    }

    private String getWeek() {
        if (isWeekend()) {
            return "주말 할인";
        }
        return "평일 할인";
    }

    private int calculateBenefitPrice() {
        if(isWeekend()) {
            return orderDetails.getMainDishCount() * MENU_DISCOUNT;
        }
        return orderDetails.getDessertCount() * MENU_DISCOUNT;
    }

    private boolean isPossibleGetSpecialDiscount() {
        return reservationDate.getDayOfWeek().getValue() == 7 || reservationDate.getDayOfMonth() == 25;
    }

    private boolean isBenefitPriceZero(int benefitPrice) {
        return benefitPrice == 0;
    }

    public boolean didTotalPriceMeetCriteria() {
        return totalPrice >= GIVEAWAY_CRITERIA;
    }

    private boolean isWeekend() {
        int dayOfWeek = reservationDate.getDayOfWeek().getValue();
        return dayOfWeek == 5 || dayOfWeek == 6;
    }
}
