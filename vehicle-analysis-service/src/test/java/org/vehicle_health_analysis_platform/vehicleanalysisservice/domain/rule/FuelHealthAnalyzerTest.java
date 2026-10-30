package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.junit.jupiter.api.Test;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FuelHealthAnalyzerTest {

    private final FuelHealthAnalyzer analyzer =
            new FuelHealthAnalyzer();

    @Test
    void shouldHaveNoImpactWhenFuelLevelIsNormal() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(50.0)
        );

        assertThat(impact.scoreImpact()).isZero();
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldApplySmallImpactWhenFuelLevelIsLow() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(15.0)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-2);
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldApplyMaximumImpactWhenFuelLevelIsVeryLow() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(5.0)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-5);

        assertThat(impact.alert()).isNotNull();

        assertThat(impact.alert().type())
                .isEqualTo("LOW_FUEL");
    }

    private Telemetry createTelemetry(double carburant) {

        return new Telemetry(
                "VF1ABC12345678901",
                Instant.parse("2026-10-30T12:00:00Z"),
                40_000L,
                2.4,
                12.6,
                95.0,
                carburant,
                List.of()
        );
    }
}