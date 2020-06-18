drop table tbl_user if exists;
create table tbl_user (
    id varchar(40) not null ,
    username varchar(45) not null,
    password varchar(45) not null
);