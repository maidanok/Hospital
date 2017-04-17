
DROP TABLE IF EXISTS `prescription_type`;

CREATE TABLE `prescription_type` (
  `prescription_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_type_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`prescription_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


LOCK TABLES `prescription_type` WRITE;

INSERT INTO `prescription_type` VALUES (1,'PROCEDURE'),(2,'MEDICATION'),(3,'SURGERY'),(4,'DISCHARGE');

UNLOCK TABLES;
