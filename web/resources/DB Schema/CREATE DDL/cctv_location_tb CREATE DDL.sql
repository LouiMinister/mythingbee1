CREATE TABLE IF NOT EXISTS `cctv_location_tb` (
  `cc_code` char(20) NOT NULL,
  `lat` decimal(18,15) NOT NULL,
  `lng` decimal(18,15) NOT NULL,
  `road_addr` varchar(200) DEFAULT NULL,
  `land_addr` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`cc_code`),
  CONSTRAINT `cctv_location_tb_ibfk_1` FOREIGN KEY (`cc_code`) REFERENCES `cctv_tb` (`cc_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;