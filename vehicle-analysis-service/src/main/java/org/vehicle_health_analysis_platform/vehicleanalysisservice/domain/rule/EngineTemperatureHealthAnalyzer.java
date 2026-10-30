package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

public class EngineTemperatureHealthAnalyzer {

    public HealthImpact analyze(Telemetry telemetry) {

        Double temperature = telemetry.temperatureMoteur();

        if (temperature == null) {
            return HealthImpact.noImpact();
        }

        if (temperature <= 105.0) {
            return HealthImpact.noImpact();
        }

        if (temperature <= 115.0) {
            return HealthImpact.of(
                    -10,
                    new Alert(
                            "ENGINE_TEMPERATURE",
                            "Température moteur élevée"
                    )
            );
        }

        return HealthImpact.of(
                -20,
                new Alert(
                        "ENGINE_TEMPERATURE",
                        "Température moteur critique"
                )
        );
    }
}