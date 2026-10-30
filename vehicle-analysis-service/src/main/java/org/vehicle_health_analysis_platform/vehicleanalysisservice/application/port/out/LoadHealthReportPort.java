package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;

import java.util.Optional;

public interface LoadHealthReportPort {

    Optional<HealthReport> findLatestByVin(String vin);
}