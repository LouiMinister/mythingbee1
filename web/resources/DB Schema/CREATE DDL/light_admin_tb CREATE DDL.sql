CREATE TABLE IF NOT EXISTS `light_admin_tb` (
  `li_admin_code` char(2) NOT NULL,
  `li_admin_name` varchar(40) NOT NULL,
  `li_admin_tel` char(13) NOT NULL,
  PRIMARY KEY (`li_admin_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;