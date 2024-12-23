package subway;

import subway.domain.Option;
import subway.dto.RouteDto;
import subway.handler.ErrorHandler;
import subway.handler.InputHandler;
import subway.service.StationInitializeService;
import subway.service.RouteService;
import subway.service.SubwayService;
import subway.view.OutputView;

public class SubwayController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final SubwayService subwayService;
    private final RouteService routeService;

    public SubwayController(InputHandler inputHandler, OutputView outputView, SubwayService subwayService,
                            RouteService routeService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.subwayService = subwayService;
        this.routeService = routeService;
    }

    public void run() {
        readyToProcess();

        while (!inputHandler.readFinishSign().meansFinish()) {
            try {
                process();
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }

    private void readyToProcess() {
        StationInitializeService.saveAllStations();
        StationInitializeService.saveInformation();
    }

    private void process() {

        String startStationName;
        String endStationName;
        Option option;

        while (true) {
            try {
                option = inputHandler.readOptions();
                if (option.meansGoBack()) {
                    return;
                }

                startStationName = inputHandler.readStartStation();
                routeService.validateStationName(startStationName);

                endStationName = inputHandler.readEndStation();
                routeService.validateStationName(endStationName);

                if (startStationName.equals(endStationName)) {
                    throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
                }
                break;
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }

        RouteDto routeDto = subwayService.getRoute(startStationName, endStationName, option);
        outputView.displayRoute(routeDto);
    }
}

