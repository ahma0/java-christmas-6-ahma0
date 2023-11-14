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
    private final static int BENEFIT_CRITERIA = 10_000;
    private static final String NOT_EXIST = "없음";

    private final int totalPrice;

    private final BenefitList benefitList;

    public Discount(Reservation reservation) {
        this.totalPrice = reservation.getTotalPrice();
        this.benefitList = generateBenefitList(reservation);
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

        if (didTotalPriceMeetCriteria()) {
            return totalBenefitPrice + GIVEAWAY.getPrice();
        }

        return totalBenefitPrice;
    }

    private BenefitList generateBenefitList(Reservation reservation) {
        LocalDate reservationDate = reservation.getReservationDate();

        if (isTotalPriceLessThen()) {
            return new BenefitList();
        }

        return new BenefitList(
                new ChristmasEvent(reservationDate),
                new DecemberEvent(reservationDate, reservation.getOrderDetails())
        );
    }

    private boolean isTotalPriceLessThen() {
        return totalPrice < BENEFIT_CRITERIA;
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
