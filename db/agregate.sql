create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into people(name) values ('Никита'), ('Света'), ('Даниил');
insert into devices(name, price)
values ('IPhone 14', 7500), ('Xiaomi 12 Lite', 4500), ('Samsung S23', 6500);
insert into devices_people(device_id, people_id)
values (1,1), (2,1), (2,2), (2,3), (3,3);

select avg(devices.price) as "Средняя цена" from devices;

select pe.name Имя, avg(dev.price) "Средняя цена устройста"
from devices as dev
join devices_people as dp
on dev.id = dp.device_id
join people as pe
on pe.id = dp.people_id
group by pe.name;

select pe.name Имя, avg(dev.price) "Средняя цена устройста"
from devices as dev
join devices_people as dp
on dev.id = dp.device_id
join people as pe
on pe.id = dp.people_id
group by pe.name
having avg(dev.price) > 5000;