package org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.in;


import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Alert;

import java.util.List;
import java.util.Optional;

public interface GetVehicleAlertsUseCase {

    Optional<List<Alert>> getByVin(String vin);
}