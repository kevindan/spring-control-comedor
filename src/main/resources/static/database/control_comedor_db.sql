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
  `email` varchar(255) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_comensal`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comensales`
--

LOCK TABLES `comensales` WRITE;
/*!40000 ALTER TABLE `comensales` DISABLE KEYS */;
INSERT INTO `comensales` VALUES (1,'Huarancca','Berrio','Av. Dominicos 456, Callao','45874698',0,'valentina.berrio@deeconsultores.pe','2018-09-25','Valentina','Mujer','998745632'),(2,'Córdova','Acuña','Jr. Alfonso Ugarte 588, Tarapoto','78965412',0,'brettsacuna@gmail.com','2018-09-25','Davis Bretts','Hombre','925874569'),(3,'Huertas','Ojeda','Av. Mariano Cornejo 456, Pueblo Libre','01254789',0,'susana.ojeda@deeconsultores.pe','2018-09-25','Susana','Mujer','965874563'),(4,'Mamani','Chambi','Calle Los Cedros 285, San isidro','01269855',0,'nestitor@deeconsultores.pe','2018-09-25','Néstor','Hombre','987456333'),(5,'Bustamante','Salazar','Av. Javier Prado 2569, Salamanca','45688888',0,'gabriela.salazar@deeconsultores.pe','2018-09-25','Gabriela','Mujer','999874123'),(6,'Flores','Acuña','Av. Los Pinos 456, Santiago de Surco','99874522',0,'sol.acuna@deeconsultores.pe','2018-09-25','Sol María','Mujer','933658965'),(7,'Montilla','Robles','Jr. Loreto 457, Callao','01012369',0,'soila.maxima@gmail.com','2018-09-25','Zoila Luisa','Mujer','982236598');
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
  `monto_cancelado` decimal(10,2) DEFAULT '0.00',
  `monto_igv` decimal(10,2) DEFAULT '0.00',
  `monto_sin_igv` decimal(10,2) DEFAULT '0.00',
  `monto_total` decimal(10,2) DEFAULT '0.00',
  `saldo` decimal(10,2) DEFAULT '0.00',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `precio` decimal(10,2) DEFAULT '0.00',
  `sub_total` decimal(10,2) DEFAULT '0.00',
  `id_producto` bigint(20) DEFAULT NULL,
  `id_consumo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_detalle_consumo`),
  KEY `FKt9bq8qvpcwfmy0s1rb8ggynke` (`id_producto`),
  KEY `FK3y2drak3tkqjhj7jkcfqa4e9u` (`id_consumo`),
  CONSTRAINT `FK3y2drak3tkqjhj7jkcfqa4e9u` FOREIGN KEY (`id_consumo`) REFERENCES `consumo` (`id_consumo`),
  CONSTRAINT `FKt9bq8qvpcwfmy0s1rb8ggynke` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
  `fecha_registro` date DEFAULT NULL,
  `precio_compra` decimal(10,2) DEFAULT '0.00',
  `precio_venta` decimal(10,2) DEFAULT '0.00',
  `presentacion` varchar(255) DEFAULT NULL,
  `stock_actual` int(11) DEFAULT NULL,
  `stock_minimo` int(11) DEFAULT NULL,
  `id_tipo_producto` bigint(20) DEFAULT NULL,
  `alerta` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id_producto`),
  KEY `FKojclx01cd3urfm3ac7l3aafud` (`id_tipo_producto`),
  CONSTRAINT `FKojclx01cd3urfm3ac7l3aafud` FOREIGN KEY (`id_tipo_producto`) REFERENCES `tipo_producto` (`id_tipo_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Coca Cola personal de 500 ml',0,'2018-09-25',1.80,2.50,'Envase no retornable de 500 ml',15,5,4,_binary ''),(2,'Guaraná Backus personal de 500 ml',0,'2018-09-25',1.50,2.00,'Envase no retornable de 500 ml',10,5,4,_binary ''),(3,'Chicha Morada',0,'2018-09-25',0.00,1.50,'Vaso mediano de 200 ml',10,1,4,_binary ''),(4,'Desayuno',0,'2018-09-25',0.00,8.00,'Desayuno completo: Entrada, segundo y postre',40,5,1,_binary '\0'),(5,'Almuerzo',0,'2018-09-25',0.00,8.00,'Almuerzo Completo: Entrada, segundo y postre',50,5,1,_binary '\0'),(6,'Cena',0,'2018-09-25',0.00,8.00,'Cena completa: Entrada, segundo y postre',35,5,1,_binary '\0'),(7,'Papas fritas',0,'2018-09-25',0.00,4.50,'Plato de porción mediana',6,2,2,_binary '\0'),(8,'Arroz cocido',0,'2018-09-25',0.00,3.00,'Plato de porción pequeña',6,1,2,_binary '\0'),(9,'Huevo frito',0,'2018-09-25',0.00,1.50,'Unidad',10,3,2,_binary '\0'),(10,'Empanada de Pollo',0,'2018-09-25',2.00,3.50,'Unidad de tamaño mediano',9,2,3,_binary ''),(11,'Sandwich Triple de pollo, durazno y jamón',0,'2018-09-25',2.30,3.00,'Unidad de tamaño grande',8,3,3,_binary ''),(12,'Ensalada César',0,'2018-09-25',0.00,3.50,'Bowl de tamaño pequeño',7,2,3,_binary '\0'),(13,'Sandwich de pollo a la brasa',0,'2018-09-25',2.50,3.50,'Unidad de tamaño mediano',8,2,3,_binary ''),(14,'Chifles piuranos',0,'2018-09-25',1.00,1.50,'Bolsa de 120 gr',9,2,5,_binary ''),(15,'Doritos',0,'2018-09-25',1.00,1.50,'Bolsa de 85 gr',10,2,5,_binary ''),(16,'Chicharrón de soya',0,'2018-09-25',1.00,1.50,'Bolsa de 90 gr',15,3,5,_binary ''),(17,'Pie de limón',0,'2018-09-25',0.00,4.00,'Unidad de 250 gr',5,2,6,_binary ''),(18,'Chesse cake de maracuyá',0,'2018-09-25',2.00,3.50,'Unidad de 250 gr',8,2,6,_binary ''),(19,'Mazamorra morada',0,'2018-09-25',1.50,2.50,'Vaso de 200 ml',7,2,6,_binary ''),(20,'Leche asada',0,'2018-09-25',2.00,3.50,'Pirex de 250 gr',6,2,6,_binary ''),(21,'Mote Frito picante',1,'2018-09-26',1.00,1.50,'Bolsa de 150 gr',15,1,5,_binary '');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_producto`
--

LOCK TABLES `tipo_producto` WRITE;
/*!40000 ALTER TABLE `tipo_producto` DISABLE KEYS */;
INSERT INTO `tipo_producto` VALUES (1,'Menú',0),(2,'Complementos',0),(3,'Aperitivo',0),(4,'Bebidas',0),(5,'Snacks',0),(6,'Postres',0);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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

-- Dump completed on 2018-09-26 18:17:06
