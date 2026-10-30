package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service;

import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in.AnalyzeVehicleTelemetryUseCase;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.LoadRecallsPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.SaveHealthReportPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Recall;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

import java.util.List;

public class AnalyzeVehicleTelemetryService
        implements AnalyzeVehicleTelemetryUseCase {

    private final LoadRecallsPort loadRecallsPort;
    private final SaveHealthReportPort saveHealthReportPort;
    private final VehicleHealthAnalyzer vehicleHealthAnalyzer;

    public AnalyzeVehicleTelemetryService(
            LoadRecallsPort loadRecallsPort,
            SaveHealthReportPort saveHealthReportPort,
            VehicleHealthAnalyzer vehicleHealthAnalyzer
    ) {
        this.loadRecallsPort = loadRecallsPort;
        this.saveHealthReportPort = saveHealthReportPort;
        this.vehicleHealthAnalyzer = vehicleHealthAnalyzer;
    }

    @Override
    public void analyze(Telemetry telemetry) {

        List<Recall> recalls =
                loadRecallsPort.findByVin(telemetry.vin());

        HealthReport healthReport =
                vehicleHealthAnalyzer.analyze(
                        telemetry,
                        recalls
                );

        saveHealthReportPort.save(healthReport);
    }
}