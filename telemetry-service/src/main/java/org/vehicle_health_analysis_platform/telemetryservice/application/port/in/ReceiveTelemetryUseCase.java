package org.vehicle_health_analysis_platform.telemetryservice.application.port.in;


import org.vehicle_health_analysis_platform.telemetryservice.domain.model.Telemetry;

public interface ReceiveTelemetryUseCase {

    void receive(Telemetry telemetry);

}