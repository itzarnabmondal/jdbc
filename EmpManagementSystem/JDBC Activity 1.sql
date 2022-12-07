use mydb;
create table Department(dep_id numeric primary key,dep_name varchar(20)unique,dep_head varchar(30)unique,
    dep_desc varchar(100)unique);
create table Employee(emp_id numeric primary key,emp_name varchar(30),emp_address varchar(50),emp_sal double,
    emp_contact_no bigint(13)unique,dep_id numeric,foreign key(dep_id) references Department(dep_id),
    check(emp_sal between 1000 and 1000000));

