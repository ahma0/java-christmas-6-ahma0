package christmas.controller;

import christmas.model.Discount;
import christmas.model.Reservation;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void startChristmasOrder() {
        outputView.printGreetingMessage();
        Reservation reservation = inputView.inputReservationDate();
        inputView.inputOrderDetails(reservation);
        outputView.printResult(reservation, new Discount(reservation));
    }
}
