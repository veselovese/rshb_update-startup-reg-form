CREATE TABLE digital_publications.o_plt_post_view_ct
(
    publication_id varchar(255) not null,
    view_count     bigint       not null default 0,

    CONSTRAINT o_plt_post_view_ct_pkey
        PRIMARY KEY (publication_id)
);

CREATE TABLE digital_publications.o_plt_post_view_history
(
    session_id     varchar(255) not null,
    publication_id varchar(255) not null,
    viewed         timestamp    not null,

    CONSTRAINT o_plt_post_view_history_pkey
        PRIMARY KEY (session_id, publication_id)
)