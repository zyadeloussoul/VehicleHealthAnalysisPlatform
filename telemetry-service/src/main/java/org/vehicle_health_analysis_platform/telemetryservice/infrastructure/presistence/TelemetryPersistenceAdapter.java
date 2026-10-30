package org.vehicle_health_analysis_platform.telemetryservice.infrastructure.presistence;

import org.springframework.stereotype.Component;
import org.vehicle_health_analysis_platform.telemetryservice.application.port.out.SaveTelemetryPort;
import org.vehicle_health_analysis_platform.telemetryservice.domain.model.Telemetry;


@Component
public class TelemetryPersistenceAdapter implements SaveTelemetryPort {

    private final TelemetryJpaRepository repository;

    public TelemetryPersistenceAdapter(TelemetryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Telemetry telemetry) {

        TelemetryJpaEntity entity = new TelemetryJpaEntity(
                telemetry.vin(),
                telemetry.timestamp(),
                telemetry.kilometrage(),
                telemetry.pressionPneu(),
                telemetry.batterie(),
                telemetry.temperatureMoteur(),
                telemetry.carburant(),
                telemetry.codesErreur()
        );

        repository.save(entity);
    }
}
