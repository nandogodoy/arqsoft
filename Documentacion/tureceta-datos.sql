/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : tureceta

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2014-11-27 21:41:53
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
-- Records of busquedaentity
-- ----------------------------
INSERT INTO `busquedaentity` VALUES ('32', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('33', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('34', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('35', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('36', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('37', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('38', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('39', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('40', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('41', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('42', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('43', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('44', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('45', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('46', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('47', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('48', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('49', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('50', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('51', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('52', '2014-11-27', '3');
INSERT INTO `busquedaentity` VALUES ('53', '2014-11-27', '3');

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
-- Records of busquedas_ingredientes
-- ----------------------------
INSERT INTO `busquedas_ingredientes` VALUES ('36', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('37', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('38', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('40', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('41', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('42', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('51', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('52', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('53', '1');
INSERT INTO `busquedas_ingredientes` VALUES ('36', '4');
INSERT INTO `busquedas_ingredientes` VALUES ('51', '4');
INSERT INTO `busquedas_ingredientes` VALUES ('52', '4');
INSERT INTO `busquedas_ingredientes` VALUES ('53', '4');
INSERT INTO `busquedas_ingredientes` VALUES ('47', '5');
INSERT INTO `busquedas_ingredientes` VALUES ('48', '5');
INSERT INTO `busquedas_ingredientes` VALUES ('49', '5');
INSERT INTO `busquedas_ingredientes` VALUES ('43', '8');
INSERT INTO `busquedas_ingredientes` VALUES ('44', '8');
INSERT INTO `busquedas_ingredientes` VALUES ('45', '8');
INSERT INTO `busquedas_ingredientes` VALUES ('46', '8');
INSERT INTO `busquedas_ingredientes` VALUES ('35', '9');
INSERT INTO `busquedas_ingredientes` VALUES ('36', '10');
INSERT INTO `busquedas_ingredientes` VALUES ('40', '10');
INSERT INTO `busquedas_ingredientes` VALUES ('41', '10');
INSERT INTO `busquedas_ingredientes` VALUES ('42', '10');
INSERT INTO `busquedas_ingredientes` VALUES ('51', '10');
INSERT INTO `busquedas_ingredientes` VALUES ('52', '10');
INSERT INTO `busquedas_ingredientes` VALUES ('53', '10');
INSERT INTO `busquedas_ingredientes` VALUES ('33', '12');
INSERT INTO `busquedas_ingredientes` VALUES ('34', '12');
INSERT INTO `busquedas_ingredientes` VALUES ('35', '12');
INSERT INTO `busquedas_ingredientes` VALUES ('32', '16');
INSERT INTO `busquedas_ingredientes` VALUES ('39', '16');
INSERT INTO `busquedas_ingredientes` VALUES ('33', '23');
INSERT INTO `busquedas_ingredientes` VALUES ('34', '23');
INSERT INTO `busquedas_ingredientes` VALUES ('35', '23');
INSERT INTO `busquedas_ingredientes` VALUES ('33', '24');
INSERT INTO `busquedas_ingredientes` VALUES ('34', '24');
INSERT INTO `busquedas_ingredientes` VALUES ('38', '26');
INSERT INTO `busquedas_ingredientes` VALUES ('33', '27');
INSERT INTO `busquedas_ingredientes` VALUES ('34', '27');
INSERT INTO `busquedas_ingredientes` VALUES ('35', '27');
INSERT INTO `busquedas_ingredientes` VALUES ('50', '32');

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
-- Records of ingredienteentity
-- ----------------------------
INSERT INTO `ingredienteentity` VALUES ('0', null);
INSERT INTO `ingredienteentity` VALUES ('5', 'aceite');
INSERT INTO `ingredienteentity` VALUES ('20', 'aceitunas');
INSERT INTO `ingredienteentity` VALUES ('7', 'agua');
INSERT INTO `ingredienteentity` VALUES ('4', 'arroz');
INSERT INTO `ingredienteentity` VALUES ('25', 'arvejas');
INSERT INTO `ingredienteentity` VALUES ('32', 'asado');
INSERT INTO `ingredienteentity` VALUES ('13', 'boniato');
INSERT INTO `ingredienteentity` VALUES ('9', 'carne');
INSERT INTO `ingredienteentity` VALUES ('24', 'carne picada');
INSERT INTO `ingredienteentity` VALUES ('15', 'cebolla');
INSERT INTO `ingredienteentity` VALUES ('35', 'cerveza');
INSERT INTO `ingredienteentity` VALUES ('27', 'chorizo');
INSERT INTO `ingredienteentity` VALUES ('23', 'fideos');
INSERT INTO `ingredienteentity` VALUES ('30', 'hamburguesas');
INSERT INTO `ingredienteentity` VALUES ('3', 'harina');
INSERT INTO `ingredienteentity` VALUES ('8', 'huevo');
INSERT INTO `ingredienteentity` VALUES ('6', 'leche');
INSERT INTO `ingredienteentity` VALUES ('16', 'lechuga');
INSERT INTO `ingredienteentity` VALUES ('17', 'limon');
INSERT INTO `ingredienteentity` VALUES ('29', 'matambre');
INSERT INTO `ingredienteentity` VALUES ('18', 'mayonesa');
INSERT INTO `ingredienteentity` VALUES ('22', 'mostaza');
INSERT INTO `ingredienteentity` VALUES ('36', 'pan');
INSERT INTO `ingredienteentity` VALUES ('21', 'pan de viena');
INSERT INTO `ingredienteentity` VALUES ('19', 'pan rallado');
INSERT INTO `ingredienteentity` VALUES ('31', 'panceta');
INSERT INTO `ingredienteentity` VALUES ('12', 'papa');
INSERT INTO `ingredienteentity` VALUES ('33', 'papas');
INSERT INTO `ingredienteentity` VALUES ('11', 'pescado');
INSERT INTO `ingredienteentity` VALUES ('2', 'pimienta');
INSERT INTO `ingredienteentity` VALUES ('10', 'pollo');
INSERT INTO `ingredienteentity` VALUES ('26', 'pure');
INSERT INTO `ingredienteentity` VALUES ('34', 'queso');
INSERT INTO `ingredienteentity` VALUES ('1', 'sal');
INSERT INTO `ingredienteentity` VALUES ('28', 'salsa de tomate');
INSERT INTO `ingredienteentity` VALUES ('14', 'zanahoria');

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
-- Records of recetaentity
-- ----------------------------
INSERT INTO `recetaentity` VALUES ('0', null, null, null, null, null);
INSERT INTO `recetaentity` VALUES ('16', '2', 'huevos fritos', 'este es el procedimiento de los huevos fritos', '4.5', '1');
INSERT INTO `recetaentity` VALUES ('17', '4', 'Milanesas', 'este es el procedimiento para hacer unas ricas milanesas 2', '5.5', '1');
INSERT INTO `recetaentity` VALUES ('18', '0', 'pastel de carne', 'este es el procedimiento de pastel de carne', '0', '2');
INSERT INTO `recetaentity` VALUES ('19', '0', 'Milanesas de pollo', 'este es el procedimiento para hacer milanesas de pollo', '0', '3');
INSERT INTO `recetaentity` VALUES ('20', '2', 'Arroz con pollo', 'Mezcla todo y dale con fe', '7.5', '3');
INSERT INTO `recetaentity` VALUES ('21', '0', 'panchos al pan', 'precisas un procedimiento para hacer panchos?', '0', '3');
INSERT INTO `recetaentity` VALUES ('22', '2', 'guiso', 'este es el procedimiento para hacer un buen guiso', '3.5', '4');
INSERT INTO `recetaentity` VALUES ('23', '0', 'matambre a la leche', 'poner el matambre al horno con leche por arriba y cuidalo que no se queme!', '0', '4');
INSERT INTO `recetaentity` VALUES ('24', '5', 'asado', 'este es el procedimiento de ...', '8.2', '5');
INSERT INTO `recetaentity` VALUES ('25', '0', 'papas fritas', 'este es el procedimiento para hacer unas ricas fritas', '0', '6');

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
-- Records of recetas_ingredientes
-- ----------------------------
INSERT INTO `recetas_ingredientes` VALUES ('16', '1');
INSERT INTO `recetas_ingredientes` VALUES ('17', '1');
INSERT INTO `recetas_ingredientes` VALUES ('18', '1');
INSERT INTO `recetas_ingredientes` VALUES ('19', '1');
INSERT INTO `recetas_ingredientes` VALUES ('20', '1');
INSERT INTO `recetas_ingredientes` VALUES ('22', '1');
INSERT INTO `recetas_ingredientes` VALUES ('23', '1');
INSERT INTO `recetas_ingredientes` VALUES ('24', '1');
INSERT INTO `recetas_ingredientes` VALUES ('25', '1');
INSERT INTO `recetas_ingredientes` VALUES ('20', '4');
INSERT INTO `recetas_ingredientes` VALUES ('25', '5');
INSERT INTO `recetas_ingredientes` VALUES ('23', '6');
INSERT INTO `recetas_ingredientes` VALUES ('16', '8');
INSERT INTO `recetas_ingredientes` VALUES ('17', '8');
INSERT INTO `recetas_ingredientes` VALUES ('18', '8');
INSERT INTO `recetas_ingredientes` VALUES ('17', '9');
INSERT INTO `recetas_ingredientes` VALUES ('22', '9');
INSERT INTO `recetas_ingredientes` VALUES ('19', '10');
INSERT INTO `recetas_ingredientes` VALUES ('20', '10');
INSERT INTO `recetas_ingredientes` VALUES ('22', '12');
INSERT INTO `recetas_ingredientes` VALUES ('22', '14');
INSERT INTO `recetas_ingredientes` VALUES ('21', '18');
INSERT INTO `recetas_ingredientes` VALUES ('19', '19');
INSERT INTO `recetas_ingredientes` VALUES ('18', '20');
INSERT INTO `recetas_ingredientes` VALUES ('21', '21');
INSERT INTO `recetas_ingredientes` VALUES ('21', '22');
INSERT INTO `recetas_ingredientes` VALUES ('22', '23');
INSERT INTO `recetas_ingredientes` VALUES ('18', '24');
INSERT INTO `recetas_ingredientes` VALUES ('22', '25');
INSERT INTO `recetas_ingredientes` VALUES ('18', '26');
INSERT INTO `recetas_ingredientes` VALUES ('22', '27');
INSERT INTO `recetas_ingredientes` VALUES ('22', '28');
INSERT INTO `recetas_ingredientes` VALUES ('23', '29');
INSERT INTO `recetas_ingredientes` VALUES ('24', '32');
INSERT INTO `recetas_ingredientes` VALUES ('25', '33');
INSERT INTO `recetas_ingredientes` VALUES ('21', '36');

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
-- Records of sequence
-- ----------------------------
INSERT INTO `sequence` VALUES ('SEQ_GEN', '100');

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
-- Records of usuarioentity
-- ----------------------------
INSERT INTO `usuarioentity` VALUES ('1', '6', 'alvaro@ort.edu.uy', '2014-11-27 21:09:57', 'alvaro', '98db6b79acb71383b5a83e0bbc1cadd4', '7d8326d859c10431a8fc3d39f4c6b319', '5.16667');
INSERT INTO `usuarioentity` VALUES ('2', '0', 'beatriz@ort.edu.uy', '2014-11-27 21:12:16', 'beatriz', '323242097368577e6f3aac03c6dcedb6', '37981c637e9bdb5d8ae019de2fccdfe5', '0');
INSERT INTO `usuarioentity` VALUES ('3', '9', 'carlos@ort.edu.uy', '2014-11-27 21:45:09', 'carlos', 'dc599a9972fde3045dab59dbd1ae170b', 'c4083fc309b96356be703f8eca540196', '7');
INSERT INTO `usuarioentity` VALUES ('4', '0', 'diego@ort.edu.uy', '2014-11-27 20:55:44', 'diego', '078c007bd92ddec308ae2f5115c1775d', '06962acb74d7fbbf5903948f18d42915', '0');
INSERT INTO `usuarioentity` VALUES ('5', '0', 'franco@ort.edu.uy', '2014-11-27 20:55:57', 'franco', '6dd1411a66159040b7fff30d0097dbe4', 'b3906f90c34053b0f045ef105ab6aa59', '0');
INSERT INTO `usuarioentity` VALUES ('6', '0', 'gloria@ort.edu.uy', '2014-11-27 20:56:06', 'gloria', '8d281a60d6d637903d4eccd26ddb0104', 'b014b6fb4f027381fd8728abfa652e7f', '0');
INSERT INTO `usuarioentity` VALUES ('7', '0', 'horacio@ort.edu.uy', '2014-11-27 20:56:16', 'horacio', 'b6eb29bd247c236fd94718ad4f5f9eb1', '333d3919ba1a3d57d8e96f2a42c60455', '0');
INSERT INTO `usuarioentity` VALUES ('8', '0', 'irma@ort.edu.uy', '2014-11-27 20:56:29', 'irma', '76af47488ac4ecce7c29005f15cf7d0e', '499b05728849fe7ab2ea1620d64e6f34', '0');
INSERT INTO `usuarioentity` VALUES ('9', '0', 'juan@ort.edu.uy', '2014-11-27 20:56:38', 'juan', 'a94652aa97c7211ba8954dd15a3cf838', '1e2c38f738d791902824e6ca3bc4a6be', '0');
INSERT INTO `usuarioentity` VALUES ('10', '0', 'kenny@ort.edu.uy', '2014-11-27 20:56:47', 'kenny', 'fde290ea8d375a112998beacd5f4cff5', 'aacc8618f9f8a8843f51db08bcaf0170', '0');
INSERT INTO `usuarioentity` VALUES ('11', '0', 'leonardo@ort.edu.uy', '2014-11-27 20:56:57', 'leonardo', '020e60c6a84db8c5d4c2d56a4e4fe082', 'b5df1a8f15275e0adabc63f5c0496429', '0');
INSERT INTO `usuarioentity` VALUES ('12', '0', 'mariana@ort.edu.uy', '2014-11-27 20:57:07', 'mariana', 'e60408e9a55027070e3caf0550d2b4df', 'ae1ba71f0ab20d5c4accf5c863a06fae', '0');
INSERT INTO `usuarioentity` VALUES ('13', '0', 'nicolas@ort.edu.uy', '2014-11-27 20:57:14', 'nicolas', 'deb97a759ee7b8ba42e02dddf2b412fe', 'e2d8fd5f627034d8edfc0b5bcfb9c5d2', '0');
INSERT INTO `usuarioentity` VALUES ('14', '0', 'oscar@ort.edu.uy', '2014-11-27 20:57:24', 'oscar', 'f156e7995d521f30e6c59a3d6c75e1e5', '382345debbe85b62e0870189bc805b9b', '0');
INSERT INTO `usuarioentity` VALUES ('15', '0', 'pablo@ort.edu.uy', '2014-11-27 20:57:34', 'pablo', '7e4b64eb65e34fdfad79e623c44abd94', 'c221584d716ba80501819d616d54cda1', '0');

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

-- ----------------------------
-- Records of usuarioentity_recetas
-- ----------------------------
