CREATE TABLE IF NOT EXISTS `bell_tb` (
  `be_code` char(8) NOT NULL,
  `be_admin_code` char(4) NOT NULL,
  PRIMARY KEY (`be_code`),
  KEY `R_40` (`be_admin_code`),
  CONSTRAINT `bell_tb_ibfk_1` FOREIGN KEY (`be_admin_code`) REFERENCES `bell_admin_tb` (`be_admin_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;