/*
Navicat MySQL Data Transfer

Source Server         : admin
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cloud

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-16 21:37:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dir_inf
-- ----------------------------
DROP TABLE IF EXISTS `dir_inf`;
CREATE TABLE `dir_inf` (
  `dir_id` int(11) NOT NULL AUTO_INCREMENT,
  `dir_name` varchar(512) NOT NULL,
  `parent_dir` int(11) DEFAULT NULL,
  `dir_user_id` int(11) NOT NULL,
  `dir_path` varchar(1024) NOT NULL,
  PRIMARY KEY (`dir_id`),
  KEY `dir_user_id` (`dir_user_id`),
  CONSTRAINT `dir_inf_ibfk_1` FOREIGN KEY (`dir_user_id`) REFERENCES `user_inf` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_inf
-- ----------------------------
DROP TABLE IF EXISTS `file_inf`;
CREATE TABLE `file_inf` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(512) NOT NULL,
  `file_size` int(11) NOT NULL,
  `file_type` varchar(128) NOT NULL,
  `file_upload_time` datetime NOT NULL,
  `file_status` int(11) NOT NULL,
  `file_dir_id` int(11) NOT NULL,
  `file_upload_user_id` int(11) NOT NULL,
  PRIMARY KEY (`file_id`),
  KEY `file_upload_user_id` (`file_upload_user_id`),
  KEY `file_dir_id` (`file_dir_id`),
  CONSTRAINT `file_inf_ibfk_2` FOREIGN KEY (`file_upload_user_id`) REFERENCES `user_inf` (`user_id`),
  CONSTRAINT `file_inf_ibfk_3` FOREIGN KEY (`file_dir_id`) REFERENCES `dir_inf` (`dir_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `register_time` datetime NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
