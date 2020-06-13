-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fixmycode
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `archivos`
--

DROP TABLE IF EXISTS `archivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `archivos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `archivo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `tipoArchivo` enum('MARCA','JAVASCRIPT','CSS','SASS','JAVA','SQL','PROPERTIES') COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archivos`
--

LOCK TABLES `archivos` WRITE;
/*!40000 ALTER TABLE `archivos` DISABLE KEYS */;
INSERT INTO `archivos` VALUES (10,'OGG92G8DAjax.java','JAVA'),(11,'SC5HBBXZalumnosAjax.jsp','JAVA'),(12,'HBF1QDDQapplication.properties','PROPERTIES'),(13,'8ZQVD4W0factorial.java','JAVA'),(14,'6SD2354Rcreate_database_fixmycode.sql','SQL'),(15,'YT725CMDControlador.java','JAVA'),(16,'GIPHKOVGCuotaEdificio.java','JAVA'),(17,'67K77NOBejercicio1_examen.html','MARCA'),(18,'YRGZ9PGPmain.js','JAVASCRIPT'),(19,'RF1R98G0estilos-examen.css','CSS');
/*!40000 ALTER TABLE `archivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `etiquetas`
--

DROP TABLE IF EXISTS `etiquetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `etiquetas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `etiquetas`
--

LOCK TABLES `etiquetas` WRITE;
/*!40000 ALTER TABLE `etiquetas` DISABLE KEYS */;
INSERT INTO `etiquetas` VALUES (4,'Java'),(5,'JavaEE'),(6,'Hibernate'),(7,'HTML'),(8,'SQL'),(9,'Configuración'),(10,'CSS');
/*!40000 ALTER TABLE `etiquetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfiles`
--

DROP TABLE IF EXISTS `perfiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `perfiles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol` enum('REGISTRADO','ADMIN') COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfiles`
--

LOCK TABLES `perfiles` WRITE;
/*!40000 ALTER TABLE `perfiles` DISABLE KEYS */;
INSERT INTO `perfiles` VALUES (3,'REGISTRADO'),(4,'ADMIN');
/*!40000 ALTER TABLE `perfiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicaciones`
--

DROP TABLE IF EXISTS `publicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `detalles` varchar(600) COLLATE utf8_spanish_ci NOT NULL,
  `fechaPublicacion` datetime(6) DEFAULT NULL,
  `titulo` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcuualw35fb3065r7mjiijb898` (`usuario_id`),
  CONSTRAINT `FKcuualw35fb3065r7mjiijb898` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicaciones`
--

LOCK TABLES `publicaciones` WRITE;
/*!40000 ALTER TABLE `publicaciones` DISABLE KEYS */;
INSERT INTO `publicaciones` VALUES (11,'Necesito ayuda con esta petición AJAX, que no responde correctamente','2020-06-13 17:17:09.968000','Ajax responde con error 500',14),(12,'Aquí dejo el archivo de configuración de Spring para crear el data source','2020-06-13 17:21:01.754000','Data Source con Spring',15),(13,'Cómo realizar el factorial de un número con Java','2020-06-13 17:25:39.043000','Factorial de un número',16),(14,'Esta es la base de datos de mi aplicación, ¿debería cambiar algo?','2020-06-13 17:32:12.958000','Script de creación de base de datos',17),(15,'Me salta esta excepción cuando intento enviar el seguro de un edificio','2020-06-13 17:41:09.990000','ClassNotFoundException',18),(16,'Dejo un ejercicio para modificar, añadir y eliminar usuarios de una tabla. Requiere Bootstrap 4 y FontAwesome','2020-06-13 17:58:51.755000','Añadir y modificar usuarios en tabla con JQuery',19),(18,'Esta hoja de estilos puede facilmente utilizarse para las vistas de un CRUD simple','2020-06-13 18:03:24.603000','Hoja de estilos para CRUD',20);
/*!40000 ALTER TABLE `publicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicaciones_archivos`
--

DROP TABLE IF EXISTS `publicaciones_archivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicaciones_archivos` (
  `Publicacion_id` int(11) NOT NULL,
  `archivos_id` int(11) NOT NULL,
  UNIQUE KEY `UK_8mg2vhnxbedywl1b5bkeur5k` (`archivos_id`),
  KEY `FKor7fpi3u4j7dnbm3nth93vifp` (`Publicacion_id`),
  CONSTRAINT `FKexu7e72wht8d4iqj0ji9g3ahb` FOREIGN KEY (`archivos_id`) REFERENCES `archivos` (`id`),
  CONSTRAINT `FKor7fpi3u4j7dnbm3nth93vifp` FOREIGN KEY (`Publicacion_id`) REFERENCES `publicaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicaciones_archivos`
--

LOCK TABLES `publicaciones_archivos` WRITE;
/*!40000 ALTER TABLE `publicaciones_archivos` DISABLE KEYS */;
INSERT INTO `publicaciones_archivos` VALUES (11,10),(11,11),(12,12),(13,13),(14,14),(15,15),(15,16),(16,17),(16,18),(18,19);
/*!40000 ALTER TABLE `publicaciones_archivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicaciones_etiquetas`
--

DROP TABLE IF EXISTS `publicaciones_etiquetas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicaciones_etiquetas` (
  `Publicacion_id` int(11) NOT NULL,
  `etiquetas_id` int(11) NOT NULL,
  KEY `FK5tq28ykoyso1l0mi8wxcabn4t` (`etiquetas_id`),
  KEY `FKpr92tn5ycgn5s8omvyi9v88bl` (`Publicacion_id`),
  CONSTRAINT `FK5tq28ykoyso1l0mi8wxcabn4t` FOREIGN KEY (`etiquetas_id`) REFERENCES `etiquetas` (`id`),
  CONSTRAINT `FKpr92tn5ycgn5s8omvyi9v88bl` FOREIGN KEY (`Publicacion_id`) REFERENCES `publicaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicaciones_etiquetas`
--

LOCK TABLES `publicaciones_etiquetas` WRITE;
/*!40000 ALTER TABLE `publicaciones_etiquetas` DISABLE KEYS */;
INSERT INTO `publicaciones_etiquetas` VALUES (12,9),(13,4),(14,8),(15,4),(16,7),(18,10);
/*!40000 ALTER TABLE `publicaciones_etiquetas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicaciones_respuestas`
--

DROP TABLE IF EXISTS `publicaciones_respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicaciones_respuestas` (
  `Publicacion_id` int(11) NOT NULL,
  `respuestas_id` int(11) NOT NULL,
  UNIQUE KEY `UK_jbxwdogp0vkb5gr6pdl1rm22u` (`respuestas_id`),
  KEY `FKeq3sx62nreycvuqeelurwxx2x` (`Publicacion_id`),
  CONSTRAINT `FKeq3sx62nreycvuqeelurwxx2x` FOREIGN KEY (`Publicacion_id`) REFERENCES `publicaciones` (`id`),
  CONSTRAINT `FKqcgf1rsetbegylgq4ofy90vks` FOREIGN KEY (`respuestas_id`) REFERENCES `respuestas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicaciones_respuestas`
--

LOCK TABLES `publicaciones_respuestas` WRITE;
/*!40000 ALTER TABLE `publicaciones_respuestas` DISABLE KEYS */;
INSERT INTO `publicaciones_respuestas` VALUES (11,1),(15,2);
/*!40000 ALTER TABLE `publicaciones_respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publicaciones_valoraciones`
--

DROP TABLE IF EXISTS `publicaciones_valoraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publicaciones_valoraciones` (
  `Publicacion_id` int(11) NOT NULL,
  `valoraciones_id` int(11) NOT NULL,
  UNIQUE KEY `UK_nntvi51mlotxx7u56e9lc8m5i` (`valoraciones_id`),
  KEY `FKr0ot0gkc3d1uk2i89h4yh6ld3` (`Publicacion_id`),
  CONSTRAINT `FKap6uf415dk56hy4yite4xqexq` FOREIGN KEY (`valoraciones_id`) REFERENCES `valoraciones` (`id`),
  CONSTRAINT `FKr0ot0gkc3d1uk2i89h4yh6ld3` FOREIGN KEY (`Publicacion_id`) REFERENCES `publicaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publicaciones_valoraciones`
--

LOCK TABLES `publicaciones_valoraciones` WRITE;
/*!40000 ALTER TABLE `publicaciones_valoraciones` DISABLE KEYS */;
INSERT INTO `publicaciones_valoraciones` VALUES (11,26),(11,28),(12,29),(13,24),(13,25),(13,27),(16,30),(18,31);
/*!40000 ALTER TABLE `publicaciones_valoraciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuestas`
--

DROP TABLE IF EXISTS `respuestas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuestas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `respuesta` varchar(400) COLLATE utf8_spanish_ci DEFAULT NULL,
  `publicacion_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7yo3yvomj8cco5n47o6x57fno` (`publicacion_id`),
  KEY `FK64htn6ybgsflpvkjwoqt84phd` (`usuario_id`),
  CONSTRAINT `FK64htn6ybgsflpvkjwoqt84phd` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FK7yo3yvomj8cco5n47o6x57fno` FOREIGN KEY (`publicacion_id`) REFERENCES `publicaciones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuestas`
--

LOCK TABLES `respuestas` WRITE;
/*!40000 ALTER TABLE `respuestas` DISABLE KEYS */;
INSERT INTO `respuestas` VALUES (1,'Deberías compartir el archivo .js en lugar de la página JSP',11,15),(2,'Revisa las dependencias en el pom.xml\r\n',15,13);
/*!40000 ALTER TABLE `respuestas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `enabled` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `passwd` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `username` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `idPerfil` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_usuarios_Email` (`email`),
  UNIQUE KEY `UK_usuarios_Username` (`username`),
  KEY `FKds7f4b6hm5kmtfdi95i3xl8it` (`idPerfil`),
  CONSTRAINT `FKds7f4b6hm5kmtfdi95i3xl8it` FOREIGN KEY (`idPerfil`) REFERENCES `perfiles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (13,'avatar.png','admin@admin.es',1,'Administrador','{noop}1234','admin',4),(14,'avatar.png','jpacheco@gmail.com',1,'Jorge Pacheco','{noop}1234','jpacheco',3),(15,'avatar.png','almag00@gmail.es',1,'Alma Guzmán','{noop}1234','almag00',3),(16,'SJV9EX5PtuxGandalf.png','pepep@gmail.com',1,'Pepe Playa','{noop}1234','pepep',3),(17,'avatar.png','rsanchez99@gmail.com',1,'Roberto Sánchez','{noop}1234','rsanchez99',3),(18,'I48FD5TGtuxBorraxo.png','marcosa99@gmail.com',1,'Marcos Arenas','{noop}1234','marcosa99',3),(19,'avatar.png','ramonn@gmail.com',1,'Ramón Nuñez','{noop}1234','ramonn',3),(20,'avatar.png','julianm@gmail.com',1,'Julián Marea','{noop}1234','julianm',3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_publicaciones`
--

DROP TABLE IF EXISTS `usuarios_publicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_publicaciones` (
  `Usuario_id` int(11) NOT NULL,
  `publicaciones_id` int(11) NOT NULL,
  UNIQUE KEY `UK_4b45aj3h5ftkx86lom7iv9hji` (`publicaciones_id`),
  KEY `FKkfuvvai9gk1li97wr7alup4hf` (`Usuario_id`),
  CONSTRAINT `FK6nu9rh1uif8a2xl9itm8wkgfk` FOREIGN KEY (`publicaciones_id`) REFERENCES `publicaciones` (`id`),
  CONSTRAINT `FKkfuvvai9gk1li97wr7alup4hf` FOREIGN KEY (`Usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_publicaciones`
--

LOCK TABLES `usuarios_publicaciones` WRITE;
/*!40000 ALTER TABLE `usuarios_publicaciones` DISABLE KEYS */;
INSERT INTO `usuarios_publicaciones` VALUES (14,11),(15,12),(16,13),(17,14),(18,15),(19,16),(20,18);
/*!40000 ALTER TABLE `usuarios_publicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valoraciones`
--

DROP TABLE IF EXISTS `valoraciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valoraciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valoracion` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmtbedrv2q0wjdsrvnb57g8whw` (`usuario_id`),
  CONSTRAINT `FKmtbedrv2q0wjdsrvnb57g8whw` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valoraciones`
--

LOCK TABLES `valoraciones` WRITE;
/*!40000 ALTER TABLE `valoraciones` DISABLE KEYS */;
INSERT INTO `valoraciones` VALUES (24,'POSITIVA',16),(25,'POSITIVA',15),(26,'NEGATIVA',15),(27,'POSITIVA',17),(28,'NEGATIVA',17),(29,'POSITIVA',17),(30,'POSITIVA',19),(31,'NEGATIVA',20);
/*!40000 ALTER TABLE `valoraciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-13 20:10:55
