create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    departments_id int references departments
);

create table teens (
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);