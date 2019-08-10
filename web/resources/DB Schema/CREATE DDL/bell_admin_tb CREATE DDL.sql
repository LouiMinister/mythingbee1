CREATE TABLE IF NOT EXISTS `bell_admin_tb` (
  `be_admin_code` char(4) NOT NULL,
  `be_admin_name` varchar(40) NOT NULL,
  `be_admin_tel` char(13) NOT NULL,
  PRIMARY KEY (`be_admin_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
