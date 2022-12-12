package lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;

public enum LottoResult {
    FIFTH_PRICE(3, 5_000),
    FOURTH_PRICE(4, 50_000),
    THIRD_PRICE(5, 1_500_000),
    SECOND_PRICE(5, 30_000_000),
    FIRST_PRICE(6, 2_000_000_000);


    private int hit;
    private int reward;

    LottoResult(int hit, int reward) {
        this.hit = hit;
        this.reward = reward;
    }

    public int getHit() {
        return hit;
    }

    public int getReward() {
        return reward;
    }

    public static LinkedHashMap<LottoResult, Integer> createResultTable() {
        LinkedHashMap<LottoResult, Integer> resultTable = new LinkedHashMap<>();
        for (LottoResult lottoResult : Arrays.asList(values())) {
            resultTable.put(lottoResult, 0);
        }

        return resultTable;
    }

}
