package christmas.model;

import christmas.model.value.Beverage;
import christmas.model.value.Menu;

public class Discount {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;

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
}
