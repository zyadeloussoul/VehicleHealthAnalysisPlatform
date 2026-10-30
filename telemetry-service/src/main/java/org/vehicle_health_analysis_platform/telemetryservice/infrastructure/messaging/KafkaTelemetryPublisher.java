package org.vehicle_health_analysis_platform.telemetryservice.infrastructure.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.vehicle_health_analysis_platform.telemetryservice.application.port.out.PublishTelemetryPort;
import org.vehicle_health_analysis_platform.telemetryservice.domain.model.Telemetry;

@Component
public class KafkaTelemetryPublisher implements PublishTelemetryPort {

    private static final String TOPIC = "vehicle.telemetry.received";

    private final KafkaTemplate<String, TelemetryReceivedEvent> kafkaTemplate;

    public KafkaTelemetryPublisher(
            KafkaTemplate<String, TelemetryReceivedEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(Telemetry telemetry) {

        TelemetryReceivedEvent event = new TelemetryReceivedEvent(
                telemetry.vin(),
                telemetry.timestamp(),
                telemetry.kilometrage(),
                telemetry.pressionPneu(),
                telemetry.batterie(),
                telemetry.temperatureMoteur(),
                telemetry.carburant(),
                telemetry.codesErreur()
        );

        kafkaTemplate.send(
                TOPIC,
                telemetry.vin(),
                event
        );
    }
}