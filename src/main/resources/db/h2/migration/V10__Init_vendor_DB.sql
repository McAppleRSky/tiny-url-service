CREATE SEQUENCE hibernate_sequence start 1 increment 1;

CREATE TABLE direction (
    id int4 NOT NULL,
    path varchar(3) NOT NULL,
    url varchar(120) NOT NULL,
    expire timestamp,
    usr_id int4 NOT NULL,
    PRIMARY KEY (id) );

CREATE TABLE usr (
    id int4 NOT NULL,
    email varchar(128),
    login varchar(32) NOT NULL,
    name varchar(75) NOT NULL,
    password varchar(16) NOT NULL,
    PRIMARY KEY (id) );

CREATE TABLE user_role (
    user_id int4 NOT NULL,
    roles varchar(8) );

ALTER TABLE if exists direction
    ADD CONSTRAINT direction_path_uk unique (path);

ALTER TABLE if exists direction
    ADD CONSTRAINT direction_url_uk unique (url);

ALTER TABLE if exists usr
    ADD CONSTRAINT usr_login_uk unique (login);

ALTER TABLE if exists direction
    ADD CONSTRAINT direction_user_fk
    FOREIGN KEY (usr_id) REFERENCES usr;

ALTER TABLE if exists user_role
    ADD CONSTRAINT user_role_user_fk
    FOREIGN KEY (user_id) REFERENCES usr;
