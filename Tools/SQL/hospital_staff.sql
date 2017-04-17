

DROP TABLE IF EXISTS `staff`;

CREATE TABLE `staff` (
  `person_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `login` varchar(15) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `fired` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`person_id`),
  KEY `person_id_idx` (`person_id`),
  KEY `post_id_idx` (`post_id`),
  CONSTRAINT `person_id` FOREIGN KEY (`person_id`) REFERENCES `person` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `staff` WRITE;

INSERT INTO `staff` VALUES
(23,8,'GregHous','111',0),
(24,8,'ErForm','123',0),
(25,8,'Lissss','123',0);

UNLOCK TABLES;
