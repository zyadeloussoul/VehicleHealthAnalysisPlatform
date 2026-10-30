package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;

import org.junit.jupiter.api.Test;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BatteryHealthAnalyzerTest {

    private final BatteryHealthAnalyzer analyzer =
            new BatteryHealthAnalyzer();

    @Test
    void shouldHaveNoImpactWhenBatteryIsHealthy() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(12.6)
        );

        assertThat(impact.scoreImpact()).isZero();
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldApplySmallImpactWhenBatteryIsSlightlyLow() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(12.3)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-5);
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldApplyMediumImpactWhenBatteryIsLow() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(12.1)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-10);

        assertThat(impact.alert()).isNotNull();
        assertThat(impact.alert().type())
                .isEqualTo("BATTERY");
    }

    @Test
    void shouldApplyMaximumImpactWhenBatteryIsCritical() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(11.9)
        );

        assertThat(impact.scoreImpact()).isEqualTo(-15);

        assertThat(impact.alert()).isNotNull();
        assertThat(impact.alert().type())
                .isEqualTo("BATTERY");
    }

    private Telemetry createTelemetry(double batterie) {

        return new Telemetry(
                "VF1ABC12345678901",
                Instant.parse("2026-10-30T12:00:00Z"),
                40_000L,
                2.4,
                batterie,
                95.0,
                50.0,
                List.of()
        );
    }
}