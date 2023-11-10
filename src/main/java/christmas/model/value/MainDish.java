package christmas.model.value;

public enum MainDish implements Menu {

    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIP("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);

    private final String mainDishName;
    private final int price;

    MainDish(String mainDishName, int price) {
        this.mainDishName = mainDishName;
        this.price = price;
    }

    @Override
    public String getMenuName() {
        return mainDishName;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
