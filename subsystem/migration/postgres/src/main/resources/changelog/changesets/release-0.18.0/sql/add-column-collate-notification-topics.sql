ALTER TABLE digital_notifications.topics
ADD COLUMN IF NOT EXISTS "collate" boolean not null default true;