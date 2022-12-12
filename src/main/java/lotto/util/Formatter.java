package lotto.util;

import lotto.domain.LottoResult;

import java.text.DecimalFormat;

public class Formatter {

    public static String changeRewardFormat(LottoResult lottoResult) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        String reward = formatter.format(lottoResult.getReward());
        return reward;
    }
}
