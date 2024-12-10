package subway;

import subway.domain.FinishSign;
import subway.handler.InputHandler;

public class SubwayController {
    private final InputHandler inputHandler;

    public SubwayController(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void run() {
        FinishSign finishSign = inputHandler.readFinishSign();

        if (finishSign.meansFinish()) {
            return;
        }
    }
}

