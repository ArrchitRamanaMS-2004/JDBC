create database jdbcdemo;
use jdbcdemo;

create table employee(
emp_id int primary key ,
ename varchar(30),
salary int 
);

insert into employee values (1,'Ram',100000);
insert into employee values (2,'Rishi',300000);
insert into employee values (3,'Ramya',200000);

select * from employee;

delimiter $$
create procedure GetEmp()
begin
	select * from employee;
end$$
delimiter ;

delimiter $$
create procedure GetEmpById( in id int )
begin
	select * 
    from employee
    where emp_id = id;
end$$
delimiter ;

delimiter $$
create procedure GetNameById(
in id int ,
out empname varchar(50)
)
begin
	select ename
    into empname
    from employee where emp_id = id;
end$$
delimiter ;

call GetNameById();

select * from employee;