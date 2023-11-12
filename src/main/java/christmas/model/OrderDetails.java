package christmas.model;

import christmas.model.value.CourseMeal;
import christmas.model.value.Menu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static christmas.exception.ChristmasException.causeIllegalArgumentExceptionForOrderDetails;
import static christmas.exception.ChristmasException.causeIllegalStateExceptionForOrderDetails;

public class OrderDetails {

    private static final int MAX_ORDER = 20;

    private final Map<Menu, Integer> orderDetails;

    public OrderDetails(String orders) {
        validateReservationOrderDetails(orders);
        this.orderDetails = parseOrderDetails(orders);
    }

    public List<String> getOrderDetailsWithFormat() {
        return orderDetails.entrySet().stream()
                .map(entry -> String.format("%s %d개", entry.getKey().getMenuName(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static Map<Menu, Integer> parseOrderDetails(String orders) {
        return Arrays.stream(orders.split(","))
                .map(pair -> pair.split("-"))
                .collect(Collectors.toMap(
                        keyValue -> CourseMeal.findMenuItemByName(keyValue[0]),
                        keyValue -> Integer.parseInt(keyValue[1])
                ));
    }

    private void validateReservationOrderDetails(String orders) {
        causeIllegalStateExceptionForOrderDetails(isBlankOrEmptyByOrderDetails(orders));
        causeIllegalArgumentExceptionForOrderDetails(
                isDuplicated(orders)
                        || isAllBeverage(orders)
                        || hasOrderExceededMaximumQuantity(orders)
        );
        validateOrderDetails(orders);
    }

    private void validateOrderDetails(String orders) {
        Arrays.stream(orders.replaceAll(" ", "").split(","))
                .map(pair -> pair.split("-"))
                .forEach(pair -> causeIllegalArgumentExceptionForOrderDetails(!isDigit(pair[1]) || isZero(pair[1])));
    }

    private boolean isBlankOrEmptyByOrderDetails(String orders) {
        return Arrays.stream(orders.split(",|-"))
                .anyMatch(this::isBlankOrEmpty);
    }

    private boolean isBlankOrEmpty(String str) {
        return str.isEmpty() || str.isBlank();
    }

    private boolean isDuplicated(String orders) {
        List<String> collect = Arrays.stream(orders.replaceAll(" ", "").split(","))
                .map(pair -> pair.split("-")[0])
                .toList();

        return collect.size() != new HashSet<>(collect).size();
    }

    private boolean hasOrderExceededMaximumQuantity(String orders) {
        return Arrays.stream(orders.replaceAll(" ", "").split(","))
                .map(pair -> pair.split("-")[1])
                .mapToInt(Integer::parseInt)
                .sum() > MAX_ORDER;
    }

    private boolean isAllBeverage(String orders) {
        List<Menu> menuNames = Arrays.stream(orders.replaceAll(" ", "").split(","))
                .map(pair -> pair.split("-")[0])
                .map(CourseMeal::findMenuItemByName)
                .toList();

        return new HashSet<>(CourseMeal.BEVERAGE.getMenus()).containsAll(menuNames);
    }

    private boolean isZero(String str) {
        return Integer.parseInt(str) == 0;
    }

    private boolean isDigit(String str) {
        return str.matches("-?\\d+");
    }
}
