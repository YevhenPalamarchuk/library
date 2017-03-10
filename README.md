# library
Simple project of the library webapp

The application needs Apache Tomcat v9.0 & MySQL DB
------

MySQL create-statements:
------
CREATE DATABASE `proglib` /*!40100 DEFAULT CHARACTER SET utf8 */;

------

CREATE TABLE `authentication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `date_of_reg` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

------

CREATE TABLE `db_books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) DEFAULT NULL,
  `author` varchar(250) DEFAULT NULL,
  `publisher` varchar(250) DEFAULT NULL,
  `year` year(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

------------------------------------------------

Default login & password for DB (library/src/main/resources/config.properties): 
------
login: root;
password: root;
you can change it if it differs from your.
