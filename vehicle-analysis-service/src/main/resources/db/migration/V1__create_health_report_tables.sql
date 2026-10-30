CREATE TABLE health_report
(
    id              BIGSERIAL PRIMARY KEY,
    vin             VARCHAR(17) NOT NULL,
    analyzed_at     TIMESTAMP WITH TIME ZONE NOT NULL,
    health_score    INTEGER NOT NULL
);

CREATE INDEX idx_health_report_vin
    ON health_report(vin);


CREATE TABLE health_report_alert
(
    id                  BIGSERIAL PRIMARY KEY,
    health_report_id    BIGINT NOT NULL,
    type                VARCHAR(100) NOT NULL,
    message             VARCHAR(500) NOT NULL,

    CONSTRAINT fk_alert_health_report
        FOREIGN KEY (health_report_id)
            REFERENCES health_report(id)
            ON DELETE CASCADE
);

CREATE INDEX idx_health_report_alert_report_id
    ON health_report_alert(health_report_id);