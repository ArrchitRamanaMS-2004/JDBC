create database busresv;
use busresv;

create table bus (
	busNo int primary key,
    ac char,
    capacity int
);

alter table bus modify column ac boolean;

insert into bus values 
(1,1,2),
(2,1,48),
(3,0,52);

select * from bus;

SELECT capacity FROM bus WHERE busNo = 1; 

create table booking (
	passenger_name varchar(50),
    busNo int,
    travel_date date
);

select * from booking;

SELECT COUNT(*) FROM booking WHERE busNo =  AND travel_date = 

insert into booking values (,,);