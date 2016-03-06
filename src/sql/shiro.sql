-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.12-enterprise-commercial-advanced-log - MySQL Enterprise Server - Advanced Edition (Commercial)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 shiro2 的数据库结构
DROP DATABASE IF EXISTS `shiro2`;
CREATE DATABASE IF NOT EXISTS `shiro2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shiro2`;


-- 导出  表 shiro2.sys_organization 结构
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE IF NOT EXISTS `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_organization_parent_id` (`parent_id`),
  KEY `idx_sys_organization_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  shiro2.sys_organization 的数据：~4 rows (大约)
DELETE FROM `sys_organization`;
/*!40000 ALTER TABLE `sys_organization` DISABLE KEYS */;
INSERT INTO `sys_organization` (`id`, `name`, `parent_id`, `parent_ids`, `available`) VALUES
	(1, '总公司', 0, '0/', 1),
	(2, '分公司1', 1, '0/1/', 1),
	(3, '分公司2', 1, '0/1/', 1),
	(4, '分公司11', 2, '0/1/2/', 1);
/*!40000 ALTER TABLE `sys_organization` ENABLE KEYS */;


-- 导出  表 shiro2.sys_resource 结构
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE IF NOT EXISTS `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_sys_resource_parent_id` (`parent_id`),
  KEY `idx_sys_resource_parent_ids` (`parent_ids`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- 正在导出表  shiro2.sys_resource 的数据：~21 rows (大约)
DELETE FROM `sys_resource`;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` (`id`, `name`, `type`, `url`, `parent_id`, `parent_ids`, `permission`, `available`) VALUES
	(1, '资源', 'menu', '', 0, '0/', '', 1),
	(11, '组织机构管理', 'menu', '/organization', 1, '0/1/', 'organization:*', 1),
	(12, '组织机构新增', 'button', '', 11, '0/1/11/', 'organization:create', 1),
	(13, '组织机构修改', 'button', '', 11, '0/1/11/', 'organization:update', 1),
	(14, '组织机构删除', 'button', '', 11, '0/1/11/', 'organization:delete', 1),
	(15, '组织机构查看', 'button', '', 11, '0/1/11/', 'organization:view', 1),
	(21, '用户管理', 'menu', '/user', 1, '0/1/', 'user:*', 1),
	(22, '用户新增', 'button', '', 21, '0/1/21/', 'user:create', 1),
	(23, '用户修改', 'button', '', 21, '0/1/21/', 'user:update', 1),
	(24, '用户删除', 'button', '', 21, '0/1/21/', 'user:delete', 1),
	(25, '用户查看', 'button', '', 21, '0/1/21/', 'user:view', 1),
	(31, '资源管理', 'menu', '/resource', 1, '0/1/', 'resource:*', 1),
	(32, '资源新增', 'button', '', 31, '0/1/31/', 'resource:create', 1),
	(33, '资源修改', 'button', '', 31, '0/1/31/', 'resource:update', 1),
	(34, '资源删除', 'button', '', 31, '0/1/31/', 'resource:delete', 1),
	(35, '资源查看', 'button', '', 31, '0/1/31/', 'resource:view', 1),
	(41, '角色管理', 'menu', '/role', 1, '0/1/', 'role:*', 1),
	(42, '角色新增', 'button', '', 41, '0/1/41/', 'role:create', 1),
	(43, '角色修改', 'button', '', 41, '0/1/41/', 'role:update', 1),
	(44, '角色删除', 'button', '', 41, '0/1/41/', 'role:delete', 1),
	(45, '角色查看', 'button', '', 41, '0/1/41/', 'role:view', 1);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;


-- 导出  表 shiro2.sys_role 结构
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在导出表  shiro2.sys_role 的数据：~4 rows (大约)
DELETE FROM `sys_role`;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `role`, `description`, `available`) VALUES
	(1, 'admin', '超级管理员', 1),
	(5, 'menuAdmin', '菜单维护', 0),
	(6, 'roleAdmin', '角色管理', 0),
	(7, 'userAdmin', '用户管理', 0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;


-- 导出  表 shiro2.sys_role_resource 结构
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE IF NOT EXISTS `sys_role_resource` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  shiro2.sys_role_resource 的数据：~6 rows (大约)
DELETE FROM `sys_role_resource`;
/*!40000 ALTER TABLE `sys_role_resource` DISABLE KEYS */;
INSERT INTO `sys_role_resource` (`role_id`, `resource_id`) VALUES
	(1, 11),
	(1, 21),
	(1, 31),
	(1, 41),
	(5, 31),
	(6, 41),
	(7, 21);
/*!40000 ALTER TABLE `sys_role_resource` ENABLE KEYS */;


-- 导出  表 shiro2.sys_user 结构
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `organization_id` bigint(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_user_username` (`username`),
  KEY `idx_sys_user_organization_id` (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- 正在导出表  shiro2.sys_user 的数据：~3 rows (大约)
DELETE FROM `sys_user`;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `organization_id`, `username`, `password`, `salt`, `locked`) VALUES
	(1, 1, 'admin', '33e65ecad163cbe95657f965731344e5', '0d0fd6cdfc8d5eb5978eb9242826b63f', 0),
	(11, 3, 'wang', '06231adc17c3ab3c0c5887a2c72ae564', '105561aa992c3a982ab62644b30add01', 0),
	(12, 4, 'yang', '4d0e906a955caddfba4ba604ca1e77bf', '005dae7a458014017aeb4499b412a615', 0);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;


-- 导出  表 shiro2.sys_user_role 结构
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `user_id` bigint(21) NOT NULL,
  `role_id` bigint(21) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  shiro2.sys_user_role 的数据：~2 rows (大约)
DELETE FROM `sys_user_role`;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(11, 5),
	(12, 6);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
