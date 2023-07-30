select
    departments.name as Департамент,
    employees.name as Работник
from
    departments
    left join employees
    on departments.id = employees.departments_id;
	
select
	departments.name as Департамент,
    employees.name as Работник
from
	departments
    right join employees
    on departments.id = employees.departments_id;

select
	departments.name as Департамент,
    employees.name as Работник
from
	departments
    full join employees
    on departments.id = employees.departments_id;
	
select
	departments.name as Департамент,
    employees.name as Работник
from
	departments
    cross join employees;
	
select
	departments.name as Департамент,
    employees.name as Работник
from
	departments
    left join employees
    on departments.id = employees.departments_id
where 
	employees.name is null;
	
select
    departments.name as Департамент,
    employees.name as Работник
from
    departments
    left join employees
    on departments.id = employees.departments_id
where
	employees.name is not null;
	
select
    departments.name as Департамент,
    employees.name as Работник
from
    departments
    right join employees
    on departments.id = employees.departments_id
where
	departments.name is not null;
	
select
	t1.name name_one,
	t2.name name_two
from
	teens as t1
	cross join teens as t2
where
	t1.gender != t2.gender
	and t1.name > t2.name;
