create table change
(
    id           bigint       not null,
    denomination varchar(255) not null,
    total_amount       integer      not null,
    primary key (id)
)

create table inventory
(
    item_code  bigint       not null,
    name       varchar(255) not null,
    unit_price integer      not null,
    quantity   integer,
    primary key (item_code)
)