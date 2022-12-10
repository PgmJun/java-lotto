package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.List;
import java.util.StringJoiner;

public class OutputView {

    public void printLottoNumbers(List<Lotto> lottos) {
        lottos.forEach(lotto -> {
            StringJoiner joiner = new StringJoiner(", ", "[", "]");
            for (LottoNumber number : lotto.getNumbers()) {
                joiner.add(number.getNumber().toString());
            }
            println(joiner.toString());
        });

        printEnter();
    }

    public void printLottoCount(int lottoCount) {
        printEnter();
        println(lottoCount + "개를 구매했습니다.");
    }

    public void printError(Exception error) {
        System.out.println(error.getMessage());
    }

    public void print(String message) {
        System.out.print(message);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void printEnter() {
        System.out.println();
    }

}

