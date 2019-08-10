CREATE TABLE IF NOT EXISTS `bell_location_tb` (
  `be_code` char(16) NOT NULL,
  `lat` decimal(18,15) NOT NULL,
  `lng` decimal(18,15) NOT NULL,
  `road_addr` char(200) DEFAULT NULL,
  `land_addr` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`be_code`),
  CONSTRAINT `bell_location_tb_ibfk_1` FOREIGN KEY (`be_code`) REFERENCES `bell_tb` (`be_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
