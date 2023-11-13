package christmas.model.dto;

import christmas.model.ChristmasEvent;
import christmas.model.DecemberEvent;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BenefitList {

    private final List<Benefit> benefits;

    private final int totalBenefitPrice;

    public BenefitList(ChristmasEvent christmasEvent, DecemberEvent decemberEvent, int totalPrice) {
        this.benefits = generateBenefitList(christmasEvent, decemberEvent);
        this.totalBenefitPrice = calculateTotalBenefitAmount();
    }

    public List<String> getBenefitListWithFormat() {
        return benefits.stream()
                .filter(this::isNotBenefitPriceZero)
                .map(Benefit::getBenefitWithFormat)
                .toList();
    }

    public int getTotalBenefitPrice() {
        return totalBenefitPrice;
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
