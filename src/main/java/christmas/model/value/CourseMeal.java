package christmas.model.value;

import christmas.exception.ExceptionMessage;

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

    public Menu findMenuItemByName(String itemName) {
        return menus.stream()
                .filter(menuItem -> menuItem.getMenuName().equals(itemName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessage.NOI_IN_MENU));
    }

}
