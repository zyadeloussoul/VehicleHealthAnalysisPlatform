package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MileageHealthAnalyzerTest {

    private final MileageHealthAnalyzer analyzer =
            new MileageHealthAnalyzer();

    @ParameterizedTest
    @CsvSource({
            "40000, 0",
            "50000, 0",
            "75000, -2",
            "100000, -2",
            "120000, -5",
            "150000, -5",
            "180000, -10"
    })
    void shouldCalculateCorrectImpact(
            Long kilometrage,
            int expectedImpact
    ) {

        Telemetry telemetry = new Telemetry(
                "VF1ABC12345678901",
                Instant.now(),
                kilometrage,
                2.4,
                12.6,
                95.0,
                50.0,
                List.of()
        );

        HealthImpact impact = analyzer.analyze(telemetry);

        assertThat(impact.scoreImpact())
                .isEqualTo(expectedImpact);
    }
}