package subway.handler;

import subway.view.FinishSign;
import subway.domain.Option;
import subway.view.InputView;

public class InputHandler {
    private static final String REGEX_OF_STATION_NAME = "^[가-힣]*역$";

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
        if (!name.matches(REGEX_OF_STATION_NAME)) {
            throw new IllegalArgumentException("역 이름은 역으로 끝나야 하며, 정확하게 한글로 입력하셔야 합니다.");
        }
    }

}
