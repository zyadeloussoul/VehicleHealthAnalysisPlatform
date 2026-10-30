package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model;

public record HealthImpact(
        int scoreImpact,
        Alert alert
) {

    public static HealthImpact noImpact() {
        return new HealthImpact(0, null);
    }

    public static HealthImpact of(int scoreImpact, Alert alert) {
        return new HealthImpact(scoreImpact, alert);
    }
}