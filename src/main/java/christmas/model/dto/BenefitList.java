package christmas.model.dto;

import christmas.model.ChristmasEvent;
import christmas.model.DecemberEvent;
import christmas.model.value.Beverage;
import christmas.model.value.Menu;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BenefitList {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;
    private static final int GIVEAWAY_CRITERIA = 120_000;

    private final List<Benefit> benefits;
    private final int totalPrice;

    private final int totalBenefitPrice;

    public BenefitList(ChristmasEvent christmasEvent, DecemberEvent decemberEvent, int totalPrice) {
        this.totalPrice = totalPrice;
        this.benefits = generateBenefitList(christmasEvent, decemberEvent);
        this.totalBenefitPrice = calculateTotalBenefitAmount();
    }

    public List<String> getBenefitListWithFormat() {
        return benefits.stream()
                .filter(this::isNotBenefitPriceZero)
                .map(benefit -> String.format("%s: -%,d원", benefit.getBenefitName(), benefit.getBenefitPrice()))
                .toList();
    }

    public int getTotalBenefitPrice() {
        return totalBenefitPrice;
    }

    private List<Benefit> generateBenefitList(ChristmasEvent christmasEvent, DecemberEvent decemberEvent) {
        return Stream.of(
                        christmasEvent.calculateDDayDiscountAndGet(),
                        decemberEvent.getWeekDiscount(),
                        decemberEvent.getSpecialDate(),
                        getGiveawayWithBenefitDto()
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
