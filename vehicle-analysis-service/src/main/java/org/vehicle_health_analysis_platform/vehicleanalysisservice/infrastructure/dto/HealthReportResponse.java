package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.dto;


import java.time.Instant;
import java.util.List;

public record HealthReportResponse(
        String vin,
        Instant analyzedAt,
        int healthScore,
        List<AlertResponse> alerts
) {
}