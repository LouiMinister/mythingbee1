CREATE TABLE IF NOT EXISTS `light_location_tb` (
  `li_code` char(9) NOT NULL,
  `lat` decimal(18,15) NOT NULL,
  `lng` decimal(18,15) NOT NULL,
  `road_addr` varchar(200) DEFAULT NULL,
  `land_addr` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`li_code`),
  CONSTRAINT `light_location_tb_ibfk_1` FOREIGN KEY (`li_code`) REFERENCES `light_tb` (`li_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;