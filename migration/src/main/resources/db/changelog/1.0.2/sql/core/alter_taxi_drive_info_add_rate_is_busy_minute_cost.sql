alter table taxi_drive_info add column if not exists rate int;
alter table taxi_drive_info add column if not exists is_busy boolean;
alter table taxi_drive_info add column if not exists minute_cost int default 10;
