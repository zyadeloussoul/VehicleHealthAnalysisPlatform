package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "health_report_alert")
public class AlertJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "message", nullable = false, length = 500)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_report_id", nullable = false)
    private HealthReportJpaEntity healthReport;

    protected AlertJpaEntity() {
    }

    public AlertJpaEntity(
            String type,
            String message,
            HealthReportJpaEntity healthReport
    ) {
        this.type = type;
        this.message = message;
        this.healthReport = healthReport;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}