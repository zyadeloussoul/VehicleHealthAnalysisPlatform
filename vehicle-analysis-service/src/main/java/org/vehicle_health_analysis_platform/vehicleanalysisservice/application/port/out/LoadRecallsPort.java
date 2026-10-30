package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out;



import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Recall;

import java.util.List;

public interface LoadRecallsPort {

    List<Recall> findByVin(String vin);
}