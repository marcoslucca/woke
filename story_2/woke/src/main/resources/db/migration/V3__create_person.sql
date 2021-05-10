create table person
(
    id             uuid           not null primary key,
    full_name       varchar(120)   not null,
    phone          varchar(15)    not null,
    email          varchar(60)    not null,
    birth_date      varchar(12)    not null,
    version        bigint         not null,
    created_at     timestamp      not null,
    updated_at     timestamp      not null
);

create
    index person_full_name_index on person using hash (full_name);
