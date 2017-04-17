
DROP TABLE IF EXISTS `prescription_execution`;

CREATE TABLE `prescription_execution` (
  `prescription_execution_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `prescription_execution_date` date DEFAULT NULL,
  `done` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`prescription_execution_id`),
  KEY `staff_idx` (`staff_id`),
  KEY `p_idx` (`prescription_id`),
  CONSTRAINT `prescription` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `staff_id` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `prescription_execution` WRITE;

UNLOCK TABLES;