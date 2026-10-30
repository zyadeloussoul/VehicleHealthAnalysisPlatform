package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model;

import java.time.Instant;
import java.util.List;

public record HealthReport(
        String vin,
        Instant analyzedAt,
        int healthScore,
        List<Alert> alerts
) {
}