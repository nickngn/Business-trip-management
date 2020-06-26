CREATE DATABASE  IF NOT EXISTS `business_trip_management` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `business_trip_management`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: business_trip_management
-- ------------------------------------------------------
-- Server version	5.6.48

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `finance_incurred_plan`
--

DROP TABLE IF EXISTS `finance_incurred_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `finance_incurred_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee` varchar(30) NOT NULL,
  `cost` decimal(15,2) NOT NULL DEFAULT '0.00',
  `plan_id` int(11) NOT NULL,
  `is_confirmed` tinyint(1) DEFAULT NULL,
  `tax` decimal(10,2) DEFAULT '0.00',
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `finance_incurred_plan_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finance_incurred_plan`
--

LOCK TABLES `finance_incurred_plan` WRITE;
/*!40000 ALTER TABLE `finance_incurred_plan` DISABLE KEYS */;
INSERT INTO `finance_incurred_plan` VALUES (1,'dfsd',10000.00,1043,1,0.00,NULL);
/*!40000 ALTER TABLE `finance_incurred_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finance_plan`
--

DROP TABLE IF EXISTS `finance_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `finance_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee` varchar(30) NOT NULL,
  `cost` decimal(15,2) NOT NULL DEFAULT '0.00',
  `plan_id` int(11) NOT NULL,
  `description` varchar(256) DEFAULT '',
  `tax` decimal(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `finance_plan_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finance_plan`
--

LOCK TABLES `finance_plan` WRITE;
/*!40000 ALTER TABLE `finance_plan` DISABLE KEYS */;
INSERT INTO `finance_plan` VALUES (1,'Ti?n ?i l?i',100000.00,1042,NULL,0.00),(2,'Ti?n ?i l?i',100000.00,1043,NULL,0.00);
/*!40000 ALTER TABLE `finance_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `person_quantity` int(11) DEFAULT NULL,
  `location` varchar(128) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `attachment` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'dfdfd',43,'','2020-06-25','2020-06-26','',NULL);
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personel_incurred_plan`
--

DROP TABLE IF EXISTS `personel_incurred_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personel_incurred_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `action` varchar(20) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `is_confirmed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `personel_incurred_plan_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`),
  CONSTRAINT `personel_incurred_plan_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personel_incurred_plan`
--

LOCK TABLES `personel_incurred_plan` WRITE;
/*!40000 ALTER TABLE `personel_incurred_plan` DISABLE KEYS */;
INSERT INTO `personel_incurred_plan` VALUES (1,1043,503,'out','2020-06-24','Tham gia',0);
/*!40000 ALTER TABLE `personel_incurred_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personel_plan`
--

DROP TABLE IF EXISTS `personel_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personel_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `plan_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `personel_plan_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`),
  CONSTRAINT `personel_plan_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personel_plan`
--

LOCK TABLES `personel_plan` WRITE;
/*!40000 ALTER TABLE `personel_plan` DISABLE KEYS */;
INSERT INTO `personel_plan` VALUES (1,1042,505),(2,1042,506),(3,1043,505),(4,1043,506);
/*!40000 ALTER TABLE `personel_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `location` varchar(50) NOT NULL,
  `start_date` date DEFAULT NULL,
  `finish_date` date DEFAULT NULL,
  `leader_id` int(11) DEFAULT NULL,
  `status` enum('Unconfirmed','On_Going','Denied','Finished') NOT NULL DEFAULT 'Unconfirmed',
  `total_cost` double(15,2) DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `frk_plan_user_idx` (`leader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1044 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1042,'vintegration-platform','Thành ph? Pleiku, Gia Lai','2020-06-14','2020-06-29',504,'Denied',0.00),(1043,'vintegration-platform','Thành ph? Pleiku, Gia Lai','2020-06-22','2020-06-25',504,'Finished',110000.00);
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_DIRECTOR'),(2,'ROLE_EMPLOYEE'),(3,'ROLE_ACCOUNTANT'),(4,'ROLE_SYSTEM_MANAGEMENT');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `threshold`
--

DROP TABLE IF EXISTS `threshold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `threshold` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fee` varchar(255) NOT NULL,
  `amount` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fee_UNIQUE` (`fee`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `threshold`
--

LOCK TABLES `threshold` WRITE;
/*!40000 ALTER TABLE `threshold` DISABLE KEYS */;
INSERT INTO `threshold` VALUES (1,'Đi lại',1000000.00),(2,'sdfsadf',324234.00),(3,'dfgerte',34234.00);
/*!40000 ALTER TABLE `threshold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `position` varchar(40) DEFAULT NULL,
  `work_unit` varchar(255) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `for_key_user_role` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=507 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (503,'admin','$2a$10$iit9X5KXxz0BYS6rq2aka.eddlpdQ27HPlqLDtIfpfxlw3eck.eHy','Admin','IT',NULL,4),(504,'director1','$2a$10$8IP9AmlD4/C8HSLgl38qXuQQe.nyJOYtGYj2ADe.bxyG7QESURD7u','Director','Director 1',NULL,1),(505,'employee1','$2a$10$HYH8iATxYcRIVW5SSXQjm.YCjB0sO/ixBNU/PnVbpqz5A6uNFGjI2','Employee','employee 1',NULL,2),(506,'acountant1','$2a$10$aiiiVwyWRj4IBPQW7ILJk.hFiuD2bQisMNrmsbcLfwqJeZgDPCy1q','Accountant','acountant 1',NULL,3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-24 18:42:13
