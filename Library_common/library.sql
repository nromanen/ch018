-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Дек 16 2013 г., 12:21
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
-- Структура таблицы `bookcase`
--

CREATE TABLE IF NOT EXISTS `bookcase` (
  `bcid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bcid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `bookcase`
--

INSERT INTO `bookcase` (`bcid`, `name`) VALUES
(1, 'Programming'),
(2, 'Electronics'),
(3, 'Math');

-- --------------------------------------------------------

--
-- Структура таблицы `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bcid` int(10) unsigned NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `authors` varchar(45) DEFAULT NULL,
  `year_public` int(10) unsigned DEFAULT NULL,
  `publication` varchar(45) DEFAULT NULL,
  `pages` int(10) unsigned DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `shelf` int(10) unsigned DEFAULT NULL,
  `term` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Books_FKIndex3` (`bcid`),
  KEY `FK_b8a4t8eet7yau6cmbl8yxaa8l` (`bcid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Дамп данных таблицы `books`
--

INSERT INTO `books` (`id`, `bcid`, `title`, `authors`, `year_public`, `publication`, `pages`, `description`, `shelf`, `term`) VALUES
(1, 3, 'asdasd', 'Test A', 2013, 'asdad', 100, 'asfasf', 1, 14);

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
-- Структура таблицы `confirm`
--

CREATE TABLE IF NOT EXISTS `confirm` (
  `Person_id` int(10) unsigned NOT NULL,
  `confirm` tinyint(1) DEFAULT NULL,
  `SMS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Person_id`),
  KEY `Confirm_FKIndex1` (`Person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `Role_id` int(10) unsigned NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `e_mail` varchar(45) DEFAULT NULL,
  `cellphone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Person_FKIndex3` (`Role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `rating`
--

CREATE TABLE IF NOT EXISTS `rating` (
  `Person_id` int(10) unsigned NOT NULL,
  `timelyReturn` int(10) unsigned DEFAULT NULL,
  `untimelyReturn` int(10) unsigned DEFAULT NULL,
  `multibookAllowed` int(10) unsigned DEFAULT NULL,
  `failedOrder` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Person_id`),
  KEY `Rating_FKIndex1` (`Person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleName` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `security`
--

CREATE TABLE IF NOT EXISTS `security` (
  `Person_id` int(10) unsigned NOT NULL,
  `hash` varchar(255) NOT NULL,
  `salt` varchar(20) NOT NULL,
  PRIMARY KEY (`Person_id`),
  KEY `Security_FKIndex1` (`Person_id`)
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
  ADD CONSTRAINT `FK_b8a4t8eet7yau6cmbl8yxaa8l` FOREIGN KEY (`bcid`) REFERENCES `bookcase` (`bcid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
