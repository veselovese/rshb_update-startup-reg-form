ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN telegram varchar(300) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN birth_date date null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN city_id varchar(300) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN city_custom varchar(500) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN university_id varchar(300) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN university_custom varchar(500) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN edu_stage varchar(100) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN edu_year int8 null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN skills varchar(1000) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN desired_tasks varchar(1000) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN portfolio varchar(1000) null default null;

ALTER TABLE digital_internship.o_plt_registration
    ADD COLUMN experience varchar(1000) null default null;
