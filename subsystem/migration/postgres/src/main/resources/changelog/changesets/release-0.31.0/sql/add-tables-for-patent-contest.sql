-- Справочник городов по-умолчанию
create table digital_contests.patent_fish_def_city_ref
(
    id          bigint generated always as identity primary key,
    external_id varchar(100) not null unique,
    title       varchar(255) not null,
    "disabled"  bool         not null default false
);

insert into digital_contests.patent_fish_def_city_ref (external_id, title)
values ('45.000.000.000.1', 'Москва'),
       ('40.000.000.000.1', 'Санкт-Петербург'),
       ('50.401.000.000.1', 'Новосибирск'),
       ('65.401.000.000.1', 'Екатеринбург'),
       ('92.401.000.000.1', 'Казань');

-- Справочник вариантов количества клиентов
create table digital_contests.patent_fish_clients_num_ref
(
    id          bigint generated always as identity primary key,
    external_id varchar(100) not null unique,
    title       varchar(255) not null,
    "disabled"  bool         not null default false
);

insert into digital_contests.patent_fish_clients_num_ref (external_id, title)
values ('bd1066cf-cf60-44ff-92f4-fcde396177ed', '0'),
       ('fc93d134-00ab-430e-92ba-390d3cdabd48', '1-5'),
       ('38db46b3-b59a-4876-aaf1-24c874daf5ef', '6-10'),
       ('4e448443-ee68-45a2-864f-86cecddd4274', 'более 11');

-- Справочник типов коммерческой реализации
create table digital_contests.patent_fish_com_impl_ref
(
    id          bigint generated always as identity primary key,
    external_id varchar(100) not null unique,
    title       varchar(255) not null,
    "disabled"  bool         not null default false
);

insert into digital_contests.patent_fish_com_impl_ref (external_id, title)
values ('8d080e64-e00a-4091-b66a-f72f38e7837d', 'Коммерциализирован'),
       ('e02c2b4f-421d-4230-b8c4-141db8fae276', 'Не коммерциализирован');

-- Справочник типов раундов финансирования
create table digital_contests.patent_fish_fin_round_ref
(
    id          bigint generated always as identity primary key,
    external_id varchar(100) not null unique,
    title       varchar(255) not null,
    "disabled"  bool         not null default false
);

insert into digital_contests.patent_fish_fin_round_ref (external_id, title)
values ('dab24c64-cea7-4009-8dd7-8def8abcc6a8', 'Pre-Seed'),
       ('2339074a-a394-414b-9b09-4d39f0780e7f', 'Seed'),
       ('e69a71f0-a7ae-4d53-9823-ef12499275ca', 'Раунд A'),
       ('07c228be-7ab7-4bfa-ad50-542a87088ac2', 'Раунды B/C/D');

-- Справочник типов хозяйствующих субъектов
create table digital_contests.patent_fish_mng_subj_ref
(
    id          bigint generated always as identity primary key,
    external_id varchar(100) not null unique,
    title       varchar(255) not null,
    "disabled"  bool         not null default false
);

insert into digital_contests.patent_fish_mng_subj_ref (external_id, title)
values ('cab54ee6-156c-495a-87b8-eca7aef1c9f3', 'МСП'),
       ('404b377f-3a97-466f-b67f-fb35c1f2b673', 'ИП'),
       ('d48ca353-a107-43da-a58c-14c7fff676c4', 'ВУЗ'),
       ('1afefda1-7bae-41ff-9b9b-a67a44f08ec0', 'НИИ'),
       ('0828acec-3a49-4b59-ba41-58d0456513c6', 'Физ.лицо'),
       ('24760df4-2611-4798-bc54-66c74ee67e42', 'Крупное предприятие');

-- Справочник типов этапов проекта
create table digital_contests.patent_fish_proj_stage_ref
(
    id          bigint generated always as identity primary key,
    external_id varchar(100) not null unique,
    title       varchar(255) not null,
    "disabled"  bool         not null default false
);

insert into digital_contests.patent_fish_proj_stage_ref (external_id, title)
values ('0e4f97a9-b2fb-4d6d-878c-bb850a1248d1', 'НИОКР'),
       ('7335ed28-c885-41d9-b62b-3191f53f301a', 'Pre-MVP'),
       ('449460e5-0095-4328-9592-22681b662391', 'MVP'),
       ('6fc3783c-edef-415a-93c1-0a5339339dd8', 'Первые продажи'),
       ('dc28d2fa-1fbc-49f8-a690-3a1e28635bc9', 'Масштабирование'),
       ('b5166f45-95c3-4c06-a8ba-f7ef4f3e9307', 'Поздний рост');

-- Таблица с заявками на участие в конкурсе патентов (рыбное хозяйство)
create table digital_contests.patent_fish_regs
(
    id                 bigint generated always as identity primary key,
    submit_date        timestamp     not null,
    appl_name          varchar(255)  not null,
    position           varchar(100)  null,
    org                varchar(100)  null,
    proj_name          varchar(255)  not null,
    proj_stage         varchar(100)  not null,
    pat_reg_num        varchar(100)  not null,
    pat_reg_year       int           not null,
    pat_mpk            varchar(100)  not null,
    pat_holder         varchar(100)  not null,
    invent_descr_short varchar(300)  not null,
    invent_descr_full  varchar(3000) not null,
    mng_subj           varchar(100)  not null,
    email              varchar(254)  not null,
    phone              varchar(15)   not null,
    com_impl           varchar(100)  not null,
    site_url           varchar(2048) null,
    invest_amnt        varchar(30)   null,
    fin_round          varchar(100)  not null,
    clients_amount     varchar(100)  not null,
    staff_size         int           null,
    victories          varchar(255)  null,
    city_id            varchar(50)   not null,
    suspicious         bool          not null default false
);
