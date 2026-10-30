package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in.GetHealthReportUseCase;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in.GetVehicleAlertsUseCase;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.dto.AlertResponse;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.dto.HealthReportResponse;

import java.util.List;


@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleHealthController {

    private final GetHealthReportUseCase getHealthReportUseCase;
    private final GetVehicleAlertsUseCase getVehicleAlertsUseCase;

    public VehicleHealthController(
            GetHealthReportUseCase getHealthReportUseCase,
            GetVehicleAlertsUseCase getVehicleAlertsUseCase
    ) {
        this.getHealthReportUseCase = getHealthReportUseCase;
        this.getVehicleAlertsUseCase = getVehicleAlertsUseCase;
    }

    @GetMapping("/{vin}/health-report")
    public ResponseEntity<HealthReportResponse> getHealthReport(
            @PathVariable String vin
    ) {

        return getHealthReportUseCase
                .getLatestByVin(vin)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private HealthReportResponse toResponse(HealthReport report) {

        var alerts = report.alerts()
                .stream()
                .map(alert -> new AlertResponse(
                        alert.type(),
                        alert.message()
                ))
                .toList();

        return new HealthReportResponse(
                report.vin(),
                report.analyzedAt(),
                report.healthScore(),
                alerts
        );
    }
    @GetMapping("/{vin}/alerts")
    public ResponseEntity<List<AlertResponse>> getAlerts(
            @PathVariable String vin
    ) {

        return getVehicleAlertsUseCase
                .getByVin(vin)
                .map(alerts -> alerts.stream()
                        .map(alert -> new AlertResponse(
                                alert.type(),
                                alert.message()
                        ))
                        .toList())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
