package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

public class MileageHealthAnalyzer {

    public HealthImpact analyze(Telemetry telemetry) {

        Long kilometrage = telemetry.kilometrage();

        if (kilometrage == null || kilometrage <= 50_000) {
            return HealthImpact.noImpact();
        }

        if (kilometrage <= 100_000) {
            return HealthImpact.of(-2, null);
        }

        if (kilometrage <= 150_000) {
            return HealthImpact.of(-5, null);
        }

        return HealthImpact.of(-10, null);
    }
}