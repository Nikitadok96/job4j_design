insert into producer(name) values ('Моя семья');
insert into producer(name) values ('Простоквашино');
insert into producer(name) values ('Дороничи');

insert into product(name, producer_id) values ('Сок яблочный', 1);
insert into product(name, producer_id) values ('Сок апельсиновый', 1);
insert into product(name, producer_id) values ('Кефир 3.2%', 2);
insert into product(name, producer_id) values ('Молоко 3.2%', 2);
insert into product(name, producer_id) values ('Мясо курицы', 3);
insert into product(name, producer_id) values ('Мясо говяжье', 3);
insert into product(name, producer_id) values ('Пельмени', 3);

insert into client(name) values ('Никита');
insert into client(name) values ('Наташа');

insert into orders(product_id) values (1);
insert into orders(product_id) values (1);
insert into orders(product_id) values (2);
insert into orders(product_id) values (5);
insert into orders(product_id) values (5);
insert into orders(product_id) values (5);

insert into client_action(action, client_id, orders_id)
values ('create_order', 1, 1);
insert into client_action(action, client_id, orders_id)
values ('create_order', 1, 2);
insert into client_action(action, client_id, orders_id)
values ('create_order', 1, 4);
insert into client_action(action, client_id, orders_id)
values ('create_order', 2, 3);
insert into client_action(action, client_id, orders_id)
values ('create_order', 2, 6);