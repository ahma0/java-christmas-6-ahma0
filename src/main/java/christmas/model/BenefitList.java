package christmas.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BenefitList {

    private final static int BENEFIT_CRITERIA = 10_000;

    private final List<Benefit> benefits;
    private final int totalBenefitPrice;
    private final int totalPrice;

    public BenefitList(ChristmasEvent christmasEvent, DecemberEvent decemberEvent, int totalPrice) {
        this.benefits = generateBenefitList(christmasEvent, decemberEvent);
        this.totalBenefitPrice = calculateTotalBenefitAmount();
        this.totalPrice = totalPrice;
    }

    public List<String> getBenefitListWithFormat() {
        if(!isTotalPriceLessThen()) {
            return new ArrayList<>();
        }

        return benefits.stream()
                .filter(this::isNotBenefitPriceZero)
                .map(Benefit::getBenefitWithFormat)
                .toList();
    }

    public int getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    private boolean isTotalPriceLessThen() {
        return totalPrice < BENEFIT_CRITERIA;
    }

    private List<Benefit> generateBenefitList(ChristmasEvent christmasEvent, DecemberEvent decemberEvent) {
        return Stream.of(
                        christmasEvent.calculateDDayDiscountAndGet(),
                        decemberEvent.getWeekDiscount(),
                        decemberEvent.getSpecialDate()
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private int calculateTotalBenefitAmount() {
        return benefits.stream()
                .mapToInt(Benefit::getBenefitPrice)
                .sum();
    }

    private boolean isNotBenefitPriceZero(Benefit benefit) {
        return benefit.getBenefitPrice() != 0;
    }

}
