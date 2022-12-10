package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private final OutputView outputView = new OutputView();

    public String inputPurchaseAmount() {
        outputView.println("구입금액을 입력해 주세요.");
        return read();
    }

    public String inputWinningLottoNumber() {
        outputView.println("당첨 번호를 입력해 주세요.");
        return read();
    }

    public String inputBonusNumber() {
        outputView.println("보너스 번호를 입력해 주세요.");
        return read();
    }

    private String read() {
        return Console.readLine();
    }
}
