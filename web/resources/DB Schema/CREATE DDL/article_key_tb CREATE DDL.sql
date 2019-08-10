CREATE TABLE article_key_tb
(
	ke_code               CHAR(6) NOT NULL,
	ar_code               CHAR(10) NOT NULL,
	FOREIGN KEY(ke_code) REFERENCES keyword_tb(ke_code)ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY(ar_code) REFERENCES article_tb(ar_code)ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE article_key_tb
	ADD  PRIMARY KEY (ke_code,ar_code);