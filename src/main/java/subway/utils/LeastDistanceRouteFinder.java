package subway.utils;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Station;
import subway.domain.StationIntervalInfo;
import subway.domain.StationIntervalInfoRepository;
import subway.domain.StationRepository;

public class LeastDistanceRouteFinder {
    private WeightedMultigraph<String, DefaultWeightedEdge> graph;
    private DijkstraShortestPath dijkstraShortestPath;

    public List<String> find(String startStationName, String endStationName) {
        if (dijkstraShortestPath == null) {
            List<Station> stations = StationRepository.stations();
            updateStations(stations);
            updateDistances(stations);
            dijkstraShortestPath = new DijkstraShortestPath(graph);
        }
        try {
            return dijkstraShortestPath.getPath(startStationName, endStationName).getVertexList();
        } catch (Exception e) {
            throw new RuntimeException("해당 경로는 존재하지 않습니다.");
        }
    }

    private void updateStations(List<Station> stations) {
        graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Station station : stations) {
            graph.addVertex(station.getName());
        }
    }

    private void updateDistances(List<Station> stations) {
        for (Station station : stations) {
            List<StationIntervalInfo> stationIntervalInfos = StationIntervalInfoRepository.getAllStationDistancesStartsAt(station);
            for (StationIntervalInfo stationIntervalInfo : stationIntervalInfos) {
                graph.setEdgeWeight(graph.addEdge(station.getName(), stationIntervalInfo.getEndStation().getName()),
                        stationIntervalInfo.getDistance());
            }
        }
    }
}
