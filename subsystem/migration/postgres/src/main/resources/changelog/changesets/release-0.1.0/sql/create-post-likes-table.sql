CREATE TABLE digital_publications.o_plt_post_likes
(
    session_id     varchar(255) not null,
    publication_id varchar(255) not null,
    state          varchar(100) not null,
    updated        timestamp    null,

    CONSTRAINT o_plt_post_likes_pkey
        PRIMARY KEY (session_id, publication_id)
);

CREATE TABLE digital_publications.o_plt_post_reactions_ct
(
    publication_id varchar(255) not null,
    reaction       varchar(100) not null,
    reaction_count bigint       not null default 0,

    CONSTRAINT o_plt_post_likes_ct_pkey
        PRIMARY KEY (publication_id, reaction)
);