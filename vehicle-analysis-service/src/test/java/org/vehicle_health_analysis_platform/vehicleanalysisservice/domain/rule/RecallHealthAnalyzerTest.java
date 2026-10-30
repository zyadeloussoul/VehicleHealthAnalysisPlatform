package org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.rule;
import org.junit.jupiter.api.Test;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.HealthImpact;
import org.vehicle_health_analysis_platform.vehicleanalysisservice.domain.model.Recall;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecallHealthAnalyzerTest {

    private final RecallHealthAnalyzer analyzer =
            new RecallHealthAnalyzer();

    @Test
    void shouldHaveNoImpactWhenThereIsNoRecall() {

        HealthImpact impact = analyzer.analyze(List.of());

        assertThat(impact.scoreImpact()).isZero();
        assertThat(impact.alert()).isNull();
    }

    @Test
    void shouldApplyImpactWhenRecallExists() {

        Recall recall = new Recall(
                "RCL-001",
                "BRAKING_SYSTEM",
                "Brake system recall"
        );

        HealthImpact impact =
                analyzer.analyze(List.of(recall));

        assertThat(impact.scoreImpact()).isEqualTo(-15);

        assertThat(impact.alert()).isNotNull();

        assertThat(impact.alert().type())
                .isEqualTo("RECALL");
    }

    @Test
    void shouldNotAccumulateImpactForMultipleRecalls() {

        Recall recall1 = new Recall(
                "RCL-001",
                "BRAKING_SYSTEM",
                "Brake system recall"
        );

        Recall recall2 = new Recall(
                "RCL-002",
                "AIRBAG",
                "Airbag recall"
        );

        HealthImpact impact =
                analyzer.analyze(
                        List.of(recall1, recall2)
                );

        assertThat(impact.scoreImpact()).isEqualTo(-15);
        assertThat(impact.alert()).isNotNull();
    }
}