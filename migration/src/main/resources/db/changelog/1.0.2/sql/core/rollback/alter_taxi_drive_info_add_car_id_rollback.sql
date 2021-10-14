drop constraint if exists taxi_drive_info_car_id_fk;

alter table taxi_drive_info drop column if exists car_id;

alter table taxi_drive_info add column if not exists car_model text;