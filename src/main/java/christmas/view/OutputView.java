package christmas.view;

import christmas.model.Reservation;

public class OutputView {

    public void printGreetingMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOrderSheet(Reservation reservation) {

        System.out.println("\n<주문 메뉴>");
        reservation.getOrderDetailsWithFormat().forEach(System.out::println);

    }

}
