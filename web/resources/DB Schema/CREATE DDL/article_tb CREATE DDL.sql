
CREATE TABLE `article_tb` (
	`ar_code` CHAR(10) NOT NULL,
	`pr_code` CHAR(2) NOT NULL,
	`di_code` CHAR(2) NOT NULL,
	`summary` VARCHAR(200) NULL DEFAULT '',
	`title` VARCHAR(50) NOT NULL,
	`URL` VARCHAR(100) NULL DEFAULT NULL,
	`ar_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`view_cnt` DOUBLE NOT NULL DEFAULT '0',
	PRIMARY KEY (`ar_code`),
	INDEX `pr_code` (`pr_code`),
	INDEX `di_code` (`di_code`),
	CONSTRAINT `article_tb_ibfk_1` FOREIGN KEY (`pr_code`) REFERENCES `press_tb` (`pr_code`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `article_tb_ibfk_2` FOREIGN KEY (`di_code`) REFERENCES `district_tb` (`di_code`) ON UPDATE CASCADE ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;