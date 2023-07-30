select * from cars;

select
	car_bodies.name
from
	car_bodies
	left join cars
	on car_bodies.id = cars.bodies_id
where
	cars.bodies_id is null;
	
select
	car_engines.name
from
	car_engines
	left join cars
	on car_engines.id = cars.engines_id
where
	cars.engines_id is null;
	
select
	car_transmissions.name
from
	car_transmissions
	left join cars
	on car_transmissions.id = cars.transmissions
where
	cars.transmissions is null;
	