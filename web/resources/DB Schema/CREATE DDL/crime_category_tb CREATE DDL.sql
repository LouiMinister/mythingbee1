create table crime_category_tb(
	cr_code char(2) primary key not null,
	cr_name varchar(20) not null
)
engine=innodb charset=utf8;