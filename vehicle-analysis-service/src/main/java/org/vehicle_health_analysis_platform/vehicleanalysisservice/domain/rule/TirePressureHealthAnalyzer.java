package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

public class TirePressureHealthAnalyzer {

    private final double recommendedPressure;

    public TirePressureHealthAnalyzer(double recommendedPressure) {
        this.recommendedPressure = recommendedPressure;
    }

    public HealthImpact analyze(Telemetry telemetry) {

        Double pression = telemetry.pressionPneu();

        if (pression == null || recommendedPressure <= 0) {
            return HealthImpact.noImpact();
        }

        double deviation =
                Math.abs(recommendedPressure - pression)
                        / recommendedPressure;

        if (deviation < 0.10) {
            return HealthImpact.noImpact();
        }

        if (deviation < 0.20) {
            return HealthImpact.of(-5, null);
        }

        if (deviation < 0.25) {
            return HealthImpact.of(-10, null);
        }

        return HealthImpact.of(
                -15,
                new Alert(
                        "TIRE_PRESSURE",
                        "Pression pneu anormale"
                )
        );
    }
}
