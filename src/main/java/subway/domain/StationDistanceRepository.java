package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StationDistanceRepository {
    private static final List<StationDistance> stationDistances = new ArrayList<>();

    public static List<StationDistance> stationDistances() {
        return Collections.unmodifiableList(stationDistances);
    }

    public static void addStationDistance(StationDistance stationDistance) {
        stationDistances.add(stationDistance);
    }

    public static List<StationDistance> getAllStationDistancesStartsAt(Station station) {
        return stationDistances.stream()
                .filter(stationDistance -> stationDistance.getStartStation().equals(station))
                .collect(Collectors.toList());
    }

    public static StationDistance findByStartEndStation(Station startStation, Station endStation) {
        return stationDistances.stream()
                .filter(stationDistance -> stationDistance.getStartStation().equals(startStation)
                        && stationDistance.getEndStation().equals(endStation))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 경로는 존재하지 않습니다."));
    }
}
