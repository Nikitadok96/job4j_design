create table person(
	id serial primary key,
	name varchar (255)
);

create table favcolor(
	id serial primary key,
	name varchar (255)
);

create table person_color(
	id serial primary key,
	person_id int references favcolor(id),
	favcolor_id int references mother(id)
);

insert into person(name) values ('Oleg');
insert into person(name) values ('Nikita');

insert into favcolor(name) values ('Black');
insert into favcolor(name) values ('White');
insert into favcolor(name) values ('Blue');

insert into person_color(person_id, favcolor_id) values (1, 1);
insert into person_color(person_id, favcolor_id) values (1, 2);
insert into person_color(person_id, favcolor_id) values (2, 2);
insert into person_color(person_id, favcolor_id) values (2, 3);