package christmas.exception;

public class ChristmasException {

    public static void CauseIllegalArgumentException(boolean condition, String message) {
        if (condition) {
            throw new IllegalArgumentException(message);
        }
    }

}
