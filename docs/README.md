# 🎄 크리스마스 이벤트

## 📌 이벤트 목표

1. **중복된 할인과 증정**을 허용, 혜택을 많이 받는다는 것을 체감할 수 있게
2. 지난 5년 중 최고의 판매 금액을 달성
3. **12월 이벤트 참여 고객의 5%**가 **내년 1월 새해 이벤트에 재참여**

<br>

## 📅 계획

### 크리스마스 디데이 할인
  - 기간: 2023.12.1 ~ 2023.12.25
  - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인
  - 총 주문 금액에서 해당 금액만큼 할인

<br>

### 12월 할인
- 기간: 2023.12.1 ~ 2023.12.31
- 평일 할인
  - 일요일 ~ 목요일
  - **디저트 메뉴**를 메뉴 1개당 2,023원 할인
- 주말 할인
  - 금요일, 토요일
  - **메인 메뉴**를 1개당 2,023원 할인
- 특별 할인
  - 이벤트 달력에 별이 있으면 총 주문 금액에서 1,000원 할인
  - 3, 10, 17, 24, 25, 31일
- 증정 이벤트
  - 할인 전 총 주문 금액이 12만 원 이상일 때, 샴페인 1개 증정

<br>

### 12월 이벤트 배지 부여

- 총 혜택 금액에 따라 다른 이벤트 배지를 부여
- 2024 새해 이벤트에서 활용할 예정
  - 5,000원 이상: 별
  - 10,000원 이상: 트리
  - 20,000원 이상: 산타


## 이벤트 주의사항

- 총 주문 금액 10,000원 이상부터 적용
- 음료만 주문 시, 주문 불가능
- 메뉴는 한 번에 최대 20개까지만 주문 가능
  - 메뉴 종류가 아닌 개수 20개

<br>

---

# 🚨 개발 요청 사항

- 총 혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
- 할인 후 예상 결제 금액 = 할인 전 총주문 금액 - 할인 금액\

- 증정 메뉴
  - 증정 이벤트에 해당하지 않는 경우, 증정 메뉴 "없음"으로 보여 주세요.
- 혜택 내역
  - 고객에게 적용된 이벤트 내역만 보여 주세요.
  - 적용된 이벤트가 하나도 없다면 혜택 내역 "없음"으로 보여 주세요.
- 이벤트 배지
  - 이벤트 배지가 부여되지 않는 경우, "없음"으로 보여 주세요.

<br>

---

# 🔖 구현할 기능 목록

## Controller

### 예약을 시작하는 기능

<br>

---

## Exception

### ExceptionMessage

- 에러 메시지는 `[ERROR]`로 시작합니다.

<br>

### ChristmasException

- boolean과 message를 받아 에러를 일으킵니다.


<br>

---

## View

### InputView

#### 식당 예상 방문 날짜를 입력하는 기능

- `12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)`를 출력한다.

#### 메뉴와 메뉴 개수를 입력 기능

- `주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)`를 출력한다.


<br>

### Output View

- outputView에는 다음과 같은 기능들이 있다.
  - `12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!`를 출력하는 기능
  - 주문 메뉴를 출력하는 기능
  - 할인 전 총주문 금액을 출력하는 기능
  - 증정 메뉴를 출력하는 기능
  - 혜택 내역을 출력하는 기능
  - 총 혜택 금액을 출력하는 기능
  - 할인 후 예상 결제 금액을 출력하는 기능
  - 12월 이벤트 배지를 출력하는 기능
- 만약 이벤트에 해당되지 않는 경우 `없읍`을 출력한다.

<br>

---

## Model

### 메뉴를 저장하는 enum

- Appetizer
- Main
- Dessert
- Beverage

<br>

### EventBadge

- 5천 원 이상: 별
- 1만 원 이상: 트리
- 2만 원 이상: 산타

<br>

### Reservation

- 식당 예상 방문 날짜를 입력받으면 검증을 거친 뒤 LocalDate 객체로 저장한다.
  - 아래의 경우 예외를 발생시킨다.
    - 숫자가 아닐 경우
    - 공백이나 빈 칸일 경우
    - 31을 초과할 경우
    - 음수일 경우

- 메뉴와 메뉴 개수를 저장한다.
  - 아래의 경우 예외를 발생시킨다.
    - 메뉴가 존재하지 않는 경우
    - 공백이거나 빈칸일 경우
    - 개수가 숫자가 아닐 경우
    - 개수가 자연수가 아닐 경우
    - 메뉴 개수의 총 합이 20을 넘을 경우

<br>

### Discount

- 혜택 내역을 출력하기 전 데이터가 있는지 없는지에 따라 포맷팅을 한다.

<br>

### Event

- EventDate 객체를 갖고있다.

<br>

### EventDate

- 이벤트의 시작 날짜와 종료 날짜를 저장한다.

<br>

### ChristmasEvent

- Event 객체를 상속받는다.
- 식당 방문 날짜를 입력받아 디데이 계산을 한다.

<br>

### DecemberEvent

- Event 객체를 상속받는다.
- 평일일 경우 디저트 메뉴를, 주말일 경우 메인 메뉴를 할인한다.
  - 메뉴 1개당 2,023원 할인
- 이벤트 달력에 별이 있으면 총 주문 금액에서 1000원을 할인한다.
- 총 구매 금액이 12만원을 넘을 경우 샴페인 1병을 증정한다.

<br>

### Benefit

- 혜택 이름과 가격을 담을 클래스이다.

<br>

### BenefitList

- Benefit의 리스트를 정의하는 클래스이다.
- 혜택 내역을 계산한다.
- 만약 가격이 1만원을 넘지 않을 경우 빈 리스트를 저장한다.