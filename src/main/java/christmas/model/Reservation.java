package christmas.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static christmas.exception.ChristmasException.*;

public class Reservation {

    private final LocalDate reservationDate;
    private OrderDetails orderDetails;

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

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public List<String> getOrderDetailsWithFormat() {
        return orderDetails.getOrderDetailsWithFormat();
    }

    public String getReservationDateWithFormat() {
        return reservationDate.format(DateTimeFormatter.ofPattern("MM월 dd일"));
    }

    public void setOrderDetails(String orders) {
        orderDetails = new OrderDetails(orders);
    }

    public int getTotalPrice() {
        return orderDetails.calculateTotalPrice();
    }

    private void validateReservationDate(String day) {
        causeIllegalArgumentExceptionForDate(
                isBlankOrEmpty(day) || !isDigit(day) || isDateExceeded(day)
        );
    }

    private boolean isBlankOrEmpty(String str) {
        return str.isEmpty() || str.isBlank();
    }

    private boolean isDigit(String str) {
        return str.matches("-?\\d+");
    }

    private boolean isDateExceeded(String day) {
        int date = Integer.parseInt(day);
        return date <= 0 || date > 31;
    }
}
