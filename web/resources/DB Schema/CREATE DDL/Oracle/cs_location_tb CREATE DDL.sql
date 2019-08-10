create table cs_location_tb (
    cs_code char(8) primary key not null,
    lat number(19,16) not null,
    lng number(19,16) not null,
    road_addr varchar2(200) not null,
    land_addr varchar2(200) not null,
    foreign key (cs_code) references convenience_store_tb (cs_code)
    on delete cascade)
    