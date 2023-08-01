create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
	Begin
		insert into products(name, producer, count, price)
		values (i_name, prod, i_count, i_price);
	END;
$$;

create or replace procedure delete_product(i_id integer)
language 'plpgsql'
as
$$
	Begin
		if i_id in (select id from products) then
			delete from products where i_id = id;
		end if;
	END;
$$;
			
		

create or replace function sell_product_function(i_id integer, i_count integer)
returns integer
language 'plpgsql'
as
$$
	declare 
		result integer;
	BEGIN
		if i_count > 0 then
			update products set count = count - i_count where id = i_id;
			select into result count from products where id = i_id;
			delete from products where id = i_id and count <= 0;
		end if;
		return result;
	END;
$$;
	
	