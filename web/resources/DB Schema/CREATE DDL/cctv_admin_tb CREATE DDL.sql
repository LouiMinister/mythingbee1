CREATE TABLE IF NOT EXISTS `cctv_admin_tb` (
  `cc_admin_code` char(4) NOT NULL,
  `cc_admin_name` varchar(40) NOT NULL,
  `cc_admin_tel` varchar(13) NOT NULL,
  PRIMARY KEY (`cc_admin_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;