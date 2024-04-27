drop table if exists item;

create table item
(
    id   int          not null auto_increment,
    name varchar(250) not null,
    primary key (id)
);

drop table if exists Employee;
create table Employee
(
    id         int          not null auto_increment,
    name       varchar(250) not null,
    role       varchar(250) not null,
    department varchar(250) not null,
    salary     int          not null,
    startDate  date         not null,
    primary key (id)
);