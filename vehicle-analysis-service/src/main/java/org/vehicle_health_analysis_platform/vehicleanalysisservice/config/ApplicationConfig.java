package org.vehicle_health_analysis_platform.vehicleanalysisservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.LoadHealthReportPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.LoadRecallsPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.SaveHealthReportPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service.AnalyzeVehicleTelemetryService;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service.GetHealthReportService;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service.GetVehicleAlertsService;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.service.VehicleHealthAnalyzer;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule.*;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.external.RecallApiAdapter;

@Configuration
public class ApplicationConfig {

    @Bean
    public GetHealthReportService getHealthReportService(
            LoadHealthReportPort loadHealthReportPort
    ) {
        return new GetHealthReportService(loadHealthReportPort);
    }
    @Bean
    public MileageHealthAnalyzer mileageHealthAnalyzer() {
        return new MileageHealthAnalyzer();
    }

    @Bean
    public BatteryHealthAnalyzer batteryHealthAnalyzer() {
        return new BatteryHealthAnalyzer();
    }

    @Bean
    public EngineTemperatureHealthAnalyzer engineTemperatureHealthAnalyzer() {
        return new EngineTemperatureHealthAnalyzer();
    }

    @Bean
    public FuelHealthAnalyzer fuelHealthAnalyzer() {
        return new FuelHealthAnalyzer();
    }

    @Bean
    public ErrorCodeHealthAnalyzer errorCodeHealthAnalyzer() {
        return new ErrorCodeHealthAnalyzer();
    }

    @Bean
    public RecallHealthAnalyzer recallHealthAnalyzer() {
        return new RecallHealthAnalyzer();
    }
    @Bean
    public TirePressureHealthAnalyzer tirePressureHealthAnalyzer(
            @Value("${vehicle.health.tire.recommended-pressure}")
            double recommendedPressure
    ) {
        return new TirePressureHealthAnalyzer(recommendedPressure);
    }
    @Bean
    public VehicleHealthAnalyzer vehicleHealthAnalyzer(
            MileageHealthAnalyzer mileageHealthAnalyzer,
            TirePressureHealthAnalyzer tirePressureHealthAnalyzer,
            BatteryHealthAnalyzer batteryHealthAnalyzer,
            EngineTemperatureHealthAnalyzer engineTemperatureHealthAnalyzer,
            FuelHealthAnalyzer fuelHealthAnalyzer,
            ErrorCodeHealthAnalyzer errorCodeHealthAnalyzer,
            RecallHealthAnalyzer recallHealthAnalyzer
    ) {
        return new VehicleHealthAnalyzer(
                mileageHealthAnalyzer,
                tirePressureHealthAnalyzer,
                batteryHealthAnalyzer,
                engineTemperatureHealthAnalyzer,
                fuelHealthAnalyzer,
                errorCodeHealthAnalyzer,
                recallHealthAnalyzer
        );
    }
    @Bean
    public AnalyzeVehicleTelemetryService analyzeVehicleTelemetryService(
            LoadRecallsPort loadRecallsPort,
            SaveHealthReportPort saveHealthReportPort,
            VehicleHealthAnalyzer vehicleHealthAnalyzer
    ) {
        return new AnalyzeVehicleTelemetryService(
                loadRecallsPort,
                saveHealthReportPort,
                vehicleHealthAnalyzer
        );
    }
    @Bean
    public RestClient recallRestClient(
            @Value("${external.recall-api.base-url}") String baseUrl
    ) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
    @Bean
    public RecallApiAdapter recallApiAdapter(
            RestClient recallRestClient
    ) {
        return new RecallApiAdapter(recallRestClient);
    }
    @Bean
    public GetVehicleAlertsService getVehicleAlertsService(
            LoadHealthReportPort loadHealthReportPort
    ) {
        return new GetVehicleAlertsService(loadHealthReportPort);
    }
}