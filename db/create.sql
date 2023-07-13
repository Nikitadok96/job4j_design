create table users (
	id serial primary key,
	name varchar (255)
);

create table rules(
	id serial primary key,
	name varchar (255)
);

create table roles(
	id serial primary key,
	name varchar (255),
	user_id int references users(id)
);

create table rules_roles(
	id serial primary key,
	rule_id int references rules(id),
	role_id int references roles(id)
);

create table items(
	id serial primary key,
	name varchar (255),
	user_id int references users(id)
);

create table comments (
	id serial primary key,
	name varchar (255),
	item_id int references items(id)
);

create table attachs (
	id serial primary key,
	name varchar(255),
	item_id int references items(id)
);

create table categories (
	id serial primary key,
	name varchar (255),
	item_id int references items(id)
);

create table states (
	id serial primary key,
	name varchar (255),
	item_id int references items(id)
);