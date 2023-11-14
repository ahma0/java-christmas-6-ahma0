package christmas.model;

import christmas.model.value.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BenefitList {

    private final List<Benefit> benefits;
    private final int totalBenefitPrice;

    private ChristmasEvent christmasEvent;
    private DecemberEvent decemberEvent;

    public BenefitList() {
        benefits = new ArrayList<>();
        totalBenefitPrice = 0;
    }

    public BenefitList(ChristmasEvent christmasEvent, DecemberEvent decemberEvent) {
        this.christmasEvent = christmasEvent;
        this.decemberEvent = decemberEvent;
        this.benefits = generateBenefitList();
        this.totalBenefitPrice = calculateTotalBenefitAmount();
    }

    public Optional<Menu> getGiveaway() {
        if (decemberEvent != null) {
            return decemberEvent.getGiveaway();
        }
        return Optional.empty();
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

    public int getTotalBenefitPriceWithoutGiveaway() {
        if (decemberEvent == null) {
            return 0;
        }

        return totalBenefitPrice - decemberEvent.getGiveaway().map(Menu::getPrice).orElse(0);
    }

    private List<Benefit> generateBenefitList() {
        return Stream.of(
                        christmasEvent.calculateDDayDiscountAndGet(),
                        decemberEvent.getWeekDiscount(),
                        decemberEvent.getSpecialDate(),
                        decemberEvent.getGiveawayWithBenefitDto()
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
