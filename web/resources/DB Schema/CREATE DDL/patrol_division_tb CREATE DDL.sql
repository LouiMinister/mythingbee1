create table patrol_division_tb(
	pd_code char(5) not null primary key,
	ps_code char(2) not null,
	pd_name varchar(20) not null,
    pd_tel varchar(20) not null,
	foreign key (ps_code) references police_station_tb(ps_code)
	on delete cascade on update cascade
)
engine=innodb default charset=utf8;