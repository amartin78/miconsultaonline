-- MySQL dump 10.13  Distrib 5.7.34, for osx10.12 (x86_64)
--
-- Host: localhost    Database: consultaonline
-- ------------------------------------------------------
-- Server version	5.7.34

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
-- Table structure for table `Medico`
--

use heroku_01d8c9df605ed4c;

DROP TABLE IF EXISTS `Medico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Medico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `especialidad` varchar(45) DEFAULT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Medico`
--

LOCK TABLES `Medico` WRITE;
/*!40000 ALTER TABLE `Medico` DISABLE KEYS */;
INSERT INTO `Medico` VALUES (1,'Arturo','arturo@hotmail.com','1234','urólogo'),(2,'Vicente','vicente@hotmail.com','4321','Gastroenterólogo');
/*!40000 ALTER TABLE `Medico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Paciente`
--

DROP TABLE IF EXISTS `Paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Paciente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `fec_nacimiento` date NOT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `estado_civil` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Paciente`
--

LOCK TABLES `Paciente` WRITE;
/*!40000 ALTER TABLE `Paciente` DISABLE KEYS */;
INSERT INTO `Paciente` VALUES (1,'Luis','Tello','luis@hotmail.com','1234','1980-09-04',NULL,NULL,NULL),(2,'Carlos','Rubio','carlos@hotmail.com','1234','2000-11-02',NULL,NULL,NULL),(4,'Rodrigo','GarcÃ­a','rodrigo@hotmail.com','1234','2010-11-19',NULL,NULL,NULL);
/*!40000 ALTER TABLE `Paciente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-15 15:50:07
