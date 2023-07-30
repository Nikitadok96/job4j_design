insert into car_bodies(name) values('Хетчбэк');
insert into car_bodies(name) values('Седан');
insert into car_bodies(name) values('Лифтбэк');

insert into car_engines(name) values('M272');
insert into car_engines(name) values('MZ');
insert into car_engines(name) values('M62');

insert into car_transmissions(name) values('Механическая');
insert into car_transmissions(name) values('Автоматическая');
insert into car_transmissions(name) values('Вариатор');

insert into cars(name, bodies_id, engines_id, transmissions)
values ('BMW M5', 1, 3, 2);
insert into cars(name, bodies_id, engines_id, transmissions)
values ('BMW M3', 1, 2, 2);
insert into cars(name, bodies_id, engines_id, transmissions)
values ('BMW M1', 2, 2, 2);
insert into cars(name, bodies_id, engines_id, transmissions)
values ('LADA Granta', 2, null, 1);
insert into cars(name, bodies_id, engines_id, transmissions)
values ('LADA Vesta', 2, 3, null);
insert into cars(name, bodies_id, engines_id, transmissions)
values ('unknown', null, null, null);