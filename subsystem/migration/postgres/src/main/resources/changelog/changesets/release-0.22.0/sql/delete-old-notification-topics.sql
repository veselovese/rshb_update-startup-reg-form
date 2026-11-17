DELETE FROM digital_notifications.topics
WHERE topic_key in (
    'student.accelerator.request',
    'agrocode.request',
    'agrocode.request.report'
);