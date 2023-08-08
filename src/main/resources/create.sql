create table money
(
    id           bigint       not null,
    denomination varchar(255) not null,
    amount       integer      not null,
    primary key (id)
)

create table item_inventory
(
    item_code  bigint       not null,
    name       varchar(255) not null,
    unit_price integer      not null,
    quantity   integer,
    primary key (item_code)
)