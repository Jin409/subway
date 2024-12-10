package subway;

import subway.domain.Option;
import subway.dto.StationRequestDto;
import subway.handler.ErrorHandler;
import subway.handler.InputHandler;
import subway.service.SubwayService;

public class SubwayController {
    private final InputHandler inputHandler;
    private final SubwayService subwayService;

    public SubwayController(InputHandler inputHandler, SubwayService subWayService) {
        this.inputHandler = inputHandler;
        this.subwayService = subWayService;
    }

    public void run() {
        readyToProcess();

        while (!inputHandler.readFinishSign().meansFinish()) {
            try {
                processProgram();
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

    private void processProgram() {
        Option option = inputHandler.readOptions();
        if (option.meansGoBack()) {
            return;
        }

        StationRequestDto stationRequestDto = inputHandler.readStations();

        if (option.meansLeastDistance()) {
            subwayService.getMinDistanceRoute(stationRequestDto);
            return;
        }

        if (option.meansMinTime()) {
            return;
        }

    }
}

