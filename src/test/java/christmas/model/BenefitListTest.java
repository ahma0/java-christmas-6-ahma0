package christmas.model;

import christmas.model.value.Beverage;
import christmas.model.value.Menu;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BenefitListTest {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;

    @DisplayName("아무런 이벤트에 해당되지 않을 경우 혜택 금액은 0원이다.")
    @Test
    void createBenefitListByNoneEvent() {
        //given
        Reservation reservation = new Reservation("26");
        reservation.setOrderDetails("해산물파스타-1");

        //when
        BenefitList benefitList = new BenefitList(
                new ChristmasEvent(reservation.getReservationDate()),
                new DecemberEvent(reservation)
        );

        //then
        assertAll(
                () -> assertThat(benefitList.getTotalBenefitPrice()).isEqualTo(0),
                () -> assertThat(benefitList.getTotalBenefitPriceWithoutGiveaway()).isEqualTo(0),
                () -> assertThat(benefitList.getBenefits()).isEmpty()
        );
    }

    @DisplayName("혜택 금액이 12만원을 넘는 경우에만 샴페인을 증정한다.")
    @Test
    void createBenefitListByNoneChampagne() {
        //given
        Reservation reservation = new Reservation("3");
        reservation.setOrderDetails("해산물파스타-1,제로콜라-1");

        Reservation reservationWithChampagne = new Reservation("3");
        reservationWithChampagne.setOrderDetails("티본스테이크-2,양송이수프-1,제로콜라-2");

        Tuple tuple = tuple("증정 이벤트", GIVEAWAY.getPrice());

        //when
        BenefitList benefitList = new BenefitList(
                new ChristmasEvent(reservation.getReservationDate()),
                new DecemberEvent(reservation)
        );

        BenefitList benefitListWithChampagne = new BenefitList(
                new ChristmasEvent(reservationWithChampagne.getReservationDate()),
                new DecemberEvent(reservationWithChampagne)
        );

        //then
        assertAll(
                () -> assertThat(benefitList.getTotalBenefitPrice()).isNotEqualTo(0),
                () -> assertThat(benefitList.getTotalBenefitPriceWithoutGiveaway()).isNotEqualTo(0),
                () -> assertThat(benefitList.getBenefits())
                        .extracting("benefitName", "benefitPrice")
                        .doesNotContain(tuple),
                () -> assertThat(benefitListWithChampagne.getBenefits())
                        .extracting("benefitName", "benefitPrice")
                        .contains(tuple)
        );
    }
}
