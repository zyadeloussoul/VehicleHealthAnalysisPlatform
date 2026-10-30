package org.vehicle_health_analysis_platform.telemetryservice.infrastructure.presistence;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "telemetry")
public class TelemetryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin", nullable = false, length = 17)
    private String vin;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "kilometrage")
    private Long kilometrage;

    @Column(name = "pression_pneu")
    private Double pressionPneu;

    @Column(name = "batterie")
    private Double batterie;

    @Column(name = "temperature_moteur")
    private Double temperatureMoteur;

    @Column(name = "carburant")
    private Double carburant;

    @ElementCollection
    @CollectionTable(
            name = "telemetry_error_codes",
            joinColumns = @JoinColumn(name = "telemetry_id")
    )
    @Column(name = "code_erreur")
    private List<String> codesErreur = new ArrayList<>();

    protected TelemetryJpaEntity() {
    }

    public TelemetryJpaEntity(
            String vin,
            Instant timestamp,
            Long kilometrage,
            Double pressionPneu,
            Double batterie,
            Double temperatureMoteur,
            Double carburant,
            List<String> codesErreur
    ) {
        this.vin = vin;
        this.timestamp = timestamp;
        this.kilometrage = kilometrage;
        this.pressionPneu = pressionPneu;
        this.batterie = batterie;
        this.temperatureMoteur = temperatureMoteur;
        this.carburant = carburant;
        this.codesErreur = codesErreur != null
                ? new ArrayList<>(codesErreur)
                : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }
}