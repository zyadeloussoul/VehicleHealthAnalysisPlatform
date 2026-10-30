package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthReport;

public interface SaveHealthReportPort {

    void save(HealthReport healthReport);
}