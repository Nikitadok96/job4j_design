create table director(
	id serial primary key,
	name varchar(255)
);

create table workers(
	id serial primary key,
	name varchar (255),
	director_id int references director(id)
);

insert into director(name) values ('Elena');
insert into director(name) values ('Tatiana');

insert into workers(name, director_id) VALUES ('Marina', 1);
insert into workers(name, director_id) VALUES ('Galina', 2);

select * from director;

select * from workers;