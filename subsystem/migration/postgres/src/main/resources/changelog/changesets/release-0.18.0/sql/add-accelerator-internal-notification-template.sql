INSERT INTO digital_notifications.topics (id, topic_key, "enabled", deleted, email_template)
VALUES
    (3, 'student.accelerator.request', true, false, '<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <style>
        .table_col {
            font-family: Ubuntu, Arial, sans-serif;
            font-size: 1em;
            font-weight: bold;
            width: 100%;
            text-align: left;
            border-collapse: collapse;
            border: none;
            border-radius: 5px;
            color: #89E159;
        }

        .table_col th:first-child, .table_col td:first-child {
            color: #89E159;
            border-left: none;
        }

        .table_col th {
            font-weight: bold;
            border-bottom: 1px solid #89E159;
            padding: 8px 10px;
            width: 20%;
        }

        .table_col td {
            font-size: 1em;
            font-weight: normal;
            border-bottom: 1px solid #89E159;
            padding: 12px 10px;
            color: white;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>
    <table class="table_col">
        <tbody>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${request.getFullName}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${request.getEmail()}"/>
        </tr>

        <tr>
            <th scope="row">Контактный телефон</th>
            <td th:text="${request.getPhone()}"/>
        </tr>

        <tr>
            <th scope="row">Род деятельности</th>
            <td th:text="${request.getRole()}"/>
        </tr>

        <tr th:if="${about != null && !about.isBlank()}">
            <th scope="row">О себе</th>
            <td th:text="${about}" />
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>'
    )

;