ALTER TABLE digital_contests.o_plt_chef_contestants
    ADD COLUMN suspicious boolean null default false;

ALTER TABLE digital_puzzles.o_plt_crossword_participants
    ADD COLUMN suspicious boolean null default false;

ALTER TABLE digital_feedback.o_plt_feedback_reports
    ADD COLUMN suspicious boolean null default false;

ALTER TABLE digital_innovations.o_plt_applicants
    ADD COLUMN suspicious boolean null default false;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN suspicious boolean null default false;

ALTER TABLE digital_innovations.o_plt_window_demands
    ADD COLUMN suspicious boolean null default false;

ALTER TABLE digital_innovations.o_plt_window_proposals
    ADD COLUMN suspicious boolean null default false;
