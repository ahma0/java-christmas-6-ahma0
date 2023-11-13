package christmas.model.dto;

public class Benefit {

    private String benefitName;
    private int benefitPrice;

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
