-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Дек 29 2013 г., 11:27
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
  `image` varchar(255) DEFAULT NULL,
  `pages` int(11) DEFAULT NULL,
  `publication` varchar(255) DEFAULT NULL,
  `shelf` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year_public` int(11) DEFAULT NULL,
  `gid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k00r52dx96mgbrvv8i05saupq` (`gid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Дамп данных таблицы `books`
--

INSERT INTO `books` (`id`, `authors`, `bookcase`, `description`, `image`, `pages`, `publication`, `shelf`, `term`, `title`, `year_public`, `gid`) VALUES
(1, 'Gonsalves', 2, 'fgh', NULL, 800, 'Apress', 2, 14, 'Java EE', 2013, 1),
(2, 'New author', 5, 'cool book', NULL, 200, 'Home', 15, 14, 'New book', 2009, 2),
(3, 'Fain Y.', 2, 'Java train', NULL, 470, 'WILEY', 4, 14, ' Java Programming 24-Hour Trainer', 2011, 1),
(4, 'Craig Walls', 2, 'Java spring', NULL, 401, 'Manning', 2, 14, 'Spring in action', 2011, 1),
(5, 'K. Sierra, B. Bates', 2, 'Java', NULL, 890, 'MC Graw Hill', 3, 14, 'SCJP', 2008, 1),
(6, 'M. Deinum & K. Serneels', 2, 'Java spring', NULL, 590, 'Apress', 1, 14, 'Pro Spring MVC: with Web Flow', 2011, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `booksinuse`
--

CREATE TABLE IF NOT EXISTS `booksinuse` (
  `buid` int(11) NOT NULL AUTO_INCREMENT,
  `inUse` tinyint(1) DEFAULT NULL,
  `issue_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `Books_id` int(11) DEFAULT NULL,
  `Person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`buid`),
  KEY `FK_2nkoqh845najnu1vfv20lkp6p` (`Books_id`),
  KEY `FK_rece9y8w4qeg63ksgcrecwn54` (`Person_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `booksinuse`
--

INSERT INTO `booksinuse` (`buid`, `inUse`, `issue_date`, `return_date`, `term`, `Books_id`, `Person_id`) VALUES
(1, 0, '2013-12-26 01:46:12', '2013-12-26 01:46:12', 14, 1, 3),
(2, 0, '2013-12-28 01:46:12', '2013-12-30 01:46:12', 14, 1, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`),
  UNIQUE KEY `UK_ctffrbu4484ft8dlsa5vmqdka` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `genre`
--

INSERT INTO `genre` (`gid`, `name`) VALUES
(2, 'Math'),
(1, 'Programming');

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` datetime DEFAULT NULL,
  `issue_date` datetime DEFAULT NULL,
  `Books_id` int(11) DEFAULT NULL,
  `Person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_iwon2xdheg3wibjdl8rp7tsf3` (`Books_id`),
  KEY `FK_fowd2x4g292tvs9sykevhxbos` (`Person_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`id`, `order_date`, `issue_date`, `Books_id`, `Person_id`) VALUES
(2, '2013-12-24 00:00:00', '2013-12-25 22:42:00', 6, 1),
(15, '2013-12-25 00:00:00', '2013-12-26 13:34:00', 6, 1),
(16, '2013-12-25 00:00:00', '2013-12-26 14:00:00', 6, 2),
(17, '2013-12-25 00:00:00', '2013-12-27 18:00:00', 1, 3),
(18, '2013-12-25 00:00:00', '2013-12-26 16:00:00', 2, 2),
(19, '2013-12-25 00:00:00', '2013-12-28 00:00:00', 4, 1),
(20, '2013-12-25 00:00:00', '2013-12-26 18:06:00', 2, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cellphone` varchar(255) DEFAULT NULL,
  `confirmed` tinyint(1) DEFAULT NULL,
  `e_mail` varchar(255) DEFAULT NULL,
  `failedOrders` int(11) DEFAULT NULL,
  `multibookAllowed` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sms` tinyint(1) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `timely_returns` int(11) DEFAULT NULL,
  `untimely_returns` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jlau8tu7a0dxnfvl91wqxcwae` (`e_mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`id`, `cellphone`, `confirmed`, `e_mail`, `failedOrders`, `multibookAllowed`, `name`, `password`, `role`, `salt`, `sms`, `surname`, `timely_returns`, `untimely_returns`) VALUES
(1, '0992745624', 1, 'yurik.my@gmail.com', 0, 10, 'Yurik', NULL, NULL, NULL, 0, 'Mikhaletsky', 0, 0),
(2, '052000000', 1, 'w@r.ney', 0, 10, 'Wane', NULL, NULL, NULL, 0, 'Rooney', 0, 0),
(3, '052005445', 1, 'j@c.ole', 0, 10, 'Joe', NULL, NULL, NULL, 0, 'Cole', 0, 0);

-- --------------------------------------------------------

--
-- Структура таблицы `wishlist`
--

CREATE TABLE IF NOT EXISTS `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Books_id` int(11) DEFAULT NULL,
  `Person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qc2mmx7r8ebvjkf4x67qv02hf` (`Books_id`),
  KEY `FK_8fo1hianyjf9k9pcdh1jlcxdq` (`Person_id`)
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
  ADD CONSTRAINT `FK_2nkoqh845najnu1vfv20lkp6p` FOREIGN KEY (`Books_id`) REFERENCES `books` (`id`);

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_fowd2x4g292tvs9sykevhxbos` FOREIGN KEY (`Person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FK_iwon2xdheg3wibjdl8rp7tsf3` FOREIGN KEY (`Books_id`) REFERENCES `books` (`id`);

--
-- Ограничения внешнего ключа таблицы `wishlist`
--
ALTER TABLE `wishlist`
  ADD CONSTRAINT `FK_8fo1hianyjf9k9pcdh1jlcxdq` FOREIGN KEY (`Person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FK_qc2mmx7r8ebvjkf4x67qv02hf` FOREIGN KEY (`Books_id`) REFERENCES `books` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
