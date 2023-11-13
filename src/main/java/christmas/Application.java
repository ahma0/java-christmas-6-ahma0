package christmas;

import christmas.controller.ChristmasController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {

    private static final ChristmasController controller = new ChristmasController(new InputView(), new OutputView());

    public static void main(String[] args) {
        controller.startChristmasOrder();
    }
}
