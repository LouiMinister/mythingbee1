create table cs_tel_tb(
    cs_code char(8) primary key not null,
    cs_tel varchar2(20) not null,
    foreign key (cs_code) references convenience_store_tb (cs_code)
    on delete cascade)
    
    