
CREATE TABLE light_location_tb
(
	li_code               CHAR(8) NOT NULL PRIMARY KEY,
	road_addr             VARCHAR(200) NULL,
	land_addr             VARCHAR(200) NULL,
	lat                   DECIMAL(18,15) NOT NULL,
	lng                   DECIMAL(18,15) NOT NULL,
	FOREIGN KEY (li_code) REFERENCES light_tb (li_code)
	ON DELETE CASCADE ON UPDATE CASCADE
)engine=innodb default charset=utf8;
