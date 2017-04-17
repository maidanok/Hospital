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
  PRIMARY KEY (`survey_history_id`),
  KEY `staff_idx` (`staff_id`),
  KEY `diagnose_idx` (`diagnose_id`),
  KEY `sick_list_idx` (`sick_list_id`),
  CONSTRAINT `sick_list` FOREIGN KEY (`sick_list_id`) REFERENCES `sick_list` (`sick_list_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `diagnose` FOREIGN KEY (`diagnose_id`) REFERENCES `diagnose` (`diagnose_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `staff` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`person_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey_history`
--

LOCK TABLES `survey_history` WRITE;
/*!40000 ALTER TABLE `survey_history` DISABLE KEYS */;
INSERT INTO `survey_history` VALUES (1,4,3,23,'2017-12-11','идиот'),(2,4,3,25,'2017-04-16','проверочка');
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

-- Dump completed on 2017-04-17 12:11:46
