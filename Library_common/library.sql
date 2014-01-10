-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Янв 10 2014 г., 23:56
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
  `image` varchar(255) DEFAULT 'http://placehold.it/120x150',
  `pages` int(11) DEFAULT NULL,
  `publication` varchar(255) DEFAULT NULL,
  `shelf` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year_public` int(11) DEFAULT NULL,
  `gid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k00r52dx96mgbrvv8i05saupq` (`gid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Дамп данных таблицы `books`
--

INSERT INTO `books` (`id`, `authors`, `bookcase`, `description`, `image`, `pages`, `publication`, `shelf`, `term`, `title`, `year_public`, `gid`) VALUES
(1, 'Gonsalves', 1, 'asdd', 'http://placehold.it/120x150', 803, 'Apress', 1, 14, 'Java EE', 2013, 1),
(3, 'Fain Y.', 2, 'Java train', 'http://placehold.it/120x150', 470, 'WILEY', 4, 14, ' Java Programming 24-Hour Trainer', 2011, 1),
(4, 'Craig Walls', 2, 'Java spring', 'http://placehold.it/120x150', 401, 'Manning', 2, 14, 'Spring in action', 2011, 1),
(5, 'K. Sierra, B. Bates', 2, 'Java', 'http://placehold.it/120x150', 890, 'MC Graw Hill', 3, 14, 'SCJP', 2008, 1),
(6, 'M. Deinum & K. Serneels', 1, 'Java spring', 'http://placehold.it/120x150', 590, 'Apress', 0, 14, 'Pro Spring MVC: with Web Flow', 2011, 1),
(8, 'New author', 1, 'asd', 'http://placehold.it/120x150', 154, 'Home', 1, 14, 'New book', 2012, 2),
(9, 'New author', 0, '', 'http://placehold.it/120x150', 0, '', 0, 14, 'Java EE', 0, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `booksinuse`
--

INSERT INTO `booksinuse` (`buid`, `inUse`, `issue_date`, `return_date`, `term`, `Books_id`, `Person_id`) VALUES
(3, 0, '2014-01-10 14:18:08', '2014-01-24 14:18:08', 14, 1, 6);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=44 ;

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
  `rating` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jlau8tu7a0dxnfvl91wqxcwae` (`e_mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`id`, `cellphone`, `confirmed`, `e_mail`, `failedOrders`, `multibookAllowed`, `name`, `password`, `role`, `salt`, `sms`, `surname`, `timely_returns`, `untimely_returns`, `rating`) VALUES
(6, '0992745624', 1, 'yurik.my@gmail.com', 0, 20, 'Yurik', '$2a$10$sP1DUNkzqrtwmfNtox4GiuOyhwWF9EgsNzU1Gdk6I64N7SEYSCVpO', 'ROLE_LIBRARIAN', NULL, 1, 'Mikhaletsky', 12, 0, 13),
(7, '', 1, 'user@test.com', 0, 10, '', '$2a$10$5/ZU51vZ3Q1tNFaGvhtlMukhJoL0GBv3E4HxTn83Kl2vBFPA9Ejf.', 'ROLE_USER', NULL, 0, '', 13, 2, 4),
(8, '', 1, 'test@person.com', 0, 10, 'Fddd', '$2a$10$omA0M/VbeCjh05YM9v2bvuuWBBhTLnzh5ziirjt9t2qXZ3nmtvqw6', 'ROLE_USER', NULL, 0, '', 2, 0, 1),
(9, NULL, 0, 'yurik@ygmail.com', 0, 10, NULL, '$2a$10$Nf9ypd6X2DxdC8GXF3KMi.v0nbLf0dfTFc/O4fTaoTlO04fsK/u9C', 'ROLE_USER', NULL, 0, NULL, 0, 0, 1),
(10, NULL, 0, 'yurik@gmail.com', 0, 10, NULL, '$2a$10$k54/IW.fl/YbYEgJyrpdo.D5LwGZ.1CK2gjL1EWMVMcr0e.qVVw6i', 'ROLE_USER', NULL, 0, NULL, 0, 0, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Дамп данных таблицы `wishlist`
--

INSERT INTO `wishlist` (`id`, `Books_id`, `Person_id`) VALUES
(1, 3, NULL),
(2, 5, NULL),
(3, 4, NULL),
(6, 3, 8),
(7, 1, 8),
(10, 1, 6),
(11, 3, 6);

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
  ADD CONSTRAINT `FK_2nkoqh845najnu1vfv20lkp6p` FOREIGN KEY (`Books_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `FK_rece9y8w4qeg63ksgcrecwn54` FOREIGN KEY (`Person_id`) REFERENCES `person` (`id`);

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
