package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Reservation;

public class InputView {

    public Reservation inputReservationDate() {
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String answer = Console.readLine();
                return new Reservation(answer);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
