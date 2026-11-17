-- Справочник населенных пунктов РФ ОКАТО
CREATE TABLE digital_refs.okato_city
(
    id     varchar(50)  NOT NULL,
    title  varchar(300) NOT NULL,
    region varchar(300) NOT NULL,

    CONSTRAINT okato_city_pk
        PRIMARY KEY (id)
);
