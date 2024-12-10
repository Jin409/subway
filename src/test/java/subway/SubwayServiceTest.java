package subway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.Test;
import subway.domain.Option;
import subway.dto.RouteDto;
import subway.service.StationInitializeService;
import subway.service.SubwayService;
import subway.utils.LeastDistanceRouteFinder;
import subway.utils.MinTimeRequiredRouteFinder;

public class SubwayServiceTest {
    @Test
    void 옯바른_경로를_반환한다() {
        // given
        StationInitializeService.saveAllStations();
        StationInitializeService.saveInformation();

        SubwayService subwayService = new SubwayService(new LeastDistanceRouteFinder(),
                new MinTimeRequiredRouteFinder());

        // when
        RouteDto route = subwayService.getRoute("남부터미널역", "양재시민의숲역", Option.LEAST_DISTANCE);

        // then
        assertAll(() -> assertThat(route.getRoute()).isEqualTo(List.of("남부터미널역", "양재역", "양재시민의숲역")),
                () -> assertThat(route.getDistance()).isEqualTo(16),
                () -> assertThat(route.getTimeRequired()).isEqualTo(8));
    }

    @Test
    void 양방향의_모든_경로가_가능한지_확인한다() {
        // given
        StationInitializeService.saveAllStations();
        StationInitializeService.saveInformation();

        SubwayService subwayService = new SubwayService(new LeastDistanceRouteFinder(),
                new MinTimeRequiredRouteFinder());

        // when
        RouteDto route = subwayService.getRoute("양재시민의숲역", "남부터미널역", Option.LEAST_DISTANCE);

        // then
        assertAll(() -> assertThat(route.getRoute()).isEqualTo(List.of("양재시민의숲역", "양재역", "남부터미널역")),
                () -> assertThat(route.getDistance()).isEqualTo(16),
                () -> assertThat(route.getTimeRequired()).isEqualTo(8));
    }
}
