package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;
import org.junit.jupiter.api.Test;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorCodeHealthAnalyzerTest {

    private final ErrorCodeHealthAnalyzer analyzer =
            new ErrorCodeHealthAnalyzer();

    @Test
    void shouldHaveNoImpactWhenThereIsNoErrorCode() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(List.of())
        );

        assertThat(impact.scoreImpact()).isZero();
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldApplyImpactWhenOneErrorCodeIsDetected() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(List.of("P0300"))
        );

        assertThat(impact.scoreImpact()).isEqualTo(-10);

        assertThat(impact.alert()).isNotNull();
        assertThat(impact.alert().type())
                .isEqualTo("DIAGNOSTIC_ERROR");
    }

    @Test
    void shouldApplyMaximumImpactWhenMultipleErrorCodesAreDetected() {

        HealthImpact impact = analyzer.analyze(
                createTelemetry(
                        List.of("P0300", "P0420")
                )
        );

        assertThat(impact.scoreImpact()).isEqualTo(-20);

        assertThat(impact.alert()).isNotNull();
        assertThat(impact.alert().type())
                .isEqualTo("DIAGNOSTIC_ERRORS");
    }

    private Telemetry createTelemetry(
            List<String> codesErreur
    ) {

        return new Telemetry(
                "VF1ABC12345678901",
                Instant.parse("2026-10-30T12:00:00Z"),
                40_000L,
                2.4,
                12.6,
                95.0,
                50.0,
                codesErreur
        );
    }
}