package subway.service;

import java.util.List;
import subway.domain.Option;
import subway.domain.Station;
import subway.domain.StationDistance;
import subway.domain.StationDistanceRepository;
import subway.domain.StationRepository;
import subway.domain.StationTimeRequired;
import subway.domain.StationTimeRequiredRepository;
import subway.dto.RouteDto;
import subway.utils.LeastDistanceRouteFinder;
import subway.utils.MinTimeRequiredRouteFinder;

public class SubwayService {
    private final LeastDistanceRouteFinder leastDistanceRouteFinder;
    private final MinTimeRequiredRouteFinder minTimeRequiredRouteFinder;

    public SubwayService(LeastDistanceRouteFinder leastDistanceRouteFinder,
                         MinTimeRequiredRouteFinder minTimeRequiredRouteFinder) {
        this.leastDistanceRouteFinder = leastDistanceRouteFinder;
        this.minTimeRequiredRouteFinder = minTimeRequiredRouteFinder;
    }

    public RouteDto getRoute(String startStationName, String endStationName, Option option) {
        if (option.meansLeastDistance()) {
            List<String> route = leastDistanceRouteFinder.find(startStationName, endStationName);
            return new RouteDto(route, calculateTime(route), calculateDistance(route));
        }
        List<String> route = minTimeRequiredRouteFinder.find(startStationName, endStationName);
        return new RouteDto(route, calculateTime(route), calculateDistance(route));
    }

    private int calculateDistance(List<String> route) {
        int result = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            Station startStation = findByName(route.get(i));
            Station endStation = findByName(route.get(i + 1));

            StationDistance stationDistance = StationDistanceRepository.findByStartEndStation(startStation, endStation);
            result += stationDistance.getDistance();
        }
        return result;
    }

    private int calculateTime(List<String> route) {
        int result = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            Station startStation = findByName(route.get(i));
            Station endStation = findByName(route.get(i + 1));

            StationTimeRequired stationTimeRequired = StationTimeRequiredRepository.findByStartEndStation(startStation,
                    endStation);
            result += stationTimeRequired.getTimeRequired();
        }
        return result;
    }

    private Station findByName(String name) {
        return StationRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("해당 역은 존재하지 않습니다."));
    }
}
