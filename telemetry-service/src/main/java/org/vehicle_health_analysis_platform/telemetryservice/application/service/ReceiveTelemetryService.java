package org.vehicle_health_analysis_platform.telemetryservice.application.service;


import org.vehicle_health_analysis_platform.telemetryservice.application.port.in.ReceiveTelemetryUseCase;
import org.vehicle_health_analysis_platform.telemetryservice.application.port.out.PublishTelemetryPort;
import org.vehicle_health_analysis_platform.telemetryservice.application.port.out.SaveTelemetryPort;
import org.vehicle_health_analysis_platform.telemetryservice.domain.model.Telemetry;

public class ReceiveTelemetryService implements ReceiveTelemetryUseCase {

    private final SaveTelemetryPort saveTelemetryPort;
    private final PublishTelemetryPort publishTelemetryPort;

    public ReceiveTelemetryService(
            SaveTelemetryPort saveTelemetryPort,
            PublishTelemetryPort publishTelemetryPort
    ) {
        this.saveTelemetryPort = saveTelemetryPort;
        this.publishTelemetryPort = publishTelemetryPort;
    }

    @Override
    public void receive(Telemetry telemetry) {

        saveTelemetryPort.save(telemetry);

        publishTelemetryPort.publish(telemetry);
    }
}