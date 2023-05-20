CREATE TABLE accidents
(
    id               serial primary key,
    name             text,
    text             text,
    address          text,
    accident_type_id int not null references accident_types (id)
);