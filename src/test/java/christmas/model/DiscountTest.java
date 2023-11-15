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

}
