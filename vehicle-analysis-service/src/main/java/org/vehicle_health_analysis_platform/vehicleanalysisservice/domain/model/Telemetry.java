package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model;


import java.time.Instant;
import java.util.List;

public record Telemetry(
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