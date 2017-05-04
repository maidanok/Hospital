
DROP TABLE IF EXISTS `survey_history`;

CREATE TABLE `survey_history` (
  `survey_history_id` int(11) NOT NULL AUTO_INCREMENT,
  `sick_list_id` int(11) DEFAULT NULL,
  `diagnose_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `survey_date` date DEFAULT NULL,
  `survey_description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`survey_history_id`),
  KEY `staff_idx` (`staff_id`),
  KEY `diagnose_idx` (`diagnose_id`),
  KEY `sick_list_idx` (`sick_list_id`),
  CONSTRAINT `sick_list` FOREIGN KEY (`sick_list_id`) REFERENCES `sick_list` (`sick_list_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `diagnose` FOREIGN KEY (`diagnose_id`) REFERENCES `diagnose` (`diagnose_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



LOCK TABLES `survey_history` WRITE;

INSERT INTO `survey_history` VALUES
(1,4,3,23,'2017-12-11','идиот'),
(2,4,3,25,'2017-04-16','проверочка');

UNLOCK TABLES;

