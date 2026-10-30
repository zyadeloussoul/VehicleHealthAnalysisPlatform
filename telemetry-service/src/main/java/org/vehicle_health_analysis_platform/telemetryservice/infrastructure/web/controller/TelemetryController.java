package org.vehicle_health_analysis_platform.telemetryservice.infrastructure.web.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vehicle_health_analysis_platform.telemetryservice.application.port.in.ReceiveTelemetryUseCase;
import org.vehicle_health_analysis_platform.telemetryservice.domain.model.Telemetry;
import org.vehicle_health_analysis_platform.telemetryservice.infrastructure.web.dto.TelemetryRequest;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {

    private final ReceiveTelemetryUseCase receiveTelemetryUseCase;

    public TelemetryController(ReceiveTelemetryUseCase receiveTelemetryUseCase) {
        this.receiveTelemetryUseCase = receiveTelemetryUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> receive(@Valid @RequestBody TelemetryRequest request) {

        Telemetry telemetry = new Telemetry(
                request.vin(),
                request.timestamp(),
                request.kilometrage(),
                request.pressionPneu(),
                request.batterie(),
                request.temperatureMoteur(),
                request.carburant(),
                request.codesErreur()
        );

        receiveTelemetryUseCase.receive(telemetry);

        return ResponseEntity.accepted().build();
    }
}