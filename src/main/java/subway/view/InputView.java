package subway.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readFinishOrNot() {
        System.out.println("## 메인 화면\n"
                + "1. 경로 조회\n"
                + "Q. 종료");
        System.out.println();
        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public String readOptions() {
        System.out.println("## 경로 기준\n"
                + "1. 최단 거리\n"
                + "2. 최소 시간\n"
                + "B. 돌아가기");
        System.out.println("\n"
                + "## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public String readStartStation() {
        System.out.println("## 출발역을 입력하세요.");
        return scanner.nextLine();
    }

    public String readEndStation() {
        System.out.println("## 도착역을 입력하세요.");
        return scanner.nextLine();
    }
}
