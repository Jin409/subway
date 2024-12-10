package subway.view;

import subway.dto.RouteDto;

public class OutputView {
    public void displayRoute(RouteDto routeDto) {
        System.out.println("## 조회 결과");
        System.out.println("[INFO] ---");
        System.out.println("[INFO] 총 거리: " + routeDto.getDistance() + "km");
        System.out.println("[INFO] 총 소요 시간: " + routeDto.getTimeRequired() + "분");
        System.out.println("[INFO] ---");

        for (String name : routeDto.getRoute()) {
            System.out.println("[INFO] " + name);
        }
    }
}
