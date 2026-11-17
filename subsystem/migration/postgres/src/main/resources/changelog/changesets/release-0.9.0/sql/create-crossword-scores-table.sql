CREATE TABLE digital_puzzles.o_plt_crossword_scores
(
    session_id       varchar(100) not null,
    participant_name varchar(300) not null,
    submit_date      timestamp    not null,
    crossword_id     bigint       not null,
    result           bigint       not null,

    CONSTRAINT o_plt_crossword_scores_pk
        PRIMARY KEY (session_id, crossword_id),

    CONSTRAINT o_plt_crossword_scores_crossword_fk
        FOREIGN KEY (crossword_id)
            REFERENCES digital_puzzles.o_plt_crosswords (id)
            ON DELETE CASCADE
);