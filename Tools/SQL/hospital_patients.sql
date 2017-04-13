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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'Perov','Р�РІР°РЅРѕРІ','Р�РІР°РЅРѕРІРёС‡','1986-10-10','m','Strit 1','BS 201690'),(2,'Perov','Иванов','Иванович','1986-09-10','m','Strit 1','BS 201690'),(3,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(4,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(5,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(6,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(7,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(8,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(9,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(10,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(11,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(12,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(13,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(14,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(15,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(16,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(17,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(18,'Иван','Иванов','Иванович','1986-10-11','m','Strit 1','BS 201690'),(19,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(20,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690'),(21,'Perov','Иванов','Иванович','1986-10-10','m','Strit 1','BS 201690');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-12 10:22:58
