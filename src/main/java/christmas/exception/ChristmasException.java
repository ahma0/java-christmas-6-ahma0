package christmas.exception;

public class ChristmasException {

    public static void causeIllegalArgumentException(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void causeIllegalArgumentExceptionForDate(boolean condition) {
        if (condition) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_DATE);
        }
    }

    public static void causeIllegalArgumentExceptionForOrderDetails(boolean condition) {
        if (condition) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_ORDER);
        }
    }

    public static void causeIllegalStateExceptionForOrderDetails(boolean condition) {
        if (condition) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_VALID_ORDER);
        }
    }

}
