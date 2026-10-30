package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.persistence;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "health_report")
public class HealthReportJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin", nullable = false, length = 17)
    private String vin;

    @Column(name = "analyzed_at", nullable = false)
    private Instant analyzedAt;

    @Column(name = "health_score", nullable = false)
    private Integer healthScore;

    @OneToMany(
            mappedBy = "healthReport",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AlertJpaEntity> alerts = new ArrayList<>();

    protected HealthReportJpaEntity() {
    }

    public HealthReportJpaEntity(
            String vin,
            Instant analyzedAt,
            Integer healthScore
    ) {
        this.vin = vin;
        this.analyzedAt = analyzedAt;
        this.healthScore = healthScore;
    }

    public void addAlert(String type, String message) {

        AlertJpaEntity alert =
                new AlertJpaEntity(
                        type,
                        message,
                        this
                );

        alerts.add(alert);
    }

    public Long getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public Instant getAnalyzedAt() {
        return analyzedAt;
    }

    public Integer getHealthScore() {
        return healthScore;
    }

    public List<AlertJpaEntity> getAlerts() {
        return alerts;
    }
}