package christmas.model;

import christmas.model.value.Beverage;
import christmas.model.value.Menu;

public class Discount {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;
    private static final int GIVEAWAY_CRITERIA = 120_000;
    private static final String NOT_EXIST = "없음";

    private final ChristmasEvent christmasEvent;
    private final DecemberEvent decemberEvent;
    private final int totalPrice;

    private int benefit;

    public Discount(Reservation reservation) {
        this.christmasEvent = new ChristmasEvent();
        this.decemberEvent = new DecemberEvent();
        this.totalPrice = reservation.getTotalPrice();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getGiveaway() {
        if (didTotalPriceMeetCriteria()) {
            return String.format("%s %d개", GIVEAWAY.getMenuName(), 1);
        }
        return NOT_EXIST;
    }

    private boolean didTotalPriceMeetCriteria() {
        return totalPrice >= GIVEAWAY_CRITERIA;
    }
}
