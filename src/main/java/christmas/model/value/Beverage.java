package christmas.model.value;

public enum Beverage {

    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String beverageName;
    private final int price;

    Beverage(String beverageName, int price) {
        this.beverageName = beverageName;
        this.price = price;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public int getPrice() {
        return price;
    }
}
