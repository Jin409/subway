package subway.domain;

public class StationDistance {
    private final int distance;
    private final Station startStation;
    private final Station endStation;

    public StationDistance(int distance, Station startStation, Station endStation) {
        this.distance = distance;
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
}
