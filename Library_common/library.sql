-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Дек 16 2013 г., 23:00
-- Версия сервера: 5.6.11
-- Версия PHP: 5.5.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `library`
--
CREATE DATABASE IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `library`;

-- --------------------------------------------------------

--
-- Структура таблицы `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Genre_gid` int(10) unsigned NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `authors` varchar(45) DEFAULT NULL,
  `year_public` int(10) unsigned DEFAULT NULL,
  `publication` varchar(45) DEFAULT NULL,
  `pages` int(10) unsigned DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `term` int(10) unsigned DEFAULT NULL,
  `shelf` int(10) unsigned NOT NULL,
  `bookcase` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Books_FKIndex3` (`Genre_gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `bookscount`
--

CREATE TABLE IF NOT EXISTS `bookscount` (
  `Books_id` int(10) unsigned NOT NULL,
  `count` int(10) unsigned DEFAULT NULL,
  `currentCount` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Books_id`),
  KEY `BooksCount_FKIndex1` (`Books_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `booksinuse`
--

CREATE TABLE IF NOT EXISTS `booksinuse` (
  `Person_id` int(10) unsigned NOT NULL,
  `Books_id` int(10) unsigned NOT NULL,
  `date_issue` datetime NOT NULL,
  `return_date` datetime NOT NULL,
  `term` int(10) unsigned NOT NULL,
  `inUse` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Person_id`,`Books_id`),
  KEY `Person_has_Books_FKIndex1` (`Person_id`),
  KEY `Person_has_Books_FKIndex2` (`Books_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `gid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Books_id` int(10) unsigned NOT NULL,
  `Person_id` int(10) unsigned NOT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_FKIndex1` (`Person_id`),
  KEY `orders_FKIndex2` (`Books_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `e_mail` varchar(45) DEFAULT NULL,
  `cellphone` varchar(20) DEFAULT NULL,
  `confirmed` tinyint(1) DEFAULT '0',
  `SMS` tinyint(1) DEFAULT '0',
  `hash` varchar(35) DEFAULT NULL,
  `salt` varchar(5) DEFAULT NULL,
  `role` enum('ADMINISTRATOR','LIBRARIAN','USER') NOT NULL,
  `timely_returns` int(10) unsigned DEFAULT '0',
  `untimely_returns` int(10) unsigned DEFAULT '0',
  `multibookAllowed` int(10) unsigned DEFAULT '10',
  `failedOrders` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `wishlist`
--

CREATE TABLE IF NOT EXISTS `wishlist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Books_id` int(10) unsigned NOT NULL,
  `person_id` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `WishList_FKIndex1` (`Books_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
