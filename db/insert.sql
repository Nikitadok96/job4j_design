insert into users(name) values ('Никита');
insert into users(name) values ('Олег');

insert into roles(name, user_id) values ('Логист', 1);
insert into roles(name, user_id) values ('Менеджер', 2);

insert into rules(name) values ('Отправить посылку');
insert into rules(name) values ('Забрать посылку');
insert into rules(name) values ('Продать');

insert into rules_roles(rule_id, role_id) values (1, 1);
insert into rules_roles(rule_id, role_id) values (2, 1);
insert into rules_roles(rule_id, role_id) values (1, 2);
insert into rules_roles(rule_id, role_id) values (3, 2);

insert into items(name, user_id) values ('600A', 1);
insert into items(name, user_id) values ('700A', 1);
insert into items(name, user_id) values ('800A', 2);

insert into comments(name, item_id) values ('Срочно', 1);
insert into comments(name, item_id) values ('Самовывоз', 2);
insert into comments(name, item_id) values ('Почта', 3);

insert into attachs(name, item_id) values ('ТТН', 1);
insert into attachs(name, item_id) values ('Акт реализации', 2);
insert into attachs(name, item_id) values ('Акт реализации', 3);

insert into categories(name, item_id) values ('Важная', 1);
insert into categories(name, item_id) values ('Основная', 2);
insert into categories(name, item_id) values ('Основная', 3);

insert into states(name, item_id) values ('В работе', 1);
insert into states(name, item_id) values ('Ожидает', 2);
insert into states(name, item_id) values ('В работе', 3);