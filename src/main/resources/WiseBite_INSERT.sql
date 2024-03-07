USE `wisebite`;
-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (x86_64)
--
-- Host: localhost    Database: wisebite
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(100) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `infix` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) NOT NULL,
  `usertype` enum('ADMIN','CLIENT','COACH','DIETITIAN') NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('user1','testtest','Arnie','van','Schwarzi','CLIENT'),('user10','testtest','Elizabeth',NULL,'Greatest','COACH'),('user11','testtest','Marc',NULL,'Anthony','COACH'),('user2','testtest','Taylor','den','Swifti','CLIENT'),('user3','testtest','John',NULL,'Doe','CLIENT'),('user4','testtest','Thomas',NULL,'Rocky','CLIENT'),('user5','testtest','Forest',NULL,'Gump','CLIENT'),('user6','testtest','Keanu',NULL,'Rivers','CLIENT'),('user7','testtest','Anna',NULL,'Karenina','CLIENT'),('user8','testtest','Terry ',NULL,'Pratchett','DIETITIAN'),('user9','testtest','Sophia',NULL,'Loren','DIETITIAN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `verzinzelf10` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `date_time` datetime NOT NULL,
  `subject` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `attendee` varchar(45) NOT NULL,
  PRIMARY KEY (`date_time`,`username`),
  KEY `verzinzelf13_idx` (`username`),
  KEY `verzinzelf14_idx` (`attendee`),
  CONSTRAINT `verzinzelf13` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf14` FOREIGN KEY (`attendee`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `username` varchar(45) NOT NULL,
  `weight` double NOT NULL,
  `height` double NOT NULL,
  `start_date` date NOT NULL,
  `dietitian` varchar(45) DEFAULT NULL,
  `coach` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `verzinzelf15_idx` (`dietitian`),
  KEY `verzinzelf17_idx` (`coach`),
  CONSTRAINT `verzinzelf15` FOREIGN KEY (`dietitian`) REFERENCES `dietitian` (`username`),
  CONSTRAINT `verzinzelf17` FOREIGN KEY (`coach`) REFERENCES `coach` (`username`),
  CONSTRAINT `verzinzelf2` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('user1',88,155,'2023-12-12','user8','user10'),('user2',90,165,'2023-12-12','user8','user10'),('user3',110,190,'2024-12-12','user8','user10'),('user4',88,165,'2024-01-01','user8','user10'),('user5',98,177,'2023-12-12','user9','user11'),('user6',100,165,'2024-01-10','user9','user11'),('user7',77,174,'2023-02-02','user9','user11'),('user8',84,167,'2023-12-02','user9','user11');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coach`
--

DROP TABLE IF EXISTS `coach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coach` (
  `username` varchar(45) NOT NULL,
  `dietitian` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `verzinzelf12_idx` (`username`),
  KEY `verzinzelf16_idx` (`dietitian`),
  CONSTRAINT `verzinzelf12` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf16` FOREIGN KEY (`dietitian`) REFERENCES `dietitian` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coach`
--

LOCK TABLES `coach` WRITE;
/*!40000 ALTER TABLE `coach` DISABLE KEYS */;
INSERT INTO `coach` VALUES ('user10','user8'),('user11','user9');
/*!40000 ALTER TABLE `coach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailytask`
--

DROP TABLE IF EXISTS `dailytask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dailytask` (
  `date` date NOT NULL,
  `daily_goal` tinyint NOT NULL,
  `client` varchar(45) NOT NULL,
  `workout_id` int DEFAULT NULL,
  `diet_id` int DEFAULT NULL,
  PRIMARY KEY (`date`,`client`),
  KEY `verzinzelf3_idx` (`client`),
  KEY `verzinzelf7_idx` (`workout_id`),
  KEY `verzinzelf5_idx` (`diet_id`),
  CONSTRAINT `verzinzelf3` FOREIGN KEY (`client`) REFERENCES `plan` (`client`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `verzinzelf5` FOREIGN KEY (`diet_id`) REFERENCES `diet` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `verzinzelf7` FOREIGN KEY (`workout_id`) REFERENCES `workout` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailytask`
--

LOCK TABLES `dailytask` WRITE;
/*!40000 ALTER TABLE `dailytask` DISABLE KEYS */;
INSERT INTO `dailytask` VALUES ('2022-01-01',2,'user2',2,3),('2022-01-02',2,'user2',1,4),('2022-01-03',2,'user2',2,4),('2022-01-04',2,'user2',1,2),('2023-12-12',0,'user1',1,1),('2024-01-01',2,'user1',1,2),('2024-01-02',1,'user1',2,3),('2024-01-03',2,'user1',1,4),('2024-12-12',1,'user1',2,2);
/*!40000 ALTER TABLE `dailytask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet`
--

DROP TABLE IF EXISTS `diet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `calorie_amount` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diet`
--

LOCK TABLES `diet` WRITE;
/*!40000 ALTER TABLE `diet` DISABLE KEYS */;
INSERT INTO `diet` VALUES (1,'lowcarb',1500),(2,'lowfat',1500),(3,'veggie',1500),(4,'highccal',1800);
/*!40000 ALTER TABLE `diet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dietitian`
--

DROP TABLE IF EXISTS `dietitian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dietitian` (
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `verzinzelf11_idx` (`username`),
  CONSTRAINT `verzinzelf11` FOREIGN KEY (`username`) REFERENCES `user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dietitian`
--

LOCK TABLES `dietitian` WRITE;
/*!40000 ALTER TABLE `dietitian` DISABLE KEYS */;
INSERT INTO `dietitian` VALUES ('user8'),('user9');
/*!40000 ALTER TABLE `dietitian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `reps` int DEFAULT NULL,
  `weight_amount` int DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES ('burpee','cardio',10,0,15),('clean','weight',2,10,12),('press','gewoon',3,1,10),('snatch','weight',3,22,14),('squat','gewoon',4,14,15);
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exerciseworkout`
--

DROP TABLE IF EXISTS `exerciseworkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exerciseworkout` (
  `exercise` varchar(45) NOT NULL,
  `workout` int NOT NULL,
  PRIMARY KEY (`exercise`,`workout`),
  KEY `verzinzelf9_idx` (`workout`),
  KEY `verzinzelf8_idx` (`exercise`),
  CONSTRAINT `verzinzelf8` FOREIGN KEY (`exercise`) REFERENCES `exercise` (`name`),
  CONSTRAINT `verzinzelf9` FOREIGN KEY (`workout`) REFERENCES `workout` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exerciseworkout`
--

LOCK TABLES `exerciseworkout` WRITE;
/*!40000 ALTER TABLE `exerciseworkout` DISABLE KEYS */;
INSERT INTO `exerciseworkout` VALUES ('burpee',1),('press',1),('squat',1),('clean',2),('snatch',2);
/*!40000 ALTER TABLE `exerciseworkout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal` (
  `recipe` varchar(45) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`recipe`,`id`),
  KEY `verzinzelf6_idx` (`recipe`),
  KEY `verzinzelf4_idx` (`id`),
  CONSTRAINT `verzinzelf4` FOREIGN KEY (`id`) REFERENCES `diet` (`id`),
  CONSTRAINT `verzinzelf6` FOREIGN KEY (`recipe`) REFERENCES `recipe` (`name`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES ('Breakfast2',1),('Dinner',1),('Snack2',1),('Breakfast',2),('Dinner2',2),('Lunch1',2),('Snack',2),('breakfast3',3),('Dinner3',3),('lunch4',3),('Breakfast4',4),('Snack4',4);
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `weight_loss_goal` int NOT NULL,
  `plan_goal` tinyint(1) NOT NULL,
  `duration` int NOT NULL,
  `client` varchar(45) NOT NULL,
  PRIMARY KEY (`client`),
  CONSTRAINT `verzinzelf1` FOREIGN KEY (`client`) REFERENCES `client` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (20,0,60,'user1'),(10,0,30,'user2'),(30,0,40,'user3'),(15,0,25,'user4');
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `name` varchar(45) NOT NULL,
  `description` text NOT NULL,
  `calorie_amount` int NOT NULL,
  `instructions` text NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES ('Breakfast','somedescription',400,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Breakfast2','somedescription2',500,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Breakfast3','somethingdescript3',444,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Breakfast4','somethingagain',532,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Dinner','healthy dinner',322,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Dinner2','healthy dinner',432,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Dinner3','another dinner',433,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Lunch1','lunchdescript',323,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('lunch2','luunchnice',222,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('lunch4','another lunch',343,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Snack','some snack',123,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Snack2','some snack',232,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'),('Snack4','some snack',222,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.');
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout`
--

DROP TABLE IF EXISTS `workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `duration` int NOT NULL,
  `burned_calories` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout`
--

LOCK TABLES `workout` WRITE;
/*!40000 ALTER TABLE `workout` DISABLE KEYS */;
INSERT INTO `workout` VALUES (1,'cardio',60,600),(2,'strength',30,300),(3,'something',35,400),(4,'againsomething',15,200);
/*!40000 ALTER TABLE `workout` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-07 14:04:30

