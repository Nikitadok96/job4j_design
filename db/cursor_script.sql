Begin;
declare 
	cursor_products SCROLL cursor for
		select * from products;

MOVE LAST from cursor_products;
MOVE BACKWARD 4 FROM cursor_products;
FETCH BACKWARD FROM cursor_products;
MOVE BACKWARD 7 FROM cursor_products;
FETCH BACKWARD FROM cursor_products;
MOVE BACKWARD 4 FROM cursor_products;
FETCH BACKWARD FROM cursor_products;
FETCH BACKWARD FROM cursor_products;
CLOSE cursor_products;
commit;

		