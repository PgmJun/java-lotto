package lotto.util;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.List;

import static lotto.util.Constant.ERROR_PREFIX;

public class Validator {

    public Integer numberFormatValidator(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException(ERROR_PREFIX + "로또 번호가 정수형이 아닙니다.");
        }
    }

    public void bonusNumberDuplicationValidator(Lotto winningLotto, LottoNumber bonusNumber) {
        List<LottoNumber> winningLottoNumbers = winningLotto.getNumbers();
        boolean isDuplicated = winningLottoNumbers.stream()
                .filter(LottoNumber ->
                        LottoNumber.getNumber().equals(bonusNumber.getNumber()))
                .findAny()
                .isPresent();

        if(isDuplicated == true) {
            throw new IllegalArgumentException(ERROR_PREFIX + "보너스 번호가 당첨 번호에 포함되어 있습니다.");
        }
    }
}
