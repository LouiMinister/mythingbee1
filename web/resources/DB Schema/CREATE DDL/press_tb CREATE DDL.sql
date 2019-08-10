CREATE TABLE press_tb
(
	pr_code               CHAR(2) NOT NULL,
	pr_name               VARCHAR(30) NOT NULL,
	PRIMARY KEY(pr_code)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;