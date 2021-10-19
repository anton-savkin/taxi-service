alter table taxi_drive_info alter column rate type int;

alter table taxi_drive_info drop column if exists orders_cnt;