package subway.domain;

public class StationTimeRequired {
    private final int timeRequired;
    private final Station startStation;
    private final Station endStation;

    public StationTimeRequired(int timeRequired, Station startStation, Station endStation) {
        this.timeRequired = timeRequired;
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public Station getStartStation() {
        return startStation;
    }

    public int getTimeRequired() {
        return timeRequired;
    }

    public Station getEndStation() {
        return endStation;
    }
}
