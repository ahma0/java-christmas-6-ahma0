package christmas.model;

import java.util.List;

public class Discount {

    private static final int BENEFIT_CRITERIA = 10_000;
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
        return String.format("%,d원", totalPrice - benefitList.getTotalBenefitPriceWithoutGiveaway());
    }

    public String getGiveaway() {
        return benefitList.getGiveaway()
                .map(menu -> String.format("%s 1개", menu.getMenuName()))
                .orElse(NOT_EXIST);
    }

    public List<String> getBenefitListForFormat() {
        List<String> benefitDetails = getBenefitListWithFormat();

        if (benefitDetails.isEmpty()) {
            return List.of(NOT_EXIST);
        }

        return benefitDetails;
    }

    public int getTotalBenefitAmount() {
        return benefitList.getTotalBenefitPrice();
    }

    private BenefitList generateBenefitList(Reservation reservation) {
        if (isTotalPriceLessThen()) {
            return new BenefitList();
        }

        return new BenefitList(
                new ChristmasEvent(reservation.getReservationDate()),
                new DecemberEvent(reservation)
        );
    }

    private boolean isTotalPriceLessThen() {
        return totalPrice < BENEFIT_CRITERIA;
    }

    private List<String> getBenefitListWithFormat() {
        return benefitList.getBenefitListWithFormat();
    }

}
