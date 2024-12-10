package subway.handler;

import subway.view.FinishSign;
import subway.domain.Option;
import subway.view.InputView;

public class InputHandler {
    private final InputView inputView;

    public InputHandler(InputView inputView) {
        this.inputView = inputView;
    }

    public FinishSign readFinishSign() {
        while (true) {
            try {
                String rawFinishSign = inputView.readFinishOrNot();
                return FinishSign.findBySign(rawFinishSign);
            } catch (Exception e) {
                ErrorHandler.handle(e);
            }
        }
    }

    public Option readOptions() {
        String rawOption = inputView.readOptions();
        return Option.findBySign(rawOption);
    }

    public String readStartStation() {
        String stationName = inputView.readStartStation();
        validateStationName(stationName);
        return stationName;
    }

    public String readEndStation() {
        String stationName = inputView.readEndStation();
        validateStationName(stationName);
        return stationName;
    }


    private void validateStationName(String name) {
        if (!name.contains("역")) {
            throw new IllegalArgumentException("역으로 끝나도록 입력해야 합니다.");
        }
    }

}
