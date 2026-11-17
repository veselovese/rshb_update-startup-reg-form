insert into digital_notifications.templates (id, "key", "type", default_subject, "content")
values (17, 'contest.patent.new.registration.internal.notification', 'EMAIL', null, '<!DOCTYPE html>

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
    </style>
</head>

<body style="background: #7686A0">

<div>

    <h1>Новая заявка участие в конкурсе патентов</h1>

    <table class="table_col">
        <tbody>

        <tr th:if="${suspicious}">
            <th scope="row">Статус</th>
            <td th:text="Подозрительно" />
        </tr>

        <tr>
            <th scope="row">Дата</th>
            <td th:text="${submitDate}" />
        </tr>

        <tr>
            <th scope="row">ФИО</th>
            <td th:text="${applicantName}" />
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
            <td th:text="${organization}" />
        </tr>

        <tr th:if="${position != null && !position.isBlank()}">
            <th scope="row">Должность</th>
            <td th:text="${position}" />
        </tr>

        <tr>
            <th scope="row">Город</th>
            <td th:text="${city}"/>
        </tr>

        <tr th:if="${staffSize != null}">
            <th scope="row">Количество сотрудников</th>
            <td th:text="${staffSize}" />
        </tr>

        <tr th:if="${siteUrl != null && !siteUrl.isBlank()}">
            <th scope="row">Сайт</th>
            <td th:text="${siteUrl}" />
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
</div>
</body>
</html>');

insert into digital_notifications.templates (id, "key", "type", default_subject, "content")
values (
        18,
        'contest.patent.new.registration.applicant.notification',
        'EMAIL',
        'Ваша заявка принята — Конкурс патентов Россельхозбанка',

        '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/xhtml" lang="ru">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Ваша заявка принята — Конкурс патентов Россельхозбанка</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
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
    <table width="100%" cellspacing="0" cellpadding="0" border="0" style="border-collapse:collapse;margin:0;padding:0">
        <tbody style="margin:0;padding:0">
        <tr style="margin:0;padding:0">
            <td align="center" valign="top" bgcolor="#1F2431" style="border-collapse:collapse;margin:0;padding:0">
                <table width="100%" border="0" cellspacing="0" cellpadding="0"
                       style="border-collapse:collapse;margin:0;max-width:600px;padding:0;width:100%">
                    <tbody style="margin:0;padding:0">
                    <tr style="margin:0;padding:0">
                        <td align="center" valign="top" bgcolor="#1F2431"
                            style="border-collapse:collapse;margin:0;padding:0">
                            <table width="100%" cellspacing="0" cellpadding="0" border="0"
                                   style="border-collapse:collapse;margin:0;padding:0">
                                <tbody style="margin:0;padding:0">
                                <tr style="margin:0;padding:0">
                                    <td style="border-collapse:collapse;margin:0;padding:0">
                                        <table bgcolor="#1F2431" align="center" cellpadding="0" cellspacing="0"
                                               role="presentation" width="100%"
                                               style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                            <tbody style="margin:0;padding:0">
                                            <tr style="margin:0;padding:0">
                                                <td background="https://rshbdigital.ru/img/mail-header-top-new.jpg"
                                                    bgcolor="#1F2431" valign="top"
                                                    style="background-position:left top;background-repeat:no-repeat;background-size:contain;border-collapse:collapse;box-sizing:border-box;margin:0 auto;max-width:100%;padding:0">
                                                    <div style="margin:0;padding:0">
                                                        <table border="0" cellpadding="0" cellspacing="0"
                                                               role="presentation" width="100%"
                                                               style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0">
                                                            <tbody style="box-sizing:border-box;margin:0;padding:0">
                                                            <tr style="box-sizing:border-box;margin:0;padding:0">
                                                                <td valign="top" width="205"
                                                                    style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;padding-bottom:28px;padding-left:30px;padding-right:20px;padding-top:28px;vertical-align:top;width:205px">
                                                                    <a rel="noopener noreferrer"
                                                                       href="https://rshbdigital.ru/"
                                                                       title="RSHB DIGITAL"
                                                                       style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:71px;min-width:136px;padding:0;text-decoration:none"
                                                                       target="_blank"><img height="auto" width="136"
                                                                                            alt="RSHB DIGITAL"
                                                                                            src="https://rshbdigital.ru/img/logo_a.png"
                                                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;max-width:100%;outline:0;padding:0;text-decoration:none;width:136px"
                                                                                            class="CToWUd"
                                                                                            jslog="138226; u014N:xr6bB; 53:W2ZhbHNlLDBd"></a>
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
                            <table width="100%" cellspacing="0" cellpadding="0" border="0"
                                   style="border-collapse:collapse;margin:0;padding:0">
                                <tbody style="margin:0;padding:0">
                                <tr style="margin:0;padding:0">
                                    <td style="border-collapse:collapse;margin:0;padding:0">
                                        <table align="center" cellpadding="0" cellspacing="0" role="presentation"
                                               width="100%"
                                               style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                            <tbody style="margin:0;padding:0">
                                            <tr style="margin:0;padding:0">
                                                <td valign="top" align="center" style="margin:0;padding:0">
                                                    <div class="block-bg"
                                                         style="background-color:#1F2431;margin:0;padding:0">
                                                        <table width="100%" cellspacing="0" cellpadding="0" border="0"
                                                               style="border-collapse:collapse;margin:0;padding:0">
                                                            <tbody style="margin:0;padding:0">
                                                            <tr style="margin:0;padding:0">
                                                                <td style="border-collapse:collapse;margin:0;padding:0">
                                                                    <table align="center" cellpadding="0"
                                                                           cellspacing="0" role="presentation"
                                                                           width="100%"
                                                                           style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                        <tbody style="margin:0;padding:0">

                                                                        <tr style="margin:0;padding:0">
                                                                            <td valign="center" align="left"
                                                                                style="height:82px;margin:0;padding-top:25px;padding-left:25px;width:100%">
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:24px;font-style:normal;font-weight:500;line-height: 32px;margin:0;padding:0;padding-bottom:8px;width:100%">
                                                                                    Уважаемый участник конкурса, ваша заявка принята!</p>

                                                                            </td>
                                                                        </tr>

                                                                        <tr>
                                                                            <td valign="center" align="left" style="height:82px;margin:0;padding-top:25px;padding-left:25px;width:100%">
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:400;line-height: 22px;margin:0;padding:0;padding-bottom:24px;width:100%">
                                                                                    Благодарим вас за заявку на конкурс патентов Россельхозбанка в рыбопромышленной отрасли. Приём заявок на конкурс продлится до 31 августа 2023г., отбор участников для следующего этапа пройдёт с 1 по 21 сентября 2023г.</p>

                                                                                    <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:400;line-height: 22px;margin:0;padding:0;padding-bottom:24px;width:100%">
                                                                                        В случае успешного прохождения, организаторы конкурса свяжутся с вами по указанному в заявке почтовому адресу после окончания периода отбора.</p>

                                                                                        <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:400;line-height: 22px;margin:0;padding:0;padding-bottom:24px;width:100%">
                                                                                            При возникновении вопросов в рамках проведения конкурса вы можете обратиться на почту <a href="mailto:SoldatenkoVA@intech.rshb.ru" style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height:18px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%">SoldatenkoVA@intech.rshb.ru</a></p>

<p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:400;line-height: 22px;margin:0;padding:0;padding-bottom:24px;width:100%">
    Читайте новости агротеха и следите за трендами в России и мире в наших блогах в
<a href="https://t.me/rshbdigital" style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:13px;font-style:normal;font-weight:400;line-height:22px;margin:0;padding:0;text-align:left;width:100%">Телеграм</a> и на
<a href="https://vc.ru/rshb" style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:13px;font-style:normal;font-weight:400;line-height:22px;margin:0;padding:0;text-align:left;width:100%">VC</a>!
</p>
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height: 18px;margin:0;padding:0;width:100%">
                                                                                    С уважением, <br>
                                                                                    Команда платформы <a href="https://rshbdigital.ru/" style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height:18px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%">«РСХБ в цифре»</a></p>
                                                                                <p style="color:#8990A5;font-family:Ubuntu,Arial,sans-serif;font-size:10px;font-style:normal;font-weight:400;line-height: 16px;margin:0;padding:0;padding-bottom:40px;padding-top:20px;width:100%">
                                                                                    Письмо сгенерировано автоматически.</p>
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
                            <table width="100%" cellspacing="0" cellpadding="0" border="0"
                                   style="border-collapse:collapse;margin:0;padding:0">
                                <tbody style="margin:0;padding:0">
                                <tr style="margin:0;padding:0;border-top: 2px solid #8990A5">
                                    <td style="border-collapse:collapse;margin:0;padding:0">
                                        <center style="margin:0;max-width:600px;padding:0;width:100%">
                                            <table bgcolor="#1F2431" align="center" cellpadding="0" cellspacing="0"
                                                   role="presentation" width="100%"
                                                   style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                <tbody style="margin:0;padding:0">
                                                <tr style="margin:0;padding:0">
                                                    <td style="margin:0;padding:0"><span
                                                            style="display:inline-block;margin:0;padding:0;width:275px"><table
                                                            bgcolor="#1F2431" align="center" cellpadding="0"
                                                            cellspacing="0" role="presentation" width="100%"
                                                            style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:300px;padding:0;width:100%"><tbody
                                                            style="margin:0;padding:0"><tr style="margin:0;padding:0"><td
                                                            bgcolor="#1F2431" valign="center" align="left"
                                                            style="margin:0;padding:0;padding-left:25px;padding-top:25px;width:50%"><a
                                                            rel="noopener noreferrer" href="https://www.rshb.ru/"
                                                            title="Россельхозбанк"
                                                            style="box-sizing:border-box;display:inline-block;height:auto;margin:0;max-width:100%;min-width:176px;padding:0;text-decoration:none"
                                                            target="_blank"><img height="auto" width="176"
                                                                                 alt="Россельхозбанк"
                                                                                 src="https://rshbdigital.ru/img/mail-rshb-logo.png"
                                                                                 style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;max-width:100%;outline:0;padding:0;text-decoration:none;width:176px"></a></td></tr><tr
                                                            style="margin:0;padding:0"><td bgcolor="#1F2431"
                                                                                           valign="center"
                                                                                           align="center"
                                                                                           style="margin:0;padding:0;padding-bottom:20px;padding-left:25px;padding-top:32px"><p
                                                            class="copyright"
                                                            style="color:#BEC3C9;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0;text-align:left">© 2000-2023 АО «Россельхозбанк»<br
                                                            style="margin:0;padding:0">Генеральная лицензия Банка<br
                                                            style="margin:0;padding:0">России № 3349 от 12 августа 2015</p></td></tr></tbody></table></span><span
                                                            style="display:inline-block;margin:0;padding:0;width:298px"><table
                                                            bgcolor="#1F2431" align="center" cellpadding="0"
                                                            cellspacing="0" role="presentation" width="100%"
                                                            style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:300px;padding:0;width:100%"><tbody
                                                            style="margin:0;padding:0"><tr style="margin:0;padding:0"><td
                                                            bgcolor="#1F2431" valign="center" align="center"
                                                            style="margin:0;padding:0;padding-left:25px;padding-top:25px;width:50%"><table
                                                            width="100%" cellspacing="0" cellpadding="0" border="0"
                                                            style="border-collapse:collapse;margin:0;padding:0"><tbody
                                                            style="margin:0;padding:0"><tr style="margin:0;padding:0"><td
                                                            style="border-collapse:collapse;margin:0;padding:0"
                                                            align="left" width="60px"><a rel="noopener noreferrer"
                                                                                         href="https://habr.com/ru/company/rshb/blog/"
                                                                                         title="habr"
                                                                                         style="box-sizing:border-box;display:inline-block;height:auto;margin:0;max-width:100%;min-width:45px;padding:0;text-decoration:none"
                                                                                         target="_blank"><img
                                                            height="auto" width="45" alt="habr"
                                                            src="https://rshbdigital.ru/img/mail-habr.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:45px"></a></td><td
                                                            style="border-collapse:collapse;margin:0;padding:0"
                                                            align="left" width="53px"><a rel="noopener noreferrer"
                                                                                         href="https://vc.ru/rshb"
                                                                                         title="vc"
                                                                                         style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:21px;padding:0;text-decoration:none"
                                                                                         target="_blank"><img
                                                            height="auto" width="21" alt="vc"
                                                            src="https://rshbdigital.ru/img/mail-vc.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:32px"></a></td><td
                                                            style="border-collapse:collapse;margin:0;padding:0"
                                                            align="left" width="47px"><a rel="noopener noreferrer"
                                                                                         href="https://t.me/agrotech_startup"
                                                                                         title="telegram"
                                                                                         style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:18px;padding:0;text-decoration:none"
                                                                                         target="_blank"><img
                                                            height="auto" width="18" alt="telegram"
                                                            src="https://rshbdigital.ru/img/mail-telegram.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:27px"></a></td><td
                                                            style="border-collapse:collapse;margin:0;padding:0"
                                                            align="left" width="88px"><a rel="noopener noreferrer"
                                                                                         href="https://rutube.ru/channel/24967187/"
                                                                                         title="rutube"
                                                                                         style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:67px;padding:0;text-decoration:none"
                                                                                         target="_blank"><img
                                                            height="auto" width="67" alt="rutube"
                                                            src="https://rshbdigital.ru/img/mail-rutube-2012-logo.png"
                                                            style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:67px"></a></td><td
                                                            style="border-collapse:collapse;margin:0;padding:0"
                                                            align="left" width="47px"><a rel="noopener noreferrer"
                                                                                         href="https://zen.yandex.ru/rshbdigital"
                                                                                         title="zen"
                                                                                         style="background:url(https://rshbdigital.ru/img/mail-zen-new-icon.png) no-repeat;box-sizing:border-box;display:inline-block;height:22px;margin:0;max-width:100%;min-height:auto;min-width:22px;padding:0;text-decoration:none;width:22px"
                                                                                         target="_blank"></a></td></tr></tbody></table></td></tr><tr
                                                            style="margin:0;padding:0"><td bgcolor="#1F2431"
                                                                                           valign="center"
                                                                                           align="center"
                                                                                           style="margin:0;padding:0;padding-bottom:20px;padding-left:25px;padding-top:32px"><p
                                                            class="address"
                                                            style="color:#FFF!important;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0;padding-bottom:5px;text-align:left;text-decoration:none">Вы можете ознакомиться с "<a
                                                            rel="noopener noreferrer"
                                                            href="https://www.rshb.ru/pd-policy/"
                                                            style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:500;line-height:20px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%"
                                                            class="unsubscribe-link"
                                                            target="_blank">Политикой в отношении обработки персональных данных</a>" и "<a
                                                            rel="noopener noreferrer" href="#"
                                                            style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:500;line-height:20px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%"
                                                            class="unsubscribe-link"
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

insert into digital_notifications.topics (id, topic_key, enabled, deleted, "collate", template_id)
values (16, 'contest.patent.fish.registration.request', true, false, true, 17);