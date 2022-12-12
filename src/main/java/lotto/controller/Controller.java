package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public abstract class Controller {

    protected InputView inputView;
    protected OutputView outputView;
    protected LottoService lottoService;

    public Controller(LottoService lottoService, InputView inputView, OutputView outputView) {
        this.lottoService = lottoService;
        this.inputView = inputView;
        this.outputView = outputView;
    }


    public abstract void run();

    protected void printError(Exception error) {
        outputView.printError(error);
    }
}
