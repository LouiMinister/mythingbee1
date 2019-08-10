CREATE TABLE IF NOT EXISTS `cctv_tb` (
  `cc_code` char(9) NOT NULL,
  `cc_admin_code` char(4) NOT NULL,
  PRIMARY KEY (`cc_code`),
  KEY `R_41` (`cc_admin_code`),
  CONSTRAINT `cctv_tb_ibfk_1` FOREIGN KEY (`cc_admin_code`) REFERENCES `cctv_admin_tb` (`cc_admin_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;