package christmas.model;

import christmas.model.value.CourseMeal;
import christmas.model.value.Menu;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static christmas.exception.ChristmasException.*;

public class Reservation {

    private final LocalDate reservationDate;
    private Map<Menu, Integer> orderDetails;

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

    public Map<Menu, Integer> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orders) {
        validateReservationOrderDetails(orders);
        this.orderDetails = parseOrderDetails(orders);
    }

    private static Map<Menu, Integer> parseOrderDetails(String orders) {
        return Arrays.stream(orders.split(","))
                .map(pair -> pair.split("-"))
                .collect(Collectors.toMap(
                        keyValue -> CourseMeal.findMenuItemByName(keyValue[0]),
                        keyValue -> Integer.parseInt(keyValue[1])
                ));
    }

    private void validateReservationDate(String day) {
        causeIllegalArgumentExceptionForDate(
                isBlankOrEmpty(day) || ! isDigit(day) || isDateExceeded(day)
        );
    }

    private void validateReservationOrderDetails(String orders) {
        causeIllegalStateExceptionForOrderDetails(isBlankOrEmptyByOrderDetails(orders));
        causeIllegalArgumentExceptionForOrderDetails(isDuplicated(orders));
        validateOrderDetails(orders);
    }

    private void validateOrderDetails(String orders) {
        Arrays.stream(orders.replaceAll(" ", "").split(","))
                .map(pair -> pair.split("-"))
                .forEach(pair -> causeIllegalArgumentExceptionForOrderDetails(isDigit(pair[1]) || isZero(pair[1])));
    }

    private boolean isBlankOrEmptyByOrderDetails(String orders) {
        return Arrays.stream(orders.split(",|-"))
                .anyMatch(order -> order.isBlank() || order.isEmpty());
    }

    private boolean isBlankOrEmpty(String day) {
        return day.isEmpty() || day.isBlank();
    }

    private boolean isDuplicated(String orders) {
        List<String> collect = Arrays.stream(orders.replaceAll(" ", "").split(","))
                .map(pair -> pair.split("-")[0])
                .toList();

        return collect.stream().distinct().count() != collect.size();
    }

    private boolean isZero(String str) {
        return Integer.parseInt(str) == 0;
    }

    private boolean isDigit(String str) {
        return str.matches("-?\\d+");
    }

    private boolean isDateExceeded(String day) {
        int date = Integer.parseInt(day);
        return date <= 0 || date > 31;
    }


}
