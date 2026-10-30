package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

public class FuelHealthAnalyzer {

    public HealthImpact analyze(Telemetry telemetry) {

        Double carburant = telemetry.carburant();

        if (carburant == null) {
            return HealthImpact.noImpact();
        }

        if (carburant > 20.0) {
            return HealthImpact.noImpact();
        }

        if (carburant >= 10.0) {
            return HealthImpact.of(-2, null);
        }

        return HealthImpact.of(
                -5,
                new Alert(
                        "LOW_FUEL",
                        "Niveau de carburant très faible"
                )
        );
    }
}