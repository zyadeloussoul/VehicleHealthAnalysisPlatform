package org.vehicle_health_analysis_platform.telemetryservice.infrastructure.web.dto;

import jakarta.validation.constraints.*;

import java.time.Instant;
import java.util.List;

public record TelemetryRequest(

        @NotBlank(message = "Le VIN est obligatoire")
        @Size(min = 17, max = 17, message = "Le VIN doit contenir 17 caractères")
        String vin,

        @NotNull(message = "Le timestamp est obligatoire")
        Instant timestamp,

        @NotNull(message = "Le kilométrage est obligatoire")
        @PositiveOrZero(message = "Le kilométrage ne peut pas être négatif")
        Long kilometrage,

        @NotNull(message = "La pression du pneu est obligatoire")
        @PositiveOrZero(message = "La pression du pneu ne peut pas être négative")
        Double pressionPneu,

        @NotNull(message = "La batterie est obligatoire")
        @PositiveOrZero(message = "La batterie ne peut pas être négative")
        Double batterie,

        @NotNull(message = "La température moteur est obligatoire")
        Double temperatureMoteur,

        @NotNull(message = "Le niveau de carburant est obligatoire")
        @DecimalMax(
                value = "100.0",
                message = "Le niveau de carburant ne peut pas dépasser 100%"
        )
        Double carburant,

        List<String> codesErreur

) {
}