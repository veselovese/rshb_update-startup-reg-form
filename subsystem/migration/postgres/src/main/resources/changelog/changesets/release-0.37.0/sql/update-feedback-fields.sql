update digital_feedback.o_plt_feedback_reports
set suspicious = false
where suspicious is null;

alter table digital_feedback.o_plt_feedback_reports
    alter column suspicious
        set default false;

alter table digital_feedback.o_plt_feedback_reports
    alter column suspicious
        set not null;

alter table digital_feedback.o_plt_feedback_reports
    drop column log;
