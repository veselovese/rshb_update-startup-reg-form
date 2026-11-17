alter table
    digital_notifications.notification_log
    alter column
        description
        drop not null;

update
    digital_notifications.notification_log
set
    description = null
where description = '{
  "attachments" : [ ]
}';
