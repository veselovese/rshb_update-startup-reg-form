insert into digital_notifications.templates (id, "key", "type", default_subject, "content")
values (24, 'startups.registration.internal.notification', 'EMAIL', null, '<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" lang="ru">

<head>
    <meta charset="UTF-8">
    <title>Новая заявка на включение стартапа в витрину - РСХБ в цифре</title>
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

    <h1>Новая заявка на включение стартапа в витрину стартапов</h1>

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

        <tr th:if="${organization != null && !organization.isBlank()}">
            <th scope="row">Организация</th>
            <td th:text="${organization}"/>
        </tr>

        <tr th:if="${position != null && !position.isBlank()}">
            <th scope="row">Должность</th>
            <td th:text="${position}"/>
        </tr>

        <tr>
            <th scope="row">Контактный телефон</th>
            <td th:text="${phone}"/>
        </tr>

        <tr>
            <th scope="row">Электронная почта</th>
            <td th:text="${email}"/>
        </tr>

        <tr th:if="${vkProfileUrl != null && !vkProfileUrl.isBlank()}">
            <th scope="row">Профиль VK</th>
            <td th:text="${vkProfileUrl}"/>
        </tr>

        <tr>
            <th scope="row">Наименование проекта</th>
            <td th:text="${projectName}"/>
        </tr>

        <tr>
            <th scope="row">Страна</th>
            <td th:text="${country}" />
        </tr>

        <tr>
            <th scope="row">Город</th>
            <td th:text="${city}" />
        </tr>

        <tr>
            <th scope="row">Краткое описание проекта</th>
            <td th:text="${projectDescriptionShort}"/>
        </tr>

        <tr>
            <th scope="row">Полное описание проекта</th>
            <td th:text="${projectDescriptionFull}"/>
        </tr>

        <tr th:if="${investValue != null}">
            <th scope="row">Объем инвестиций</th>
            <td>
                <span th:text="${investValue}" />&nbsp;<span th:text="${investCurr}" />
            </td>
        </tr>

        <tr th:if="${category != null}">
            <th scope="row">Категория</th>
            <td th:text="${category}" />
        </tr>

        <tr>
            <th scope="row">Год основания стартапа</th>
            <td th:text="${foundedYear}" />
        </tr>

        <tr th:if="${staffSize != null}">
            <th scope="row">Кол-во сотрудников</th>
            <td th:text="${staffSize}" />
        </tr>

        <tr>
            <th scope="row">Этап проекта</th>
            <td th:text="${projectStage}" />
        </tr>

        <tr>
            <th scope="row">Кол-во клиентов</th>
            <td th:text="${clientsNumber}" />
        </tr>

        <tr>
            <th scope="row">Раунд финансирования</th>
            <td th:text="${finRound}" />
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
');

insert into digital_notifications.templates (id, "key", "type", default_subject, "content")
values (25, 'startups.registration.applicant.notification', 'EMAIL', 'Ваша заявка принята - РСХБ в цифре', '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="ru" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta content="text/html; charset=utf-8" http-equiv="content-type">
    <title>Ваша заявка принята — РСХБ в цифре</title>
    <link href="https://fonts.googleapis.com" rel="preconnect">
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <style type="text/css">@media (max-width: 600px) {
        .menu-link {
            font-size: 14px
        }

        .main-header {
            font-size: 24px;
            line-height: 105px
        }

        .secondary-header {
            font-size: 24px
        }

        .link-item {
            font-size: 14px
        }

        .text-item {
            font-size: 12px
        }
    }</style>
</head>
<body class="main" style="font-family:Ubuntu,Arial,sans-serif;padding: 0;margin: 0;">
<div style="background:#1F2431;margin:0;padding:0;">
    <table border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0;padding:0" width="100%">
        <tbody style="margin:0;padding:0">
        <tr style="margin:0;padding:0">
            <td align="center" bgcolor="#1F2431" style="border-collapse:collapse;margin:0;padding:0" valign="top">
                <table border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0;max-width:600px;padding:0;width:100%"
                       width="100%">
                    <tbody style="margin:0;padding:0">
                    <tr style="margin:0;padding:0">
                        <td align="center" bgcolor="#1F2431" style="border-collapse:collapse;margin:0;padding:0"
                            valign="top">
                            <table border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0;padding:0"
                                   width="100%">
                                <tbody style="margin:0;padding:0">
                                <tr style="margin:0;padding:0">
                                    <td style="border-collapse:collapse;margin:0;padding:0">
                                        <table align="center" bgcolor="#1F2431" cellpadding="0" cellspacing="0"
                                               role="presentation" style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%"
                                               width="100%">
                                            <tbody style="margin:0;padding:0">
                                            <tr style="margin:0;padding:0">
                                                <td background="https://rshbdigital.ru/img/mail-header-top-new.jpg"
                                                    bgcolor="#1F2431" style="background-position:left top;background-repeat:no-repeat;background-size:contain;border-collapse:collapse;box-sizing:border-box;margin:0 auto;max-width:100%;padding:0"
                                                    valign="top">
                                                    <div style="margin:0;padding:0">
                                                        <table border="0" cellpadding="0" cellspacing="0"
                                                               role="presentation" style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0"
                                                               width="100%">
                                                            <tbody style="box-sizing:border-box;margin:0;padding:0">
                                                            <tr style="box-sizing:border-box;margin:0;padding:0">
                                                                <td style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;padding-bottom:28px;padding-left:30px;padding-right:20px;padding-top:28px;vertical-align:top;width:205px" valign="top"
                                                                    width="205">
                                                                    <a href="https://rshbdigital.ru/"
                                                                       rel="noopener noreferrer"
                                                                       style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:71px;min-width:136px;padding:0;text-decoration:none"
                                                                       target="_blank"
                                                                       title="RSHB DIGITAL"><img alt="RSHB DIGITAL" class="CToWUd"
                                                                                                 height="auto"
                                                                                                 jslog="138226; u014N:xr6bB; 53:W2ZhbHNlLDBd"
                                                                                                 src="https://rshbdigital.ru/img/logo_a.png"
                                                                                                 style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;max-width:100%;outline:0;padding:0;text-decoration:none;width:136px"
                                                                                                 width="136"></a>
                                                                </td>

                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <table border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0;padding:0"
                                   width="100%">
                                <tbody style="margin:0;padding:0">
                                <tr style="margin:0;padding:0">
                                    <td style="border-collapse:collapse;margin:0;padding:0">
                                        <table align="center" cellpadding="0" cellspacing="0" role="presentation"
                                               style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%"
                                               width="100%">
                                            <tbody style="margin:0;padding:0">
                                            <tr style="margin:0;padding:0">
                                                <td align="center" style="margin:0;padding:0" valign="top">
                                                    <div class="block-bg"
                                                         style="background-color:#1F2431;margin:0;padding:0">
                                                        <table border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0;padding:0"
                                                               width="100%">
                                                            <tbody style="margin:0;padding:0">
                                                            <tr style="margin:0;padding:0">
                                                                <td style="border-collapse:collapse;margin:0;padding:0">
                                                                    <table align="center" cellpadding="0"
                                                                           cellspacing="0" role="presentation"
                                                                           style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%"
                                                                           width="100%">
                                                                        <tbody style="margin:0;padding:0">

                                                                        <tr style="margin:0;padding:0">
                                                                            <td align="left" style="height:82px;margin:0;padding-top:25px;padding-left:25px;width:100%"
                                                                                valign="center">
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:24px;font-style:normal;font-weight:500;line-height: 32px;margin:0;padding:0;padding-bottom:8px;width:100%">
                                                                                    Спасибо, что написали нам!</p>

                                                                            </td>
                                                                        </tr>

                                                                        <tr>
                                                                            <td align="left" style="height:82px;margin:0;padding-top:25px;padding-left:25px;width:100%"
                                                                                valign="center">
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:400;line-height: 22px;margin:0;padding:0;padding-bottom:24px;width:100%">
                                                                                    Мы ценим ваше внимание и постараемся
                                                                                    обработать ваш запрос в кратчайшие
                                                                                    сроки.</p>

                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:400;line-height: 22px;margin:0;padding:0;padding-bottom:24px;width:100%">
                                                                                    Читайте новости агротеха и следите
                                                                                    за трендами в России и мире в наших
                                                                                    блогах в
                                                                                    <a href="https://t.me/rshbdigital"
                                                                                       style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:13px;font-style:normal;font-weight:400;line-height:22px;margin:0;padding:0;text-align:left;width:100%">Телеграм</a>
                                                                                    и на
                                                                                    <a href="https://vc.ru/rshb"
                                                                                       style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:13px;font-style:normal;font-weight:400;line-height:22px;margin:0;padding:0;text-align:left;width:100%">VC</a>!
                                                                                </p>
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height: 18px;margin:0;padding:0;width:100%">
                                                                                    С уважением, <br>
                                                                                    Команда платформы <a
                                                                                        href="https://rshbdigital.ru/"
                                                                                        style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height:18px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%">«РСХБ
                                                                                    в цифре»</a></p>
                                                                                <p style="color:#8990A5;font-family:Ubuntu,Arial,sans-serif;font-size:10px;font-style:normal;font-weight:400;line-height: 16px;margin:0;padding:0;padding-bottom:40px;padding-top:20px;width:100%">
                                                                                    Письмо сгенерировано
                                                                                    автоматически.</p>
                                                                            </td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <table border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0;padding:0"
                                   width="100%">
                                <tbody style="margin:0;padding:0">
                                <tr style="margin:0;padding:0;border-top: 2px solid #8990A5">
                                    <td style="border-collapse:collapse;margin:0;padding:0">
                                        <center style="margin:0;max-width:600px;padding:0;width:100%">
                                            <table align="center" bgcolor="#1F2431" cellpadding="0" cellspacing="0"
                                                   role="presentation" style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%"
                                                   width="100%">
                                                <tbody style="margin:0;padding:0">
                                                <tr style="margin:0;padding:0">
                                                    <td style="margin:0;padding:0"><span
                                                            style="display:inline-block;margin:0;padding:0;width:275px"><table
                                                            align="center" bgcolor="#1F2431" cellpadding="0"
                                                            cellspacing="0" role="presentation" style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:300px;padding:0;width:100%"
                                                            width="100%"><tbody
                                                            style="margin:0;padding:0"><tr style="margin:0;padding:0"><td
                                                            align="left" bgcolor="#1F2431" style="margin:0;padding:0;padding-left:25px;padding-top:25px;width:50%"
                                                            valign="center"><a
                                                            href="https://www.rshb.ru/" rel="noopener noreferrer"
                                                            style="box-sizing:border-box;display:inline-block;height:auto;margin:0;max-width:100%;min-width:176px;padding:0;text-decoration:none"
                                                            target="_blank"
                                                            title="Россельхозбанк"><img alt="Россельхозбанк" height="auto"
                                                                                        src="https://rshbdigital.ru/img/mail-rshb-logo.png"
                                                                                        style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;max-width:100%;outline:0;padding:0;text-decoration:none;width:176px"
                                                                                        width="176"></a></td></tr><tr
                                                            style="margin:0;padding:0"><td align="center"
                                                                                           bgcolor="#1F2431"
                                                                                           style="margin:0;padding:0;padding-bottom:20px;padding-left:25px;padding-top:32px"
                                                                                           valign="center"><p
                                                            class="copyright"
                                                            style="color:#BEC3C9;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0;text-align:left">© 2000-2023 АО «Россельхозбанк»<br
                                                            style="margin:0;padding:0">Генеральная лицензия Банка<br
                                                            style="margin:0;padding:0">России № 3349 от 12 августа 2015</p></td></tr></tbody></table></span><span
                                                            style="display:inline-block;margin:0;padding:0;width:298px"><table
                                                            align="center" bgcolor="#1F2431" cellpadding="0"
                                                            cellspacing="0" role="presentation" style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:300px;padding:0;width:100%"
                                                            width="100%"><tbody
                                                            style="margin:0;padding:0"><tr style="margin:0;padding:0"><td
                                                            align="center" bgcolor="#1F2431" style="margin:0;padding:0;padding-left:25px;padding-top:25px;width:50%"
                                                            valign="center"><table
                                                            border="0" cellpadding="0" cellspacing="0" style="border-collapse:collapse;margin:0;padding:0"
                                                            width="100%"><tbody
                                                            style="margin:0;padding:0"><tr style="margin:0;padding:0"><td
                                                            align="left"
                                                            style="border-collapse:collapse;margin:0;padding:0" width="60px"><a href="https://habr.com/ru/company/rshb/blog/"
                                                                                                                                rel="noopener noreferrer"
                                                                                                                                style="box-sizing:border-box;display:inline-block;height:auto;margin:0;max-width:100%;min-width:45px;padding:0;text-decoration:none"
                                                                                                                                target="_blank"
                                                                                                                                title="habr"><img
                                                            alt="habr" height="auto" src="https://rshbdigital.ru/img/mail-habr.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:45px"
                                                            width="45"></a></td><td
                                                            align="left"
                                                            style="border-collapse:collapse;margin:0;padding:0" width="53px"><a href="https://vc.ru/rshb"
                                                                                                                                rel="noopener noreferrer"
                                                                                                                                style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:21px;padding:0;text-decoration:none"
                                                                                                                                target="_blank"
                                                                                                                                title="vc"><img
                                                            alt="vc" height="auto" src="https://rshbdigital.ru/img/mail-vc.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:32px"
                                                            width="21"></a></td><td
                                                            align="left"
                                                            style="border-collapse:collapse;margin:0;padding:0" width="47px"><a href="https://t.me/agrotech_startup"
                                                                                                                                rel="noopener noreferrer"
                                                                                                                                style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:18px;padding:0;text-decoration:none"
                                                                                                                                target="_blank"
                                                                                                                                title="telegram"><img
                                                            alt="telegram" height="auto" src="https://rshbdigital.ru/img/mail-telegram.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:27px"
                                                            width="18"></a></td><td
                                                            align="left"
                                                            style="border-collapse:collapse;margin:0;padding:0" width="88px"><a href="https://rutube.ru/channel/24967187/"
                                                                                                                                rel="noopener noreferrer"
                                                                                                                                style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:67px;padding:0;text-decoration:none"
                                                                                                                                target="_blank"
                                                                                                                                title="rutube"><img
                                                            alt="rutube" height="auto" src="https://rshbdigital.ru/img/mail-rutube-2012-logo.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:67px"
                                                            width="67"></a></td><td
                                                            align="left"
                                                            style="border-collapse:collapse;margin:0;padding:0" width="47px"><a href="https://zen.yandex.ru/rshbdigital"
                                                                                                                                rel="noopener noreferrer"
                                                                                                                                style="background:url(https://rshbdigital.ru/img/mail-zen-new-icon.png) no-repeat;box-sizing:border-box;display:inline-block;height:22px;margin:0;max-width:100%;min-height:auto;min-width:22px;padding:0;text-decoration:none;width:22px"
                                                                                                                                target="_blank"
                                                                                                                                title="zen"></a></td></tr></tbody></table></td></tr><tr
                                                            style="margin:0;padding:0"><td align="center"
                                                                                           bgcolor="#1F2431"
                                                                                           style="margin:0;padding:0;padding-bottom:20px;padding-left:25px;padding-top:32px"
                                                                                           valign="center"><p
                                                            class="address"
                                                            style="color:#FFF!important;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0;padding-bottom:5px;text-align:left;text-decoration:none">Вы можете ознакомиться с "<a
                                                            class="unsubscribe-link"
                                                            href="https://www.rshb.ru/pd-policy/"
                                                            rel="noopener noreferrer"
                                                            style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:500;line-height:20px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%"
                                                            target="_blank">Политикой в отношении обработки персональных данных</a>" и "<a
                                                            class="unsubscribe-link" href="#"
                                                            rel="noopener noreferrer"
                                                            style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:500;line-height:20px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%"
                                                            target="_blank">Пользовательским соглашением</a>"</p></td></tr></tbody></table></span>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </center>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
');

insert into digital_notifications.topics (id, topic_key, "enabled", deleted, "collate", template_id)
values (19, 'startups.registration.request', true, false, true, 24);