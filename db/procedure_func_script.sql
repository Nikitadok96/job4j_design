call insert_data('product_1', 'producer_1', 10, 50);
call insert_data('product_2', 'producer_1', 5, 30);
call insert_data('product_3', 'producer_2', 30, 100);

select * from products;

select sell_product_function(2, 2);
call delete_product(3);

