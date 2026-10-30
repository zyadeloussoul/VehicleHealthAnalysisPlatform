package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in.GetVehicleAlertsUseCase;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.LoadHealthReportPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;

import java.util.List;
import java.util.Optional;

public class GetVehicleAlertsService implements GetVehicleAlertsUseCase {

    private final LoadHealthReportPort loadHealthReportPort;

    public GetVehicleAlertsService(
            LoadHealthReportPort loadHealthReportPort
    ) {
        this.loadHealthReportPort = loadHealthReportPort;
    }

    @Override
    public Optional<List<Alert>> getByVin(String vin) {

        return loadHealthReportPort
                .findLatestByVin(vin)
                .map(report -> report.alerts());
    }
}
