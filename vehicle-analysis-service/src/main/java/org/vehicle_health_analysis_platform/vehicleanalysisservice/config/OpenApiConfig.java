package org.vehicle_health_analysis_platform.vehicleanalysisservice.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI vehicleAnalysisOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Vehicle Health Analysis API")
                        .version("v1")
                        .description(
                                "API responsible for vehicle health reports and alerts."
                        ));
    }
}