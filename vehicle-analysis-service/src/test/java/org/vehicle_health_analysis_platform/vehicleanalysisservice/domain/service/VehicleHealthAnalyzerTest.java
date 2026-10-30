package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.service;

import org.junit.jupiter.api.Test;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service.VehicleHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Recall;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.BatteryHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.EngineTemperatureHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.ErrorCodeHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.FuelHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.MileageHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.RecallHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.TirePressureHealthAnalyzer;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VehicleHealthAnalyzerTest {

    private final VehicleHealthAnalyzer analyzer =
            new VehicleHealthAnalyzer(
                    new MileageHealthAnalyzer(),
                    new TirePressureHealthAnalyzer(2.4),
                    new BatteryHealthAnalyzer(),
                    new EngineTemperatureHealthAnalyzer(),
                    new FuelHealthAnalyzer(),
                    new ErrorCodeHealthAnalyzer(),
                    new RecallHealthAnalyzer()
            );

    @Test
    void shouldCalculateGlobalVehicleHealthReport() {

        Telemetry telemetry = new Telemetry(
                "VF1ABC12345678901",
                Instant.parse("2026-10-30T12:00:00Z"),
                120_000L,
                2.4,
                12.1,
                110.0,
                5.0,
                List.of("P0300")
        );

        List<Recall> recalls = List.of(
                new Recall(
                        "RCL-001",
                        "BRAKING_SYSTEM",
                        "Brake system recall"
                )
        );

        HealthReport report =
                analyzer.analyze(telemetry, recalls);

        assertThat(report.vin())
                .isEqualTo("VF1ABC12345678901");

        assertThat(report.healthScore())
                .isEqualTo(45);

        assertThat(report.alerts())
                .hasSize(5);

        assertThat(report.alerts())
                .extracting(alert -> alert.type())
                .containsExactlyInAnyOrder(
                        "BATTERY",
                        "ENGINE_TEMPERATURE",
                        "LOW_FUEL",
                        "DIAGNOSTIC_ERROR",
                        "RECALL"
                );
    }
}
