--配置hosts
127.0.0.1 slf.test.center

--安装redis


--安装mysql数据库.test库


CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `test`;


CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `age` bigint(20) NOT NULL,
  `status` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;




--启动单台应用端口为10009
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=2999,suspend=n -jar springBootMybatis-0.0.1.jar

--访问
http://slf.test.center:10009/springBootMybatis/addOrder
http://slf.test.center:10009/springBootMybatis/noTxOrder
http://slf.test.center:10009/springBootMybatis/updateOrder





