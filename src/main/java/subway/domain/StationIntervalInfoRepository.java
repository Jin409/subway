package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StationIntervalInfoRepository {
    private static final List<StationIntervalInfo> STATION_INTERVAL_INFOS = new ArrayList<>();

    public static List<StationIntervalInfo> stationDistances() {
        return Collections.unmodifiableList(STATION_INTERVAL_INFOS);
    }

    public static void addStationDistance(StationIntervalInfo stationIntervalInfo) {
        STATION_INTERVAL_INFOS.add(stationIntervalInfo);
    }

    public static List<StationIntervalInfo> getAllStationDistancesStartsAt(Station station) {
        return STATION_INTERVAL_INFOS.stream()
                .filter(stationDistance -> stationDistance.getStartStation().equals(station))
                .collect(Collectors.toList());
    }

    public static StationIntervalInfo findByStartEndStation(Station startStation, Station endStation) {
        return STATION_INTERVAL_INFOS.stream()
                .filter(stationDistance -> stationDistance.getStartStation().equals(startStation)
                        && stationDistance.getEndStation().equals(endStation))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 경로는 존재하지 않습니다."));
    }
}
