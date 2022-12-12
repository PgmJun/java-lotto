package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.util.Constant.ERROR_PREFIX;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = makeLottoNumbers(numbers);
        sortNumbers();
    }

    private void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberDuplication(numbers);
    }

    // TODO: 추가 기능 구현

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_PREFIX + "로또 번호는 6개여야 합니다.");
        }
    }

    private void validateNumberDuplication(List<Integer> numbers) {
        List<Integer> removeDuplcationNumbers = numbers.stream()
                .distinct()
                .collect(Collectors.toList());
        if (removeDuplcationNumbers.size() != 6) {
            throw new IllegalArgumentException(ERROR_PREFIX + "중복되는 로또 번호가 존재합니다.");
        }
    }

    private List<LottoNumber> makeLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return lottoNumbers;
    }

    private void sortNumbers() {
        Collections.sort(numbers);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public boolean numberHitCheck(LottoNumber lottoNumber) {
        return numbers.stream()
                .filter(number -> number.getNumber() == lottoNumber.getNumber())
                .findAny()
                .isPresent();
    }


}
