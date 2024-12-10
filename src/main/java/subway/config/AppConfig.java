package subway.config;

import java.util.Scanner;
import subway.StationService;
import subway.SubwayController;
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

    public StationService stationService() {
        return new StationService();
    }

    public SubwayController subwayController() {
        return new SubwayController(inputHandler(), stationService());
    }
}
