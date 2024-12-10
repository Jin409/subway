package subway;

import subway.domain.FinishSign;
import subway.domain.Option;
import subway.dto.StationRequestDto;
import subway.handler.ErrorHandler;
import subway.handler.InputHandler;

public class SubwayController {
    private final InputHandler inputHandler;
    private final StationService stationService;

    public SubwayController(InputHandler inputHandler, StationService stationService) {
        this.inputHandler = inputHandler;
        this.stationService = stationService;
    }

    public void run() {
        while (!inputHandler.readFinishSign().meansFinish()) {
            try {
                processProgram();
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }

    private void processProgram() {
        Option option = inputHandler.readOptions();
        if (option.meansGoBack()) {
            return;
        }

        StationRequestDto stationRequestDto = inputHandler.readStations();
        stationService.validateStationName(stationRequestDto);

        if (option.meansMinTime()) {
            return;
        }

        if (option.meansLeastDistance()) {
            return;
        }

    }
}

