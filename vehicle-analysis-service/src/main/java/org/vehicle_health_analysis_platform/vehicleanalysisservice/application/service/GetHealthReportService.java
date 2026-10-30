package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in.GetHealthReportUseCase;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.LoadHealthReportPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;

import java.util.Optional;

public class GetHealthReportService implements GetHealthReportUseCase {

    private final LoadHealthReportPort loadHealthReportPort;

    public GetHealthReportService(
            LoadHealthReportPort loadHealthReportPort
    ) {
        this.loadHealthReportPort = loadHealthReportPort;
    }

    @Override
    public Optional<HealthReport> getLatestByVin(String vin) {

        return loadHealthReportPort.findLatestByVin(vin);
    }
}
