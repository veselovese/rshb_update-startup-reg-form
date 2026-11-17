insert into digital_notifications.templates (id, key, type, default_subject, content)
values
    (11, 'news.subscription.activation.confirmation', 'EMAIL', null, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/xhtml" lang="ru">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Рассылка RSHB DIGITAL</title>
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
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:400;line-height: 24px;margin:0;padding:0;padding-bottom:16px;width:100%">
                                                                                    Пожалуйста, подтвердите подписку на
                                                                                    еженедельный дайджест новостей РСХБ
                                                                                    в цифре.</p>
                                                                                <p style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height: 22px;margin:0;padding:0;padding-bottom:6px;width:100%">
                                                                                    Если вы получили это письмо по
                                                                                    ошибке, проигнорируйте его.</p>
                                                                            </td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>

                                                                </td>
                                                            </tr>
                                                            <tr style="margin:0;padding:0">
                                                                <td valign="center" align="left"
                                                                    style="margin:0;padding:0;padding-left:25px;padding-right:25px;width:100%">
                                                                    <table align="center" cellpadding="0"
                                                                           cellspacing="0"
                                                                           role="presentation" width="100%"
                                                                           style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                        <tbody style="margin:0;padding:0">
                                                                        <tr style="margin:0;padding:0">
                                                                            <td valign="center"
                                                                                align="center"
                                                                                style="height:138px;margin:0;padding:0;width:100%">
                                                                                <a rel="noopener noreferrer"
                                                                                   th:href="${activationUrl}"
                                                                                   target="_blank"
                                                                                   title="Новости"
                                                                                   class="block-button"
                                                                                   style="background:#89E159;border-radius:12px;color:#131A25;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:20px;font-style:normal;font-weight:500;line-height:32px;margin:0;padding:15px 55px 19px;text-decoration:none;cursor: pointer;"><b>Подтвердить
                                                                                    подписку</b></a></td>
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

insert into digital_notifications.templates (id, key, type, default_subject, content)
values
    (12, 'news.subscription.mailing.digest', 'EMAIL', null, '<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" lang="ru">

<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>Рассылка RSHB DIGITAL</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <style type="text/css">
        @media (max-width: 600px) {
            .menu-link {
                font-size: 14px !important;
            }

            .main-header {
                font-size: 24px !important;
                line-height: 105px !important;
            }

            .secondary-header {
                font-size: 24px !important;
            }

            .link-item {
                font-size: 14px !important;
            }

            .text-item {
                font-size: 12px !important;
            }
        }
    </style>
</head>

<body class="main" style="font-family:Ubuntu,Arial,sans-serif;padding: 0;margin: 0;">
    <div style="background:#1F2431;margin:0;padding:0;">
        <table width="100%" cellspacing="0" cellpadding="0" border="0"
            style="border-collapse:collapse;margin:0;padding:0">
            <tbody style="margin:0;padding:0">
                <tr style="margin:0;padding:0">
                    <td align="center" valign="top" bgcolor="#1F2431"
                        style="border-collapse:collapse;margin:0;padding:0">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0"
                            style="border-collapse:collapse;margin:0;max-width:600px;padding:0;width:100%">
                            <tbody style="margin:0;padding:0">
                                <tr style="margin:0;padding:0">
                                    <td align="center" valign="top" bgcolor="#7686A0"
                                        style="border-collapse:collapse;margin:0;padding:0">
                                        <table width="100%" cellspacing="0" cellpadding="0" border="0"
                                            style="border-collapse:collapse;margin:0;padding:0">
                                            <tbody style="margin:0;padding:0">
                                                <tr style="margin:0;padding:0">
                                                    <td style="border-collapse:collapse;margin:0;padding:0">
                                                        <table bgcolor="#faf1d1" align="center" cellpadding="0"
                                                            cellspacing="0" role="presentation" width="100%"
                                                            style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                            <tbody style="margin:0;padding:0">
                                                                <tr style="margin:0;padding:0">
                                                                    <td background="https://rshbdigital.ru/img/mail-header-top-new.jpg"
                                                                        bgcolor="#1F2431" valign="top"
                                                                        style="background-position:left top;background-repeat:no-repeat;background-size:contain;border-collapse:collapse;box-sizing:border-box;margin:0 auto;max-width:100%;padding:0">
                                                                        <div style="margin:0;padding:0">
                                                                            <table border="0" cellpadding="0"
                                                                                cellspacing="0" role="presentation"
                                                                                width="100%"
                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0">
                                                                                <tbody
                                                                                    style="box-sizing:border-box;margin:0;padding:0">
                                                                                    <tr
                                                                                        style="box-sizing:border-box;margin:0;padding:0">
                                                                                        <td valign="top" width="205"
                                                                                            style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;padding-bottom:28px;padding-left:30px;padding-right:20px;padding-top:28px;vertical-align:top;width:205px">
                                                                                            <a rel="noopener noreferrer"
                                                                                                href="https://rshbdigital.ru/"
                                                                                                title="RSHB DIGITAL"
                                                                                                style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:71px;min-width:136px;padding:0;text-decoration:none"
                                                                                                target="_blank"><img
                                                                                                    height="auto"
                                                                                                    width="136"
                                                                                                    alt="RSHB DIGITAL"
                                                                                                    src="https://rshbdigital.ru/img/logo_a.png"
                                                                                                    style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;max-width:100%;outline:0;padding:0;text-decoration:none;width:136px"
                                                                                                    class="CToWUd"
                                                                                                    jslog="138226; u014N:xr6bB; 53:W2ZhbHNlLDBd"></a>
                                                                                        </td>
                                                                                        <td align="center"
                                                                                            style="margin:0;padding:0">
                                                                                            <a rel="noopener noreferrer"
                                                                                                href="https://rshbdigital.ru/articles/"
                                                                                                target="_blank"
                                                                                                title="Новости"
                                                                                                class="menu-link"
                                                                                                style="color:#FFF;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:20px;font-style:normal;font-weight:500;margin:0;padding:0;padding-right:10px;text-decoration:none">Новости</a>
                                                                                        </td>
                                                                                        <td align="center"
                                                                                            style="margin:0;padding:0">
                                                                                            <a rel="noopener noreferrer"
                                                                                                href="https://rshbdigital.ru/events/"
                                                                                                target="_blank"
                                                                                                title="События"
                                                                                                class="menu-link"
                                                                                                style="color:#FFF;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:20px;font-style:normal;font-weight:500;margin:0;padding:0;padding-right:10px;text-decoration:none">События</a>
                                                                                        </td>
                                                                                        <td align="center"
                                                                                            style="margin:0;padding:0">
                                                                                            <a rel="noopener noreferrer"
                                                                                                href="https://rshbdigital.ru/podcast/"
                                                                                                target="_blank"
                                                                                                title="Подкасты"
                                                                                                class="menu-link"
                                                                                                style="color:#FFF;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:20px;font-style:normal;font-weight:500;margin:0;padding:0;padding-right:10px;text-decoration:none">Подкасты</a>
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
                                                        <table bgcolor="#faf1d1" align="center" cellpadding="0"
                                                            cellspacing="0" role="presentation" width="100%"
                                                            style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                            <tbody style="margin:0;padding:0">
                                                                <tr style="margin:0;padding:0">
                                                                    <td bgcolor="#1F2431" valign="top" align="center"
                                                                        style="margin:0;padding:0;overflow: hidden;">
                                                                        <p class="main-header"
                                                                            style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:40px;font-style:normal;font-weight:700;margin:0;padding:40px 10px;width:100%">
                                                                            Еженедельный дайджест</p>
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
                                                        <table align="center" cellpadding="0" cellspacing="0"
                                                            role="presentation" width="100%"
                                                            style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                            <tbody style="margin:0;padding:0">


                                                                <!-- БЛОК "НОВОСТИ" -->
                                                                <tr style="margin:0;padding:0"
                                                                    th:if="${newsItems != null && !newsItems.isEmpty()}">
                                                                    <td valign="top" align="center"
                                                                        style="margin:0;padding:0">
                                                                        <div class="block-bg"
                                                                            style="background-color:#1F2431;background-image:url(https://rshbdigital.ru/img/mail-dots-net.png);background-position:left top;background-repeat:no-repeat;margin:0;padding:0">
                                                                            <table width="100%" cellspacing="0"
                                                                                cellpadding="0" border="0"
                                                                                style="border-collapse:collapse;margin:0;padding:0">
                                                                                <tbody style="margin:0;padding:0">
                                                                                    <tr style="margin:0;padding:0">
                                                                                        <td
                                                                                            style="border-collapse:collapse;margin:0;padding:0">

                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">
                                                                                                    <tr
                                                                                                        style="margin:0;padding:0">
                                                                                                        <td valign="center"
                                                                                                            align="left"
                                                                                                            style="height:82px;margin:0;padding:0;padding-left:25px;width:100%">
                                                                                                            <p class="secondary-header"
                                                                                                                style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:34px;font-style:normal;font-weight:500;margin:0;padding:0;padding-bottom:6px;width:100%">
                                                                                                                Актуальные
                                                                                                                новости
                                                                                                            </p>
                                                                                                            <p class="secondary-header-border"
                                                                                                                style="background-color:#89E159;height:4px;margin:0;padding:0;width:60px">
                                                                                                            </p>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </tbody>
                                                                                            </table>

                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">

                                                                                                    <!-- СПИСОК НОВОСТЕЙ -->
                                                                                                    <tr style="margin:0;padding:0"
                                                                                                        th:each="newsItem: ${newsItems}">
                                                                                                        <td valign="center"
                                                                                                            align="left"
                                                                                                            style="margin:0;padding:0;padding-left:25px;padding-right:25px;padding-top:25px;width:100%">

                                                                                                            <table
                                                                                                                align="center"
                                                                                                                cellpadding="0"
                                                                                                                cellspacing="0"
                                                                                                                role="presentation"
                                                                                                                width="100%"
                                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                                                                                <tbody
                                                                                                                    style="margin:0;padding:0">

                                                                                                                    <tr
                                                                                                                        style="margin:0;padding:0">
                                                                                                                        <td valign="top"
                                                                                                                            align="center"
                                                                                                                            style="margin:0;padding:0;width:125px">

                                                                                                                            <table
                                                                                                                                align="center"
                                                                                                                                cellpadding="0"
                                                                                                                                cellspacing="0"
                                                                                                                                role="presentation"
                                                                                                                                width="100%"
                                                                                                                                style="border-collapse:collapse;box-sizing:border-box;height:100px;margin:0;padding:0;width:125px">
                                                                                                                                <tbody
                                                                                                                                    style="margin:0;padding:0">

                                                                                                                                    <!-- ИЗОБРАЖЕНИЕ НОВОСТИ -->
                                                                                                                                    <tr
                                                                                                                                        style="margin:0;padding:0">
                                                                                                                                        <td valign="top"
                                                                                                                                            align="right"
                                                                                                                                            th:background="${newsItem.imageUrl}"
                                                                                                                                            style="background-position:center;background-repeat:no-repeat;background-size:cover;border-collapse:collapse;border-radius:12px;box-sizing:border-box;margin:0 auto;max-width:100%;padding:6px">

                                                                                                                                            <p class="preview-text-news"
                                                                                                                                                style="background:#fff;border-radius:38px;margin:0;padding:6px;text-align:right;width:max-content">
                                                                                                                                                <span
                                                                                                                                                    class="preview-text-data-news"
                                                                                                                                                    style="color:#7B828F;display:block;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;margin:0;padding:0"
                                                                                                                                                    th:text="${newsItem.publicationDateFormatted}">
                                                                                                                                                </span>
                                                                                                                                            </p>
                                                                                                                                        </td>
                                                                                                                                    </tr>
                                                                                                                                </tbody>
                                                                                                                            </table>

                                                                                                                        </td>

                                                                                                                        <td valign="top"
                                                                                                                            style="margin:0;max-width:400px;padding:0">
                                                                                                                            <table
                                                                                                                                align="center"
                                                                                                                                cellpadding="0"
                                                                                                                                cellspacing="0"
                                                                                                                                role="presentation"
                                                                                                                                width="100%"
                                                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                                                                                                <tbody
                                                                                                                                    style="margin:0;padding:0">

                                                                                                                                    <!-- ССЫЛКА НА НОВОСТЬ -->
                                                                                                                                    <tr
                                                                                                                                        style="margin:0;padding:0">
                                                                                                                                        <td valign="center"
                                                                                                                                            align="left"
                                                                                                                                            style="margin:0;padding:0;padding-bottom:10px;padding-left:20px">
                                                                                                                                            <a rel="noopener noreferrer"
                                                                                                                                                target="_blank"
                                                                                                                                                title="Новости"
                                                                                                                                                class="link-item"
                                                                                                                                                style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:20px;font-style:normal;font-weight:400;line-height:28px;margin:0;padding:0;text-decoration-line:underline"
                                                                                                                                                th:text="${newsItem.title}"
                                                                                                                                                th:href="${newsItem.url}">
                                                                                                                                            </a>
                                                                                                                                        </td>
                                                                                                                                    </tr>

                                                                                                                                </tbody>
                                                                                                                            </table>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                </tbody>
                                                                                                            </table>
                                                                                                            <p class="under-border"
                                                                                                                style="background-color:#9EA4AF;height:1px;margin:0;margin-top:25px;padding:0;width:100%">
                                                                                                            </p>
                                                                                                        </td>
                                                                                                    </tr>

                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr style="margin:0;padding:0">
                                                                                        <td valign="center" align="left"
                                                                                            style="margin:0;padding:0;padding-left:25px;padding-right:25px;width:100%">
                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">
                                                                                                    <tr
                                                                                                        style="margin:0;padding:0">
                                                                                                        <td valign="center"
                                                                                                            align="center"
                                                                                                            style="height:138px;margin:0;padding:0;width:100%">
                                                                                                            <a rel="noopener noreferrer"
                                                                                                                href="https://rshbdigital.ru/articles/"
                                                                                                                target="_blank"
                                                                                                                title="Новости"
                                                                                                                class="block-button"
                                                                                                                style="background:#89E159;border-radius:15px;color:#131A25;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:500;line-height:24px;margin:0;padding:15px 55px 19px;text-decoration:none"><b>Читать
                                                                                                                    на
                                                                                                                    сайте</b></a>
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

                                                                <!-- БЛОК "СОБЫТИЯ" -->
                                                                <tr style="margin:0;padding:0"
                                                                    th:if="${eventsItems != null && !eventsItems.isEmpty()}">
                                                                    <td valign="top" align="center"
                                                                        style="margin:0;padding:0">
                                                                        <div class="block-bg"
                                                                            style="background-color:#1F2431;background-image:url(https://rshbdigital.ru/img/mail-dots-net.png);background-position:left top;background-repeat:no-repeat;margin:0;padding:0">
                                                                            <table width="100%" cellspacing="0"
                                                                                cellpadding="0" border="0"
                                                                                style="border-collapse:collapse;margin:0;padding:0">
                                                                                <tbody style="margin:0;padding:0">
                                                                                    <tr style="margin:0;padding:0">
                                                                                        <td
                                                                                            style="border-collapse:collapse;margin:0;padding:0">
                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">
                                                                                                    <tr
                                                                                                        style="margin:0;padding:0">
                                                                                                        <td valign="center"
                                                                                                            align="left"
                                                                                                            style="height:82px;margin:0;padding:0;padding-left:25px;width:100%">
                                                                                                            <p class="secondary-header"
                                                                                                                style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:34px;font-style:normal;font-weight:500;margin:0;padding:0;padding-bottom:6px;width:100%">
                                                                                                                Интересные
                                                                                                                события
                                                                                                            </p>
                                                                                                            <p class="secondary-header-border"
                                                                                                                style="background-color:#89E159;height:4px;margin:0;padding:0;width:60px">
                                                                                                            </p>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </tbody>
                                                                                            </table>

                                                                                            <!-- СПИСОК СОБЫТИЙ -->
                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">


                                                                                                    <tr style="margin:0;padding:0"
                                                                                                        th:each="eventItem: ${eventsItems}">
                                                                                                        <td valign="center"
                                                                                                            align="left"
                                                                                                            style="margin:0;padding:0;padding-left:25px;padding-right:25px;padding-top:25px;width:100%">
                                                                                                            <table
                                                                                                                align="center"
                                                                                                                cellpadding="0"
                                                                                                                cellspacing="0"
                                                                                                                role="presentation"
                                                                                                                width="100%"
                                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                                                                                <tbody
                                                                                                                    style="margin:0;padding:0">
                                                                                                                    <tr
                                                                                                                        style="margin:0;padding:0">
                                                                                                                        <td valign="top"
                                                                                                                            align="center"
                                                                                                                            style="margin:0;padding:0;width:125px">
                                                                                                                            <table
                                                                                                                                align="center"
                                                                                                                                cellpadding="0"
                                                                                                                                cellspacing="0"
                                                                                                                                role="presentation"
                                                                                                                                width="100%"
                                                                                                                                style="border-collapse:collapse;box-sizing:border-box;height:100px;margin:0;padding:0;width:125px">
                                                                                                                                <tbody
                                                                                                                                    style="margin:0;padding:0">
                                                                                                                                    <tr
                                                                                                                                        style="margin:0;padding:0">
                                                                                                                                        <td valign="top"
                                                                                                                                            align="right"
                                                                                                                                            background="https://rshbdigital.ru/img/mail-mock-img-event.png"
                                                                                                                                            style="margin:0;padding:6px">
                                                                                                                                            <p class="preview-text"
                                                                                                                                                style="border-radius:12px;margin:0;padding:4px;text-align:right">
                                                                                                                                                <span
                                                                                                                                                    class="preview-text-data"
                                                                                                                                                    style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:18px;margin:0;padding:0"
                                                                                                                                                    th:text="${eventItem.formattedTimeInterval}"></span>
                                                                                                                                                <span
                                                                                                                                                    class="preview-text-value"
                                                                                                                                                    style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:500;line-height:20px;margin:0;padding:0"
                                                                                                                                                    th:text="${eventItem.place}"></span>
                                                                                                                                            </p>
                                                                                                                                        </td>
                                                                                                                                    </tr>
                                                                                                                                </tbody>
                                                                                                                            </table>
                                                                                                                        </td>
                                                                                                                        <td valign="top"
                                                                                                                            style="margin:0;max-width:400px;padding:0">
                                                                                                                            <table
                                                                                                                                align="center"
                                                                                                                                cellpadding="0"
                                                                                                                                cellspacing="0"
                                                                                                                                role="presentation"
                                                                                                                                width="100%"
                                                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                                                                                                <tbody
                                                                                                                                    style="margin:0;padding:0">
                                                                                                                                    <tr
                                                                                                                                        style="margin:0;padding:0">
                                                                                                                                        <td valign="center"
                                                                                                                                            align="left"
                                                                                                                                            style="margin:0;padding:0;padding-bottom:10px;padding-left:20px">
                                                                                                                                            <a rel="noopener noreferrer"
                                                                                                                                                target="_blank"
                                                                                                                                                title="Новости"
                                                                                                                                                class="link-item"
                                                                                                                                                style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:20px;font-style:normal;font-weight:400;line-height:28px;margin:0;padding:0;text-decoration-line:underline"
                                                                                                                                                th:text="${eventItem.title}"
                                                                                                                                                th:href="${eventItem.url}"></a>
                                                                                                                                        </td>
                                                                                                                                    </tr>
                                                                                                                                </tbody>
                                                                                                                            </table>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                </tbody>
                                                                                                            </table>
                                                                                                            <p class="under-border"
                                                                                                                style="background-color:#9EA4AF;height:1px;margin:0;margin-top:25px;padding:0;width:100%">
                                                                                                            </p>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr style="margin:0;padding:0">
                                                                                        <td valign="center" align="left"
                                                                                            style="margin:0;padding:0;padding-left:25px;padding-right:25px;width:100%">
                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">
                                                                                                    <tr
                                                                                                        style="margin:0;padding:0">
                                                                                                        <td valign="center"
                                                                                                            align="center"
                                                                                                            style="height:138px;margin:0;padding:0;width:100%">
                                                                                                            <a rel="noopener noreferrer"
                                                                                                                href="https://rshbdigital.ru/events/"
                                                                                                                target="_blank"
                                                                                                                title="Новости"
                                                                                                                class="block-button"
                                                                                                                style="background:#89E159;border-radius:15px;color:#131A25;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:500;line-height:24px;margin:0;padding:15px 55px 19px;text-decoration:none"><b>Весь
                                                                                                                    календарь</b></a>
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

                                                                <!-- БЛОК "ПОДКАСТЫ" -->
                                                                <tr style="margin:0;padding:0"
                                                                    th:if="${podcastsItems != null && !podcastsItems.isEmpty()}">
                                                                    <td valign="top" align="center"
                                                                        style="margin:0;padding:0">
                                                                        <div class="block-bg"
                                                                            style="background-color:#1F2431;background-image:url(https://rshbdigital.ru/img/mail-dots-net.png);background-position:left top;background-repeat:no-repeat;margin:0;padding:0">
                                                                            <table width="100%" cellspacing="0"
                                                                                cellpadding="0" border="0"
                                                                                style="border-collapse:collapse;margin:0;padding:0">
                                                                                <tbody style="margin:0;padding:0">
                                                                                    <tr style="margin:0;padding:0">
                                                                                        <td
                                                                                            style="border-collapse:collapse;margin:0;padding:0">
                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">
                                                                                                    <tr
                                                                                                        style="margin:0;padding:0">
                                                                                                        <td valign="center"
                                                                                                            align="left"
                                                                                                            style="height:82px;margin:0;padding:0;padding-left:25px;width:100%">
                                                                                                            <p class="secondary-header"
                                                                                                                style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:34px;font-style:normal;font-weight:500;margin:0;padding:0;padding-bottom:6px;width:100%">
                                                                                                                Новые
                                                                                                                подкасты
                                                                                                            </p>
                                                                                                            <p class="secondary-header-border"
                                                                                                                style="background-color:#89E159;height:4px;margin:0;padding:0;width:60px">
                                                                                                            </p>
                                                                                                        </td>
                                                                                                    </tr>
                                                                                                </tbody>
                                                                                            </table>


                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">

                                                                                                    <!-- СПИСОК ПОДКАСТОВ -->
                                                                                                    <tr style="margin:0;padding:0"
                                                                                                        th:each="podcastItem: ${podcastsItems}">
                                                                                                        <td valign="center"
                                                                                                            align="left"
                                                                                                            style="margin:0;padding:0;padding-left:25px;padding-right:25px;padding-top:25px;width:100%">
                                                                                                            <table
                                                                                                                align="center"
                                                                                                                cellpadding="0"
                                                                                                                cellspacing="0"
                                                                                                                role="presentation"
                                                                                                                width="100%"
                                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                                                                                <tbody
                                                                                                                    style="margin:0;padding:0">
                                                                                                                    <tr
                                                                                                                        style="margin:0;padding:0">
                                                                                                                        <td valign="top"
                                                                                                                            align="center"
                                                                                                                            style="margin:0;padding:0;width:125px">
                                                                                                                            <table
                                                                                                                                align="center"
                                                                                                                                cellpadding="0"
                                                                                                                                cellspacing="0"
                                                                                                                                role="presentation"
                                                                                                                                width="100%"
                                                                                                                                style="border-collapse:collapse;box-sizing:border-box;height:100px;margin:0;padding:0;width:125px">
                                                                                                                                <tbody
                                                                                                                                    style="margin:0;padding:0">
                                                                                                                                    <tr
                                                                                                                                        style="margin:0;padding:0">
                                                                                                                                        <td valign="top"
                                                                                                                                            align="right"
                                                                                                                                            background="https://rshbdigital.ru/img/mail-mock-img-podcast.png"
                                                                                                                                            style="margin:0;padding:6px">
                                                                                                                                            <p class="preview-text"
                                                                                                                                                style="border-radius:12px;margin:0;padding:4px;text-align:right">
                                                                                                                                                <span
                                                                                                                                                    class="preview-text-data"
                                                                                                                                                    style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:18px;margin:0;padding:0"
                                                                                                                                                    th:text="${podcastItem.publicationDateFormatted}"></span>
                                                                                                                                                <span
                                                                                                                                                    class="preview-text-value"
                                                                                                                                                    style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:500;line-height:20px;margin:0;padding:0"
                                                                                                                                                    th:text="${podcastItem.subTitle}"></span>
                                                                                                                                            </p>
                                                                                                                                        </td>
                                                                                                                                    </tr>
                                                                                                                                </tbody>
                                                                                                                            </table>
                                                                                                                        </td>
                                                                                                                        <td valign="top"
                                                                                                                            style="margin:0;max-width:400px;padding:0">
                                                                                                                            <table
                                                                                                                                align="center"
                                                                                                                                cellpadding="0"
                                                                                                                                cellspacing="0"
                                                                                                                                role="presentation"
                                                                                                                                width="100%"
                                                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                                                                                                <tbody
                                                                                                                                    style="margin:0;padding:0">
                                                                                                                                    <tr
                                                                                                                                        style="margin:0;padding:0">
                                                                                                                                        <td valign="center"
                                                                                                                                            align="left"
                                                                                                                                            style="margin:0;padding:0;padding-bottom:10px;padding-left:20px">
                                                                                                                                            <a rel="noopener noreferrer"
                                                                                                                                                target="_blank"
                                                                                                                                                title="Новости"
                                                                                                                                                class="link-item"
                                                                                                                                                style="color:#89E159;font-family:Ubuntu,Arial,sans-serif;font-size:20px;font-style:normal;font-weight:400;line-height:28px;margin:0;padding:0;text-decoration-line:underline"
                                                                                                                                                th:href="${podcastItem.url}"
                                                                                                                                                th:text="${podcastItem.title}"></a>
                                                                                                                                        </td>
                                                                                                                                    </tr>
                                                                                                                                    <tr
                                                                                                                                        style="margin:0;padding:0">
                                                                                                                                        <td valign="center"
                                                                                                                                            align="left"
                                                                                                                                            style="margin:0;padding:0;padding-left:20px">
                                                                                                                                            <p class="text-item"
                                                                                                                                                style="color:#FFF;font-family:Ubuntu,Arial,sans-serif;font-size:14px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0"
                                                                                                                                                th:text="${podcastItem.summary}">
                                                                                                                                            </p>
                                                                                                                                        </td>
                                                                                                                                    </tr>
                                                                                                                                </tbody>
                                                                                                                            </table>
                                                                                                                        </td>
                                                                                                                    </tr>
                                                                                                                </tbody>
                                                                                                            </table>
                                                                                                            <p class="under-border"
                                                                                                                style="background-color:#9EA4AF;height:1px;margin:0;margin-top:25px;padding:0;width:100%">
                                                                                                            </p>
                                                                                                        </td>
                                                                                                    </tr>

                                                                                                </tbody>
                                                                                            </table>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr style="margin:0;padding:0">
                                                                                        <td valign="center" align="left"
                                                                                            style="margin:0;padding:0;padding-left:25px;padding-right:25px;width:100%">
                                                                                            <table align="center"
                                                                                                cellpadding="0"
                                                                                                cellspacing="0"
                                                                                                role="presentation"
                                                                                                width="100%"
                                                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:600px;padding:0;width:100%">
                                                                                                <tbody
                                                                                                    style="margin:0;padding:0">
                                                                                                    <tr
                                                                                                        style="margin:0;padding:0">
                                                                                                        <td valign="center"
                                                                                                            align="center"
                                                                                                            style="height:138px;margin:0;padding:0;width:100%">
                                                                                                            <a rel="noopener noreferrer"
                                                                                                                href="https://rshbdigital.ru/articles/"
                                                                                                                target="_blank"
                                                                                                                title="Новости"
                                                                                                                class="block-button"
                                                                                                                style="background:#89E159;border-radius:15px;color:#131A25;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:16px;font-style:normal;font-weight:500;line-height:24px;margin:0;padding:15px 55px 19px;text-decoration:none"><b>Другие
                                                                                                                    подкасты</b></a>
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
                                                                <tr style="margin:0;padding:0">
                                                                    <td style="margin:0;padding:0">
                                                                        <p class="under-border-content"
                                                                            style="background-color:#89E159;height:2px;margin:0;padding:0;width:100%">
                                                                        </p>
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
                                                        <center style="margin:0;max-width:600px;padding:0;width:100%">
                                                            <table bgcolor="#1F2431" align="center" cellpadding="0"
                                                                cellspacing="0" role="presentation" width="100%"
                                                                style="border-collapse:collapse;box-sizing:border-box;margin:0;padding:0;width:100%">
                                                                <tbody style="margin:0;padding:0">
                                                                    <tr style="margin:0;padding:0">
                                                                        <td style="margin:0;padding:0"><span
                                                                                style="display:inline-block;margin:0;padding:0;width:275px">
                                                                                <table bgcolor="#1F2431" align="center"
                                                                                    cellpadding="0" cellspacing="0"
                                                                                    role="presentation" width="100%"
                                                                                    style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:300px;padding:0;width:100%">
                                                                                    <tbody style="margin:0;padding:0">
                                                                                        <tr style="margin:0;padding:0">
                                                                                            <td bgcolor="#1F2431"
                                                                                                valign="center"
                                                                                                align="left"
                                                                                                style="margin:0;padding:0;padding-left:25px;padding-top:25px;width:50%">
                                                                                                <a rel="noopener noreferrer"
                                                                                                    href="https://www.rshb.ru/"
                                                                                                    title="Россельхозбанк"
                                                                                                    style="box-sizing:border-box;display:inline-block;height:auto;margin:0;max-width:100%;min-width:176px;padding:0;text-decoration:none"
                                                                                                    target="_blank"><img
                                                                                                        height="auto"
                                                                                                        width="176"
                                                                                                        alt="Россельхозбанк"
                                                                                                        src="https://rshbdigital.ru/img/mail-rshb-logo.png"
                                                                                                        style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;max-width:100%;outline:0;padding:0;text-decoration:none;width:176px"></a>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr style="margin:0;padding:0">
                                                                                            <td bgcolor="#1F2431"
                                                                                                valign="center"
                                                                                                align="center"
                                                                                                style="margin:0;padding:0;padding-bottom:20px;padding-left:25px;padding-top:32px">
                                                                                                <p class="copyright" style="color:#BEC3C9;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0;text-align:left">
                                                                                                    © 2000-2023 АО «Россельхозбанк»
                                                                                                    <br style="margin:0;padding:0">Генеральная лицензия Банка<br style="margin:0;padding:0">
                                                                                                    России № 3349 от 12 августа 2015
                                                                                                </p>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </span><span
                                                                                style="display:inline-block;margin:0;padding:0;width:298px">
                                                                                <table bgcolor="#1F2431" align="center"
                                                                                    cellpadding="0" cellspacing="0"
                                                                                    role="presentation" width="100%"
                                                                                    style="border-collapse:collapse;box-sizing:border-box;margin:0;max-width:300px;padding:0;width:100%">
                                                                                    <tbody style="margin:0;padding:0">
                                                                                        <tr style="margin:0;padding:0">
                                                                                            <td bgcolor="#1F2431"
                                                                                                valign="center"
                                                                                                align="center"
                                                                                                style="margin:0;padding:0;padding-left:25px;padding-top:25px;width:50%">
                                                                                                <table width="100%"
                                                                                                    cellspacing="0"
                                                                                                    cellpadding="0"
                                                                                                    border="0"
                                                                                                    style="border-collapse:collapse;margin:0;padding:0">
                                                                                                    <tbody
                                                                                                        style="margin:0;padding:0">
                                                                                                        <tr
                                                                                                            style="margin:0;padding:0">
                                                                                                            <td style="border-collapse:collapse;margin:0;padding:0"
                                                                                                                align="left"
                                                                                                                width="60px">
                                                                                                                <a rel="noopener noreferrer"
                                                                                                                    href="https://habr.com/ru/company/rshb/blog/"
                                                                                                                    title="habr"
                                                                                                                    style="box-sizing:border-box;display:inline-block;height:auto;margin:0;max-width:100%;min-width:45px;padding:0;text-decoration:none"
                                                                                                                    target="_blank"><img
                                                                                                                        height="auto"
                                                                                                                        width="45"
                                                                                                                        alt="habr"
                                                                                                                        src="https://rshbdigital.ru/img/mail-habr.png"
                                                                                                                        style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:45px"></a>
                                                                                                            </td>
                                                                                                            <td style="border-collapse:collapse;margin:0;padding:0"
                                                                                                                align="left"
                                                                                                                width="53px">
                                                                                                                <a rel="noopener noreferrer"
                                                                                                                    href="https://vc.ru/rshb"
                                                                                                                    title="vc"
                                                                                                                    style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:21px;padding:0;text-decoration:none"
                                                                                                                    target="_blank"><img
                                                                                                                        height="auto"
                                                                                                                        width="21"
                                                                                                                        alt="vc"
                                                                                                                        src="https://rshbdigital.ru/img/mail-vc.png"
                                                                                                                        style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:32px"></a>
                                                                                                            </td>
                                                                                                            <td style="border-collapse:collapse;margin:0;padding:0"
                                                                                                                align="left"
                                                                                                                width="47px">
                                                                                                                <a rel="noopener noreferrer"
                                                                                                                    href="https://t.me/agrotech_startup"
                                                                                                                    title="telegram"
                                                                                                                    style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:18px;padding:0;text-decoration:none"
                                                                                                                    target="_blank"><img
                                                                                                                        height="auto"
                                                                                                                        width="18"
                                                                                                                        alt="telegram"
                                                                                                                        src="https://rshbdigital.ru/img/mail-telegram.png"
                                                                                                                        style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:27px"></a>
                                                                                                            </td>
                                                                                                            <td style="border-collapse:collapse;margin:0;padding:0"
                                                                                                                align="left"
                                                                                                                width="88px">
                                                                                                                <a rel="noopener noreferrer"
                                                                                                                    href="https://rutube.ru/channel/24967187/"
                                                                                                                    title="rutube"
                                                                                                                    style="box-sizing:border-box;display:inline-block;margin:0;max-width:100%;min-height:auto;min-width:67px;padding:0;text-decoration:none"
                                                                                                                    target="_blank"><img
                                                                                                                        height="auto"
                                                                                                                        width="67"
                                                                                                                        alt="rutube"
                                                                                                                        src="https://rshbdigital.ru/img/mail-rutube-2012-logo.png"
                                                                                                                        style="border:0;box-sizing:border-box;display:block;font-size:13px;height:auto;margin:0;outline:0;padding:0;text-decoration:none;width:67px"></a>
                                                                                                            </td>
                                                                                                            <td style="border-collapse:collapse;margin:0;padding:0"
                                                                                                                align="left"
                                                                                                                width="47px">
                                                                                                                <a rel="noopener noreferrer"
                                                                                                                    href="https://zen.yandex.ru/rshbdigital"
                                                                                                                    title="zen"
                                                                                                                    style="background:url(https://rshbdigital.ru/img/mail-zen-new-icon.png) no-repeat;box-sizing:border-box;display:inline-block;height:22px;margin:0;max-width:100%;min-height:auto;min-width:22px;padding:0;text-decoration:none;width:22px"
                                                                                                                    target="_blank"></a>
                                                                                                            </td>
                                                                                                        </tr>
                                                                                                    </tbody>
                                                                                                </table>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr style="margin:0;padding:0">
                                                                                            <td bgcolor="#1F2431"
                                                                                                valign="center"
                                                                                                align="center"
                                                                                                style="margin:0;padding:0;padding-bottom:20px;padding-left:25px;padding-top:32px">
                                                                                                <p class="address"
                                                                                                    style="color:#FFF!important;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0;padding-bottom:5px;text-align:left;text-decoration:none">
                                                                                                    117647, г. Москва,
                                                                                                    Профсоюзная ул,
                                                                                                    д.125А<br
                                                                                                        style="margin:0;padding:0">E-mail:
                                                                                                    <a rel="noopener noreferrer"
                                                                                                        href="mailto:office@rshb.ru"
                                                                                                        class="address"
                                                                                                        target="_blank"
                                                                                                        style="color:#FFF!important;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:400;line-height:20px;margin:0;padding:0;padding-bottom:5px;text-align:left;text-decoration:none">office@rshb.ru</a>
                                                                                                </p>
                                                                                                <a rel="noopener noreferrer"
                                                                                                    style="color:#89E159;display:inline-block;font-family:Ubuntu,Arial,sans-serif;font-size:12px;font-style:normal;font-weight:500;line-height:20px;margin:0;padding:0;text-align:left;text-decoration:none;width:100%"
                                                                                                    class="unsubscribe-link"
                                                                                                    target="_blank"
                                                                                                    th:href="${unsubscribeLink}">Отписаться
                                                                                                    от рассылки</a>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                            </span>
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

</html>');

insert into digital_notifications.templates (id, key, type, default_subject, content)
values
    (13, 'internal.statistics.form.usage.report', 'EMAIL', 'Статистика заявок на сайте RSHB DIGITAL', '<!DOCTYPE html>

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
                    <td th:text="${stat.formType.getTitle()}" />
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
</html>');

insert into digital_notifications.topics(id, topic_key, enabled, deleted, "collate", template_id)
values (13, 'internal.statistics.form.usage.report', true, false, true, 13);

drop table digital_statistics.o_plt_notification_receivers;
