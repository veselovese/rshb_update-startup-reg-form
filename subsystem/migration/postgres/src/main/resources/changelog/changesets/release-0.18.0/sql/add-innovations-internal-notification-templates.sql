INSERT INTO digital_notifications.topics (id, topic_key, "enabled", deleted, "collate", email_template)
VALUES
    (7, 'innovations.general.request', true, false, false, '<!DOCTYPE html>

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
            <td th:text="${applicantName}" />
        </tr>

        <tr>
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr>
            <th scope="row">Предложение или запрос</th>
            <td th:text="${aboutProposal}" />
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
'
    ),
    (8, 'innovations.window.demand', true, false, false, '<!DOCTYPE html>

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
            <th scope="row">Название компании</th>
            <td th:text="${companyName}" />
        </tr>

        <tr>
            <th scope="row">Ссылка на сайт</th>
            <td th:text="${companySite}" />
        </tr>

        <tr>
            <th scope="row">Краткое описание запроса</th>
            <td th:text="${summary}"/>
        </tr>

        <tr>
            <th scope="row">Полное описание запроса</th>
            <td th:text="${description}"/>
        </tr>

        <tr>
            <th scope="row">Контактный телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
'
    ),
    (9, 'innovations.window.proposal', true, false, false, '<!DOCTYPE html>

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
            <th scope="row">Связанная заявка</th>
            <td th:text="${demandUrl}"/>
        </tr>

        <tr>
            <th scope="row">Описание предлагаемого решения</th>
            <td th:text="${description}"/>
        </tr>

        <tr th:if="${startupLink != null && !startupLink.isBlank()}">
            <th scope="row">Ссылка на стартап в витрине стартапов</th>
            <td th:text="${startupLink}" />
        </tr>

        <tr>
            <th scope="row">Наименование стартапа</th>
            <td th:text="${startupName}" />
        </tr>

        <tr>
            <th scope="row">ФИО представителя</th>
            <td th:text="${representativeName}" />
        </tr>

        <tr>
            <th scope="row">Контактный телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
'
    )
;

DROP TABLE IF EXISTS digital_innovations.o_plt_notification_receivers;