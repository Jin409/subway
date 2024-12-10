package subway.handler;

import subway.domain.FinishSign;
import subway.view.InputView;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public FinishSign readFinishSign() {
        String rawFinishSign = inputView.readFinishOrNot();
        return FinishSign.findBySign(rawFinishSign);
    }

}
