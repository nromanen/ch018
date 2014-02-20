-- MySQL dump 10.13  Distrib 5.6.11, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.6.11-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authors` varchar(200) NOT NULL,
  `available` int(11) DEFAULT '0',
  `bookcase` int(11) DEFAULT '0',
  `count` int(11) DEFAULT '0',
  `description` longtext,
  `image` varchar(255) DEFAULT '/resources/img/books/default.gif',
  `numberOfEvaluations` int(11) DEFAULT '0',
  `pages` int(11) DEFAULT '0',
  `publication` varchar(50) NOT NULL,
  `rating` float DEFAULT '0',
  `shelf` int(11) DEFAULT '0',
  `term` int(11) DEFAULT '14',
  `title` varchar(200) NOT NULL,
  `year_public` int(11) DEFAULT '0',
  `genre_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lwwyu1h28alv6nnnb31qnfq` (`genre_id`),
  CONSTRAINT `FK_lwwyu1h28alv6nnnb31qnfq` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Antonio Goncalves',4,1,5,'<p> Java Enterprise Edition (Java EE) continues to be one of the leading Java technologies and platforms. <em>Beginning Java EE 7</em> is the first tutorial book on Java EE 7. <br> <br> Step by step and easy to follow, this book describes many of the Java EE 7 specifications and reference implementations, and shows them in action using practical examples. This definitive book also uses the newest version of GlassFish to deploy and administer the code examples. <br> <br> Written by an expert member of the Java EE specification request and review board in the Java Community Process (JCP), this book contains the best information possible, from an expert’s perspective on enterprise Java technologies. </p> <h3>What you’ll learn</h3> <ul> <li>Get started with the latest version of the Java EE Platform. </li> <li>Explore and use the EJB and JPA APIs from entities to session beans to message driven beans, and more. </li> <li>Discover web tier development APIs including JSF, Facelets and Expression Language. </li> <li>Uncover SOAP web services, RESTful web services, and more available in this latest Java EE. </li> <li>Create dynamic user interfaces for your enterprise and transactional Java applications. </li> </ul> <h3>Who this book is for</h3> <p>This book is for Java or Spring programmers with some experience and those new to Java EE platform. Architects will also find information about how to layer their Java EE applications. </p>','/resources/img/books/javaee7.png',5,608,'Apress',4.94,1,14,'Beginning Java EE 7',2013,2),(3,'Fain Y.',4,2,5,'<p> As one of the most popular software languages for building Web applications, Java is often the first programming language developers learn. The latest version includes numerous updates that both novice and experienced developers need to know. With this invaluable book-and-video package, Java authority Yakov Fain fully covers Java s new features as well as its language extensions, classes and class methods, and the Swing Application Framework. For each lesson that he discusses in the book, there is an accompanying instructional video to reinforce your learning experience.</p> <p> Lessons include:</p> <ul> <li>Introducing Java </li> <li>Eclipse IDE </li> <li>Object-Oriented Programming </li> <li>Class Methods</li> <li>Back to Java Basics </li> <li>Packages, Interfaces, and Encapsulation </li> <li>Programming with Abstract Classes and Interfaces</li> <li>Introducing the Graphic User Interface</li> <li>Event Handling in UI</li> <li>Introduction to Java Applets </li> <li>Developing a Tic-Tac-Toe Applet </li> <li>Developing a Ping-Pong Game </li> <li>Error Handling </li> <li>Introduction to Collections </li> <li>Introduction to Generics </li> <li>Working with Streams </li> <li>Java Serialization </li> <li>Network Programming</li> <li>Processing E-Mails with Java </li> <li>Introduction to Multi-Threading </li> <li>Digging Deeper into Concurrent Execution </li> <li>Working with Databases Using JDBC </li> <li>Swing with JTable </li> <li>Annotations and Reflection </li> <li>Remote Method Invocation </li> <li>Java EE 6 Overview </li> <li>Programming with Servlets </li> <li>JavaServer Pages </li> <li>Developing Web Applications with JSF </li> <li>Introducing JMS and MOM </li> <li>Introducing JNDI</li> <li>Introduction to Enterprise JavaBeans</li> <li>Introduction to the Java Persistence API</li> <li>Working with RESTful Web Services</li> <li>Introduction to Spring MVC Framework </li> <li>Introduction to Hibernate Framework </li> <li>Bringing JavaFX to the Mix </li> <li>Java Technical Interviews </li> </ul> <p> <b>Note: As part of the print version of this title, video lessons are included on DVD. For e-book versions, video lessons can be accessed at wrox.com using a link provided in the interior of the e-book.</b> </p>','/resources/img/books/java24.jpg',7,470,'WILEY',4.85714,4,14,'Java Programming 24-Hour Trainer',2011,2),(4,'Craig Walls',4,2,5,'<p> <em>Spring In Action - Covers Spring 3.0 </em> written by Craig Walls is an applied guide for the popular Spring Framework. Learning Spring is a requirement for all Java designers and Spring 3.0 lays a sound foundation, covering innovative features like SpEL (Spring Expression Language) and new interpretations of the IoC container. </p> <p> Like the previous editions, Spring 3.0 persists with its convenient, handy approach to highlight and zero in on the most significant facets of Spring like REST, Security, Webflow, and remote services. Walls uses a simple but efficient method to explain the building of any kind of J2EE application. <br> Walls uses easy language, explains using diminutive code snippets, and vibrant examples. </p>','/resources/img/books/springina.jpg',6,401,'Manning',5,2,14,'Spring in action',2011,2),(5,'K. Sierra, B. Bates',4,2,5,'With hundreds of practice questions and hands-on exercises, SCJP Sun Certified Programmer for Java 6 Study Guide covers what you need to know--and shows you how to prepare--for this challenging exam.','/resources/img/books/scjp.jpg',3,890,'MC Graw Hill',4.66667,3,14,'SCJP',2008,2),(6,'M. Deinum & K. Serneels',5,1,5,'Pro Spring MVC is an in-depth guide to Spring MVC, a modern web framework build on top of the Spring Framework.','/resources/img/books/webflow.png',3,590,'Apress',4.33333,1,14,'Pro Spring MVC: with Web Flow',2011,2),(14,'Joanna Martine Woolfolk',5,10,5,'Everyone\'s favorite astrology book, having sold over 500,000 copies, is now even easier to use with an interactive CD-ROM','/resources/img/books/astro.jpg',1,461,'Taylor Trade Publishing',4,1,14,'The Only Astrology Book You\'ll Ever Need',2006,6),(15,'Roger L Tokheim',1,6,2,'Designed to be used as an introductory text for students new to the electronics field','/resources/img/books/digital.JPG',1,392,'World',5,1,14,'Digital Electronics',1988,4),(16,'J.R.R. Tolkien',4,12,5,'In a hole in the ground there lived a hobbit...','/resources/img/books/hobbit.jpg',2,365,'Houghton Mifflin',4.85,1,14,'The Hobbit (Middle-Earth Universe)',2002,10),(17,'Jared Diamond',8,14,8,'Life isn\'t fair--here\'s why: Since 1500, Europeans have, for better & worse, called the tune that the world has danced to.','/resources/img/books/guns.jpg',0,494,'W.W. Norton & Company',0,2,14,'Guns, Germs, and Steel: The Fates of Human Societies',2005,5),(18,'Clifford A. Pickover',3,15,3,'Math’s infinite mysteries and beauty unfold in this follow-up to the best-selling The Science Book','/resources/img/books/math.jpg',0,985,'The Science Book',0,1,14,'The Math Book',2008,3),(19,'Friedrich Nietzsche, Walter Kaufmann',5,20,5,'Thus Spoke Zarathustra: A Book for All and None Thus is a philosophical novel by German philosopher Friedrich Nietzsche','/resources/img/books/nitzshe.jpg',0,327,'Penguin Books',0,2,14,'Thus Spoke Zarathustra',1978,7),(20,'Jon Ronson',6,11,6,'The Psychopath Test is a fascinating journey through the minds of madness.','/resources/img/books/psycho.jpg',0,288,'Riverhead Hardcover',0,2,14,'The Psychopath Test: A Journey Through the Madness Industry',2011,9),(22,'Alex Libby, Dan Wellman',5,1,5,'jQuery UI, the official UI widget library for jQuery, gives you a solid platform on which to build rich and engaging interfaces quickly, with maximum compatibility, stability, and effort. jQuery UI’s ready-made widgets help to reduce the amount of code that you need to write to take a project from conception to completion.  jQuery UI 1.10: The User Interface Library for jQuery has been specially revised for Version 1.10 of jQuery UI. It is written to maximize your experience with the library by breaking down each component and walking you through examples that progressively build up your knowledge, taking you from beginner to advanced user in a series of easy-to-follow steps.  Throughout the book, you\'ll learn how to create a basic implementation of each component, then customize and configure the components to tailor them to your application.  Each chapter will also show you the custom events fired by the components covered and how these events can be intercepted and acted upon to bring out the best of the library.  We will then go on to cover the use of visually engaging, highly configurable user interface widgets. At the end of this book, we\'ll look at the functioning of all of the UI effects available in the jQuery UI library.  Approach  This book consists of an easy-to-follow, example-based approach that leads you step-by-step through the implementation and customization of each library component.  Who this book is for  This book is for frontend designers and developers who need to learn how to use jQuery UI quickly. To get the most out of this book, you should have a good working knowledge of HTML, CSS, and JavaScript, and should ideally be comfortable using jQuery.','/resources/img/books/lrg.jpg',0,502,'O\'Rilley',0,1,14,'jQuery UI 1.10: The User Interface Library for jQuery',2013,2),(23,'Jesse Liberty',3,2,4,'<p> The programming language C# was built with the future of application development in mind. Pursuing that vision, C#\'s designers succeeded in creating a safe, simple, component-based, high-performance language that works effectively with Microsoft\'s .NET Framework. Now the favored language among those programming for the Microsoft platform, C# continues to grow in popularity as more developers discover its strength and flexibility. And, from the start, C# developers have relied on Programming C# both as an introduction to the language and a means of further building their skills. <br> <br> The fourth edition of <i>Programming C#</i> --the top-selling C# book on the market--has been updated to the C# ISO standard as well as changes to Microsoft\'s implementation of the language. It also provides notes and warnings on C# 1.1 and C# 2.0. <br> <br> Aimed at experienced programmers and web developers, <i>Programming C#</i> , 4th Edition, doesn\'t waste too much time on the basics. Rather, it focuses on the features and programming patterns unique to the C# language. New C# 2005 features covered in-depth include: </p> <ul> <li>Visual Studio 2005 </li> <li>Generics </li> <li>Collection interfaces and iterators </li> <li>Anonymous methods </li> <li>New ADO.NET data controls </li> <li>Fundamentals of Object-Oriented Programming</li> </ul> Author Jesse Liberty, an acclaimed web programming expert and entrepreneur, teaches C# in a way that experienced programmers will appreciate by grounding its applications firmly in the context of Microsoft\'s .NET platform and the development of desktop and Internet applications. <br> <br> Liberty also incorporates reader suggestions from previous editions to help create the most consumer-friendly guide possible.','/resources/img/books/lrgjpg.png',1,672,'O\'Reilly Media',3.8,2,14,'Building .NET Applications with C#',2005,2);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booksinuse`
--

DROP TABLE IF EXISTS `booksinuse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booksinuse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `issue_date` datetime DEFAULT NULL,
  `mark` float DEFAULT '0',
  `return_date` datetime DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5y0fa2guej2upnvrhyfhhfpc8` (`book_id`),
  KEY `FK_mo4ohii4g1k8433a35y64nkyb` (`person_id`),
  CONSTRAINT `FK_5y0fa2guej2upnvrhyfhhfpc8` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_mo4ohii4g1k8433a35y64nkyb` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksinuse`
--

LOCK TABLES `booksinuse` WRITE;
/*!40000 ALTER TABLE `booksinuse` DISABLE KEYS */;
INSERT INTO `booksinuse` VALUES (8,'2014-02-05 00:26:56',4,'2014-02-19 00:26:56',3,18),(14,'2014-02-13 22:00:00',5,'2014-03-05 22:00:00',4,1),(15,'2014-02-13 22:00:00',5,'2014-03-05 22:00:00',5,1),(16,'2014-02-14 12:00:00',5,'2014-03-06 12:00:00',2,1),(17,'2014-02-16 15:00:00',5,'2014-02-24 15:00:00',15,1),(18,'2014-02-19 11:00:00',4.7,'2014-03-05 11:00:00',16,1),(19,'2014-02-20 17:00:00',3.8,'2014-02-28 17:00:00',23,1);
/*!40000 ALTER TABLE `booksinuse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10);
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genrelocalization`
--

DROP TABLE IF EXISTS `genrelocalization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genrelocalization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(255) DEFAULT NULL,
  `localizedName` varchar(255) DEFAULT NULL,
  `genre_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_37aeba2spfhks9qi0hrb7m1pe` (`genre_id`),
  CONSTRAINT `FK_37aeba2spfhks9qi0hrb7m1pe` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genrelocalization`
--

LOCK TABLES `genrelocalization` WRITE;
/*!40000 ALTER TABLE `genrelocalization` DISABLE KEYS */;
INSERT INTO `genrelocalization` VALUES (1,'en','Astrology',6),(2,'en','Electronics',4),(3,'en','Encyclopedia',8),(4,'en','Fantasy',10),(5,'en','History',5),(6,'en','Math',3),(7,'en','None genred',1),(8,'en','Philosophy',7),(9,'en','Programming',2),(10,'en','Psychology',9),(11,'ru','Астрология',6),(12,'ru','Электроника',4),(13,'ru','Энциклопедия',8),(14,'ru','Фентези',10),(15,'ru','История',5),(16,'ru','Математика',3),(17,'ru','Без жанра',1),(18,'ru','Философия',7),(19,'ru','Программирование',2),(20,'ru','Психология',9),(21,'uk','Астрологія',6),(22,'uk','Електроніка',4),(23,'uk','Енциклопедія',8),(24,'uk','Фентезі',10),(25,'uk','Історія',5),(26,'uk','Математика',3),(27,'uk','Без жанру',1),(28,'uk','Філософія',7),(29,'uk','Програмування',2),(30,'uk','Психологія',9);
/*!40000 ALTER TABLE `genrelocalization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` longtext,
  `issue_date` datetime DEFAULT NULL,
  `mark` float DEFAULT '-1',
  `book_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_e8pbuw8wsub3xgcvnolf72jsm` (`book_id`),
  KEY `FK_jt6l0spe0lqan72u4l7mmkx3g` (`person_id`),
  CONSTRAINT `FK_e8pbuw8wsub3xgcvnolf72jsm` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_jt6l0spe0lqan72u4l7mmkx3g` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,'Hddddd','2014-02-19 11:22:20',0,16,1),(2,'sdfsdfsdf','2014-02-20 17:19:45',3.8,23,1);
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` datetime DEFAULT NULL,
  `issue_date` datetime NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  `term` int(11) DEFAULT NULL,
  `preOrdered` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_o2c3ohh0hof8wms3tubkh0v5s` (`book_id`),
  KEY `FK_iej2da8bimqjxwvdma0eq8qus` (`person_id`),
  CONSTRAINT `FK_iej2da8bimqjxwvdma0eq8qus` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_o2c3ohh0hof8wms3tubkh0v5s` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2014-02-09 17:25:52','2014-03-06 17:25:00',2,18,NULL,0),(2,'2014-02-16 15:44:49','2014-02-23 15:44:00',15,3,NULL,0),(3,'2014-02-16 15:45:27','2014-02-25 15:45:00',15,4,NULL,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cellphone` varchar(255) DEFAULT NULL,
  `confirmed` tinyint(1) DEFAULT NULL,
  `e_mail` varchar(255) NOT NULL,
  `activated` tinyint(1) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'(099) 274-5624',1,'yurik.my@gmail.com',1,1,9,'Yurik','$2a$10$Gp.aP6XfF5mZRyzrL5FNTuiw2wyoTOjZFsrLPl9Tg8/qaICgK7qRK',0.9,'ROLE_LIBRARIAN',0,'Mikhaletskiy',27,2,'168be49084d0181543480732768535f521da7af3'),(3,'(097) 108-4550',1,'yurik_my@mail.ru',1,0,10,'Yurik','$2a$10$yS2y6mjHpHf5/TaHPDcYYuPN6Bv6vjdIt2Bmx.R7rpuE1oDaQBAme',1,'ROLE_USER',0,'Mykh',10,0,'310dcc42a9e9662dac0282e77251a8e18986c947'),(4,'(050) 555-5555',1,'yurik.my@yandex.ru',1,0,8,'John','$2a$10$Fzcrwk4TCHf9JqCQRDGu7.jfpMbwzB0SnDFkC95i1u9BT0NnpA1Ym',0.818,'ROLE_USER',0,'Terry',9,2,'b1d2661a53328efcd97ae4acf1fa5dbb153e97c1'),(5,'(050) 222-2222',1,'test@test.tst',1,0,10,'Joe','$2a$10$F1V1TRrhiDFeFnqaSj822OHKA5lm41f7u21ffIAUEcD1vQ44xSz8m',1,'ROLE_USER',0,'Cole',1,0,'cf926b2cb8bb29d2b189a5d482016aabfa8742af'),(13,'(099) 997-9797',0,'yuggy@gmfgsdil.cod',0,0,10,'Yurik','$2a$10$rUdVYiOZSTTsuJjfoaBt1.9imZC8Ko/l9fQir46eb69G.FSjOQaK.',1,'ROLE_USER',0,'My',0,0,'5df63bb4d53d4dc5f088adc1300ad06b4b971ca5'),(14,'0',1,'admin@admin.loc',1,0,10,'Admin','$2a$10$Gp.aP6XfF5mZRyzrL5FNTuiw2wyoTOjZFsrLPl9Tg8/qaICgK7qRK',1,'ROLE_ADMINISTRATOR',1,'Adminus',0,0,NULL),(15,NULL,0,'yurik.my@test.com',0,0,10,NULL,'$2a$10$wkzY67DtHljxtLjwWaoSEepB8i9Ggkavac1wqfjvkhJjCsrw/ap3C',1,'ROLE_USER',0,NULL,0,0,'733858e0c7d4fb09a608e98d87f50f8216be9b0c'),(16,'(099) 987-6543',0,'mike@test.com',0,0,10,'Mike','$2a$10$AA9oLc1eDo8i94Pdi0qiN.1y8h4WLzdYpJxC7gF/imVAc0v0mmg6y',1,'ROLE_USER',0,'Tyson',0,0,'b45969942d5b84962dea573a6748547499535256'),(17,'(123) 456-7897',1,'leo@test.com',1,1,5,'Leo','$2a$10$N41C9mPl6zrZDV8HCVC9H.mRgyuwbleCGXuAqUOD881dILfSdjzAi',0.5,'ROLE_USER',0,'Messi',2,1,'203c1c3fcad42f4474f71e7e509175b352793529'),(18,'(095) 191-6925',1,'grizlixx@gmail.com',1,0,10,'Pavel','$2a$10$y.dkdQz254ZcQpMqrXP2FOhJhe./rfmOG3x1dViJLKSCtWGkjv8ta',1,'ROLE_USER',1,'Mikhaletsky',0,1,'0680d3f8a54b0db50b1f1ea0158ac0987b934282');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wishlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fa5p777ket8urfn9vddi7vm4p` (`book_id`),
  KEY `FK_jbjdt125j4db1tyhyj3m9pn86` (`person_id`),
  CONSTRAINT `FK_fa5p777ket8urfn9vddi7vm4p` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `FK_jbjdt125j4db1tyhyj3m9pn86` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (3,2,17),(5,4,17);
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-20 18:38:39
