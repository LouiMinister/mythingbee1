CREATE TABLE light_tb
(
	li_code               CHAR(8) NOT NULL PRIMARY KEY,
	name                  VARCHAR(40) NOT NULL,
	tel                   VARCHAR(13) NOT NULL
)engine=innodb default charset=utf8;
