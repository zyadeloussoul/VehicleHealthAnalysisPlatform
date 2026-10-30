package org.vehicle_health_analysis_platform.vehicleanalysisservice.external;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Recall;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.infrastructure.external.RecallApiAdapter;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

class RecallApiAdapterTest {

    private WireMockServer wireMockServer;
    private RecallApiAdapter recallApiAdapter;

    @BeforeEach
    void setUp() {

        wireMockServer = new WireMockServer(0);
        wireMockServer.start();

        RestClient restClient = RestClient.builder()
                .baseUrl(wireMockServer.baseUrl())
                .build();

        recallApiAdapter = new RecallApiAdapter(restClient);
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void shouldReturnRecallsForVin() {

        String vin = "VF1ABC12345678901";

        wireMockServer.stubFor(
                get(urlEqualTo("/api/recalls/" + vin))
                        .willReturn(
                                okJson("""
                                        [
                                          {
                                            "campaignId": "RCL-001",
                                            "component": "BRAKING_SYSTEM",
                                            "description": "Brake system recall"
                                          }
                                        ]
                                        """)
                        )
        );

        List<Recall> recalls =
                recallApiAdapter.findByVin(vin);

        assertThat(recalls).hasSize(1);

        assertThat(recalls.getFirst().campaignId())
                .isEqualTo("RCL-001");

        assertThat(recalls.getFirst().component())
                .isEqualTo("BRAKING_SYSTEM");
    }

    @Test
    void shouldReturnEmptyListWhenVehicleHasNoRecall() {

        String vin = "VF1ABC12345678902";

        wireMockServer.stubFor(
                get(urlEqualTo("/api/recalls/" + vin))
                        .willReturn(okJson("[]"))
        );

        List<Recall> recalls =
                recallApiAdapter.findByVin(vin);

        assertThat(recalls).isEmpty();
    }
}