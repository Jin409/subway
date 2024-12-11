package subway.domain;

public class StationIntervalInfo {
    private final int distance;
    private final int timeRequired;
    private final Station startStation;
    private final Station endStation;

    public StationIntervalInfo(int distance, int timeRequired, Station startStation, Station endStation) {
        this.distance = distance;
        this.timeRequired = timeRequired;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public int getDistance() {
        return distance;
    }

    public Station getStartStation() {
        return startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public int getTimeRequired() {
        return timeRequired;
    }
}
