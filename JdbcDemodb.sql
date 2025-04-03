create database jdbcdemo;
use jdbcdemo;

create table employee(
emp_id int primary key ,
ename varchar(30),
salary int 
);

insert into employee values (1,'Ram',100000);

select * from employee;