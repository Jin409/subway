package subway.dto;

public class StationRequestDto {
    private final String startStation;
    private final String endStation;

    public StationRequestDto(String startStation, String endStation) {
        this.startStation = startStation;
        this.endStation = endStation;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getEndStation() {
        return endStation;
    }
}
