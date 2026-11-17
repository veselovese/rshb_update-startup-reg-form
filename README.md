# Руководство по переносу изменений

## Назначение и процесс работы

Этот репозиторий — подготовленная версия изменений.

### Необходимые действия

> Реализовать в этом репозитории обновление формы регистрации стартапа по контракту и описанию из подготовительного репозитория.
>
> Перенести функциональность в структуру целевого проекта, не выкладывая подготовительный репозиторий и не копируя проект целиком. Обновить endpoint регистрации, DTO и валидацию, справочники категорий и стадий, сохранение заявки, внутреннее уведомление и тесты. Сохранить поле логотипа как необязательную загрузку файлом через `multipart/form-data`.
>
> Добавить Liquibase-миграцию по правилам целевого репозитория. Версию/номер changeset определить относительно миграций, уже имеющихся в целевом репозитории; не считать `release-0.41.0` обязательным номером для него. Убедиться, что схема обновляется до запуска новой версии backend.
>
> После реализации выполнить подходящие тесты и проверки целевого проекта. Влить изменения в автодеплойную ветку только после review и согласования выкладки с фронтендом.

## Краткое описание

Обновляется форма регистрации стартапа:

- `POST /api/v1/owb-ms-plt-app/startups/registration` принимает `multipart/form-data`.
- Всего в контракте 19 полей: 12 обязательных и 7 необязательных.
- `attachmentLogo` необязателен. Если файл передан, поддерживаются SVG, EPS и PNG; файл добавляется к внутреннему уведомлению.
- Категории — множественный выбор. Идентификаторы категорий и стадии берутся из справочника.
- Liquibase-миграция `release-0.41.0` добавляет новые поля и таблицу связи заявки с несколькими категориями.

## Контракт для фронтенда

Сначала запросить справочники:

```http
GET /api/v1/owb-ms-plt-app/startups/registration/refs
```

Использовать `id` из `categories` для поля `categoryIds`, а `id` из `projectStage` — для `projectStageId`. Отправлять идентификаторы, а не отображаемые названия.

### Обязательные поля

| Поле | Тип / ограничения |
|---|---|
| `applicantName` | Строка, ФИО контактного лица |
| `email` | Строка, корректный email |
| `phone` | Строка, корректный телефон |
| `city` | Строка, город |
| `projectName` | Строка, до 255 символов |
| `foundedYear` | Целое число, от 2000 до текущего года |
| `descriptionShort` | Строка, до 300 символов |
| `descriptionFull` | Строка, до 1000 символов |
| `revenueRange` | Одно значение из списка ниже |
| `categoryIds` | Один или несколько ID категорий, без повторов |
| `projectStageId` | ID одной стадии |
| `hasAiSolutions` | `true` или `false` |

Допустимые значения `revenueRange`: `Нет`, `До 1 млн`, `1-10 млн`, `10-50 млн`, `50-100 млн`, `>100 млн`.

### Необязательные поля

| Поле | Тип / ограничения |
|---|---|
| `organization` | Строка, юридическое лицо |
| `inn` | Строка из 10 или 12 цифр |
| `websiteUrl` | URL сайта с `http://` или `https://` |
| `investorName` | Строка, ФИО инвестора |
| `investmentsValue` | Целое число от 0 до 999999999999 |
| `videoUrl` | Ссылка на Rutube или VK Видео |
| `attachmentLogo` | Файл SVG, EPS или PNG |

Если необязательное поле не заполнено, его можно не добавлять в запрос. `attachmentLogo` отправляется как файл; при отсутствии логотипа поле не передавать.

## Порядок внедрения

1. В подготовительном репозитории изменения содержатся в README и DTO.
2. Создать заявку в целевом репозитории с автодеплоем и сослаться на конкретные branch/commit подготовительного репозитория.
3. Попросить исполнителя перенести функциональность в целевой репозиторий. Пути файлов и версия миграции могут отличаться; ориентироваться на код, контракт и правила целевого проекта.
4. Перед review выполнить проверки в целевом проекте. В текущем подготовительном проекте использовалась команда:

   ```powershell
   .\gradlew :feature:startups:core:test :digital-service:compileJava :subsystem:migration:postgres:processResources
   ```

5. Проверить в целевом релизном процессе порядок миграции и запуска приложения. Если Liquibase не запускается автоматически, согласовать применение миграции **до запуска backend**.
6. После review влить изменения в автодеплойную ветку целевого репозитория. Именно этот merge должен запустить автодеплой.
7. После выкладки выполнить проверки из раздела «Проверки после выкладки».

Команда `processResources` проверяет обработку ресурсов при сборке, но сама по себе не применяет миграцию к базе данных. Команду и названия Gradle-задач для целевого проекта необходимо сверить с его сборкой.

## Референсные файлы в подготовительном репозитории

Следует перенести соответствующую функциональность, а не заменять одноимённые файлы целевого проекта без проверки.

### API, DTO и entity

- `digital-service/src/main/java/ru/rshb/intech/digital/service/controller/startups/StartupRegistrationController.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/dto/StartupRegistrationDto.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/dto/StartupRegLookupDto.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/entity/StartupRegistration.java`

### Обработка формы, справочники и уведомления

- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/repository/StartupCategoryRepository.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/StartupRegistrationLookupService.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/StartupRegistrationLookupServiceImpl.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/StartupRegistrationService.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/StartupRegistrationServiceImpl.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/form/StartupRegistrationForm.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/form/attribute/Categories.java` — новый файл
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/form/attribute/StartupRegistrationDetails.java` — новый файл
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/form/attribute/DescriptionShort.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/form/attribute/InvestmentsValue.java`
- `feature/startups/core/src/main/java/ru/rshb/intech/digital/feature/startups/core/service/registration/form/notification/InternalNotificationMessage.java`
- `templates/email/startups.registration.internal.notification.html`
- `subsystem/messages/src/main/resources/messages.properties`

### Миграция, сообщения и тесты

- `subsystem/migration/postgres/src/main/resources/changelog/db.dao-master.xml`
- `subsystem/migration/postgres/src/main/resources/changelog/changesets/release-0.41.0/db.dao-init.xml` — новый файл
- `subsystem/migration/postgres/src/main/resources/changelog/changesets/release-0.41.0/sql/update-startup-registration-form.sql` — новый файл
- `feature/startups/core/src/test/java/ru/rshb/intech/digital/feature/startups/dto/StartupRegistrationDtoTest.java` — новый файл

При публикации изменений в подготовительном репозитории убедиться, что commit содержит **новые файлы и удаления** старых атрибутов формы: `CategoryCustom`, `CategoryId`, `City`, `ClientsNumber`, `Country`, `FinancingRound`, `InvestmentsCurrency`, `Organization`, `Position`, `StaffSize`, `VkProfileUrl`.

## Проверки после выкладки

- `GET .../startups/registration/refs` возвращает категории и стадии.
- Заявка с корректными обязательными полями принимается без логотипа.
- При наличии допустимого файла он прикрепляется к уведомлению.
- Заявка с несколькими категориями сохраняется.
- Пропуск обязательного поля или неподдерживаемый формат логотипа обрабатывается без успешного ответа.