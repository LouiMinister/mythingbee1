CREATE TABLE link_tb(
li_code char(9) NOT NULL PRIMARY KEY,
si_code char(4) NOT NULL,
lc_code char(2) NOT NULL,
description varchar2(200) NOT NULL,
url varchar2(200) NOT NULL,
CONSTRAINT fk_si_code FOREIGN KEY(si_code) REFERENCES site_tb(si_code) ON DELETE CASCADE,
CONSTRAINT fk_lc_code FOREIGN KEY(lc_code) REFERENCES linkcategory_tb(lc_code) ON DELETE CASCADE);

CREATE OR REPLACE TRIGGER si_code_trg
 AFTER UPDATE OF si_code ON site_tb FOR EACH ROW
	BEGIN
		UPDATE link_tb
		SET si_code = :NEW.si_code
        WHERE si_code = :OLD.si_code;
	END si_code_trg;
    