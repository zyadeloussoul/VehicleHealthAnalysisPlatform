package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service;



import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.*;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class VehicleHealthAnalyzer {

    private final MileageHealthAnalyzer mileageAnalyzer;
    private final TirePressureHealthAnalyzer tirePressureAnalyzer;
    private final BatteryHealthAnalyzer batteryAnalyzer;
    private final EngineTemperatureHealthAnalyzer engineTemperatureAnalyzer;
    private final FuelHealthAnalyzer fuelAnalyzer;
    private final ErrorCodeHealthAnalyzer errorCodeAnalyzer;
    private final RecallHealthAnalyzer recallAnalyzer;

    public VehicleHealthAnalyzer(
            MileageHealthAnalyzer mileageAnalyzer,
            TirePressureHealthAnalyzer tirePressureAnalyzer,
            BatteryHealthAnalyzer batteryAnalyzer,
            EngineTemperatureHealthAnalyzer engineTemperatureAnalyzer,
            FuelHealthAnalyzer fuelAnalyzer,
            ErrorCodeHealthAnalyzer errorCodeAnalyzer,
            RecallHealthAnalyzer recallAnalyzer
    ) {
        this.mileageAnalyzer = mileageAnalyzer;
        this.tirePressureAnalyzer = tirePressureAnalyzer;
        this.batteryAnalyzer = batteryAnalyzer;
        this.engineTemperatureAnalyzer = engineTemperatureAnalyzer;
        this.fuelAnalyzer = fuelAnalyzer;
        this.errorCodeAnalyzer = errorCodeAnalyzer;
        this.recallAnalyzer = recallAnalyzer;
    }

    public HealthReport analyze(
            Telemetry telemetry,
            List<Recall> recalls
    ) {

        List<HealthImpact> impacts = List.of(
                mileageAnalyzer.analyze(telemetry),
                tirePressureAnalyzer.analyze(telemetry),
                batteryAnalyzer.analyze(telemetry),
                engineTemperatureAnalyzer.analyze(telemetry),
                fuelAnalyzer.analyze(telemetry),
                errorCodeAnalyzer.analyze(telemetry),
                recallAnalyzer.analyze(recalls)
        );

        HealthScore healthScore = new HealthScore();
        List<Alert> alerts = new ArrayList<>();

        for (HealthImpact impact : impacts) {

            healthScore.applyImpact(impact.scoreImpact());

            if (impact.alert() != null) {
                alerts.add(impact.alert());
            }
        }

        return new HealthReport(
                telemetry.vin(),
                Instant.now(),
                healthScore.getValue(),
                alerts
        );
    }
}
