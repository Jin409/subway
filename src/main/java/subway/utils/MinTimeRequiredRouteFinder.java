package subway.utils;

import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.Station;
import subway.domain.StationIntervalInfo;
import subway.domain.StationIntervalInfoRepository;
import subway.domain.StationRepository;

public class MinTimeRequiredRouteFinder {
    private WeightedMultigraph<String, DefaultWeightedEdge> graph;
    private DijkstraShortestPath dijkstraShortestPath;

    public List<String> find(String startStationName, String endStationName) {
        if (dijkstraShortestPath == null) {
            List<Station> stations = StationRepository.stations();
            updateStations(stations);
            updateTimeRequireds(stations);
            dijkstraShortestPath = new DijkstraShortestPath(graph);
        }
        return dijkstraShortestPath.getPath(startStationName, endStationName).getVertexList();
    }

    private void updateStations(List<Station> stations) {
        graph = new WeightedMultigraph(DefaultWeightedEdge.class);
        for (Station station : stations) {
            graph.addVertex(station.getName());
        }
    }

    private void updateTimeRequireds(List<Station> stations) {
        for (Station station : stations) {
            List<StationIntervalInfo> stationIntervalInfos = StationIntervalInfoRepository.getAllStationDistancesStartsAt(
                    station);
            for (StationIntervalInfo distance : stationIntervalInfos) {
                graph.setEdgeWeight(graph.addEdge(station.getName(), distance.getEndStation().getName()),
                        distance.getTimeRequired());
            }
        }
    }
}
