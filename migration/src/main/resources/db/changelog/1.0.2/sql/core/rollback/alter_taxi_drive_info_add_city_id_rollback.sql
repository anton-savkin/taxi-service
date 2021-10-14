drop constraint if exists taxi_drive_info_city_id_fk;
alter table taxi_drive_info drop column if exists city_id;