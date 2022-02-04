insert into oper (login, password, name, email)
    values('${oper.login}', '${oper.password}', 'Admin', 'admin@host.loc');

insert into oper_role (oper_id, roles)
    values(1, 'ADMIN');
