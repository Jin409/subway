package subway.dto;

import java.util.List;

public class RouteDto {
    private final List<String> route;
    private final int timeRequired;
    private final int distance;

    public RouteDto(List<String> route, int timeRequired, int distance) {
        this.route = route;
        this.timeRequired = timeRequired;
        this.distance = distance;
    }

    public List<String> getRoute() {
        return route;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public int getDistance() {
        return distance;
    }
}
