package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.persistence;




import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HealthReportJpaRepository
        extends JpaRepository<HealthReportJpaEntity, Long> {

    Optional<HealthReportJpaEntity>
    findFirstByVinOrderByAnalyzedAtDesc(String vin);
}