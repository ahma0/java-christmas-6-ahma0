package christmas.model.value;

import java.util.HashSet;
import java.util.List;

public enum Dessert implements Menu {

    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private final String dessertName;
    private final int price;

    Dessert(String dessertName, int price) {
        this.dessertName = dessertName;
        this.price = price;
    }

    @Override
    public String getMenuName() {
        return dessertName;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public static boolean contain(Menu menu) {
        return List.of(values()).contains(menu);
    }
}
