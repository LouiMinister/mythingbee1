create table pd_location_tb(
	pd_code char(5) not null,
	lat decimal(19,16) not null,
	lng decimal(19,16) not null,
	road_addr varchar(200) not  null,
	land_addr varchar(200) not null,
	primary key(pd_code),
	foreign key (pd_code) references patrol_division_tb (pd_code)
	on delete cascade on update cascade
)
engine=innodb default charset=utf8;