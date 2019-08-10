create table local_crime_tb(
	lc_code char(6) primary key not null,
	di_code char(2) not null,
	cr_code char(2) not null,
    year int not null,
	occur_cnt int not null,
	arrest_cnt int not null,
	foreign key (di_code) references district_tb (di_code)
	on delete cascade on update cascade,
	foreign key (cr_code) references crime_category_tb (cr_code)
	on delete cascade on update cascade
)
engine=innodb charset=utf8;