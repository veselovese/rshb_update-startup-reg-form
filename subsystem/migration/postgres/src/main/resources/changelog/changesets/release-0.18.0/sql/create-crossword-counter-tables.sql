CREATE TABLE digital_puzzles.o_plt_participant_counters
(
    crossword_external_id varchar(100) NOT NULL,
    interaction_count     bigint       NOT NULL default 0,

    CONSTRAINT o_plt_crossword_participant_counters_pk
        PRIMARY KEY (crossword_external_id)
);
