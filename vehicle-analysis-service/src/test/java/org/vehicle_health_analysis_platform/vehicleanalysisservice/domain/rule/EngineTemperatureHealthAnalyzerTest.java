package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.junit.jupiter.api.Test;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EngineTemperatureHealthAnalyzerTest {

    private final EngineTemperatureHealthAnalyzer analyzer =
            new EngineTemperatureHealthAnalyzer();

    @Test
    void shouldHaveNoImpactWhenTemperatureIsNormal() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(95.0)
        );

        assertThat(impact.scoreImpact()).isZero();
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldApplyMediumImpactWhenTemperatureIsHigh() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(110.0)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-10);

        assertThat(impact.alert()).isNotNull();
        assertThat(impact.alert().type())
                .isEqualTo("ENGINE_TEMPERATURE");
    }

    @Test
    void shouldApplyMaximumImpactWhenTemperatureIsCritical() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(120.0)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-20);

        assertThat(impact.alert()).isNotNull();
        assertThat(impact.alert().type())
                .isEqualTo("ENGINE_TEMPERATURE");
    }

    private Telemetry createTelemetry(double temperatureMoteur) {

        return new Telemetry(
                "VF1ABC12345678901",
                Instant.parse("2026-10-30T12:00:00Z"),
                40_000L,
                2.4,
                12.6,
                temperatureMoteur,
                50.0,
                List.of()
        );
    }
}
