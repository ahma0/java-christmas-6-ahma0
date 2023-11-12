package christmas.model.value;

import java.util.HashSet;
import java.util.List;

public enum Beverage implements Menu {

    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String beverageName;
    private final int price;

    Beverage(String beverageName, int price) {
        this.beverageName = beverageName;
        this.price = price;
    }

    @Override
    public String getMenuName() {
        return beverageName;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public static boolean containAll(List<Menu> menuNames) {
        return new HashSet<>(menuNames).containsAll(List.of(values()));
    }
}
