package subway.service;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Station;
import subway.domain.StationDistance;
import subway.domain.StationDistanceRepository;
import subway.domain.StationRepository;
import subway.domain.StationTimeRequired;
import subway.domain.StationTimeRequiredRepository;
import subway.dto.StationRequestDto;

public class SubwayService {
    public List<String> getMinDistanceRoute(StationRequestDto dto) {
        String startStationName = dto.getStartStation();
        String endStationName = dto.getEndStation();

        findByName(startStationName);
        findByName(endStationName);

        WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);

        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            graph.addVertex(station.getName());
        }

        for (Station station : stations) {
            List<StationDistance> stationDistances = StationDistanceRepository.getAllStationDistancesStartsAt(station);
            for (StationDistance stationDistance : stationDistances) {
                graph.setEdgeWeight(graph.addEdge(station.getName(), stationDistance.getEndStation().getName()),
                        stationDistance.getDistance());
            }

//            List<StationTimeRequired> stationTimeRequireds = StationTimeRequiredRepository.getAllStationDistancesStartsAt(
//                    station);
//            for (StationTimeRequired stationTimeRequired : stationTimeRequireds) {
//                graph.setEdgeWeight(graph.addEdge(station.getName(), stationTimeRequired.getEndStation().getName()),
//                        stationTimeRequired.getTimeRequired());
//            }
        }

        DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
        List<String> shortestPath = dijkstraShortestPath.getPath(startStationName, endStationName).getVertexList();

        for (
                String s : shortestPath) {
            System.out.println(s);
        }

        return null;
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
