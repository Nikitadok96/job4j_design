create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) 
values ('Walking fish', 27000, '304-12-22');
insert into fauna (name, avg_age, discovery_date)
values ('salmon fish', 12500, '1840-01-29');
insert into fauna (name, avg_age, discovery_date)
values ('fishwhite', 18300, '2001-04-17');
insert into fauna (name, avg_age, discovery_date)
values ('wolf', 20900, '100-01-01');
insert into fauna (name, avg_age, discovery_date)
values ('chicken', 1300, null);
insert into fauna (name, avg_age, discovery_date)
values ('moscow crab', 365, '2023-01-01');

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age >= 10000 AND avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';