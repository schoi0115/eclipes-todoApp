drop database if exists todo_db;
create database todo_db;
use todo_db;
create table item (
	id int primary key auto_increment,
	todoitem varchar(100)
);

insert into item values(1,'Sleep');