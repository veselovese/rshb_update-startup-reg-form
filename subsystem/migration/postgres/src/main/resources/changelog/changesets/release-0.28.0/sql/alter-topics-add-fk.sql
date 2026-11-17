alter table digital_notifications.topics
    add constraint template_id_fk
        foreign key (template_id)
            references digital_notifications.templates (id)
            on delete cascade;
