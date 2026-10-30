package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.messaging;

import java.time.Instant;
import java.util.List;

public record TelemetryReceivedEvent(
        String vin,
        Instant timestamp,
        Long kilometrage,
        Double pressionPneu,
        Double batterie,
        Double temperatureMoteur,
        Double carburant,
        List<String> codesErreur
) {
}