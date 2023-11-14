package christmas.model;

import christmas.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderDetailsTest {

    @DisplayName("주문한 메뉴의 개수가 20개가 넘으면 예외를 발생시킨다.")
    @Test
    void createOrderDetailsByOverSize() {
        assertThatThrownBy(() -> new OrderDetails("제로콜라-5,티본스테이크-10,초코케이크-9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_VALID_ORDER);
    }

    @DisplayName("주문한 메뉴가 중복될 경우 예외를 발생시킨다.")
    @Test
    void createOrderDetailsByDuplicatedMenu() {
        assertThatThrownBy(() -> new OrderDetails("티본스테이크-10,티본스테이크-10,초코케이크-9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_VALID_ORDER);
    }

    @DisplayName("주문한 메뉴가 존재하지 않을 경우 예외를 발생시킨다.")
    @Test
    void createOrderDetailsByNotExistMenu() {
        assertThatThrownBy(() -> new OrderDetails("테스트-9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_VALID_ORDER);
    }

    @DisplayName("주문한 메뉴가 전부 음료일 경우 예외를 발생시킨다.")
    @Test
    void createOrderDetailsByBeverage() {
        assertThatThrownBy(() -> new OrderDetails("제로콜라-4,레드와인-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.NOT_VALID_ORDER);
    }

}
