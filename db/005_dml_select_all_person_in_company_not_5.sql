select 
	p.name as "Имя",
	c.name as "Компания"
from 
	person as p
join
	company as c
on
	p.company_id = c.id
where company_id != 5;