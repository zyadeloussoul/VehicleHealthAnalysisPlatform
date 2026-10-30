package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in.AnalyzeVehicleTelemetryUseCase;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Telemetry;

@Component
public class TelemetryKafkaConsumer {

    private final AnalyzeVehicleTelemetryUseCase analyzeVehicleTelemetryUseCase;

    public TelemetryKafkaConsumer(
            AnalyzeVehicleTelemetryUseCase analyzeVehicleTelemetryUseCase
    ) {
        this.analyzeVehicleTelemetryUseCase = analyzeVehicleTelemetryUseCase;
    }

    @KafkaListener(
            topics = "vehicle.telemetry.received",
            groupId = "vehicle-analysis-service"
    )
    public void consume(TelemetryReceivedEvent event) {

        Telemetry telemetry = new Telemetry(
                event.vin(),
                event.timestamp(),
                event.kilometrage(),
                event.pressionPneu(),
                event.batterie(),
                event.temperatureMoteur(),
                event.carburant(),
                event.codesErreur()
        );

        analyzeVehicleTelemetryUseCase.analyze(telemetry);
    }
}