alter table taxi_drive_info alter column rate type real;

alter table taxi_drive_info add column if not exists orders_cnt int;

