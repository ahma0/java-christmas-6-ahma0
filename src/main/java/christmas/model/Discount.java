package christmas.model;

import christmas.model.value.Beverage;
import christmas.model.value.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public String getTotalPriceWithBenefit() {
        return String.format("%,d원", totalPrice - benefitList.getTotalBenefitPrice());
    }

    public String getGiveaway() {
        if (didTotalPriceMeetCriteria()) {
            return String.format("%s %,d개", GIVEAWAY.getMenuName(), 1);
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

    public int getTotalBenefitAmount() {
        int totalBenefitPrice = benefitList.getTotalBenefitPrice();

        if (isBenefitPriceZero(totalBenefitPrice)) {
            return 0;
        }

        if (didTotalPriceMeetCriteria()) {
            return totalBenefitPrice + GIVEAWAY.getPrice();
        }

        return totalBenefitPrice;
    }

    private String getPriceWithFormat(int price) {
        return String.format("-%,d원", price);
    }

    private boolean isBenefitPriceZero(int totalBenefitPrice) {
        return totalBenefitPrice == 0;
    }

    private List<String> getBenefitListWithFormat() {
        List<String> benefitListWithFormat = new ArrayList<>(benefitList.getBenefitListWithFormat());

        getGiveawayWithBenefitDto()
                .ifPresent(benefit -> benefitListWithFormat.add(benefit.getBenefitWithFormat()));

        return benefitListWithFormat;
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
