package christmas.exception;

public class ChristmasException {

    public static void causeIllegalArgumentException(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }

}
