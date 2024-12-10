package subway.service;

import java.util.List;
import subway.domain.Station;
import subway.domain.StationIntervalInfo;
import subway.domain.StationIntervalInfoRepository;
import subway.domain.StationRepository;

public class StationInitializeService {

    public static void saveAllStations() {
        List<String> stationNames = List.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역");
        for (String stationName : stationNames) {
            StationRepository.addStation(new Station(stationName));
        }
    }

    public static void saveInformation() {
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(2, 3, findByName("교대역"), findByName("강남역")));
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(2, 3, findByName("강남역"), findByName("교대역")));

        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(3, 2, findByName("교대역"), findByName("남부터미널역")));
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(3, 2, findByName("남부터미널역"), findByName("교대역")));

        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(2, 3, findByName("강남역"), findByName("역삼역")));
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(2, 3, findByName("역삼역"), findByName("강남역")));

        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(2, 8, findByName("강남역"), findByName("양재역")));
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(2, 8, findByName("양재역"), findByName("강남역")));

        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(6, 5, findByName("남부터미널역"), findByName("양재역")));
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(6, 5, findByName("양재역"), findByName("남부터미널역")));

        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(1, 1, findByName("양재역"), findByName("매봉역")));
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(1, 1, findByName("매봉역"), findByName("양재역")));

        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(10, 3, findByName("양재역"), findByName("양재시민의숲역")));
        StationIntervalInfoRepository.addStationDistance(
                new StationIntervalInfo(10, 3, findByName("양재시민의숲역"), findByName("양재역")));
    }

    private static Station findByName(String name) {
        return StationRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("해당 역은 존재하지 않습니다."));
    }
}
