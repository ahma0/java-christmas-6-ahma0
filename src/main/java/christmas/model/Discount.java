package christmas.model;

import christmas.model.dto.BenefitList;
import christmas.model.value.Beverage;
import christmas.model.value.Menu;

import java.time.LocalDate;
import java.util.List;

public class Discount {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;
    private static final int GIVEAWAY_CRITERIA = 120_000;
    private static final String NOT_EXIST = "없음";

    private final int totalPrice;

    private final BenefitList benefitList;

    public Discount(Reservation reservation) {
        this.totalPrice = reservation.getTotalPrice();
        LocalDate reservationDate = reservation.getReservationDate();
        benefitList = new BenefitList(
                new ChristmasEvent(reservationDate),
                new DecemberEvent(reservationDate, reservation.getOrderDetails()),
                totalPrice
        );
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
        List<String> benefitDetails = getBenefitListWithFormat();

        if (benefitDetails.isEmpty()) {
            return List.of(NOT_EXIST);
        }

        return benefitDetails;
    }

    public String getTotalBenefitAmount() {
        int totalBenefitPrice = benefitList.getTotalBenefitPrice();

        if (isBenefitPriceZero(totalBenefitPrice)) {
            return "0원";
        }
        return String.format("-%,d원", totalBenefitPrice);
    }

    private boolean isBenefitPriceZero(int totalBenefitPrice) {
        return totalBenefitPrice == 0;
    }

    private List<String> getBenefitListWithFormat() {
        return benefitList.getBenefitListWithFormat();
    }

    private boolean didTotalPriceMeetCriteria() {
        return totalPrice >= GIVEAWAY_CRITERIA;
    }

}
