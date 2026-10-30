package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;

import org.junit.jupiter.api.Test;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TirePressureHealthAnalyzerTest {

    private final TirePressureHealthAnalyzer analyzer =
            new TirePressureHealthAnalyzer(2.4);

    @Test
    void shouldHaveNoImpactWhenPressureIsNormal() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(2.4)
        );

        assertThat(impact.scoreImpact()).isZero();
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldHaveNoImpactWhenDeviationIsBelow10Percent() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(2.2)
        );

        assertThat(impact.scoreImpact()).isZero();
    }

    @Test
    void shouldApplySmallImpactWhenDeviationIsBetween10And20Percent() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(2.1)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-5);
    }

    @Test
    void shouldApplyMediumImpactWhenDeviationIsBetween20And25Percent() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(1.9)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-10);
    }

    @Test
    void shouldApplyMaximumImpactAt25PercentDeviation() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(1.8)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-15);
        assertThat(impact.alert()).isNotNull();
        assertThat(impact.alert().type()).isEqualTo("TIRE_PRESSURE");
    }

    private Telemetry createTelemetry(double pressionPneu) {

        return new Telemetry(
                "VF1ABC12345678901",
                Instant.parse("2026-10-30T12:00:00Z"),
                40_000L,
                pressionPneu,
                12.6,
                95.0,
                50.0,
                List.of()
        );
    }
}
