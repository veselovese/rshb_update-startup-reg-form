alter table digital_startups.reg_showcase
    alter column org drop not null,
    alter column country_id drop not null,
    alter column city_id drop not null,
    alter column proj_stage_id drop not null,
    alter column clients_id drop not null,
    alter column fin_round_id drop not null,
    add column city_name varchar(255),
    add column inn varchar(12),
    add column website_url varchar(2048),
    add column investor_name varchar(255),
    add column revenue_range varchar(50),
    add column has_ai_solutions boolean;

update digital_startups.reg_proj_stage_ref
set disabled = true
where title = 'Поздний рост';

insert into digital_startups.reg_cat_ref (external_id, title)
values ('cfd3e949-27c6-40a7-a12c-29a09456e1f1', 'Системы управления переработкой'),
       ('cfd3e949-27c6-40a7-a12c-29a09456e1f2', 'Пищевые и кормовые добавки'),
       ('cfd3e949-27c6-40a7-a12c-29a09456e1f3', 'Генетика и селекция'),
       ('cfd3e949-27c6-40a7-a12c-29a09456e1f4', 'Переработка отходов'),
       ('cfd3e949-27c6-40a7-a12c-29a09456e1f5', 'Технологии хранения'),
       ('cfd3e949-27c6-40a7-a12c-29a09456e1f6', 'Упаковка'),
       ('cfd3e949-27c6-40a7-a12c-29a09456e1f7', 'Ветеринария'),
       ('cfd3e949-27c6-40a7-a12c-29a09456e1f8', 'Цифровая платформа');

create table digital_startups.reg_showcase_categories
(
    registration_id bigint not null,
    category_id     bigint not null,

    constraint reg_showcase_categories_pk
        primary key (registration_id, category_id),
    constraint reg_showcase_categories_registration_fk
        foreign key (registration_id)
            references digital_startups.reg_showcase (id)
            on delete cascade,
    constraint reg_showcase_categories_category_fk
        foreign key (category_id)
            references digital_startups.reg_cat_ref (id)
            on delete cascade
);
