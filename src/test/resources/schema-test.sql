drop table if exists item;

create table item (
  id int not null auto_increment,
  name varchar (250) not null,
  primary key (id)
);