package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.dto;


public record AlertResponse(
        String type,
        String message
) {
}