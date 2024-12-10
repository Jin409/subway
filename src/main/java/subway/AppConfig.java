package subway;

import java.util.Scanner;
import subway.handler.InputHandler;
import subway.view.InputView;

public class AppConfig {
    private final Scanner scanner;

    public AppConfig(Scanner scanner) {
        this.scanner = scanner;
    }

    public InputView inputView() {
        return new InputView(scanner);
    }

    public InputHandler inputHandler() {
        return new InputHandler(inputView());
    }

    public SubwayController subwayController() {
        return new SubwayController(inputHandler());
    }
}
