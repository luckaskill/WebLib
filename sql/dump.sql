-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library_bd
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `house` int(11) DEFAULT NULL,
  `users_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addresses_users_idx` (`users_id`),
  CONSTRAINT `fk_addresses_users` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_passwords`
--

DROP TABLE IF EXISTS `admin_passwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `admin_passwords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) DEFAULT NULL,
  `action_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_passwords`
--

LOCK TABLES `admin_passwords` WRITE;
/*!40000 ALTER TABLE `admin_passwords` DISABLE KEYS */;
INSERT INTO `admin_passwords` (`id`, `password`, `action_name`) VALUES (5,'Block','Block'),(6,'Unblock','Unblock'),(7,'Promote','Promote'),(8,'Demotion','Demotion');
/*!40000 ALTER TABLE `admin_passwords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `issue` year(4) DEFAULT NULL,
  `cost` decimal(10,2) DEFAULT NULL,
  `rent_cost` decimal(10,2) DEFAULT NULL,
  `rating` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`id`, `title`, `author`, `issue`, `cost`, `rent_cost`, `rating`) VALUES (27,'Java core','Core A. J.',2011,10.00,5.00,78),(28,'Java core 2','Dr. Java',2013,31.00,5.00,70),(29,'Hibernate p.1','Hiber Java Core',2018,20.00,5.00,89),(30,'C#','Noone',2019,10.00,5.00,3),(31,'DI SOLID','whoami',2017,10.00,5.00,96),(32,'Spring start','Winter',2019,23.99,5.00,97),(33,'C HQ','HiQo',2013,10.00,5.00,75),(34,'PHP','PHPsher',2019,34.00,5.00,92);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rent`
--

DROP TABLE IF EXISTS `rent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `books_id` int(11) NOT NULL,
  `rentDate` date DEFAULT NULL,
  `returnDeadline` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rent_users1_idx` (`users_id`),
  KEY `fk_rent_books1_idx` (`books_id`),
  CONSTRAINT `fk_rent_books1` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`),
  CONSTRAINT `fk_rent_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rent`
--

LOCK TABLES `rent` WRITE;
/*!40000 ALTER TABLE `rent` DISABLE KEYS */;
INSERT INTO `rent` (`id`, `users_id`, `books_id`, `rentDate`, `returnDeadline`) VALUES (46,61,27,'2019-03-05','2019-07-05'),(47,61,28,'2019-03-05','2019-07-05'),(48,61,30,'2019-03-05','2019-07-05'),(54,3,27,'2019-03-05','2019-07-05'),(56,3,31,'2019-03-05','2019-07-05'),(57,3,32,'2019-03-05','2019-07-05'),(58,3,33,'2019-03-05','2019-07-05');
/*!40000 ALTER TABLE `rent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sold`
--

DROP TABLE IF EXISTS `sold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sold` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `books_id` int(11) NOT NULL,
  `buyDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_buy_users1_idx` (`users_id`),
  KEY `fk_buy_books1_idx` (`books_id`),
  CONSTRAINT `fk_buy_books1` FOREIGN KEY (`books_id`) REFERENCES `books` (`id`),
  CONSTRAINT `fk_buy_users1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sold`
--

LOCK TABLES `sold` WRITE;
/*!40000 ALTER TABLE `sold` DISABLE KEYS */;
INSERT INTO `sold` (`id`, `users_id`, `books_id`, `buyDate`) VALUES (57,3,28,'2019-03-05'),(58,61,29,'2019-03-05'),(59,2,32,'2019-03-05'),(60,2,33,'2019-03-05'),(61,2,27,'2019-03-05');
/*!40000 ALTER TABLE `sold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `accessLevel` int(11) NOT NULL DEFAULT '1',
  `cash_value` float DEFAULT '30',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `accessLevel`, `cash_value`) VALUES (1,'las','sal',3,531.97),(2,'admin','admin',2,661.03),(3,'newacc','pass',1,253),(57,'geekLib','qqq',0,25),(60,'accnew','123',1,20),(61,'whoami','qqq',1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-16 19:37:18
