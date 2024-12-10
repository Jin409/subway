package subway.config;

import java.util.Scanner;
import subway.SubwayController;
import subway.handler.InputHandler;
import subway.service.SubwayService;
import subway.utils.MinDistanceFinder;
import subway.view.InputView;
import subway.view.OutputView;

public class AppConfig {
    private final Scanner scanner;

    public AppConfig(Scanner scanner) {
        this.scanner = scanner;
    }

    public InputView inputView() {
        return new InputView(scanner);
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public InputHandler inputHandler() {
        return new InputHandler(inputView());
    }

    public MinDistanceFinder minDistanceFinder() {
        return new MinDistanceFinder();
    }

    public SubwayService subwayService() {
        return new SubwayService(minDistanceFinder());
    }

    public SubwayController subwayController() {
        return new SubwayController(inputHandler(), outputView(), subwayService());
    }
}
