
DROP TABLE IF EXISTS `diagnose`;
CREATE TABLE `diagnose` (
  `diagnose_id` int(11) NOT NULL AUTO_INCREMENT,
  `diagnose_name` varchar(45) DEFAULT NULL,
  `therapy` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`diagnose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;


LOCK TABLES `diagnose` WRITE;
INSERT INTO `diagnose` VALUES (1,'Устанавливается',''),(2,'ОРЗ','Для профилактики заражения ОРВИ необходимо изолировать больного от окружающих, выделить ему индивидуальную посуду, которую следует ошпаривать кипятком.'),(3,'Перелом','Наложить гипс'),(4,'Столбняк','Введение антитоксической противостолбнячной сыворотки и столбнячного анатоксина, человеческого противостолбнячного иммуноглобулина. Обязательна хирургическая обработка ран, в том числе и заживших к моменту болезни.В качестве симптоматической терапии применяются успокаивающие и обезболивающие средства (аминазин, промедол, димедрол), антибактериальные препараты, искусственная вентиляция легких.'),(5,'Инсульт','диазепам (реланиум, седуксен, сибазон) или рогипнол (флунитразепам)'),(6,'Крапивница','При установлении конкретного аллергена нужно устранить контакт с ним, целесообразно провести мероприятия, перечисленные в разделе «Аллергический дерматит». Медикаментозное лечение также подробно описано в этом разделе'),(10,'345','ertwer');
UNLOCK TABLES;


DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `middle_name` varchar(25) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `address` varchar(90) DEFAULT NULL,
  `passport_number` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;



LOCK TABLES `person` WRITE;
INSERT INTO `person` VALUES (2,'Фролова','Акулина','Улебовна','2017-05-16','FEMALE','601553, г. Балтийск, ул. Беговая 2-я, дом 51, квартира 256','MfN5Emm7vD'),(3,'Анисимова','Маргарита','Олеговна','1939-12-25','FEMALE','433550, г. Сурское, ул. Маршала Василевского, дом 94, квартира 31','BFMO9Z04TI'),(4,'Бирюков','Авксентий','Мэлорович','2017-10-04','MALE','636151, г. Троицкое, ул. Загородная, дом 31, квартира 212','PO6U4UMONE'),(5,'Зимин','Дмитрий','Авксентьевич','1951-01-01','MALE','623271, г. Улан-Удэ, ул. Авиаторов, дом 61, квартира 162','LEE43MOTPI'),(6,'Медведьев','Алексей','Кондратович','1956-09-27','MALE','392038, г. Усть-Калманка, ул. Жёлтиковская 1-я, дом 21, квартира 121','8ZRB5C143A'),(7,'Федосеев','Матвей','Дамирович','1960-09-08','MALE','659141, г. Добрянка, ул. Советская, дом 44, квартира 152','T0BHJIDTSE'),(8,'Орлова','Синклитикия','Ефимовна','1972-02-21','FEMALE','141516, г. Луза, ул. Авиаконструктора Микояна, дом 30, квартира 241','E98GF4I54G'),(9,'Панфилов','Парфений','Евгеньевич','1975-02-27','MALE','238050, г. Усть-Кишерть, ул. Абельмановская, дом 63, квартира 53','WTV0ZCIGSL'),(10,'Мельникова','Нина','Онисимовна','1989-05-17','FEMALE','102043, г. Чаны, ул. Бауманская, дом 77, квартира 266','W63VF3ZEYO'),(11,'Большакова','Ксения','Игоревна','1993-09-06','FEMALE','422527, г. Черемисиново, ул. Авангардная, дом 6, квартира 62','JNK6OA3R1B'),(12,'Кириллова','Василиса','Степановна','1990-12-30','FEMALE','150502, г. Сосновка, ул. Достоевского, дом 20, квартира 260','MZTIQZYWKI'),(13,'Быков','Аркадий','Даниилович','1986-10-11','MALE','404519, г. Крылово, ул. Баженова, дом 35, квартира 101','X3ZOGT1SIC'),(14,'Иван','Иванов','Иванович','1986-10-11','MALE','152646, г. Новомосковский, ул. Дальняя, дом 79, квартира 136','YIYX105V14'),(15,'Максимов','Федосей','Эдуардович','1986-10-10','MALE','679512, г. Чернушка, ул. Бакинская, дом 71, квартира 121','8KFYNTITU6'),(16,'Панова','Евпраксия','Кондратовна','1997-05-13','FEMALE','628325, г. Березник, ул. Азовская, дом 82, квартира 267','H2VEMTRLAO'),(17,'Федотов','Алексей','Агафонович','2000-02-16','MALE','346838, г. Устюжна, ул. Бартеневская, дом 68, квартира 263','4T02YFGULJ'),(18,'Шубин','Юрий','Константинович','1986-10-11','MALE','306005, г. Тюкалинск, ул. Бардина, дом 71, квартира 2','7RYIYVG9GT'),(19,'Казаков','Еремей','Леонидович','2001-03-07','MALE','623939, г. Щигры, ул. Багрицкого, дом 33, квартира 1','FOKZ4MWYJR'),(20,'Калинина','Пелагея','Дмитрьевна','2003-02-18','FEMALE','623989, г. Красногорское, ул. Вагонников 2-я, дом 64, квартира 289','MPXYHJEK0I'),(21,'Карпова','Ангелина','Макаровна','2007-07-13','FEMALE','606588, г. Лысково, ул. Гайдара, дом 56, квартира 232','79AOW74WY2'),(22,'Пахомов','Анатолий','Никитевич','2013-08-17','MALE','627040, г. Погар, ул. Бауманская, дом 75, квартира 55','OJFHVQX8NZ'),(23,'Хаус','Грегори','','2017-11-04','MALE','221b,','VD09AAGIUN'),(24,'Форман','Эрик','Эрикович','2017-05-15','MALE','не известно','61536bg'),(25,'Лиза','Кадди','','2017-05-15','FEMALE','не известно','fds2356235');
UNLOCK TABLES;


DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(45) NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;


LOCK TABLES `posts` WRITE;
INSERT INTO `posts` VALUES (6,'NURSE'),(7,'ADMINISTRATOR'),(8,'DOCTOR');
UNLOCK TABLES;

DROP TABLE IF EXISTS `prescription_type`;
CREATE TABLE `prescription_type` (
  `prescription_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_type_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`prescription_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


LOCK TABLES `prescription_type` WRITE;
INSERT INTO `prescription_type` VALUES (1,'PROCEDURE'),(2,'MEDICATION'),(3,'SURGERY'),(4,'DISCHARGE');
UNLOCK TABLES;

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
INSERT INTO `staff` VALUES (23,8,'GregHous','202cb962ac59075b964b07152d234b70',0),(24,6,'ErForm','202cb962ac59075b964b07152d234b70',0),(25,7,'Liss','202cb962ac59075b964b07152d234b70',0);
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;


LOCK TABLES `sick_list` WRITE;
INSERT INTO `sick_list` VALUES (1,NULL,NULL,NULL,NULL,NULL,1),(2,2,'2017-12-12','2017-12-12','100',NULL,1),(3,3,'2017-10-10','2017-10-11','200',NULL,1),(4,2,'2012-12-11','2012-12-11','Room','Ssssssss',1),(5,2,'2012-12-11','2012-11-13','Room','Ssssssss',1),(6,2,'2017-04-10','2012-12-11','Room','Ssssssss',1),(8,10,'2018-03-04',NULL,'528','не разобрали мычание',4),(9,3,'2017-05-17',NULL,'462','не разобрали мычание',3),(10,2,'2017-04-10','2017-04-10','палата №6','не разобрали мычание',1),(11,11,'2017-05-15',NULL,'361','не разобрали мычание',6),(12,13,'2017-05-16',NULL,'325','не разобрали мычание',5),(13,14,'2017-05-16',NULL,'515','не разобрали мычание',1),(14,15,'2017-05-17',NULL,'191','разобрали мычание',3),(15,16,'2017-04-22',NULL,'300','Пациент очень буйно себя ведет',1),(16,18,'2017-04-22',NULL,'302','Боли в животе, очень хочется водки))',1),(17,5,'2017-05-15',NULL,'109','cgdsgsdgf',3),(18,8,'2018-03-04','2017-05-14','100','Очень сильный насморк',2),(20,19,'2017-05-17',NULL,'100','Очень плохо себя чувствуетВысокая температура',2);
UNLOCK TABLES;


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
  CONSTRAINT `diagnose` FOREIGN KEY (`diagnose_id`) REFERENCES `diagnose` (`diagnose_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sick_list` FOREIGN KEY (`sick_list_id`) REFERENCES `sick_list` (`sick_list_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;



LOCK TABLES `survey_history` WRITE;
INSERT INTO `survey_history` VALUES (1,4,3,23,'2017-12-11','идиот'),(2,4,3,25,'2017-04-16','проверочка'),(3,16,6,24,'2017-04-22','Надо мазать зеленкой. А лучше облить ))'),(4,9,3,25,'2017-05-16','Перелом реберНаложить тугую повязку'),(6,8,4,25,'2018-03-04','лекарства от столбняка'),(7,11,6,25,'2017-05-15','мазать зеленкой'),(8,12,5,25,'2017-05-16','ставим капельницы'),(9,18,2,23,'2018-03-04','Носовой платок и выписываем'),(10,13,1,25,'2017-05-16',''),(11,14,3,25,'2017-05-17',''),(12,9,3,23,'2017-05-17',''),(13,20,2,23,'2017-05-17','');
UNLOCK TABLES;

DROP TABLE IF EXISTS `prescription`;
CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_type_id` int(11) NOT NULL,
  `survey_history_id` int(11) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `completed` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`prescription_id`,`prescription_type_id`),
  KEY `d_idx` (`survey_history_id`),
  KEY `prescription_type` (`prescription_type_id`),
  CONSTRAINT `prescription_type` FOREIGN KEY (`prescription_type_id`) REFERENCES `prescription_type` (`prescription_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `survey_history` FOREIGN KEY (`survey_history_id`) REFERENCES `survey_history` (`survey_history_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;


LOCK TABLES `prescription` WRITE;
INSERT INTO `prescription` VALUES (1,2,3,'Зеленка 1 раз в день',4,4),(2,3,3,'Операция \"Удаление\"',1,1),(3,1,2,'надо на ком то проверять и потому 20 уколов',20,12),(4,1,4,'Перевязка ребер каждый день',3,3),(5,2,7,'зеленка\r\n1 флакон в день \r\nв течении недели',7,1),(7,2,6,'Прививка от столбняка',5,5),(8,4,9,'',1,0),(9,1,7,'sadasd',1,1),(10,2,4,'',3,2),(11,1,10,'',5,1),(12,1,11,'Гипс',1,0),(13,1,12,'12313',5,0),(14,1,13,'Надо что то дать',7,0);
UNLOCK TABLES;


DROP TABLE IF EXISTS `prescription_execution`;
CREATE TABLE `prescription_execution` (
  `prescription_execution_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_id` int(11) NOT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `prescription_execution_date` date DEFAULT NULL,
  PRIMARY KEY (`prescription_execution_id`),
  KEY `staff_idx` (`staff_id`),
  KEY `p_idx` (`prescription_id`),
  CONSTRAINT `prescription` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `staff_id` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;




LOCK TABLES `prescription_execution` WRITE;
INSERT INTO `prescription_execution` VALUES (1,1,24,'2017-04-24'),(2,1,24,'2017-04-24'),(3,1,25,'2017-04-25'),(4,2,25,'2017-04-24'),(5,1,24,'2017-04-24'),(6,3,23,'2017-04-24'),(7,3,25,'2017-04-24'),(8,3,24,'2017-04-24'),(9,3,24,'2017-04-24'),(10,3,23,'2017-04-24'),(11,4,23,'2017-05-14'),(12,7,23,'2017-05-14'),(13,7,23,'2017-05-14'),(14,4,23,'2017-05-14'),(15,4,23,'2017-05-14'),(16,5,25,'2017-05-14'),(17,3,25,'2017-05-14'),(18,7,25,'2017-05-14'),(19,3,25,'2017-05-14'),(20,7,25,'2017-05-14'),(21,3,25,'2017-05-14'),(22,3,25,'2017-05-14'),(23,3,23,'2017-05-14'),(24,7,23,'2017-05-14'),(25,3,25,'2017-05-15'),(26,3,25,'2017-05-15'),(27,9,25,'2017-05-15'),(28,10,25,'2017-05-15'),(29,10,23,'2017-05-17'),(30,11,23,'2017-05-17');
UNLOCK TABLES;


