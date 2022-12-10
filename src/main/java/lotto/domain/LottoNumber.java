package lotto.domain;

import static lotto.util.Constant.ERROR_PREFIX;

public class LottoNumber implements Comparable<LottoNumber> {
    private int number;

    public LottoNumber(int number) {
        validateLottoNumberBound(number);
        this.number = number;
    }

    public LottoNumber(String number) {
        int convertedNumber = validateLottoNumberFormat(number);
        validateLottoNumberBound(convertedNumber);
        this.number = convertedNumber;
    }

    private void validateLottoNumberBound(int number) {
        if (number >= 1 && number <= 45) {
            return;
        }
        throw new IllegalArgumentException(ERROR_PREFIX + "로또번호의 범위가 잘못되었습니다.");
    }

    private int validateLottoNumberFormat(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(ERROR_PREFIX + "로또번호가 정수형이 아닙니다.");
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return this.number - other.number;
    }

    public Integer getNumber() {
        return number;
    }
}
