create sequence if not exists orders_seq start 1;

create table if not exists orders
(
    order_id      bigint not null default nextval('orders_seq' :: regclass),
    client_number bigint,
    driver_id     bigint not null,
    start_trip    timestamp,
    end_trip      timestamp,
    constraint orders_pk primary key (order_id)
);

comment on table orders is 'Заказы';
comment on column orders.order_id is 'Идентификатор заказа';
comment on column orders.client_number is 'Идентификатор клиента';
comment on column orders.driver_id is 'Идентификатор водителя';

alter table orders
add constraint orders_driver_id_fk foreign key (driver_id)
references taxi_drive_info (driver_id);