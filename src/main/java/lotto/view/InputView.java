package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final OutputView outputView = new OutputView();

    public String inputPurchaseAmount() {
        outputView.println("구입금액을 입력해 주세요.");
        return read();
    }

    private String read() {
        return Console.readLine();
    }
}
