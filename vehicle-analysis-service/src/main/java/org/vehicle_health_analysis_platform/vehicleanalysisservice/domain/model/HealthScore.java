package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model;



public class HealthScore {

    public static final int INITIAL_SCORE = 100;

    private int value;

    public HealthScore() {
        this.value = INITIAL_SCORE;
    }

    public int getValue() {
        return value;
    }

    public void applyImpact(int impact) {
        value = Math.max(0, Math.min(100, value + impact));
    }
}