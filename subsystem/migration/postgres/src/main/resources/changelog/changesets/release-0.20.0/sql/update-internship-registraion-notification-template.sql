UPDATE digital_notifications.topics
SET email_template = '<!DOCTYPE html>

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
            <td th:text="${registrationDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${applicantName}" />
        </tr>

        <tr>
            <th scope="row">Направление стажировки</th>
            <td th:text="${internshipField}" />
        </tr>

        <tr>
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr th:if="${about != null && !about.isBlank()}">
            <th scope="row">О себе</th>
            <td th:text="${about}" />
        </tr>

        <tr th:if="${telegram != null && !telegram.isBlank()}">
            <th scope="row">Telegram</th>
            <td th:text="${telegram}" />
        </tr>

        <tr>
            <th scope="row">Дата рождения</th>
            <td th:text="${birthDate}"/>
        </tr>

        <tr th:if="${city != null && !city.isBlank()}">
            <th scope="row">Город</th>
            <td th:text="${city}" />
        </tr>

        <tr th:if="${university != null && !university.isBlank()}">
            <th scope="row">ВУЗ</th>
            <td th:text="${university}" />
        </tr>

        <tr th:if="${eduStage != null && !eduStage.isBlank()}">
            <th scope="row">Ступень обучения</th>
            <td th:text="${eduStage}" />
        </tr>

        <tr th:if="${eduYear != null}">
            <th scope="row">Курс</th>
            <td th:text="${eduYear}" />
        </tr>

        <tr>
            <th scope="row">Знания и навыки</th>
            <td th:text="${skills}"/>
        </tr>

        <tr>
            <th scope="row">Задачи, которыми интересно было бы заниматься</th>
            <td th:text="${desiredTasks}"/>
        </tr>

        <tr th:if="${portfolio != null && !portfolio.isBlank()}">
            <th scope="row">Примеры из практики</th>
            <td th:text="${portfolio}" />
        </tr>

        <tr th:if="${experience != null && !experience.isBlank()}">
            <th scope="row">Опыт работы или стажировки</th>
            <td th:text="${experience}" />
        </tr>

        </tbody>
    </table>
</div>
</body>
</html>'
WHERE topic_key = 'internship.participation.request';
