package lotto.controller;

import lotto.domain.*;
import lotto.service.LottoService;
import lotto.util.Validator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LottoController extends Controller{
    private final LottoMachine lottoMachine = new LottoMachine();
    private final Validator validator = new Validator();

    public LottoController(LottoService lottoService, InputView inputView, OutputView outputView) {
        super(lottoService, inputView, outputView);
    }

    @Override
    public void run() {

        Money money = payLottoPrice();
        List<Lotto> lottos = purchaseLotto(lottoMachine, money);

        Lotto winningLotto = makeWinningLotto();
        LottoNumber bonusNumber = makeBonusNumber(winningLotto);

        LinkedHashMap<LottoResult, Integer> lottoResult = lottoService.getLottoResult(lottos, winningLotto, bonusNumber);
        outputView.printLottoRank(lottoResult);

        Double benefit = lottoService.calcBenefit(lottoResult, money);
        outputView.printBenefit(benefit);
    }

    private Lotto makeWinningLotto() {
        String winningLottoNumber = inputView.inputWinningLottoNumber();
        List<Integer> lottoNumbers = Arrays.stream(winningLottoNumber.split(","))
                .map(number -> validator.numberFormatValidator(number))
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }

    private LottoNumber makeBonusNumber(Lotto winningLotto) {
        String inputtedNumber = inputView.inputBonusNumber();
        LottoNumber bonusNumber = new LottoNumber(inputtedNumber);

        validator.bonusNumberDuplicationValidator(winningLotto,bonusNumber);

        return bonusNumber;
    }

    private List<Lotto> purchaseLotto(LottoMachine lottoMachine, Money money) {
        outputView.printLottoCount(getLottoCount(money));
        List<Lotto> lottos = lottoMachine.generateLottos(money);
        outputView.printLottoNumbers(lottos);

        return lottos;
    }

    private Money payLottoPrice() {
        String amount = inputView.inputPurchaseAmount();
        Money money = new Money(amount);
        return money;
    }

    private int getLottoCount(Money money) {
        return money.getAmount() / 1000;
    }


}
