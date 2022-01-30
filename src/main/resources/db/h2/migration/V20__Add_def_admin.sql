insert into usr (id, login, password, name, email)
    values(1, '${usr.login}', '${usr.password}', 'Admin', 'admin@host.loc');

insert into user_role (user_id, roles)
    values(1, 'ADMIN');
