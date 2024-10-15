CREATE TABLE aircraft
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    airframe VARCHAR(255),
    pilot_id BIGINT,
    CONSTRAINT pk_aircraft PRIMARY KEY (id)
);

ALTER TABLE aircraft
    ADD CONSTRAINT FK_AIRCRAFT_ON_PILOT FOREIGN KEY (pilot_id) REFERENCES pilot (id);