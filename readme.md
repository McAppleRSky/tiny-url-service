Стек технологий: Spring Boot starter MVC, Security (jwt), JDBC (flyway JPA h2), freemarker, pure javascript ajax json, docker ngnx linux.

Api

Get /* - принимает короткую ссылку, возвращает redirect по длинной ссылке
Get / - status 404

Get /api/0.0.1/operate - админка
Post /api/0.0.1/login - отправка логин, возвращает jwt(токены)
Post /api/0.0.1/token - отправляет refresh токен, возвращает access токен
Post /api/0.0.1/refresh - отправляет refresh токен, возвращает access, refresh токен (если не просрочен access)

Oper - operator

Get /api/0.0.1/opers - возвращает перечень пользователей
Get /api/0.0.1/oper/{id} - пользователь по id
Post /api/0.0.1/oper - создать пользователя
Patch /api/0.0.1/oper/{id} - редактировать пользователя
Delete /api/0.0.1/oper/{id} - удалить пользователя

link - shot path и long url, expire, redirect count
Get /api/0.0.1/link/{id} - линк по id (не задействован)
Post /api/0.0.1/link - создать линк
Patch /api/0.0.1/link/{id} - редактировать линк
Delete /api/0.0.1/link/{id} - удалить линк
