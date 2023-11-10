package christmas.model;

import christmas.exception.ExceptionMessage;

import java.time.LocalDate;
import java.util.Map;

import static christmas.exception.ChristmasException.causeIllegalArgumentException;

public class Reservation {

    private final LocalDate reservationDate;
    private Map<Enum<?>, Integer> orderDetails;

    public Reservation(String reservationDate) {
        validateReservationDate(reservationDate);

        this.reservationDate = LocalDate.of(
                LocalDate.now().getYear(),
                12,
                Integer.parseInt(reservationDate)
        );
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public Map<Enum<?>, Integer> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Map<Enum<?>, Integer> orderDetails) {
        this.orderDetails = orderDetails;
    }

    private void validateReservationDate(String day) {
        causeIllegalArgumentException(isBlankOrEmpty(day), ExceptionMessage.NOT_EMPTY_OR_BLANK);
        causeIllegalArgumentException(!isDigit(day), ExceptionMessage.NOT_DIGIT);
        causeIllegalArgumentException(isDateExceeded(day), ExceptionMessage.NOT_EXCEEDED);
    }

    private boolean isBlankOrEmpty(String day) {
        return day.isEmpty() || day.isBlank();
    }

    private boolean isDigit(String day) {
        return day.matches("-?\\d+");
    }

    private boolean isDateExceeded(String day) {
        int date = Integer.parseInt(day);
        return date <= 0 || date > 31;
    }


}
