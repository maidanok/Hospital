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
-- Table structure for table `diagnose`
--

DROP TABLE IF EXISTS `diagnose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnose` (
  `diagnose_id` int(11) NOT NULL AUTO_INCREMENT,
  `diagnose_name` varchar(45) DEFAULT NULL,
  `therapy` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`diagnose_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `diagnose`
--
LOCK TABLES `diagnose` WRITE;
/*!40000 ALTER TABLE `diagnose` DISABLE KEYS */;
INSERT INTO `diagnose` VALUES (1,'Устанавливается',''),
(2,'ОРЗ','Для профилактики заражения ОРВИ необходимо изолировать больного от окружающих, выделить ему индивидуальную посуду, которую следует ошпаривать кипятком.'),
(3,'Перелом','Наложить гипс'),
(4,'Столбняк','введение антитоксической противостолбнячной сыворотки и столбнячного анатоксина, человеческого противостолбнячного иммуноглобулина. Обязательна хирургическая обработка ран, в том числе и заживших к моменту болезни.\nВ качестве симптоматической терапии применяются успокаивающие и обезболивающие средства (аминазин, промедол, димедрол), антибактериальные препараты, искусственная вентиляция легких.'),
(5,'Инсульт','диазепам (реланиум, седуксен, сибазон) или рогипнол (флунитразепам)'),
(6,'Крапивница','При установлении конкретного аллергена нужно устранить контакт с ним, целесообразно провести мероприятия, перечисленные в разделе «Аллергический дерматит». Медикаментозное лечение также подробно описано в этом разделе');
/*!40000 ALTER TABLE `diagnose` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-17 12:11:45
