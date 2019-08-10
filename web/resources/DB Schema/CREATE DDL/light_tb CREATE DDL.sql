CREATE TABLE IF NOT EXISTS `light_tb` (
  `li_code` char(9) NOT NULL,
  `li_admin_code` char(2) NOT NULL,
  PRIMARY KEY (`li_code`),
  KEY `R_42` (`li_admin_code`),
  CONSTRAINT `light_tb_ibfk_1` FOREIGN KEY (`li_admin_code`) REFERENCES `light_admin_tb` (`li_admin_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;