package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

public class BatteryHealthAnalyzer {

    public HealthImpact analyze(Telemetry telemetry) {

        Double batterie = telemetry.batterie();

        if (batterie == null) {
            return HealthImpact.noImpact();
        }

        if (batterie >= 12.4) {
            return HealthImpact.noImpact();
        }

        if (batterie >= 12.2) {
            return HealthImpact.of(-5, null);
        }

        if (batterie >= 12.0) {
            return HealthImpact.of(
                    -10,
                    new Alert(
                            "BATTERY",
                            "Tension batterie faible"
                    )
            );
        }

        return HealthImpact.of(
                -15,
                new Alert(
                        "BATTERY",
                        "Tension batterie critique"
                )
        );
    }
}
