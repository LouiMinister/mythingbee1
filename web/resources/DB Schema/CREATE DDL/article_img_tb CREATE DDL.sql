CREATE TABLE article_img_tb
(
	ar_code               CHAR(10) NOT NULL,
	img_url               VARCHAR(200) NOT NULL,
	PRIMARY KEY(img_url),
	FOREIGN KEY(ar_code) REFERENCES article_tb(ar_code) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;