package subway.utils;

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
            List<StationTimeRequired> stationTimeRequireds = StationTimeRequiredRepository.getAllStationDistancesStartsAt(
                    station);
            for (StationTimeRequired stationTimeRequired : stationTimeRequireds) {
                graph.setEdgeWeight(graph.addEdge(station.getName(), stationTimeRequired.getEndStation().getName()),
                        stationTimeRequired.getTimeRequired());
            }
        }
    }
}
