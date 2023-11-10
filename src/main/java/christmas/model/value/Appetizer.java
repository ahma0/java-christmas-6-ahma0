package christmas.model.value;

public enum Appetizer {

    MUSHROOM_SOUP("양송이 수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);

    private final String appetizerName;
    private final int price;

    Appetizer(String appetizerName, int price) {
        this.appetizerName = appetizerName;
        this.price = price;
    }

    public String getAppetizerName() {
        return appetizerName;
    }

    public int getPrice() {
        return price;
    }
}
