package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoController extends Controller{
    private final LottoMachine lottoMachine = new LottoMachine();

    public LottoController(InputView inputView, OutputView outputView) {
        super(inputView, outputView);
    }

    @Override
    public void run() {

        Money money = payLottoPrice();
        List<Lotto> lottos = purchaseLotto(lottoMachine, money);

    }

    private List<Lotto> purchaseLotto(LottoMachine lottoMachine, Money money) {
        outputView.printLottoCount(getLottoCount(money));
        List<Lotto> lottos = lottoMachine.generateLottos(money);
        outputView.printLottoNumbers(lottos);

        return lottos;
    }

    private Money payLottoPrice() {
        String amount = repeat(inputView::inputPurchaseAmount);
        Money money = new Money(amount);
        return money;
    }

    private int getLottoCount(Money money) {
        return money.getAmount() / 1000;
    }


}
