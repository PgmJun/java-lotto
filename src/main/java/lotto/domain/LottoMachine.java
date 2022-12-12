package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final static int LOTTO_PRICE = 1000;

    public List<Lotto> generateLottos(Money money) {
        List<Lotto> purchaseLottos = new ArrayList<>();
        for (int i = 0; i < getLottoCount(money); i++) {
            List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            purchaseLottos.add(generateLotto(lottoNumbers));
        }

        return purchaseLottos;
    }

    private int getLottoCount(Money money) {
        return money.getAmount() / LOTTO_PRICE;
    }

    private Lotto generateLotto(List<Integer> lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

}
