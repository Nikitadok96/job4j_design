create table workers(

	id serial primary key,

	name varchar (255),

	salary integer,

	verification boolean

);
insert into workers(name, salary, verification) values('Олег', 40000, true);
update workers set salary = 38000;
delete from workers;
select * from workers;