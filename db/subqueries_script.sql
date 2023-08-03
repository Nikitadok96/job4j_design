select
	first_name,
	last_name,
	age
from
	customers
where
	age = (select min(age) from customers);


select *
from customers
where customers.id not in (select orders.customer_id from orders);
	
