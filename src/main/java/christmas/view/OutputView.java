package christmas.view;

import christmas.model.Discount;
import christmas.model.Reservation;
import christmas.model.value.EventBadge;

public class OutputView {

    private static final String NOT_EXIST = "없음";

    public void printGreetingMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printResult(Reservation reservation, Discount discount) {
        printPreviewBenefits(reservation);
        printOrderSheet(reservation);
        printTotalPrice(discount);
        printGiveaway(discount);
        printBenefitList(discount);
        printTotalBenefitAmount(discount);
        printTotalPriceWithBenefit(discount);
        printEventBadge(discount);
    }

    private void printPreviewBenefits(Reservation reservation) {
        System.out.printf("%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!%n", reservation.getReservationDateWithFormat());
    }

    private void printOrderSheet(Reservation reservation) {
        System.out.println("\n<주문 메뉴>");
        reservation.getOrderDetailsWithFormat()
                .forEach(System.out::println);
    }

    private void printTotalPrice(Discount discount) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", discount.getTotalPrice());
    }

    private void printGiveaway(Discount discount) {
        System.out.println("\n<증정 메뉴>");
        System.out.println(discount.getGiveaway());
    }

    private void printBenefitList(Discount discount) {
        System.out.println("\n<혜택 내역>");
        discount.getBenefitListForFormat().forEach(System.out::println);
    }

    private void printTotalBenefitAmount(Discount discount) {
        System.out.println("\n<총혜택 금액>");
        System.out.println(getPriceWithFormat(discount.getTotalBenefitAmount()));
    }

    private String getPriceWithFormat(int price) {

        if (price == 0) {
            return "0원";
        }

        return String.format("-%,d원", price);
    }

    private void printTotalPriceWithBenefit(Discount discount) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(discount.getTotalPriceWithBenefit());
    }

    private void printEventBadge(Discount discount) {
        System.out.println("\n<12월 이벤트 배지>");
        EventBadge eventBadge = EventBadge.getEventBadge(discount.getTotalBenefitAmount());

        if (eventBadge != null) {
            System.out.println(eventBadge.getBadgeName());
            return;
        }

        System.out.println(NOT_EXIST);
    }

}
