package subway.config;

import java.util.Scanner;
import subway.SubwayController;
import subway.handler.InputHandler;
import subway.service.StationService;
import subway.service.SubwayService;
import subway.utils.LeastDistanceRouteFinder;
import subway.utils.MinTimeRequiredRouteFinder;
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

    public LeastDistanceRouteFinder leastDistanceRouteFinder() {
        return new LeastDistanceRouteFinder();
    }

    public MinTimeRequiredRouteFinder minTimeRequiredRouteFinder() {
        return new MinTimeRequiredRouteFinder();
    }

    public SubwayService subwayService() {
        return new SubwayService(leastDistanceRouteFinder(), minTimeRequiredRouteFinder());
    }

    public StationService stationService() {
        return new StationService();
    }

    public SubwayController subwayController() {
        return new SubwayController(inputHandler(), outputView(), subwayService(), stationService());
    }
}
