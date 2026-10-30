package org.vehicle_health_analysis_platform.telemetryservice.infrastructure.presistence;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetryJpaRepository
        extends JpaRepository<TelemetryJpaEntity, Long> {
}