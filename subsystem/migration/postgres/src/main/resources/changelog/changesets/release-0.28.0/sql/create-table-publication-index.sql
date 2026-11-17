create table digital_publications.publication_index
(
    id        varchar(255),
    item_type varchar(255) not null,

    constraint post_index_pk
        primary key (id)
);
