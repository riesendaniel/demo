drop table if exists item;

create table item (
  id int not null auto_increment,
  name varchar (250) not null,
  primary key (id)
);

insert into item (name) values
  ('Apple'),
  ('Milk');


drop table if exists Employee;
create table Employee
(
    id         int          not null auto_increment,
    name       varchar(250) not null,
    role       varchar(250) not null,
    department varchar(250) not null,
    salary     int          not null,
    start_date  date         not null,
    primary key (id)
);

insert into Employee (name, role, department, salary, start_date)
values ('Daniel Riesen', 'Developer', 'IT', 90000, '2020-01-01'),
       ('Jane Doe', 'Manager', 'IT', 100000, '2020-01-01'),
       ('Mike Doe', 'CEO', 'GL', 130000, '2020-01-01');