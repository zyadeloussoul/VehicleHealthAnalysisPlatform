package org.vehicle_health_analysis_platform.telemetryservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vehicle_health_analysis_platform.telemetryservice.application.port.out.PublishTelemetryPort;
import org.vehicle_health_analysis_platform.telemetryservice.application.port.out.SaveTelemetryPort;
import org.vehicle_health_analysis_platform.telemetryservice.application.service.ReceiveTelemetryService;


@Configuration
public class ApplicationConfig {

    @Bean
    public ReceiveTelemetryService receiveTelemetryService(
            SaveTelemetryPort saveTelemetryPort,
            PublishTelemetryPort publishTelemetryPort
    ) {

        return new ReceiveTelemetryService(
                saveTelemetryPort,
                publishTelemetryPort
        );
    }
}