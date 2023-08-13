with t1 as (
	select
		company.name as Company,
		count(person.id) as Person_Count
	from
		company
	join
		person
	on company.id = person.company_id
	group by 
		company.name
)

select 
	*
from 
	t1
where
	Person_Count = (select max(Person_Count) from t1)	