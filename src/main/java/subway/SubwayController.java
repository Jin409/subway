package subway;

import subway.domain.FinishSign;
import subway.domain.Option;
import subway.handler.InputHandler;

public class SubwayController {
    private final InputHandler inputHandler;

    public SubwayController(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void run() {
        do {
            processProgram();
        } while (!inputHandler.readFinishSign().meansFinish());
    }

    private void processProgram() {
        Option option = inputHandler.readOptions();

        if (option.meansMinTime()) {
            return;
        }

        if (option.meansLeastDistance()) {
            return;
        }

        if (option.meansGoBack()) {
            return;
        }
    }
}

