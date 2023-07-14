create table tutor(
	id serial primary key,
	name varchar (255),
	office int
);

create table student(
	id serial primary key,
	name varchar (255),
	tutor_id int references tutor(id)
);

insert into tutor(name, office) values ('Светлана', 200);
insert into tutor(name, office) values ('Ирина', 300);

insert into student(name, tutor_id) values ('Никита', 1);
insert into student(name, tutor_id) values ('Дмитрий', 1);
insert into student(name, tutor_id) values ('Виктор', 1);
insert into student(name, tutor_id) values ('Олег', 2);
insert into student(name) values ('Семён');

select * from student
join tutor on student.tutor_id = tutor.id;
select s.name, tu.name, tu.office, tu.id 
from student as s join tutor as tu on s.tutor_id = tu.id;
select s.name as "Имя студента", tu.name as "Имя куратора"
from student as s join tutor as tu on s.tutor_id = tu.id;
