package subway;

import subway.domain.Station;
import subway.domain.StationRepository;
import subway.dto.StationRequestDto;

public class StationService {

    public void validateStationName(StationRequestDto dto) {
        findByName(dto.getStartStation());
        findByName(dto.getEndStation());
    }

    private Station findByName(String name) {
        return StationRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("해당 역은 존재하지 않습니다."));
    }
}
