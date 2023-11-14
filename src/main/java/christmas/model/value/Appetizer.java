package christmas.model.value;

import java.util.stream.Stream;

public enum Appetizer implements Menu {

    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);

    private final String appetizerName;
    private final int price;

    Appetizer(String appetizerName, int price) {
        this.appetizerName = appetizerName;
        this.price = price;
    }

    @Override
    public String getMenuName() {
        return appetizerName;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public static Appetizer of(String appetizerName) {
        return Stream.of(values())
                .filter(appetizer -> appetizer.appetizerName.equals(appetizerName))
                .findFirst()
                .orElseThrow();
    }
}
