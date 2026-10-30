CREATE TABLE telemetry
(
    id                          BIGSERIAL PRIMARY KEY,
    vin                         VARCHAR(17) NOT NULL,
    timestamp                   TIMESTAMP WITH TIME ZONE NOT NULL,
    kilometrage                 BIGINT,
    pression_pneu               DOUBLE PRECISION,
    batterie                    DOUBLE PRECISION,
    temperature_moteur          DOUBLE PRECISION,
    carburant                   DOUBLE PRECISION
);

CREATE INDEX idx_telemetry_vin
    ON telemetry(vin);


CREATE TABLE telemetry_error_codes
(
    telemetry_id   BIGINT NOT NULL,
    code_erreur    VARCHAR(50) NOT NULL,

    CONSTRAINT fk_telemetry_error_codes_telemetry
        FOREIGN KEY (telemetry_id)
            REFERENCES telemetry(id)
            ON DELETE CASCADE
);

CREATE INDEX idx_telemetry_error_codes_telemetry_id
    ON telemetry_error_codes(telemetry_id);