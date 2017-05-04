
DROP TABLE IF EXISTS `sick_list`;

CREATE TABLE `sick_list` (
  `sick_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) DEFAULT NULL,
  `date_in` date DEFAULT NULL,
  `date_out` date DEFAULT NULL,
  `room` varchar(20) DEFAULT NULL,
  `symptoms` varchar(300) DEFAULT NULL,
  `final_diagnose_id` int(11) DEFAULT '1',
  PRIMARY KEY (`sick_list_id`),
  KEY `person_id_idx` (`person_id`),
  KEY `final_diagnose_idx` (`final_diagnose_id`),
  CONSTRAINT `person` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;


LOCK TABLES `sick_list` WRITE;

INSERT INTO `sick_list` VALUES
(1,NULL,NULL,NULL,NULL,NULL,1),
(2,2,NULL,NULL,NULL,NULL,1),
(3,2,'2017-10-10',NULL,NULL,NULL,1),
(4,2,'2012-12-11','2012-12-11','Room','Ssssssss',1),
(5,2,'2012-12-11','2012-11-13','Room','Ssssssss',1),
(6,2,'2017-04-10','2012-12-11','Room','Ssssssss',1),
(7,2,'2017-04-10',NULL,'362','не разобрали мычание',1),
(8,2,'2017-04-10',NULL,'528','не разобрали мычание',1),
(9,2,'2017-04-10',NULL,'462','не разобрали мычание',1),
(10,2,'2017-04-10','2017-04-10','палата №6','не разобрали мычание',1),
(11,2,'2017-04-10',NULL,'361','не разобрали мычание',1),
(12,2,'2012-12-11',NULL,'325','не разобрали мычание',1),
(13,2,'2012-12-11',NULL,'515','не разобрали мычание',1),
(14,2,'2012-12-11',NULL,'191','разобрали мычание',1);

UNLOCK TABLES;
