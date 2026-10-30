package org.vehicle_health_analysis_platform.telemetryservice.application.port.out;


import org.vehicle_health_analysis_platform.telemetryservice.domain.model.Telemetry;

public interface SaveTelemetryPort {

    void save(Telemetry telemetry);
}