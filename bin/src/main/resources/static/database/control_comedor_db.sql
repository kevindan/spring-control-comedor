CREATE DATABASE  IF NOT EXISTS `control_comedor_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `control_comedor_db`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: control_comedor_db
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `comensales`
--

DROP TABLE IF EXISTS `comensales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comensales` (
  `id_comensal` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido_materno` varchar(255) DEFAULT NULL,
  `apellido_paterno` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `eliminado` int(11) DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_comensal`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comensales`
--

LOCK TABLES `comensales` WRITE;
/*!40000 ALTER TABLE `comensales` DISABLE KEYS */;
INSERT INTO `comensales` VALUES (1,'Velazco','Alvarado','Edificio Los olmos 1002, Residencial San Felipe, Jesús María','45698745',0,'2018-06-27','Ignacio Gabriel','Hombre','3220330','ignacio@hotmail.com'),(2,'Pomalca','Versalles','Calle Los Pinos 478, Miraflores','47893366',0,'2018-06-27','Andrea','Mujer','3220329','andrea@gmail.com'),(3,'Huaranca','Berrio','Dominicos 1023, Callao','47102466',0,'2018-06-27','Valentina','Mujer','3226952','valentinaguliana@gmail.com'),(4,'Sumarriva','Valenzuela','Los Álamos 1002, San Borja','10236548',0,'2018-06-27','Fernando','Hombre','3220330','fernando.valenzuela@deeconsultores.pe');
/*!40000 ALTER TABLE `comensales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumo`
--

DROP TABLE IF EXISTS `consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consumo` (
  `id_consumo` bigint(20) NOT NULL AUTO_INCREMENT,
  `eliminado` int(11) DEFAULT NULL,
  `estado_saldo` int(11) DEFAULT NULL,
  `fecha_consumo` date NOT NULL,
  `fecha_registro` date NOT NULL,
  `monto_cancelado` double DEFAULT NULL,
  `monto_igv` double DEFAULT NULL,
  `monto_sin_igv` double DEFAULT NULL,
  `monto_total` double DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  `id_comensal` bigint(20) DEFAULT NULL,
  `id_local` bigint(20) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_consumo`),
  KEY `FKgkfgm45a61uj5ht9sc9eikj5y` (`id_comensal`),
  KEY `FKsd41cwjud86j436ua83fvhmvc` (`id_local`),
  KEY `FKfea1g8rty46694tupl4218vne` (`id_usuario`),
  CONSTRAINT `FKfea1g8rty46694tupl4218vne` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `FKgkfgm45a61uj5ht9sc9eikj5y` FOREIGN KEY (`id_comensal`) REFERENCES `comensales` (`id_comensal`),
  CONSTRAINT `FKsd41cwjud86j436ua83fvhmvc` FOREIGN KEY (`id_local`) REFERENCES `locales` (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumo`
--

LOCK TABLES `consumo` WRITE;
/*!40000 ALTER TABLE `consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_consumo`
--

DROP TABLE IF EXISTS `detalle_consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalle_consumo` (
  `id_detalle_consumo` bigint(20) NOT NULL AUTO_INCREMENT,
  `cantidad` int(11) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `sub_total` double DEFAULT NULL,
  `id_producto` bigint(20) DEFAULT NULL,
  `id_consumo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_consumo`),
  KEY `FKt9bq8qvpcwfmy0s1rb8ggynke` (`id_producto`),
  KEY `FK3y2drak3tkqjhj7jkcfqa4e9u` (`id_consumo`),
  CONSTRAINT `FK3y2drak3tkqjhj7jkcfqa4e9u` FOREIGN KEY (`id_consumo`) REFERENCES `consumo` (`id_consumo`),
  CONSTRAINT `FKt9bq8qvpcwfmy0s1rb8ggynke` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_consumo`
--

LOCK TABLES `detalle_consumo` WRITE;
/*!40000 ALTER TABLE `detalle_consumo` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_consumo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locales`
--

DROP TABLE IF EXISTS `locales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `locales` (
  `id_local` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `eliminado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_local`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locales`
--

LOCK TABLES `locales` WRITE;
/*!40000 ALTER TABLE `locales` DISABLE KEYS */;
/*!40000 ALTER TABLE `locales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `id_producto` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `eliminado` int(11) DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `precio_compra` double DEFAULT NULL,
  `precio_venta` double NOT NULL,
  `presentacion` varchar(255) DEFAULT NULL,
  `stock_actual` int(11) DEFAULT NULL,
  `stock_minimo` int(11) DEFAULT NULL,
  `id_tipo_producto` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `FKojclx01cd3urfm3ac7l3aafud` (`id_tipo_producto`),
  CONSTRAINT `FKojclx01cd3urfm3ac7l3aafud` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipo_producto` (`id_tipo_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_rol` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha_registro` date NOT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `UK9yypc1um53qngb28qq2tg9kbe` (`id_usuario`,`rol`),
  CONSTRAINT `FK45bly479quh36chhc94sc4xvx` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_producto`
--

DROP TABLE IF EXISTS `tipo_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_producto` (
  `id_tipo_producto` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `eliminado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido_materno` varchar(255) DEFAULT NULL,
  `apellido_paterno` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) NOT NULL,
  `eliminado` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `habilitado` int(11) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_3m5n1w5trapxlbo2s42ugwdmd` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'control_comedor_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-27 18:16:28