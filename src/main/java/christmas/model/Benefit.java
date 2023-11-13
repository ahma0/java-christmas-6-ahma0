package christmas.model;

public class Benefit {

    private final String benefitName;
    private final int benefitPrice;

    public Benefit(String benefitName, int benefitPrice) {
        this.benefitName = benefitName;
        this.benefitPrice = benefitPrice;
    }

    public String getBenefitName() {
        return benefitName;
    }

    public int getBenefitPrice() {
        return benefitPrice;
    }

    public String getBenefitWithFormat() {
        return String.format("%s: -%,d원", benefitName, benefitPrice);
    }
}
