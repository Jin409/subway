package subway;

import subway.domain.Option;
import subway.dto.RouteDto;
import subway.dto.StationRequestDto;
import subway.handler.ErrorHandler;
import subway.handler.InputHandler;
import subway.service.SubwayService;
import subway.view.OutputView;

public class SubwayController {
    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final SubwayService subwayService;

    public SubwayController(InputHandler inputHandler, OutputView outputView, SubwayService subwayService) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.subwayService = subwayService;
    }

    public void run() {
        readyToProcess();

        while (!inputHandler.readFinishSign().meansFinish()) {
            try {
                RouteDto routeDto = processProgram();
                if (routeDto == null) {
                    return;
                }

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

    private RouteDto processProgram() {
        Option option = inputHandler.readOptions();
        if (option.meansGoBack()) {
            return null;
        }

        StationRequestDto stationRequestDto = inputHandler.readStations();

        if (option.meansLeastDistance()) {
            return subwayService.getMinDistanceRoute(stationRequestDto);
        }

        // TODO: 여기 고치기
        if (option.meansMinTime()) {
            return null;
        }
        return null;
    }
}

