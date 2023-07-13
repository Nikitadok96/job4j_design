rules_roles(
	id serial primary key,
	rule_id int references rules(id),
	role_id int references roles(id)
);