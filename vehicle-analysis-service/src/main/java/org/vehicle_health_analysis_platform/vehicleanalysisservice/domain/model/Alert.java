package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model;

public record Alert(
        String type,
        String message
) {
}