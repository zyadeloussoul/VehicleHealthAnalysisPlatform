package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.dto;


public record RecallApiResponse(
        String campaignId,
        String component,
        String description
) {
}
