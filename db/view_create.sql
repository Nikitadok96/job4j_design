create table producer (
	id serial primary key,
	name text
);

create table product (
	id serial primary key,
	name text,
	producer_id int references producer(id)
);

create table client (
	id serial primary key,
	name text
);

create table orders (
	id serial primary key,
	product_id int references product(id)
);

create table client_action (
	id serial primary key,
	action text,
	client_id int references client(id),
	orders_id int references orders(id)
);

create view show_client_who_ordered_more_2_from_same_producer
as select
	client.name as Client,
	count(product.name) as count,
	producer.name as Producer
from
	client
	join client_action on client.id = client_action.client_id
	join orders on orders.id = client_action.orders_id
	join product on product.id = orders.product_id
	join producer on producer.id = product.producer_id
group by 
	(client.name, producer.name) 
having 
	count(product.name) >= 2;

