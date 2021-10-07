create sequence if not exists user_account_seq
    start 1;

create table if not exists user_account
(
    id       bigint primary key default nextval('user_account_seq' :: regclass),
    login    varchar(100) unique not null,
    password varchar(512)
    );