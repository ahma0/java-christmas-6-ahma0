package christmas.model;

import christmas.model.value.Beverage;
import christmas.model.value.MainDish;
import christmas.model.value.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BenefitListTest {

    private static final Menu GIVEAWAY = Beverage.CHAMPAGNE;

    @DisplayName("아무런 이벤트에 해당되지 않을 경우 혜택 금액은 0원이다.")
    @Test
    void createBenefitListBy() {
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
}
