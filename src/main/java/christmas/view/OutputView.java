package christmas.view;

import christmas.model.Discount;
import christmas.model.Reservation;

public class OutputView {

    private Reservation reservation;

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public void printGreetingMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printResult(Discount discount) {
        printPreviewBenefits();
        printOrderSheet();
        printTotalPrice(discount);
    }

    private void printPreviewBenefits() {
        System.out.printf("%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", reservation.getReservationDateWithFormat());
    }

    private void printOrderSheet() {
        System.out.println("\n<주문 메뉴>");
        reservation.getOrderDetailsWithFormat()
                .forEach(System.out::println);
    }

    private void printTotalPrice(Discount discount) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원", discount.getTotalPrice());
    }

}
