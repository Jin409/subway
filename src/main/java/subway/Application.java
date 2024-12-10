package subway;

import java.util.Scanner;
import subway.view.InputView;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        AppConfig appConfig = new AppConfig(scanner);
        SubwayController subwayController = appConfig.subwayController();
        subwayController.run();
    }
}
