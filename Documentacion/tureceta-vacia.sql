/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : tureceta

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2014-11-27 20:49:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for busquedaentity
-- ----------------------------
DROP TABLE IF EXISTS `busquedaentity`;
CREATE TABLE `busquedaentity` (
  `ID` bigint(20) NOT NULL,
  `fecha` date DEFAULT NULL,
  `USUARIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_BUSQUEDAENTITY_USUARIO_ID` (`USUARIO_ID`),
  CONSTRAINT `FK_BUSQUEDAENTITY_USUARIO_ID` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuarioentity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for busquedas_ingredientes
-- ----------------------------
DROP TABLE IF EXISTS `busquedas_ingredientes`;
CREATE TABLE `busquedas_ingredientes` (
  `busqueda_id` bigint(20) NOT NULL,
  `ingrediente_id` bigint(20) NOT NULL,
  PRIMARY KEY (`busqueda_id`,`ingrediente_id`),
  KEY `FK_busquedas_ingredientes_ingrediente_id` (`ingrediente_id`),
  CONSTRAINT `FK_busquedas_ingredientes_ingrediente_id` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingredienteentity` (`ID`),
  CONSTRAINT `FK_busquedas_ingredientes_busqueda_id` FOREIGN KEY (`busqueda_id`) REFERENCES `busquedaentity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for ingredienteentity
-- ----------------------------
DROP TABLE IF EXISTS `ingredienteentity`;
CREATE TABLE `ingredienteentity` (
  `ID` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for recetaentity
-- ----------------------------
DROP TABLE IF EXISTS `recetaentity`;
CREATE TABLE `recetaentity` (
  `ID` bigint(20) NOT NULL,
  `cantvaloraciones` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `procedimiento` text,
  `valoracion` float DEFAULT NULL,
  `USUARIO_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `FK_RECETAENTITY_USUARIO_ID` (`USUARIO_ID`),
  CONSTRAINT `FK_RECETAENTITY_USUARIO_ID` FOREIGN KEY (`USUARIO_ID`) REFERENCES `usuarioentity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for recetas_ingredientes
-- ----------------------------
DROP TABLE IF EXISTS `recetas_ingredientes`;
CREATE TABLE `recetas_ingredientes` (
  `receta_id` bigint(20) NOT NULL,
  `ingrediente_id` bigint(20) NOT NULL,
  PRIMARY KEY (`receta_id`,`ingrediente_id`),
  KEY `FK_recetas_ingredientes_ingrediente_id` (`ingrediente_id`),
  CONSTRAINT `FK_recetas_ingredientes_receta_id` FOREIGN KEY (`receta_id`) REFERENCES `recetaentity` (`ID`),
  CONSTRAINT `FK_recetas_ingredientes_ingrediente_id` FOREIGN KEY (`ingrediente_id`) REFERENCES `ingredienteentity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for sequence
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;
CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for usuarioentity
-- ----------------------------
DROP TABLE IF EXISTS `usuarioentity`;
CREATE TABLE `usuarioentity` (
  `ID` bigint(20) NOT NULL,
  `cantvaloraciones` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expira` datetime DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `valoracion` float DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for usuarioentity_recetas
-- ----------------------------
DROP TABLE IF EXISTS `usuarioentity_recetas`;
CREATE TABLE `usuarioentity_recetas` (
  `UsuarioEntity_ID` bigint(20) DEFAULT NULL,
  `RECETAS` varchar(255) DEFAULT NULL,
  KEY `FK_UsuarioEntity_RECETAS_UsuarioEntity_ID` (`UsuarioEntity_ID`),
  CONSTRAINT `FK_UsuarioEntity_RECETAS_UsuarioEntity_ID` FOREIGN KEY (`UsuarioEntity_ID`) REFERENCES `usuarioentity` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
