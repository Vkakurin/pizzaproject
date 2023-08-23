insert into usr(user_id, username, password, email, active, activation_code)
values (1, 'admin123', 123, true, 123@mail.ru );

insert into user_role (user_id, roles)
values (1, 'USER'),(1,'ADMIN');
