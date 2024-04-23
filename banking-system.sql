create database banking;

use banking;

create table accounts(
account_number bigint primary key not null,
full_name varchar(255) not null,
email varchar(255) unique key not null,
balance decimal(10,2) not null,
security_pin char(4) not null
);

create table user(
full_name varchar(255) not null,
email varchar(255) primary key not null,
password varchar(255) not null
);

select * from user;

select * from accounts;

delete from user;