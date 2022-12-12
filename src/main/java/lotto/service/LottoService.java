package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Money;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static lotto.domain.LottoResult.*;

public class LottoService {

    public LinkedHashMap<LottoResult, Integer> getLottoResult(List<Lotto> lottos, Lotto winningLotto, LottoNumber bonusNumber) {
        LinkedHashMap<LottoResult, Integer> lottoResult = LottoResult.createResultTable();

        for (Lotto lotto : lottos) {
            int hitCount = getHitCount(winningLotto, lotto);
            boolean hitBonusNumber = checkBonusNumberHit(bonusNumber, lotto);

            LottoResult rank = getLottoResult(hitCount, hitBonusNumber);
            recordRank(lottoResult, rank);
        }

        return lottoResult;
    }

    private static boolean checkBonusNumberHit(LottoNumber bonusNumber, Lotto lotto) {
        List<LottoNumber> numbers = lotto.getNumbers();
        boolean hitBonusNumber = numbers.contains(bonusNumber);
        return hitBonusNumber;
    }

    private static void recordRank(HashMap<LottoResult, Integer> lottoResult, LottoResult rank) {
        if (lottoResult.containsKey(rank)) {
            lottoResult.put(rank, lottoResult.get(rank) + 1);
        } else if (rank != null) {
            lottoResult.put(rank, 1);
        }
    }

    private LottoResult getLottoResult(int hitCount, boolean hitBonusNumber) {
        if (hitCount == 3) {
            return FIFTH_PRICE;
        } else if (hitCount == 4) {
            return FOURTH_PRICE;
        } else if (hitCount == 5) {
            return checkSecondPrice(hitBonusNumber);
        } else if (hitCount == 6) {
            return FIRST_PRICE;
        }
        return null;
    }

    private static LottoResult checkSecondPrice(boolean hitBonusNumber) {
        if (hitBonusNumber == true) {
            return SECOND_PRICE;
        }
        return THIRD_PRICE;
    }

    private int getHitCount(Lotto winningLottoNumbers, Lotto lotto) {
        int hitCount = 0;
        for (LottoNumber number : lotto.getNumbers()) {
            if(winningLottoNumbers.numberHitCheck(number)){
                hitCount++;
            }
        }

        return hitCount;
    }

    public Double calcBenefit(LinkedHashMap<LottoResult, Integer> lottoResult, Money money) {
        Double totalReward = calcTotalReward(lottoResult);
        int amount = money.getAmount();

        return (totalReward / amount) * 100;
    }

    private Double calcTotalReward(LinkedHashMap<LottoResult, Integer> lottoResult) {
        int totalReward = 0;
        for (LottoResult result : lottoResult.keySet()) {
            Integer hit = lottoResult.get(result);
            totalReward += hit * result.getReward();
        }

        return Double.valueOf(totalReward);
    }


}
