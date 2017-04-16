-- MySQL dump 10.13  Distrib 5.5.23, for Win32 (x86)
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
-- Table structure for table `diagnose`
--

DROP TABLE IF EXISTS `diagnose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnose` (
  `diagnose_id` int(11) NOT NULL AUTO_INCREMENT,
  `diagnose_name` varchar(45) DEFAULT NULL,
  `therapy` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`diagnose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnose`
--

LOCK TABLES `diagnose` WRITE;
/*!40000 ALTER TABLE `diagnose` DISABLE KEYS */;
INSERT INTO `diagnose` VALUES (1,'Волчанка','Симптоматическое лечение'),(2,'ОРЗ','Для профилактики заражения ОРВИ необходимо изолировать больного от окружающих, выделить ему индивидуальную посуду, которую следует ошпаривать кипятком.'),(3,'Перелом','Наложить гипс');
/*!40000 ALTER TABLE `diagnose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patients` (
  `patient_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `middle_name` varchar(25) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `passport_number` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Perov','Р�РІР°РЅРѕРІ','Р�РІР°РЅРѕРІРёС‡','1986-10-10','m','Strit 1','BS 201690'),(2,'Perov','Иванов','Иванович','1986-09-10','m','Strit 1','BS 201690'),(3,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(4,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(5,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(6,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(7,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(8,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(9,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(10,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(11,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(12,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(13,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(14,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(15,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(16,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(17,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(18,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(19,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(20,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(21,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(22,'Perov',NULL,NULL,NULL,'MALE',NULL,NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_name` varchar(45) NOT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (6,'NURSE'),(7,'ADMINISTRATOR'),(8,'DOCTOR');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_type_id` int(11) DEFAULT NULL,
  `survey_history_id` int(11) DEFAULT NULL,
  `description` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`prescription_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_execution`
--

DROP TABLE IF EXISTS `prescription_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription_execution` (
  `prescription_execution_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `prescription_execution_date` date DEFAULT NULL,
  `done` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`prescription_execution_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_execution`
--

LOCK TABLES `prescription_execution` WRITE;
/*!40000 ALTER TABLE `prescription_execution` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription_execution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription_type`
--

DROP TABLE IF EXISTS `prescription_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription_type` (
  `prescription_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `prescription_type_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`prescription_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription_type`
--

LOCK TABLES `prescription_type` WRITE;
/*!40000 ALTER TABLE `prescription_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `prescription_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sick_list`
--

DROP TABLE IF EXISTS `sick_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sick_list` (
  `sick_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `date_in` date DEFAULT NULL,
  `date_out` date DEFAULT NULL,
  `room` varchar(20) DEFAULT NULL,
  `symptoms` varchar(300) DEFAULT NULL,
  `discharge` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`sick_list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sick_list`
--

LOCK TABLES `sick_list` WRITE;
/*!40000 ALTER TABLE `sick_list` DISABLE KEYS */;
INSERT INTO `sick_list` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL),(2,2,NULL,NULL,NULL,NULL,NULL),(3,2,'2017-10-10',NULL,NULL,NULL,NULL),(4,2,'2012-12-11','2012-12-11','Room','Ssssssss',0),(5,2,'2012-12-11','2012-12-11','Room','Ssssssss',0),(6,2,'2017-04-10','2012-12-11','Room','Ssssssss',0),(7,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',0),(8,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',0),(9,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',0),(10,2,'2017-04-10','2017-04-10','палата №6','не разобрали мычание',0),(11,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',0),(12,2,'2012-12-11',NULL,'палата №6','не разобрали мычание',0),(13,2,'2012-12-11',NULL,'палата №6','не разобрали мычание',0),(14,2,'2012-12-11',NULL,'палата №6','разобрали мычание',0);
/*!40000 ALTER TABLE `sick_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `first_name` varchar(25) DEFAULT NULL,
  `last_name` varchar(25) DEFAULT NULL,
  `middle_name` varchar(25) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `login` varchar(15) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `fired` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey_history`
--

DROP TABLE IF EXISTS `survey_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `survey_history` (
  `survey_history_id` int(11) NOT NULL AUTO_INCREMENT,
  `sick_list_id` int(11) DEFAULT NULL,
  `diagnose_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL,
  `survey_date` date DEFAULT NULL,
  `survey_description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`survey_history_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_history`
--

LOCK TABLES `survey_history` WRITE;
/*!40000 ALTER TABLE `survey_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-13 16:40:11
