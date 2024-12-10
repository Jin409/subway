package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StationTimeRequiredRepository {

    private static final List<StationTimeRequired> stationTimeRequireds = new ArrayList<>();

    public static List<StationTimeRequired> stationTimeRequireds() {
        return Collections.unmodifiableList(stationTimeRequireds);
    }

    public static void addStationTimeRequired(StationTimeRequired stationTimeRequired) {
        stationTimeRequireds.add(stationTimeRequired);
    }

    public static List<StationTimeRequired> getAllStationDistancesStartsAt(Station station) {
        return stationTimeRequireds.stream()
                .filter(stationDistance -> stationDistance.getStartStation().equals(station))
                .collect(Collectors.toList());
    }

    public static StationTimeRequired findByStartEndStation(Station startStation, Station endStation) {
        return stationTimeRequireds.stream()
                .filter(stationDistance -> stationDistance.getStartStation().equals(startStation)
                        && stationDistance.getEndStation().equals(endStation)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 경로는 존재하지 않습니다."));
    }
}
