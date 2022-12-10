package lotto.controller;

import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.function.Supplier;

public abstract class Controller {

    protected InputView inputView;
    protected OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }


    public abstract void run();

    private void printError(Exception error) {
        outputView.printError(error);
    }
}
