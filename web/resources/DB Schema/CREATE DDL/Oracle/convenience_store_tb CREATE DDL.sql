create table convenience_store_tb(
    cs_code char(8) primary key not null,
    cs_name varchar2(50) not null
)

create or replace trigger trg_cslocation_update
after update of cs_code on convenience_store_tb
for each row
BEGIN
   update cs_location_tb 
   set cs_code = :NEW.cs_code
    where cs_code = :OLD.cs_code;
END trg_cslocation_update;

create or replace trigger trg_cstel_update
after update of cs_code on convenience_store_tb
for each row
BEGIN
   update cs_tel_tb 
   set cs_code = :NEW.cs_code
    where cs_code = :OLD.cs_code;
END trg_cstel_update;


