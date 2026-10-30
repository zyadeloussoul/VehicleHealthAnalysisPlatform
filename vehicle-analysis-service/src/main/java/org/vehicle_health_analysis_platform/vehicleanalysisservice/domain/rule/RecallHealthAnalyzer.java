package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Recall;

import java.util.List;

public class RecallHealthAnalyzer {

    public HealthImpact analyze(List<Recall> recalls) {

        if (recalls == null || recalls.isEmpty()) {
            return HealthImpact.noImpact();
        }

        return HealthImpact.of(
                -15,
                new Alert(
                        "RECALL",
                        "Campagne de rappel active détectée"
                )
        );
    }
}