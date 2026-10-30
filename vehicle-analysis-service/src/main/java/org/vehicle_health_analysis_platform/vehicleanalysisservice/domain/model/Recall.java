package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model;


public record Recall(
        String campaignId,
        String component,
        String description
) {
}