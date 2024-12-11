package subway.service;

import subway.domain.StationRepository;

public class RouteService {
    public void validateStationName(String name) {
        if (StationRepository.findByName(name).isPresent()) {
            return;
        }
        throw new IllegalArgumentException("존재하지 않는 역 이름입니다.");
    }
}
