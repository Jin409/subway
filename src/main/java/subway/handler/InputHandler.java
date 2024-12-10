package subway.handler;

import java.util.List;
import subway.domain.FinishSign;
import subway.domain.Option;
import subway.dto.StationRequestDto;
import subway.view.InputView;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public FinishSign readFinishSign() {
        String rawFinishSign = inputView.readFinishOrNot();
        return FinishSign.findBySign(rawFinishSign);
    }

    public Option readOptions() {
        String rawOption = inputView.readOptions();
        return Option.findBySign(rawOption);
    }

    public StationRequestDto readStations() {
        String rawStartStation = inputView.readStartStation();
        String rawEndStation = inputView.readEndStation();

        if (rawStartStation.equals(rawEndStation)) {
            throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
        }

        return new StationRequestDto(rawStartStation, rawEndStation);
    }

}
