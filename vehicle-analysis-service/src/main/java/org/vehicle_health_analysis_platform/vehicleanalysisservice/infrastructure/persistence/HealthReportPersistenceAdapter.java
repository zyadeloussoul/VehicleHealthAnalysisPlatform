package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.persistence;


import org.springframework.stereotype.Component;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.LoadHealthReportPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.SaveHealthReportPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;

import java.util.List;
import java.util.Optional;

@Component
public class HealthReportPersistenceAdapter
        implements SaveHealthReportPort, LoadHealthReportPort {

    private final HealthReportJpaRepository repository;

    public HealthReportPersistenceAdapter(
            HealthReportJpaRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void save(HealthReport healthReport) {

        HealthReportJpaEntity entity =
                new HealthReportJpaEntity(
                        healthReport.vin(),
                        healthReport.analyzedAt(),
                        healthReport.healthScore()
                );

        for (Alert alert : healthReport.alerts()) {

            entity.addAlert(
                    alert.type(),
                    alert.message()
            );
        }

        repository.save(entity);
    }
    @Override
    public Optional<HealthReport> findLatestByVin(String vin) {

        return repository
                .findFirstByVinOrderByAnalyzedAtDesc(vin)
                .map(this::toDomain);
    }
    private HealthReport toDomain(HealthReportJpaEntity entity) {

        List<Alert> alerts = entity.getAlerts()
                .stream()
                .map(alert -> new Alert(
                        alert.getType(),
                        alert.getMessage()
                ))
                .toList();

        return new HealthReport(
                entity.getVin(),
                entity.getAnalyzedAt(),
                entity.getHealthScore(),
                alerts
        );
    }
}