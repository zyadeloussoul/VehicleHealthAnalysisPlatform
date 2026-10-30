package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;

import java.util.Optional;

public interface GetHealthReportUseCase {

    Optional<HealthReport> getLatestByVin(String vin);
}