CREATE SEQUENCE hibernate_sequence start with 1 increment by 1;
create table oper (
  id integer generated by default as identity primary key,
  email varchar(128),
  login varchar(32) not null,
  name varchar(75),
  password varchar(16) not null,
  constraint oper_login_UK unique (login)
);
create table oper_role (
  oper_id integer not null,
  roles varchar(8),
  constraint oper_role_oper_FK foreign key (oper_id) references oper
);
create table link (
  id integer not null primary key,
  expire timestamp,
  path varchar(3) not null,
  url varchar(120) not null,
  oper_id integer not null,
  constraint link_path_UK unique (path),
  constraint link_url_UK unique (url),
  constraint link_oper_foreign_FK foreign key (oper_id) references oper
);
