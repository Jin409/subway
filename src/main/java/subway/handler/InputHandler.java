package subway.handler;

import subway.domain.FinishSign;
import subway.domain.Option;
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

    public Option readOptions() {
        String rawOption = inputView.readOptions();
        return Option.findBySign(rawOption);
    }

}
