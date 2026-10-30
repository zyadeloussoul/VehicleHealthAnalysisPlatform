package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.util.List;

public class ErrorCodeHealthAnalyzer {

    public HealthImpact analyze(Telemetry telemetry) {

        List<String> codesErreur = telemetry.codesErreur();

        if (codesErreur == null || codesErreur.isEmpty()) {
            return HealthImpact.noImpact();
        }

        if (codesErreur.size() == 1) {
            return HealthImpact.of(
                    -10,
                    new Alert(
                            "DIAGNOSTIC_ERROR",
                            "Code erreur détecté : " + codesErreur.getFirst()
                    )
            );
        }

        return HealthImpact.of(
                -20,
                new Alert(
                        "DIAGNOSTIC_ERRORS",
                        "Plusieurs codes erreur détectés : " + codesErreur.size()
                )
        );
    }
}