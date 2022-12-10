package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Money;
import lotto.util.Validator;

public class InputView {
    private final Validator validator = new Validator();
    private final OutputView outputView = new OutputView();

    public String inputPurchaseAmount() {
        outputView.println("구입금액을 입력해 주세요.");
        return read();
    }

    private String read() {
        return Console.readLine();
    }
}
