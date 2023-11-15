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
    void createBenefitListByChampagne() {
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
                () -> assertThat(benefitList.getBenefits())
                        .extracting("benefitName", "benefitPrice")
                        .doesNotContain(tuple),
                () -> assertThat(benefitListWithChampagne.getBenefits())
                        .extracting("benefitName", "benefitPrice")
                        .contains(tuple)
        );
    }

    @DisplayName("1일부터 크리스마스 기간 내에만 크리스마스 디데이 할인이 적용된다.")
    @Test
    void createBenefitListByChristmasDDay() {
        //given
        Reservation reservation = new Reservation("26");
        reservation.setOrderDetails("해산물파스타-1,초코케이크-1,제로콜라-1");

        Reservation reservationWithChristmas = new Reservation("24");
        reservationWithChristmas.setOrderDetails("티본스테이크-2,양송이수프-1,제로콜라-2");

        String eventName = "크리스마스 디데이 할인";

        //when
        BenefitList benefitList = new BenefitList(
                new ChristmasEvent(reservation.getReservationDate()),
                new DecemberEvent(reservation)
        );

        BenefitList benefitListWithChristmas = new BenefitList(
                new ChristmasEvent(reservationWithChristmas.getReservationDate()),
                new DecemberEvent(reservation)
        );

        //then
        assertAll(
                () -> assertThat(benefitList.getBenefits())
                        .extracting("benefitName")
                        .doesNotContain(eventName),
                () -> assertThat(benefitListWithChristmas.getBenefits())
                        .extracting("benefitName")
                        .contains(eventName)
        );

    }

    @DisplayName("평일에 디저트 메뉴를 계산하면 평일 할인이 적용된다.")
    @Test
    void createBenefitListByDesertDiscount() {
        //given
        Reservation reservation = new Reservation("4");
        reservation.setOrderDetails("해산물파스타-1,제로콜라-1");

        Reservation reservationWithDessert = new Reservation("4");
        reservationWithDessert.setOrderDetails("티본스테이크-2,초코케이크-1,양송이수프-1,제로콜라-2");

        String eventName = "평일 할인";

        //when
        BenefitList benefitList = new BenefitList(
                new ChristmasEvent(reservation.getReservationDate()),
                new DecemberEvent(reservation)
        );

        BenefitList benefitListWithDessert = new BenefitList(
                new ChristmasEvent(reservationWithDessert.getReservationDate()),
                new DecemberEvent(reservationWithDessert)
        );

        //then
        assertAll(
                () -> assertThat(benefitList.getBenefits())
                        .extracting("benefitName")
                        .doesNotContain(eventName),
                () -> assertThat(benefitListWithDessert.getBenefits())
                        .extracting("benefitName")
                        .contains(eventName)
        );
    }

    @DisplayName("주말에 메인 메뉴를 계산하면 주말 할인이 적용된다.")
    @Test
    void createBenefitListByMainDishDiscount() {
        //given
        Reservation reservation = new Reservation("8");
        reservation.setOrderDetails("초코케이크-1,아이스크림-1,제로콜라-1");

        Reservation reservationWithMainDish = new Reservation("8");
        reservationWithMainDish.setOrderDetails("티본스테이크-2,초코케이크-1,양송이수프-1,제로콜라-2");

        String eventName = "주말 할인";

        //when
        BenefitList benefitList = new BenefitList(
                new ChristmasEvent(reservation.getReservationDate()),
                new DecemberEvent(reservation)
        );

        BenefitList benefitListWithMainDish = new BenefitList(
                new ChristmasEvent(reservationWithMainDish.getReservationDate()),
                new DecemberEvent(reservationWithMainDish)
        );

        //then
        assertAll(
                () -> assertThat(benefitList.getBenefits())
                        .extracting("benefitName")
                        .doesNotContain(eventName),
                () -> assertThat(benefitListWithMainDish.getBenefits())
                        .extracting("benefitName")
                        .contains(eventName)
        );
    }

    @DisplayName("캘린더에 별 표시가 되어있는 날짜에 결제하면 특별할인이 적용된다.")
    @Test
    void createBenefitListBySpecialDiscount() {
        //given
        Reservation reservation = new Reservation("1");
        reservation.setOrderDetails("초코케이크-1,아이스크림-1,제로콜라-1");

        Reservation reservationWithSpecial = new Reservation("3");
        reservationWithSpecial.setOrderDetails("티본스테이크-2,초코케이크-1,양송이수프-1,제로콜라-2");

        String eventName = "특별 할인";

        //when
        BenefitList benefitList = new BenefitList(
                new ChristmasEvent(reservation.getReservationDate()),
                new DecemberEvent(reservation)
        );

        BenefitList benefitListWithSpecial = new BenefitList(
                new ChristmasEvent(reservationWithSpecial.getReservationDate()),
                new DecemberEvent(reservationWithSpecial)
        );

        //then
        assertAll(
                () -> assertThat(benefitList.getBenefits())
                        .extracting("benefitName")
                        .doesNotContain(eventName),
                () -> assertThat(benefitListWithSpecial.getBenefits())
                        .extracting("benefitName")
                        .contains(eventName)
        );
    }
}
