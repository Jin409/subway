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
import subway.dto.StationRequestDto;
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

    public RouteDto getRoute(StationRequestDto dto, Option option) {
        String startStationName = dto.getStartStation();
        String endStationName = dto.getEndStation();

        findByName(startStationName);
        findByName(endStationName);

        if (option.meansLeastDistance()) {
            List<String> route = leastDistanceRouteFinder.find(startStationName, endStationName);
            return new RouteDto(route, calculateTime(route), calculateDistance(route));
        }
        List<String> route = minTimeRequiredRouteFinder.find(startStationName, endStationName);
        return new RouteDto(route, calculateTime(route), calculateDistance(route));
    }

    public RouteDto getMinTimeRoute(StationRequestDto dto) {
        String startStationName = dto.getStartStation();
        String endStationName = dto.getEndStation();

        findByName(startStationName);
        findByName(endStationName);

        List<String> route = leastDistanceRouteFinder.find(startStationName, endStationName);
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

    public void saveAllStations() {
        List<String> stationNames = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        for (String stationName : stationNames) {
            StationRepository.addStation(new Station(stationName));
        }
    }

    public void saveAllDistances() {
        StationDistanceRepository.addStationDistance(new StationDistance(2, findByName("교대역"), findByName("강남역")));
        StationDistanceRepository.addStationDistance(new StationDistance(3, findByName("교대역"), findByName("남부터미널역")));

        StationDistanceRepository.addStationDistance(new StationDistance(2, findByName("강남역"), findByName("역삼역")));
        StationDistanceRepository.addStationDistance(new StationDistance(2, findByName("강남역"), findByName("양재역")));

        StationDistanceRepository.addStationDistance(new StationDistance(6, findByName("남부터미널역"), findByName("양재역")));

        StationDistanceRepository.addStationDistance(new StationDistance(1, findByName("양재역"), findByName("매봉역")));
        StationDistanceRepository.addStationDistance(new StationDistance(10, findByName("양재역"), findByName("양재시민의숲역")));
    }

    public void saveAllTimeRequireds() {
        StationTimeRequiredRepository.addStationTimeRequired(
                new StationTimeRequired(3, findByName("교대역"), findByName("강남역")));
        StationTimeRequiredRepository.addStationTimeRequired(
                new StationTimeRequired(2, findByName("교대역"), findByName("남부터미널역")));

        StationTimeRequiredRepository.addStationTimeRequired(
                new StationTimeRequired(3, findByName("강남역"), findByName("역삼역")));
        StationTimeRequiredRepository.addStationTimeRequired(
                new StationTimeRequired(8, findByName("강남역"), findByName("양재역")));

        StationTimeRequiredRepository.addStationTimeRequired(
                new StationTimeRequired(5, findByName("남부터미널역"), findByName("양재역")));

        StationTimeRequiredRepository.addStationTimeRequired(
                new StationTimeRequired(1, findByName("양재역"), findByName("매봉역")));
        StationTimeRequiredRepository.addStationTimeRequired(
                new StationTimeRequired(3, findByName("양재역"), findByName("양재시민의숲역")));
    }
}
