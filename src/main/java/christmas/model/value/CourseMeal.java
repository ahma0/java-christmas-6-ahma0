package christmas.model.value;

import christmas.exception.ExceptionMessage;

import java.util.Arrays;
import java.util.List;

public enum CourseMeal {

    APPETIZER(Appetizer.values()),
    MAIN_DISH(MainDish.values()),
    DESSERT(Dessert.values()),
    BEVERAGE(Beverage.values());

    private final List<Menu> menus;

    CourseMeal(Menu[] menus) {
        this.menus = List.of(menus);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public static Menu findMenuItemByName(String itemName) {
        return Arrays.stream(values())
                .flatMap(courseMeal -> courseMeal.getMenus().stream())
                .filter(menuItem -> menuItem.getMenuName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.NOT_VALID_ORDER));
    }

}
