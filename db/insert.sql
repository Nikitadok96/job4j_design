insert into roles(name) values ('Логист');
insert into roles(name) values ('Менеджер');

insert into categories(name) values ('Важная');
insert into categories(name) values ('Основная');

insert into states(name) values ('В работе');
insert into states(name) values ('Ожидает');

insert into users(name, role_id) values ('Никита', 1);
insert into users(name, role_id) values ('Олег', 2);

insert into rules(name) values ('Отправить посылку');
insert into rules(name) values ('Забрать посылку');
insert into rules(name) values ('Продать');

insert into rules_roles(rule_id, role_id) values (1, 1);
insert into rules_roles(rule_id, role_id) values (2, 1);
insert into rules_roles(rule_id, role_id) values (1, 2);
insert into rules_roles(rule_id, role_id) values (3, 2);

insert into items(name, user_id, categorie_id, state_id) values ('600A', 1, 1, 1);
insert into items(name, user_id, categorie_id, state_id) values ('700A', 1, 2, 1);
insert into items(name, user_id, categorie_id, state_id) values ('800A', 2, 2, 2);

insert into comments(name, item_id) values ('Срочно', 1);
insert into comments(name, item_id) values ('Самовывоз', 2);
insert into comments(name, item_id) values ('Почта', 3);

insert into attachs(name, item_id) values ('ТТН', 1);
insert into attachs(name, item_id) values ('Акт реализации', 2);
insert into attachs(name, item_id) values ('Акт реализации', 3);

