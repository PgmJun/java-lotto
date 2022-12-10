package lotto.domain;

import static lotto.util.Constant.ERROR_PREFIX;

public class Money {
    private int amount;

    public Money(String amount) {
        int InputtedAmount = validateMoneyDataType(amount);
        validateMoneyBound(InputtedAmount);
        this.amount = InputtedAmount;
    }

    private int validateMoneyDataType(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(ERROR_PREFIX + "입력하신 금액이 정수형이 아닙니다.");
        }
    }

    private void validateMoneyBound(int amount) {
        if (amount < 0 || amount % 1000 != 0) {
            throw new IllegalArgumentException(ERROR_PREFIX + "금액의 입력값이 잘못되었습니다.");
        }
    }

    public int getAmount() {
        return this.amount;
    }
}
