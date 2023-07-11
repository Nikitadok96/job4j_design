create table child(
	id serial primary key,
	name varchar (255)
);

create table mother(
	id serial primary key,
	name varchar (255)
);

create table child_mother(
	id serial primary key,
	child_id int references child(id) unique,
	mother_id int references mother(id) unique
);

insert into child(name) values ('Sasha');
insert into mother(name) values ('Oksana');

insert into child_mother(child_id, mother_id) values (1,1);

select * from child_mother;