insert into oper (id, login, password, name, email)
    values(1, '${oper.login}', '${oper.password}', 'Admin', 'admin@host.loc');

insert into oper_role (oper_id, roles)
    values(1, 'ADMIN');
