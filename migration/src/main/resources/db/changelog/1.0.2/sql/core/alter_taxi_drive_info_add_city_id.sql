alter table taxi_drive_info add column if not exists city_id bigint;

alter table taxi_drive_info
add constraint taxi_drive_info_city_id_fk foreign key (city_id)
references city_queue (city_id);