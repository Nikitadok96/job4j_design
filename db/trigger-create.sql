create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function tax_befor()
	returns trigger as
$$
	BEGIN
		NEW.price := NEW.price * 1.2;
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create function audit_price()
	returns trigger as
$$
	BEGIN
		insert into history_of_price
		select NEW.id, NEW.name, NEW.price, now();
		return NULL;
	END;
$$
LANGUAGE 'plpgsql';

create or replace function tax()
	returns trigger as
$$
	BEGIN
		update products
		set price = price * 1.2
		where id in (select id from inserted);
		return NEW;
	END;
$$
LANGUAGE 'plpgsql';

create trigger befor_tax_trigger
	before insert or update on products
	for each row
	execute procedure tax_befor();
	

create trigger audit_history_of_price
	after insert on products
	for each row
	execute procedure audit_price();
	

create trigger tax_trigger
	after insert on products
	referencing new table as inserted
	for each statement
	execute procedure tax();
	

	
	
	

