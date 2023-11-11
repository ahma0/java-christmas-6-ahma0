package christmas.model.value;

public enum EventBadge {

    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badgeName;
    private final int purchaseAmount;

    EventBadge(String badgeName, int purchaseAmount) {
        this.badgeName = badgeName;
        this.purchaseAmount = purchaseAmount;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
