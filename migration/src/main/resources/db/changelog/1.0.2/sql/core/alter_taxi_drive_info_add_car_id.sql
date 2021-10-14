alter table taxi_drive_info drop column if exists car_model;

alter table taxi_drive_info add column if not exists car_id bigint;

alter table taxi_drive_info
add constraint taxi_drive_info_car_id_fk foreign key (car_id)
references car (id);
