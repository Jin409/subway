package subway;

import subway.domain.Option;
import subway.dto.RouteDto;
import subway.handler.ErrorHandler;
import subway.handler.InputHandler;
import subway.service.StationService;
import subway.service.SubwayService;
import subway.view.OutputView;

public class SubwayController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final SubwayService subwayService;
    private final StationService stationService;

    public SubwayController(InputHandler inputHandler, OutputView outputView, SubwayService subwayService,
                            StationService stationService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.subwayService = subwayService;
        this.stationService = stationService;
    }

    public void run() {
        readyToProcess();

        while (!inputHandler.readFinishSign().meansFinish()) {
            try {
                Option option = inputHandler.readOptions();
                if (option.meansGoBack()) {
                    return;
                }
                RouteDto routeDto = findRoute(option);
                outputView.displayRoute(routeDto);
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }

    private void readyToProcess() {
        subwayService.saveAllStations();
        subwayService.saveAllDistances();
        subwayService.saveAllTimeRequireds();
    }

    private RouteDto findRoute(Option option) {
        String startStationName = inputHandler.readStartStation();
        stationService.validateStationName(startStationName);

        String endStationName = inputHandler.readEndStation();
        stationService.validateStationName(startStationName);

        if (startStationName.equals(endStationName)) {
            throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
        }
        return subwayService.getRoute(startStationName, endStationName, option);
    }
}

