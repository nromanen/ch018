-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Дек 17 2013 г., 12:16
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authors` varchar(255) DEFAULT NULL,
  `bookcase` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `publication` varchar(255) DEFAULT NULL,
  `shelf` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year_public` int(11) DEFAULT NULL,
  `gid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k00r52dx96mgbrvv8i05saupq` (`gid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `books`
--

INSERT INTO `books` (`id`, `authors`, `bookcase`, `description`, `pages`, `publication`, `shelf`, `term`, `title`, `year_public`, `gid`) VALUES
(1, '0000', 1, 'asas', 5, 'asda', 1, 14, 'asssss', 1999, 3);

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
  `buid` int(11) NOT NULL,
  `issue_date` datetime DEFAULT NULL,
  `id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Person_id`,`Books_id`),
  KEY `Person_has_Books_FKIndex1` (`Person_id`),
  KEY `Person_has_Books_FKIndex2` (`Books_id`),
  KEY `FK_4w24l2ceif0klp5lk73bc3qjc` (`id`),
  KEY `FK_rece9y8w4qeg63ksgcrecwn54` (`Person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `genre`
--

INSERT INTO `genre` (`gid`, `name`) VALUES
(3, 'G1');

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
-- Структура таблицы `ratings`
--

CREATE TABLE IF NOT EXISTS `ratings` (
  `personid` int(11) NOT NULL,
  `booksallowed` int(11) DEFAULT NULL,
  `failedorders` int(11) DEFAULT NULL,
  `generalratio` float DEFAULT NULL,
  `timelyreturn` int(11) DEFAULT NULL,
  `untimelyreturn` int(11) DEFAULT NULL,
  PRIMARY KEY (`personid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `books`
--
ALTER TABLE `books`
  ADD CONSTRAINT `FK_k00r52dx96mgbrvv8i05saupq` FOREIGN KEY (`gid`) REFERENCES `genre` (`gid`);

--
-- Ограничения внешнего ключа таблицы `booksinuse`
--
ALTER TABLE `booksinuse`
  ADD CONSTRAINT `FK_rece9y8w4qeg63ksgcrecwn54` FOREIGN KEY (`Person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FK_4w24l2ceif0klp5lk73bc3qjc` FOREIGN KEY (`id`) REFERENCES `books` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
