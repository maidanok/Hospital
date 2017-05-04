
DROP TABLE IF EXISTS `prescription`;

CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_type_id` int(11) DEFAULT NULL,
  `survey_history_id` int(11) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`prescription_id`),
  KEY `d_idx` (`survey_history_id`),
  CONSTRAINT `prescription_type` FOREIGN KEY (`prescription_type_id`) REFERENCES `prescription_type` (`prescription_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `survey_history` FOREIGN KEY (`survey_history_id`) REFERENCES `survey_history` (`survey_history_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


LOCK TABLES `prescription` WRITE;

UNLOCK TABLES;

