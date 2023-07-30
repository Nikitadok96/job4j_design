insert into departments(name) values ('Продажи');
insert into departments(name) values ('Логистика');
insert into departments(name) values ('Сервис');

insert into employees(name, departments_id) values ('Ксения', 1);
insert into employees(name, departments_id) values ('Галина', 1);
insert into employees(name, departments_id) values ('Юлия', 3);
insert into employees(name, departments_id) values ('Татьяна', 3);
insert into employees(name, departments_id) values ('Никита', null);

insert into teens(name, gender) values ('Никита', 'male');
insert into teens(name, gender) values ('Олег', 'male');
insert into teens(name, gender) values ('Дмитрий', 'male');
insert into teens(name, gender) values ('Марина', 'female');
insert into teens(name, gender) values ('Ксения', 'female');