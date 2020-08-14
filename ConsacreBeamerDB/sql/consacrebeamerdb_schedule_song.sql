-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: consacrebeamerdb
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `schedule_song`
--

DROP TABLE IF EXISTS `schedule_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_song` (
  `scheduleId` bigint(20) NOT NULL,
  `songId` bigint(20) NOT NULL,
  UNIQUE KEY `UK_3hl4l3162lidop7oypwxx6w2l` (`songId`),
  KEY `FK7o7w0fxk4c6fwy7biapyjp7hf` (`scheduleId`),
  CONSTRAINT `FK3wtn4e1wbm509pkuqc052xb32` FOREIGN KEY (`songId`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FK5a08lksckfe2xgcc9pcdf1vrq` FOREIGN KEY (`scheduleId`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FK7o7w0fxk4c6fwy7biapyjp7hf` FOREIGN KEY (`scheduleId`) REFERENCES `song` (`id`),
  CONSTRAINT `FKa69dpjp8uh3o1j4gmis36yfav` FOREIGN KEY (`songId`) REFERENCES `song` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_song`
--

LOCK TABLES `schedule_song` WRITE;
/*!40000 ALTER TABLE `schedule_song` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_song` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-15 22:57:15
