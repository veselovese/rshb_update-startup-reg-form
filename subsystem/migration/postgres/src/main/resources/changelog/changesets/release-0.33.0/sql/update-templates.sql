update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style>

        h1 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое обращение через форму обратной связи</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr th:if="${reporterName != null && !reporterName.isBlank()}">
            <th scope="row">ФИО</th>
            <td th:text="${reporterName}"/>
        </tr>

        <tr th:if="${phone != null && !phone.isBlank()}">
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr th:if="${feedback != null && !feedback.isBlank()}">
            <th scope="row">Вопрос или предложение</th>
            <td th:text="${feedback}"/>
        </tr>
        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'feedback.report';

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
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое скачивание файла "Рейтинг инновационности"</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${fullName}"/>
        </tr>

        <tr>
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr th:if="${organization != null && !organization.isBlank()}">
            <th scope="row">Организация</th>
            <td th:text="${organization}"/>
        </tr>

        <tr th:if="${position != null && !position.isBlank()}">
            <th scope="row">Должность</th>
            <td th:text="${position}"/>
        </tr>
        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'agrobit.innovation.rating.download.internal.notification';

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
            color: #89E159;
        }

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

        .table_col th:first-child,
        .table_col td:first-child {
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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">

<div>

    <h1>Новая заявка "Стать экспертом"</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${registrationDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${expertName}"/>
        </tr>

        <tr>
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr th:if="${vkProfileUrl != null && !vkProfileUrl.isBlank()}">
            <th scope="row">Профиль VK</th>
            <th>
                <a th:href="${vkProfileUrl}" th:text="${vkProfileUrl}"/>
            </th>
        </tr>

        <tr>
            <th scope="row">Экспертные области</th>
            <td>
                <ul>
                    <li th:each="direction:${directions}" th:text="${direction}"/>
                </ul>
            </td>
        </tr>

        <tr th:if="${performanceUrls != null && !performanceUrls.isEmpty()}">
            <th scope="row">Ссылки на выступления</th>
            <th>
                <ul>
                    <li th:each="perfUrl:${performanceUrls}">
                        <a th:href="${perfUrl}" th:text="${perfUrl}"/>
                    </li>
                </ul>
            </th>
        </tr>

        <tr th:if="${about != null && !about.isBlank()}">
            <th scope="row">Комментарий</th>
            <td th:text="${comment}"/>
        </tr>

        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>

</html>
'
where "key" = 'blogs.expert.registration.request';

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
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новый результат в конкурсе "Цифровой повар"</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">Имя участника</th>
            <td th:text="${contestantName}"/>
        </tr>

        <tr>
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">URL решения</th>
            <td th:text="${solutionUrl}"/>
        </tr>

        <tr>
            <th scope="row">Результат</th>
            <td th:text="${contestResult}"/>
        </tr>

        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'chef.participant.request';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Новый вопрос - РСХБ в цифре</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <style>

        h1 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">

<div>

    <h1>Новый вопрос по олимпиаде в разделе "Навстречу Агротеху"</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${applicantName}"/>
        </tr>

        <tr>
            <th scope="row">Контактный телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr>
            <th scope="row">Вопрос</th>
            <td th:text="${question}"/>
        </tr>

        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'contest.agrotech.olympiad.question.internal.notification';

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
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">

<div>

    <h1>Новая заявка участие в конкурсе патентов</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${applicantName}"/>
        </tr>

        <tr>
            <th scope="row">Контактный телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr th:if="${organization != null && !organization.isBlank()}">
            <th scope="row">Организация</th>
            <td th:text="${organization}"/>
        </tr>

        <tr th:if="${position != null && !position.isBlank()}">
            <th scope="row">Должность</th>
            <td th:text="${position}"/>
        </tr>

        <tr>
            <th scope="row">Город</th>
            <td th:text="${city}"/>
        </tr>

        <tr th:if="${staffSize != null}">
            <th scope="row">Количество сотрудников</th>
            <td th:text="${staffSize}"/>
        </tr>

        <tr th:if="${siteUrl != null && !siteUrl.isBlank()}">
            <th scope="row">Сайт</th>
            <td th:text="${siteUrl}"/>
        </tr>

        <tr>
            <th scope="row">Количество клиентов</th>
            <td th:text="${clientsAmount}"/>
        </tr>

        <tr>
            <th scope="row">Название проекта</th>
            <td th:text="${projectName}"/>
        </tr>

        <tr>
            <th scope="row">Стадия проекта</th>
            <td th:text="${projectStage}"/>
        </tr>

        <tr>
            <th scope="row">Коммерческая реализация</th>
            <td th:text="${commercialImpl}"/>
        </tr>

        <tr th:if="${investmentsAmount != null && !investmentsAmount.isBlank()}">
            <th scope="row">Объем инвестиций</th>
            <td th:text="${investmentsAmount}"/>
        </tr>

        <tr>
            <th scope="row">Краткое описание проекта</th>
            <td th:text="${inventionDescriptionShort}"/>
        </tr>

        <tr>
            <th scope="row">Раунд финансирования</th>
            <td th:text="${financingRound}"/>
        </tr>

        <tr th:if="${victories != null && !victories.isBlank()}">
            <th scope="row">Победа в конкурсах</th>
            <td th:text="${victories}"/>
        </tr>

        <tr>
            <th scope="row">Патентообладатель</th>
            <td th:text="${patentHolder}"/>
        </tr>

        <tr>
            <th scope="row">Номер патента</th>
            <td th:text="${patentNumber}"/>
        </tr>

        <tr>
            <th scope="row">Год патентирования</th>
            <td th:text="${patentYear}"/>
        </tr>

        <tr>
            <th scope="row">Хозяйствующий субъект</th>
            <td th:text="${managingSubject}"/>
        </tr>

        <tr>
            <th scope="row">МПК патента</th>
            <td th:text="${patentMpk}"/>
        </tr>

        <tr>
            <th scope="row">Описание изобретения</th>
            <td th:text="${inventionDescriptionFull}"/>
        </tr>

        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'contest.patent.new.registration.internal.notification';

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
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое обращение в разделе "Кроссворд"</h1>

    <table class="table_col">
        <tbody>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${participantName}"/>
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
            <td th:text="${about}"/>
        </tr>
        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'crossword.participant.request';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style>

        h1, h2 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое обращение в разделе "Инновации"</h1>
    <h2>Запрос на технологию или предложение продукта</h2>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${applicantName}"/>
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
            <td th:text="${aboutProposal}"/>
        </tr>
        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'innovations.general.request';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style>

        h1, h2 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое обращение в разделе "Инновации"</h1>
    <h2>Запрос на технологию</h2>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">Название компании</th>
            <td th:text="${companyName}"/>
        </tr>

        <tr>
            <th scope="row">Ссылка на сайт</th>
            <td th:text="${companySite}"/>
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

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'innovations.window.demand';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style>

        h1, h2 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое обращение в разделе "Инновации"</h1>
    <h2>Отклик на запрос на технологию</h2>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

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
            <td th:text="${startupLink}"/>
        </tr>

        <tr>
            <th scope="row">Наименование стартапа</th>
            <td th:text="${startupName}"/>
        </tr>

        <tr>
            <th scope="row">ФИО представителя</th>
            <td th:text="${representativeName}"/>
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

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'innovations.window.proposal';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>
    <div>
        <h1>Статистика обращений через сайт РСХБ Digital за период <span th:text="${periodStart}"/> - <span
                th:text="${periodEnd}"/></h1>
    </div>
    <div th:each="stat: ${report.formStats}">
        <table class="table_col">
            <tbody>
            <tr>
                <th scope="row">Тип заявки</th>
                <td th:text="${stat.getFormTitle()}"/>
            </tr>

            <tr>
                <th scope="row">Общий итог с <span th:text="${stat.oldestRecordDate}"/></th>
                <td th:text="${stat.totalUsagesCount}"/>
            </tr>

            <tr>
                <th scope="row">Итог за неделю <span th:text="${periodStart}"/> - <span th:text="${periodEnd}"/></th>
                <td th:text="${stat.deltaUsagesCountForPeriod}"/>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'internal.statistics.form.usage.report';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style>

        h1 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">

<div>

    <h1>Новая заявка на стажировку</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${registrationDate}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${applicantName}"/>
        </tr>

        <tr>
            <th scope="row">Направление стажировки</th>
            <td th:text="${internshipField}"/>
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
            <td th:text="${about}"/>
        </tr>

        <tr th:if="${telegram != null && !telegram.isBlank()}">
            <th scope="row">Telegram</th>
            <td th:text="${telegram}"/>
        </tr>

        <tr>
            <th scope="row">Дата рождения</th>
            <td th:text="${birthDate}"/>
        </tr>

        <tr th:if="${city != null && !city.isBlank()}">
            <th scope="row">Город</th>
            <td th:text="${city}"/>
        </tr>

        <tr th:if="${university != null && !university.isBlank()}">
            <th scope="row">ВУЗ</th>
            <td th:text="${university}"/>
        </tr>

        <tr th:if="${eduStage != null && !eduStage.isBlank()}">
            <th scope="row">Ступень обучения</th>
            <td th:text="${eduStage}"/>
        </tr>

        <tr th:if="${eduYear != null}">
            <th scope="row">Курс</th>
            <td th:text="${eduYear}"/>
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
            <td th:text="${portfolio}"/>
        </tr>

        <tr th:if="${experience != null && !experience.isBlank()}">
            <th scope="row">Опыт работы или стажировки</th>
            <td th:text="${experience}"/>
        </tr>

        <tr th:if="${workHours != null && !workHours.isBlank()}">
            <th scope="row">Количество рабочих часов</th>
            <td th:text="${workHours}"/>
        </tr>

        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'internship.participation.request';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style>

        h1 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое обращение через форму обратной связи</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr th:if="${reporterName != null && !reporterName.isBlank()}">
            <th scope="row">ФИО</th>
            <td th:text="${reporterName}"/>
        </tr>

        <tr th:if="${phone != null && !phone.isBlank()}">
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr th:if="${feedback != null && !feedback.isBlank()}">
            <th scope="row">Вопрос или предложение</th>
            <td th:text="${feedback}"/>
        </tr>
        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'stipend.contest.question.request';

update digital_notifications.templates
set "content" = '<!DOCTYPE html>

<html lang="ru" xmlns:th="http://www.thymeleaf.org">

<head>
    <meta charset="UTF-8">
    <title>Уведомление РСХБ Digital</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style>

        h1 {
            font-family: Ubuntu, Arial, sans-serif;
            color: #89E159;
        }

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

        .meta_data {
            padding-top: 2em;
            font-size: 0.75em;
            color: silver;
        }
    </style>
</head>

<body style="background: #7686A0">
<div>

    <h1>Новое решение по задаче в разделе "Стипендиальный конкурс"</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно"/>
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}"/>
        </tr>

        <tr>
            <th scope="row">Задача</th>
            <td th:text="${task}"/>
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${participantName}"/>
        </tr>

        <tr>
            <th scope="row">Телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Email</th>
            <td th:text="${email}"/>
        </tr>

        <tr>
            <th scope="row">Комментарий</th>
            <td th:text="${comment}"/>
        </tr>

        </tbody>
    </table>

    <div class="meta_data">
        <span>mid:</span>
        <code th:text="${messageId}"/>
    </div>
</div>
</body>
</html>
'
where "key" = 'stipend.contest.solution.request';
