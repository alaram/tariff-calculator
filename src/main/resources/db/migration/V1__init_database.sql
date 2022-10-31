drop table if exists tariff;
drop table if exists car;
drop table if exists hibernate_sequence;

create table tariff (
  id bigint not null,
  amount varchar(255),
  city varchar(255),
  time_interval varchar(255),
  primary key (id)
) engine=InnoDB;

create table hibernate_sequence (
  next_val bigint
) engine=InnoDB;

create table car (
  id bigint not null auto_increment,
  city varchar(255),
  date varchar(255),
  vehicle_type varchar(255),
  primary key (id)
) engine=InnoDB;

insert into hibernate_sequence values ( 1 );