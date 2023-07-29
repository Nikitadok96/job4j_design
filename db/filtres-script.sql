select * from product where product.type_id = 1;

select * from product where product.name LIKE '%мороженое%';

select * from product where product.expired_date < current_date;

select
	name,
	price
from
	product
where
	price = (select max(price) from product);
	
select 
	type.name as имя_типа, 
	count(product.id) as количество
from
	type
	join product
	on type.id = product.type_id
group by имя_типа;

select
	product.name,
	type.name
from
	product
	join type
	on	type.id = product.type_id
where
	type.id = 1
	or type.id = 2;
	
select
	type.name as Категория,
	count(product.type_id) as Количество
from
	type
	join product on type.id = product.type_id
group by type.name
having count(product.type_id) > 10;

select
	product.name,
	type.name
from
	product
	join type on product.type_id = type.id;