-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Янв 25 2014 г., 23:52
-- Версия сервера: 5.6.14
-- Версия PHP: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `library`
--

-- --------------------------------------------------------

--
-- Структура таблицы `books`
--

CREATE TABLE IF NOT EXISTS `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authors` varchar(200) NOT NULL,
  `available` int(11) DEFAULT '0',
  `bookcase` int(11) DEFAULT '0',
  `count` int(11) DEFAULT '0',
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT 'http://placehold.it/120x150',
  `pages` int(11) DEFAULT '0',
  `publication` varchar(50) NOT NULL,
  `rating` float DEFAULT '0',
  `shelf` int(11) DEFAULT '0',
  `term` int(11) DEFAULT '14',
  `title` varchar(200) NOT NULL,
  `year_public` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

--
-- Дамп данных таблицы `books`
--

INSERT INTO `books` (`id`, `authors`, `available`, `bookcase`, `count`, `description`, `image`, `pages`, `publication`, `rating`, `shelf`, `term`, `title`, `year_public`) VALUES
(2, 'Antonio Goncalves', 5, 1, 5, 'Beginning Java EE 7 is one of the first tutorials written with definitive expertise on the Java EE 7 platform.', 'https://www.apress.com/media/catalog/product/cache/9/small_image/9df78eab33525d08d6e5fb8d27136e95/A/9/A9781430246268-small_4.png', 608, 'Apress', 0, 1, 14, 'Beginning Java EE 7', 2013),
(3, 'Fain Y.', 5, 2, 5, 'As one of the most popular software languages for building Web applications, Java is often the first programming language developers learn.', 'http://i31.fastpic.ru/big/2011/1224/c0/ad98f9c71227aa37a7e2fb2c6d4539c0.jpg', 470, 'WILEY', 0, 4, 14, ' Java Programming 24-Hour Trainer', 2011),
(4, 'Craig Walls', 5, 2, 5, 'Spring in Action, Third Edition, is for all Java developers', 'http://i26.fastpic.ru/big/2011/0712/42/5d3223e783805d76392181b12c60e642.jpg', 401, 'Manning', 0, 2, 14, 'Spring in action', 2011),
(5, 'K. Sierra, B. Bates', 5, 2, 5, 'With hundreds of practice questions and hands-on exercises, SCJP Sun Certified Programmer for Java 6 Study Guide covers what you need to know--and shows you how to prepare--for this challenging exam.', 'https://d3hgnfpzeohxco.cloudfront.net/images/bau/97800715/9780071591065/0/0/plain/scjp-sun-certified-programmer-for-java-6-study-guide-exam-310-065.jpg', 890, 'MC Graw Hill', 0, 3, 14, 'SCJP', 2008),
(6, 'M. Deinum & K. Serneels', 5, 1, 5, 'Pro Spring MVC is an in-depth guide to Spring MVC, a modern web framework build on top of the Spring Framework.', 'http://www.apress.com/media/catalog/product/cache/9/image/9df78eab33525d08d6e5fb8d27136e95/A/9/A9781430241553-3d_5.png', 590, 'Apress', 0, 1, 14, 'Pro Spring MVC: with Web Flow', 2011),
(14, ' Joanna Martine Woolfolk', 5, 10, 5, 'Everyone''s favorite astrology book, having sold over 500,000 copies, is now even easier to use with an interactive CD-ROM', 'http://d23a3s5l1qjyz.cloudfront.net/wp-content/uploads/2011/05/astrology-book.jpg', 461, 'Taylor Trade Publishing', 0, 1, 14, 'The Only Astrology Book You''ll Ever Need', 2006),
(15, 'Roger L Tokheim', 2, 6, 2, 'Designed to be used as an introductory text for students new to the electronics field', 'http://i.ebayimg.com/t/Digital-Electronics-Roger-L-Tokheim-1993-Hardcover/00/$T2eC16V,!yEE9s5jFKheBRbsjOE%2BUw~~_35.JPG', 392, 'World', 0, 1, 14, 'Digital Electronics', 1988),
(16, 'J.R.R. Tolkien', 5, 12, 5, 'In a hole in the ground there lived a hobbit...', 'http://d202m5krfqbpi5.cloudfront.net/books/1372847500l/5907.jpg', 365, 'Houghton Mifflin', 0, 1, 14, 'The Hobbit (Middle-Earth Universe)', 2002),
(17, 'Jared Diamond', 8, 14, 8, 'Life isn''t fair--here''s why: Since 1500, Europeans have, for better & worse, called the tune that the world has danced to.', 'http://d202m5krfqbpi5.cloudfront.net/books/1363934734l/1842.jpg', 494, 'W.W. Norton & Company', 0, 2, 14, 'Guns, Germs, and Steel: The Fates of Human Societies', 2005),
(18, 'Clifford A. Pickover', 3, 15, 3, 'Math’s infinite mysteries and beauty unfold in this follow-up to the best-selling The Science Book', 'http://ecx.images-amazon.com/images/I/41PsllMMlCL._SX258_PJlook-inside-v2,TopRight,1,0_SH20_BO1,204,203,200_.jpg', 985, 'The Science Book', 0, 1, 14, 'The Math Book', 2008),
(19, 'Friedrich Nietzsche, Walter Kaufmann', 5, 20, 5, 'Thus Spoke Zarathustra: A Book for All and None Thus is a philosophical novel by German philosopher Friedrich Nietzsche', 'http://d202m5krfqbpi5.cloudfront.net/books/1349449118l/51893.jpg', 327, 'Penguin Books', 0, 2, 14, 'Thus Spoke Zarathustra', 1978),
(20, 'Jon Ronson', 6, 11, 6, 'The Psychopath Test is a fascinating journey through the minds of madness.', 'http://d202m5krfqbpi5.cloudfront.net/books/1307825196l/9378733.jpg', 288, 'Riverhead Hardcover', 0, 2, 14, 'The Psychopath Test: A Journey Through the Madness Industry', 2011);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `book_genre`
--

CREATE TABLE IF NOT EXISTS `book_genre` (
  `book_id` int(11) NOT NULL,
  `genre_id` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`genre_id`),
  KEY `FK_21qfbh5aqd40pc3ifun9qyia5` (`genre_id`),
  KEY `FK_rqso634ufpdpsixx3fddgqfq4` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `book_genre`
--

INSERT INTO `book_genre` (`book_id`, `genre_id`) VALUES
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1),
(20, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `genre`
--

CREATE TABLE IF NOT EXISTS `genre` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gid`),
  UNIQUE KEY `UK_ctffrbu4484ft8dlsa5vmqdka` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Дамп данных таблицы `genre`
--

INSERT INTO `genre` (`gid`, `language`, `name`) VALUES
(1, 'EN', 'None genred'),
(2, 'EN', 'Programming'),
(3, 'EN', 'Math'),
(4, 'EN', 'Electronics'),
(5, 'EN', 'History'),
(6, 'EN', 'Astrology'),
(7, 'EN', 'Philosophy'),
(8, 'EN', 'Encyclopedia'),
(9, 'EN', 'Psychology'),
(10, 'EN', 'Fantasy');

-- --------------------------------------------------------

--
-- Структура таблицы `localization`
--

CREATE TABLE IF NOT EXISTS `localization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Структура таблицы `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cellphone` varchar(255) DEFAULT NULL,
  `confirmed` tinyint(1) DEFAULT NULL,
  `e_mail` varchar(255) NOT NULL,
  `emailConfirmed` tinyint(1) DEFAULT NULL,
  `failedOrders` int(11) DEFAULT NULL,
  `multibookAllowed` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `rating` double DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `sms` tinyint(1) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `timely_returns` int(11) DEFAULT NULL,
  `untimely_returns` int(11) DEFAULT NULL,
  `verificationkey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jlau8tu7a0dxnfvl91wqxcwae` (`e_mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Дамп данных таблицы `person`
--

INSERT INTO `person` (`id`, `cellphone`, `confirmed`, `e_mail`, `emailConfirmed`, `failedOrders`, `multibookAllowed`, `name`, `password`, `rating`, `role`, `sms`, `surname`, `timely_returns`, `untimely_returns`, `verificationkey`) VALUES
(1, '(099) 274-5624', 1, 'yurik.my@gmail.com', 1, 0, 9, 'Yurik', '$2a$10$Gp.aP6XfF5mZRyzrL5FNTuiw2wyoTOjZFsrLPl9Tg8/qaICgK7qRK', 0.95, 'ROLE_LIBRARIAN', 1, 'Mikhaletskiy', 19, 1, '168be49084d0181543480732768535f521da7af3'),
(3, '(097) 108-4550', 1, 'yurik_my@mail.ru', 1, 0, 10, 'Yurik', '$2a$10$yS2y6mjHpHf5/TaHPDcYYuPN6Bv6vjdIt2Bmx.R7rpuE1oDaQBAme', 1, 'ROLE_USER', 0, 'My', 7, 0, '310dcc42a9e9662dac0282e77251a8e18986c947'),
(4, '(050) 555-5555', 1, 'yurik.my@yandex.ru', 1, 0, 8, 'John', '$2a$10$Fzcrwk4TCHf9JqCQRDGu7.jfpMbwzB0SnDFkC95i1u9BT0NnpA1Ym', 0.818, 'ROLE_USER', 0, 'Terry', 9, 2, 'b1d2661a53328efcd97ae4acf1fa5dbb153e97c1'),
(5, '(050) 222-2222', 1, 'test@test.tst', 1, 0, 10, 'Joe', '$2a$10$F1V1TRrhiDFeFnqaSj822OHKA5lm41f7u21ffIAUEcD1vQ44xSz8m', 1, 'ROLE_USER', 0, 'Cole', 1, 0, 'cf926b2cb8bb29d2b189a5d482016aabfa8742af'),
(9, '(099) 234-3433', 0, 'yuris@gsssil.com', 0, 0, 10, '', '$2a$10$Qk3nsfrO5KkB4Mra/SL6NO9OOMQqDI.LC7mpYHsDbQ8jyfucvjZnK', 1, 'ROLE_USER', 0, '', 0, 0, '8b45d1691729e1a6c472537dc817da75d9cc41b3'),
(13, '(099) 997-9797', 0, 'yuggy@gmfgsdil.cod', 0, 0, 10, 'Yurik', '$2a$10$rUdVYiOZSTTsuJjfoaBt1.9imZC8Ko/l9fQir46eb69G.FSjOQaK.', 1, 'ROLE_USER', 0, 'My', 0, 0, '5df63bb4d53d4dc5f088adc1300ad06b4b971ca5');

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
-- Ограничения внешнего ключа таблицы `booksinuse`
--
ALTER TABLE `booksinuse`
  ADD CONSTRAINT `FK_rece9y8w4qeg63ksgcrecwn54` FOREIGN KEY (`Person_id`) REFERENCES `person` (`id`),
  ADD CONSTRAINT `FK_2nkoqh845najnu1vfv20lkp6p` FOREIGN KEY (`Books_id`) REFERENCES `books` (`id`);

--
-- Ограничения внешнего ключа таблицы `book_genre`
--
ALTER TABLE `book_genre`
  ADD CONSTRAINT `FK_rqso634ufpdpsixx3fddgqfq4` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `FK_21qfbh5aqd40pc3ifun9qyia5` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`gid`);

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
