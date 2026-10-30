package org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.external;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.application.port.out.LoadRecallsPort;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Recall;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.dto.RecallApiResponse;


import java.util.List;

public class RecallApiAdapter implements LoadRecallsPort {

    private final RestClient restClient;

    public RecallApiAdapter(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public List<Recall> findByVin(String vin) {

        List<RecallApiResponse> response = restClient
                .get()
                .uri("/api/recalls/{vin}", vin)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (response == null) {
            return List.of();
        }

        return response.stream()
                .map(recall -> new Recall(
                        recall.campaignId(),
                        recall.component(),
                        recall.description()
                ))
                .toList();
    }
}