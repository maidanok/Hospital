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
-- Table structure for table `sick_list`
--

DROP TABLE IF EXISTS `sick_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sick_list`
--

LOCK TABLES `sick_list` WRITE;
/*!40000 ALTER TABLE `sick_list` DISABLE KEYS */;
INSERT INTO `sick_list` VALUES (1,NULL,NULL,NULL,NULL,NULL,1),(2,2,NULL,NULL,NULL,NULL,1),(3,2,'2017-10-10',NULL,NULL,NULL,1),(4,2,'2012-12-11','2012-12-11','Room','Ssssssss',1),(5,2,'2012-12-11','2012-12-11','Room','Ssssssss',1),(6,2,'2017-04-10','2012-12-11','Room','Ssssssss',1),(7,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',1),(8,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',1),(9,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',1),(10,2,'2017-04-10','2017-04-10','палата №6','не разобрали мычание',1),(11,2,'2017-04-10',NULL,'палата №6','не разобрали мычание',1),(12,2,'2012-12-11',NULL,'палата №6','не разобрали мычание',1),(13,2,'2012-12-11',NULL,'палата №6','не разобрали мычание',1),(14,2,'2012-12-11',NULL,'палата №6','разобрали мычание',1);
/*!40000 ALTER TABLE `sick_list` ENABLE KEYS */;
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
