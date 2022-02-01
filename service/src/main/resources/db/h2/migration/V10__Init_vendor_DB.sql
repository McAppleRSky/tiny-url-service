CREATE SEQUENCE hibernate_sequence start with 1 increment by 1;

CREATE TABLE link (
    id integer NOT NULL,
    path varchar(3) NOT NULL,
    url varchar(120) NOT NULL,
    expire timestamp,
    oper_id integer NOT NULL,
    PRIMARY KEY (id) );

CREATE TABLE oper (
    id integer NOT NULL,
    email varchar(128),
    login varchar(32) NOT NULL,
    name varchar(75) NOT NULL,
    password varchar(16) NOT NULL,
    PRIMARY KEY (id) );

CREATE TABLE oper_role (
    oper_id integer NOT NULL,
    roles varchar(8) );

ALTER TABLE link
    ADD CONSTRAINT link_path_uk unique (path);

ALTER TABLE link
    ADD CONSTRAINT link_url_uk unique (url);

ALTER TABLE oper
    ADD CONSTRAINT oper_login_uk unique (login);

ALTER TABLE link
    ADD CONSTRAINT link_oper_fk
    FOREIGN KEY (oper_id) REFERENCES oper;

ALTER TABLE oper_role
    ADD CONSTRAINT oper_role_oper_fk
    FOREIGN KEY (oper_id) REFERENCES oper
