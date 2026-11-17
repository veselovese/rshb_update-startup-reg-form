update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <style>

        h1 {
            font-family: Ubuntu, Arial, sans-serif;
            color: white;
        }

        .table_col {
            font-family: Ubuntu, Arial, sans-serif;
            font-size: 1em;
            font-weight: bold;
            width: 100%;
            text-align: left;
            border-collapse: collapse;
            border-radius: 5px;
            color: #89E159;
            border-bottom: 5px solid #89E159;
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
    <div>
        <h1>Статистика обращений через сайт РСХБ Digital за период <span th:text="${periodStart}"/> - <span th:text="${periodEnd}" /></h1>
    </div>
    <div th:each="stat: ${report.formStats}">
        <table class="table_col">
            <tbody>
                <tr>
                    <th scope="row">Тип заявки</th>
                    <td th:text="${stat.getFormTitle()}" />
                </tr>

                <tr>
                    <th scope="row">Общий итог с <span th:text="${stat.oldestRecordDate}" /></th>
                    <td th:text="${stat.totalUsagesCount}" />
                </tr>

                <tr>
                    <th scope="row">Итог за неделю <span th:text="${periodStart}" /> - <span th:text="${periodEnd}"/></th>
                    <td th:text="${stat.deltaUsagesCountForPeriod}"/>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>'
where "key" = 'internal.statistics.form.usage.report';