-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hospital
-- ------------------------------------------------------
-- Server version	5.5.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'Воробьёв','Игорь','Валерьевич','1937-06-21','MALE','347732, г. Борисоглебск, ул. Бакунинская, дом 79, квартира 104','ADHDA3N0OX'),(2,'Фролова','Акулина','Улебовна','1939-02-20','FEMALE','601553, г. Балтийск, ул. Беговая 2-я, дом 51, квартира 256',NULL),(3,'Анисимова','Маргарита','Олеговна','1939-12-25','FEMALE','433550, г. Сурское, ул. Маршала Василевского, дом 94, квартира 31','BFMO9Z04TI'),(4,'Бирюков','Авксентий','Мэлорович','1950-09-18','MALE','636151, г. Троицкое, ул. Загородная, дом 31, квартира 212','PO6U4UMONE'),(5,'Зимин','Дмитрий','Авксентьевич','1951-01-01','MALE','623271, г. Улан-Удэ, ул. Авиаторов, дом 61, квартира 162','LEE43MOTPI'),(6,'Медведьев','Алексей','Кондратович','1956-09-27','MALE','392038, г. Усть-Калманка, ул. Жёлтиковская 1-я, дом 21, квартира 121','8ZRB5C143A'),(7,'Федосеев','Матвей','Дамирович','1960-09-08','MALE','659141, г. Добрянка, ул. Советская, дом 44, квартира 152','T0BHJIDTSE'),(8,'Орлова','Синклитикия','Ефимовна','1972-02-21','FEMALE','141516, г. Луза, ул. Авиаконструктора Микояна, дом 30, квартира 241','E98GF4I54G'),(9,'Панфилов','Парфений','Евгеньевич','1975-02-27','MALE','238050, г. Усть-Кишерть, ул. Абельмановская, дом 63, квартира 53','WTV0ZCIGSL'),(10,'Мельникова','Нина','Онисимовна','1989-05-17','FEMALE','102043, г. Чаны, ул. Бауманская, дом 77, квартира 266','W63VF3ZEYO'),(11,'Большакова','Ксения','Игоревна','1993-09-06','FEMALE','422527, г. Черемисиново, ул. Авангардная, дом 6, квартира 62','JNK6OA3R1B'),(12,'Кириллова','Василиса','Степановна','1990-12-30','FEMALE','150502, г. Сосновка, ул. Достоевского, дом 20, квартира 260','MZTIQZYWKI'),(13,'Быков','Аркадий','Даниилович','1986-10-11','MALE','404519, г. Крылово, ул. Баженова, дом 35, квартира 101','X3ZOGT1SIC'),(14,'Иван','Иванов','Иванович','1986-10-11','MALE','152646, г. Новомосковский, ул. Дальняя, дом 79, квартира 136','YIYX105V14'),(15,'Максимов','Федосей','Эдуардович','1986-10-10','MALE','679512, г. Чернушка, ул. Бакинская, дом 71, квартира 121','8KFYNTITU6'),(16,'Панова','Евпраксия','Кондратовна','1997-05-13','FEMALE','628325, г. Березник, ул. Азовская, дом 82, квартира 267','H2VEMTRLAO'),(17,'Федотов','Алексей','Агафонович','2000-02-16','MALE','346838, г. Устюжна, ул. Бартеневская, дом 68, квартира 263','4T02YFGULJ'),(18,'Шубин','Юрий','Константинович','1986-10-11','MALE','306005, г. Тюкалинск, ул. Бардина, дом 71, квартира 2','7RYIYVG9GT'),(19,'Казаков','Еремей','Леонидович','2001-03-07','MALE','623939, г. Щигры, ул. Багрицкого, дом 33, квартира 1','FOKZ4MWYJR'),(20,'Калинина','Пелагея','Дмитрьевна','2003-02-18','FEMALE','623989, г. Красногорское, ул. Вагонников 2-я, дом 64, квартира 289','MPXYHJEK0I'),(21,'Карпова','Ангелина','Макаровна','2007-07-13','FEMALE','606588, г. Лысково, ул. Гайдара, дом 56, квартира 232','79AOW74WY2'),(22,'Пахомов','Анатолий','Никитевич','2013-08-17','MALE','627040, г. Погар, ул. Бауманская, дом 75, квартира 55','OJFHVQX8NZ'),(23,'Хаус','Грегори','','1959-05-15','MALE','221b,','VD09AAGIUN'),(24,'Форман','Эрик','','1967-08-12','MALE','не известно','61536bg'),(25,'Лиза','Кадди','',NULL,'FEMALE','не известно','fds2356235');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-17 12:11:46
