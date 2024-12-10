package subway.handler;

import java.util.regex.Pattern;
import subway.domain.FinishSign;
import subway.domain.Option;
import subway.view.InputView;

public class InputHandler {
    private static final String REGEX_OF_KOREAN = "^[가-힣]*$";

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
        if (!name.matches(REGEX_OF_KOREAN)) {
            throw new IllegalArgumentException("역의 이름은 한글이어야 합니다.");
        }

        if (!name.contains("역")) {
            throw new IllegalArgumentException("역으로 끝나도록 입력해야 합니다.");
        }
    }

}
