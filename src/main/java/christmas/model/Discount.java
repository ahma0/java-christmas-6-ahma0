package christmas.model;

import christmas.model.dto.Benefit;
import christmas.model.value.Beverage;
import christmas.model.value.Menu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Discount {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;
    private static final int GIVEAWAY_CRITERIA = 120_000;
    private static final String NOT_EXIST = "없음";

    private final ChristmasEvent christmasEvent;
    private final DecemberEvent decemberEvent;
    private final int totalPrice;
    private final OrderDetails orderDetails;

    private int benefit;

    public Discount(Reservation reservation) {
        this.christmasEvent = new ChristmasEvent(reservation.getReservationDate());
        this.decemberEvent = new DecemberEvent(reservation.getReservationDate());
        this.totalPrice = reservation.getTotalPrice();
        this.orderDetails = reservation.getOrderDetails();
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

    public List<String> getBenefitListForFormat() {
        return generateBenefitList().stream()
                .map(benefit -> String.format("%s: -%,d\n", benefit.getBenefitName(), benefit.getBenefitPrice()))
                .collect(Collectors.toList());
    }

    private List<Benefit> generateBenefitList() {
        return Stream.of(
                        christmasEvent.calculateDDayDiscountAndGet(),
                        decemberEvent.getWeekDiscount(orderDetails),
                        decemberEvent.getSpecialDate(),
                        getGiveawayWithBenefitDto()
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private boolean didTotalPriceMeetCriteria() {
        return totalPrice >= GIVEAWAY_CRITERIA;
    }

    private Optional<Benefit> getGiveawayWithBenefitDto() {
        if (didTotalPriceMeetCriteria()) {
            return Optional.of(new Benefit("증정 이벤트", GIVEAWAY.getPrice()));
        }
        return Optional.empty();
    }


}
