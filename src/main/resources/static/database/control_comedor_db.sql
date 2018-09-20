CREATE DATABASE  IF NOT EXISTS `control_comedor_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `control_comedor_db`;
-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: control_comedor_db
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
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
 SET character_set_client = utf8mb4 ;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comensales`
--

LOCK TABLES `comensales` WRITE;
/*!40000 ALTER TABLE `comensales` DISABLE KEYS */;
INSERT INTO `comensales` VALUES (1,'Huarancca','Berrio','Jr. Los Dominicos 456, Callao','45874589',0,'2018-09-11','Valentina Guliana','Mujer','987654321','valentina.berrio@deeconsultores.pe'),(2,'Flores','Acuña','Jr. Los Pinos 132, Santiago de Surco','45871125',0,'2018-09-11','Sol María','Mujer','999854786','sol.acuna@deeconsultores.pe'),(3,'Huertas','Ojeda','Av. Mariano Cornejo 478, Pueblo libre','01201254',0,'2018-09-11','Susana','Mujer','987433256','susana.ojeda@deeconsultores.pe'),(4,'Sumarriva','Valenzuela','Calle Murillo 158, San Borja','12203698',0,'2018-09-11','Fernando','Hombre','987422214','fernando.valenzuela@deeconsultores.pe'),(5,'Urbina','León','Jr. Calle Suecia 165, Salamanca, Ate Vitarte','12047789',0,'2018-09-11','Fanny Ines','Mujer','987466523','fanny.león@deeconsultores.pe'),(6,'Mayuri','Piña','Jr. Perú 256, La Banda de Shilcayo','98745632',0,'2018-09-11','Lucía','Mujer','999658326','lucia.pina@deeconsultores.pe');
/*!40000 ALTER TABLE `comensales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumo`
--

DROP TABLE IF EXISTS `consumo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
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
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipo_producto` (
  `id_tipo_producto` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `eliminado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Menú',0),(2,'Complementos',0),(3,'Aperitivo',0),(4,'Bebidas',0),(5,'Snacks',0);
/*!40000 ALTER TABLE `tipo_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-20 18:15:02
