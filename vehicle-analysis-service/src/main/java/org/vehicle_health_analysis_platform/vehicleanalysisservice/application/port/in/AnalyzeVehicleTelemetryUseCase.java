package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

public interface AnalyzeVehicleTelemetryUseCase {

    void analyze(Telemetry telemetry);
}