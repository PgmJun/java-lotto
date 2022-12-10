package lotto;

import lotto.controller.LottoController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();

        try {
            LottoController lottoController = new LottoController(new InputView(), outputView);
            lottoController.run();
        } catch (IllegalArgumentException error) {
            outputView.printError(error);
        }
    }
}
