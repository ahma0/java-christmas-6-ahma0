package christmas.model.value;

public enum MainMenu {

    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIP("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);

    private final String mainMenuName;
    private final int price;

    MainMenu(String mainMenuName, int price) {
        this.mainMenuName = mainMenuName;
        this.price = price;
    }

    public String getMainMenuName() {
        return mainMenuName;
    }

    public int getPrice() {
        return price;
    }
}
