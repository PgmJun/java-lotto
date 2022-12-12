package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import static lotto.util.Formatter.changeRewardFormat;

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

    public void printLottoRank(HashMap<LottoResult, Integer> lottoResult) {
        printEnter();
        println("당첨 통계");
        println("---");
        lottoResult.forEach((rank, count) -> {
            printRank(rank, count);
        });
    }

    private void printRank(LottoResult lottoResult, int count) {
        String reward = changeRewardFormat(lottoResult);

        if (lottoResult == LottoResult.SECOND_PRICE) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%s원) - %d개", lottoResult.getHit(), reward, count);
        } else if (lottoResult != LottoResult.SECOND_PRICE) {
            System.out.printf("%d개 일치 (%s원) - %d개", lottoResult.getHit(), reward, count);
        }
        printEnter();
    }

    public void printBenefit(Double benefit) {
        System.out.printf("총 수익률은 %.1f%%입니다.", benefit);
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

