CREATE TABLE IF NOT EXISTS digital_system.sessions
(

    primary_id            bpchar(36)   NOT NULL,
    session_id            bpchar(36)   NOT NULL,
    creation_time         int8         NOT NULL,
    last_access_time      int8         NOT NULL,
    max_inactive_interval int4         NOT NULL,
    expiry_time           int8         NOT NULL,
    principal_name        varchar(100) NULL,

    CONSTRAINT spring_session_pk
        PRIMARY KEY (primary_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS spring_session_ix1
    ON digital_system.sessions
        USING btree (session_id);

CREATE INDEX IF NOT EXISTS spring_session_ix2
    ON digital_system.sessions
        USING btree (expiry_time);

CREATE INDEX IF NOT EXISTS spring_session_ix3
    ON digital_system.sessions
        USING btree (principal_name);

CREATE TABLE IF NOT EXISTS digital_system.sessions_attributes
(

    session_primary_id bpchar(36)   NOT NULL,
    attribute_name     varchar(200) NOT NULL,
    attribute_bytes    bytea        NOT NULL,

    CONSTRAINT spring_session_attributes_pk
        PRIMARY KEY (session_primary_id, attribute_name),

    CONSTRAINT session_attributes_fk
        FOREIGN KEY (session_primary_id)
            REFERENCES digital_system.sessions (primary_id)
            ON DELETE CASCADE
);