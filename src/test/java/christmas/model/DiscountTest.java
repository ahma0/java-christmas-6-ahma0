package christmas.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountTest {

    @DisplayName("결제 금액이 1만원 이하일 땐 이벤트가 적용되지 않는다.")
    @Test
    void createDiscountByNoEvent() {
        //given
        Reservation reservation = new Reservation("3");
        reservation.setOrderDetails("제로콜라-1,아이스크림-1");

        Discount discount = new Discount(reservation);

        //when
        List<String> benefitListForFormat = discount.getBenefitListForFormat();

        //then
        assertThat(benefitListForFormat).contains("없음");
    }

    @DisplayName("할인 금액이 2만원 이상일 때 이벤트 배지로 산타를 받는다.")
    @Test
    void createDiscountBySanta() {
        //given
        Reservation reservation = new Reservation("3");
        reservation.setOrderDetails("티본스테이크-2,제로콜라-1,초코케이크-3,아이스크림-1,타파스-1");

        Discount discount = new Discount(reservation);

        //when
        String eventBadge = discount.getEventBadge();

        //then
        assertThat(eventBadge).isEqualTo("산타");
    }

    @DisplayName("할인 금액이 1만원 이상 2만원 미만일 때 이벤트 배지로 트리를 받는다.")
    @Test
    void createDiscountByTree() {
        //given
        Reservation reservation = new Reservation("3");
        reservation.setOrderDetails("티본스테이크-1,제로콜라-1,초코케이크-3,아이스크림-1,타파스-1");

        Discount discount = new Discount(reservation);

        //when
        String eventBadge = discount.getEventBadge();

        //then
        assertThat(eventBadge).isEqualTo("트리");
    }

    @DisplayName("할인 금액이 5000원 이상 1만원 미만일 때 이벤트 배지로 별를 받는다.")
    @Test
    void createDiscountByStar() {
        //given
        Reservation reservation = new Reservation("3");
        reservation.setOrderDetails("티본스테이크-1,제로콜라-1,초코케이크-3");

        Discount discount = new Discount(reservation);

        //when
        String eventBadge = discount.getEventBadge();

        //then
        assertThat(eventBadge).isEqualTo("별");
    }

    @DisplayName("할인 금액이 5000원 미만일 땐 이벤트 배지를 받지 못한다.")
    @Test
    void createDiscountByNotExist() {
        //given
        Reservation reservation = new Reservation("3");
        reservation.setOrderDetails("제로콜라-1,초코케이크-1");

        Discount discount = new Discount(reservation);

        //when
        String eventBadge = discount.getEventBadge();

        //then
        assertThat(eventBadge).isEqualTo("없음");
    }

}
