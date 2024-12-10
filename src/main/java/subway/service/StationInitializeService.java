package subway.service;

import java.util.List;
import subway.domain.Station;
import subway.domain.StationDistance;
import subway.domain.StationDistanceRepository;
import subway.domain.StationRepository;
import subway.domain.StationTimeRequired;
import subway.domain.StationTimeRequiredRepository;

public class StationInitializeService {

    public static void saveAllStations() {
        List<String> stationNames = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        for (String stationName : stationNames) {
            StationRepository.addStation(new Station(stationName));
        }
    }

    public static void saveAllDistances() {
        StationDistanceRepository.addStationDistance(new StationDistance(2, findByName("교대역"), findByName("강남역")));
        StationDistanceRepository.addStationDistance(new StationDistance(3, findByName("교대역"), findByName("남부터미널역")));

        StationDistanceRepository.addStationDistance(new StationDistance(2, findByName("강남역"), findByName("역삼역")));
        StationDistanceRepository.addStationDistance(new StationDistance(2, findByName("강남역"), findByName("양재역")));

        StationDistanceRepository.addStationDistance(new StationDistance(6, findByName("남부터미널역"), findByName("양재역")));

        StationDistanceRepository.addStationDistance(new StationDistance(1, findByName("양재역"), findByName("매봉역")));
        StationDistanceRepository.addStationDistance(new StationDistance(10, findByName("양재역"), findByName("양재시민의숲역")));
    }

    public static void saveAllTimeRequireds() {
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

    private static Station findByName(String name) {
        return StationRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("해당 역은 존재하지 않습니다."));
    }
}
