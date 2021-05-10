create table users
(
    id             uuid           not null primary key,
    username       varchar(120)   not null,
    password       varchar(120) not null,
    version        bigint         not null,
    created_at     timestamp      not null,
    updated_at     timestamp      not null
);

create
    index user_username_index on users using hash (username);
