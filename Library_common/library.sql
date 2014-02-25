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
  `available` int(11) NOT NULL DEFAULT '0',
  `bookcase` int(11) DEFAULT '0',
  `count` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Antonio Goncalves',4,1,4,'<p> Java Enterprise Edition (Java EE) continues to be one of the leading Java technologies and platforms. <em>Beginning Java EE 7</em> is the first tutorial book on Java EE 7. <br> <br> Step by step and easy to follow, this book describes many of the Java EE 7 specifications and reference implementations, and shows them in action using practical examples. This definitive book also uses the newest version of GlassFish to deploy and administer the code examples. <br> <br> Written by an expert member of the Java EE specification request and review board in the Java Community Process (JCP), this book contains the best information possible, from an expert’s perspective on enterprise Java technologies. </p> <h3>What you’ll learn</h3> <ul> <li>Get started with the latest version of the Java EE Platform. </li> <li>Explore and use the EJB and JPA APIs from entities to session beans to message driven beans, and more. </li> <li>Discover web tier development APIs including JSF, Facelets and Expression Language. </li> <li>Uncover SOAP web services, RESTful web services, and more available in this latest Java EE. </li> <li>Create dynamic user interfaces for your enterprise and transactional Java applications. </li> </ul> <h3>Who this book is for</h3> <p>This book is for Java or Spring programmers with some experience and those new to Java EE platform. Architects will also find information about how to layer their Java EE applications. </p>','/resources/img/books/javaee7.png',5,608,'Apress',4.94,1,14,'Beginning Java EE 7',2013,2),(3,'Fain Y.',4,2,4,'<p> As one of the most popular software languages for building Web applications, Java is often the first programming language developers learn. The latest version includes numerous updates that both novice and experienced developers need to know. With this invaluable book-and-video package, Java authority Yakov Fain fully covers Java s new features as well as its language extensions, classes and class methods, and the Swing Application Framework. For each lesson that he discusses in the book, there is an accompanying instructional video to reinforce your learning experience.</p> <p> Lessons include:</p> <ul> <li>Introducing Java </li> <li>Eclipse IDE </li> <li>Object-Oriented Programming </li> <li>Class Methods</li> <li>Back to Java Basics </li> <li>Packages, Interfaces, and Encapsulation </li> <li>Programming with Abstract Classes and Interfaces</li> <li>Introducing the Graphic User Interface</li> <li>Event Handling in UI</li> <li>Introduction to Java Applets </li> <li>Developing a Tic-Tac-Toe Applet </li> <li>Developing a Ping-Pong Game </li> <li>Error Handling </li> <li>Introduction to Collections </li> <li>Introduction to Generics </li> <li>Working with Streams </li> <li>Java Serialization </li> <li>Network Programming</li> <li>Processing E-Mails with Java </li> <li>Introduction to Multi-Threading </li> <li>Digging Deeper into Concurrent Execution </li> <li>Working with Databases Using JDBC </li> <li>Swing with JTable </li> <li>Annotations and Reflection </li> <li>Remote Method Invocation </li> <li>Java EE 6 Overview </li> <li>Programming with Servlets </li> <li>JavaServer Pages </li> <li>Developing Web Applications with JSF </li> <li>Introducing JMS and MOM </li> <li>Introducing JNDI</li> <li>Introduction to Enterprise JavaBeans</li> <li>Introduction to the Java Persistence API</li> <li>Working with RESTful Web Services</li> <li>Introduction to Spring MVC Framework </li> <li>Introduction to Hibernate Framework </li> <li>Bringing JavaFX to the Mix </li> <li>Java Technical Interviews </li> </ul> <p> <b>Note: As part of the print version of this title, video lessons are included on DVD. For e-book versions, video lessons can be accessed at wrox.com using a link provided in the interior of the e-book.</b> </p>','/resources/img/books/java24.jpg',7,470,'WILEY',4.85714,4,14,'Java Programming 24-Hour Trainer',2011,2),(4,'Craig Walls',4,2,4,'<p> <em>Spring In Action - Covers Spring 3.0 </em> written by Craig Walls is an applied guide for the popular Spring Framework. Learning Spring is a requirement for all Java designers and Spring 3.0 lays a sound foundation, covering innovative features like SpEL (Spring Expression Language) and new interpretations of the IoC container. </p> <p> Like the previous editions, Spring 3.0 persists with its convenient, handy approach to highlight and zero in on the most significant facets of Spring like REST, Security, Webflow, and remote services. Walls uses a simple but efficient method to explain the building of any kind of J2EE application. <br> Walls uses easy language, explains using diminutive code snippets, and vibrant examples. </p>','/resources/img/books/springina.jpg',6,401,'Manning',5,2,14,'Spring in action',2011,2),(5,'K. Sierra, B. Bates',4,2,4,'With hundreds of practice questions and hands-on exercises, SCJP Sun Certified Programmer for Java 6 Study Guide covers what you need to know--and shows you how to prepare--for this challenging exam.','/resources/img/books/scjp.jpg',3,890,'MC Graw Hill',4.66667,3,14,'SCJP',2008,2),(6,'M. Deinum & K. Serneels',5,1,5,'Pro Spring MVC is an in-depth guide to Spring MVC, a modern web framework build on top of the Spring Framework.','/resources/img/books/webflow.png',3,590,'Apress',4.33333,1,14,'Pro Spring MVC: with Web Flow',2011,2),(14,'Joanna Martine Woolfolk',5,10,5,'Everyone\'s favorite astrology book, having sold over 500,000 copies, is now even easier to use with an interactive CD-ROM','/resources/img/books/astro.jpg',1,461,'Taylor Trade Publishing',4,1,14,'The Only Astrology Book You\'ll Ever Need',2006,6),(15,'Roger L Tokheim',1,6,2,'Designed to be used as an introductory text for students new to the electronics field','/resources/img/books/digital.JPG',1,392,'World',5,1,14,'Digital Electronics',1988,4),(16,'J.R.R. Tolkien',4,12,4,'In a hole in the ground there lived a hobbit...','/resources/img/books/hobbit.jpg',2,365,'Houghton Mifflin',4.85,1,14,'The Hobbit (Middle-Earth Universe)',2002,10),(17,'Jared Diamond',8,14,8,'Life isn\'t fair--here\'s why: Since 1500, Europeans have, for better & worse, called the tune that the world has danced to.','/resources/img/books/guns.jpg',0,494,'W.W. Norton & Company',0,2,14,'Guns, Germs, and Steel: The Fates of Human Societies',2005,5),(18,'Clifford A. Pickover',3,15,3,'Math’s infinite mysteries and beauty unfold in this follow-up to the best-selling The Science Book','/resources/img/books/math.jpg',0,985,'The Science Book',0,1,14,'The Math Book',2008,3),(19,'Friedrich Nietzsche, Walter Kaufmann',5,20,5,'Thus Spoke Zarathustra: A Book for All and None Thus is a philosophical novel by German philosopher Friedrich Nietzsche','/resources/img/books/nitzshe.jpg',0,327,'Penguin Books',0,2,14,'Thus Spoke Zarathustra',1978,7),(22,'Alex Libby, Dan Wellman',5,1,5,'jQuery UI, the official UI widget library for jQuery, gives you a solid platform on which to build rich and engaging interfaces quickly, with maximum compatibility, stability, and effort. jQuery UI’s ready-made widgets help to reduce the amount of code that you need to write to take a project from conception to completion.  jQuery UI 1.10: The User Interface Library for jQuery has been specially revised for Version 1.10 of jQuery UI. It is written to maximize your experience with the library by breaking down each component and walking you through examples that progressively build up your knowledge, taking you from beginner to advanced user in a series of easy-to-follow steps.  Throughout the book, you\'ll learn how to create a basic implementation of each component, then customize and configure the components to tailor them to your application.  Each chapter will also show you the custom events fired by the components covered and how these events can be intercepted and acted upon to bring out the best of the library.  We will then go on to cover the use of visually engaging, highly configurable user interface widgets. At the end of this book, we\'ll look at the functioning of all of the UI effects available in the jQuery UI library.  Approach  This book consists of an easy-to-follow, example-based approach that leads you step-by-step through the implementation and customization of each library component.  Who this book is for  This book is for frontend designers and developers who need to learn how to use jQuery UI quickly. To get the most out of this book, you should have a good working knowledge of HTML, CSS, and JavaScript, and should ideally be comfortable using jQuery.','/resources/img/books/lrg.jpg',0,502,'O\'Rilley',0,1,14,'jQuery UI 1.10: The User Interface Library for jQuery',2013,2),(23,'Jesse Liberty',3,2,3,'<p> The programming language C# was built with the future of application development in mind. Pursuing that vision, C#\'s designers succeeded in creating a safe, simple, component-based, high-performance language that works effectively with Microsoft\'s .NET Framework. Now the favored language among those programming for the Microsoft platform, C# continues to grow in popularity as more developers discover its strength and flexibility. And, from the start, C# developers have relied on Programming C# both as an introduction to the language and a means of further building their skills. <br> <br> The fourth edition of <i>Programming C#</i> --the top-selling C# book on the market--has been updated to the C# ISO standard as well as changes to Microsoft\'s implementation of the language. It also provides notes and warnings on C# 1.1 and C# 2.0. <br> <br> Aimed at experienced programmers and web developers, <i>Programming C#</i> , 4th Edition, doesn\'t waste too much time on the basics. Rather, it focuses on the features and programming patterns unique to the C# language. New C# 2005 features covered in-depth include: </p> <ul> <li>Visual Studio 2005 </li> <li>Generics </li> <li>Collection interfaces and iterators </li> <li>Anonymous methods </li> <li>New ADO.NET data controls </li> <li>Fundamentals of Object-Oriented Programming</li> </ul> Author Jesse Liberty, an acclaimed web programming expert and entrepreneur, teaches C# in a way that experienced programmers will appreciate by grounding its applications firmly in the context of Microsoft\'s .NET platform and the development of desktop and Internet applications. <br> <br> Liberty also incorporates reader suggestions from previous editions to help create the most consumer-friendly guide possible.','/resources/img/books/lrgjpg.png',1,672,'O\'Reilly Media',3.8,2,14,'Building .NET Applications with C#',2005,2),(24,' Leerom Segal, Aaron Goldstein, Jay Goldman, Rahaf Harfoush ',10,1,10,' From the Publisher “Next generation management calls for next generation tools. The Decoded Company shows you how to build truly 21st century operating systems that use data to empower talent, creating not just efficiencies but true capability across your entire business.” — Don Tapscott, International bestselling author of 15 books, speaker and Adjunct Professor of Management, Rotman School of Management, University of Toronto  \"The most critical resources for any organization today are information, ideas, and talent. The Decoded Company is a management toolkit for the future, offering a strong vision and a practical approach of what an organization can be when it is as connected as its customers already are.\" — Professor Klaus Schwab,World Economic Forum, Founder and Executive Chairman From the Publisher  “Next generation management calls for next generation tools. The Decoded Company shows you how to build truly 21st century operating systems that use data to empower talent, creating not just efficiencies but true capability across your entire business.” -- Don Tapscott  \"The most critical resources for any organization today are information, ideas, and talent. The Decoded Company is a management toolkit for the future, offering a strong vision and a practical approach of what an organization can be when it is as connected as its customers already are.\" -- Professor Klaus Schwab, World Economic Forum','/resources/img/books/decoded.JPG',0,336,'Portfolio Hardcover',0,11,14,'The Decoded Company: Know Your Talent Better Than You Know Your Customers ',2014,10),(25,'Alice Hoffman',5,10,5,'  Mesmerizing and illuminating, Alice Hoffman’s The Museum of Extraordinary Things is the story of an electric and impassioned love between two vastly different souls in New York during the volatile first decades of the twentieth century.  Coralie Sardie is the daughter of the sinister impresario behind The Museum of Extraordinary Things, a Coney Island boardwalk freak show that thrills the masses. An exceptional swimmer, Coralie appears as the Mermaid in her father’s “museum,” alongside performers like the Wolfman, the Butterfly Girl, and a one-hundred-year-old turtle. One night Coralie stumbles upon a striking young man taking pictures of moonlit trees in the woods off the Hudson River.  The dashing photographer is Eddie Cohen, a Russian immigrant who has run away from his father’s Lower East Side Orthodox community and his job as a tailor’s apprentice. When Eddie photographs the devastation on the streets of New York following the infamous Triangle Shirtwaist Factory fire, he becomes embroiled in the suspicious mystery behind a young woman’s disappearance and ignites the heart of Coralie.  With its colorful crowds of bootleggers, heiresses, thugs, and idealists, New York itself becomes a riveting character as Hoffman weaves her trademark magic, romance, and masterful storytelling to unite Coralie and Eddie in a sizzling, tender, and moving story of young love in tumultuous times. The Museum of Extraordinary Things is Alice Hoffman at her most spellbinding.','/resources/img/books/the museum.JPG',0,384,'Scribner',0,3,14,'The Museum of Extraordinary Things',2014,8),(26,' Gregory Feifer ',10,10,10,'  From former NPR Moscow correspondent Gregory Feifer comes an incisive portrait that draws on vivid personal stories to portray the forces that have shaped the Russian character for centuries-and continue to do so today.  RUSSIANS explores the seeming paradoxes of life in Russia by unraveling the nature of its people: what is it in their history, their desires, and their conception of themselves that makes them baffling to the West? Using the insights of his decade as a journalist in Russia, Feifer corrects pervasive misconceptions by showing that much of what appears inexplicable about the country is logical when seen from the inside. He gets to the heart of why the world\'s leading energy producer continues to exasperate many in the international community. And he makes clear why President Vladimir Putin remains popular even as the gap widens between the super-rich and the great majority of poor.  Traversing the world\'s largest country from the violent North Caucasus to Arctic Siberia, Feifer conducted hundreds of intimate conversations about everything from sex and vodka to Russia\'s complex relationship with the world. From fabulously wealthy oligarchs to the destitute elderly babushki who beg in Moscow\'s streets, he tells the story of a society bursting with vitality under a leadership rooted in tradition and often on the edge of collapse despite its authoritarian power.  Feifer also draws on formative experiences in Russia\'s past and illustrative workings of its culture to shed much-needed light on the purposely hidden functioning of its society before, during, and after communism. Woven throughout is an intimate, first-person account of his family history, from his Russian mother\'s coming of age among Moscow\'s bohemian artistic elite to his American father\'s harrowing vodka-fueled run-ins with the KGB.  What emerges is a rare portrait of a unique land of extremes whose forbidding geography, merciless climate, and crushing corruption has nevertheless produced some of the world\'s greatest art and some of its most remarkable scientific advances. RUSSIANS is an expertly observed, gripping profile of a people who will continue challenging the West for the foreseeable future.','/resources/img/books/russians.JPG',0,384,'Grand Central Publishing',0,3,14,'Russians: The People behind the Power',2014,5),(27,'Katerina Kolozova, Francois Laruelle',3,2,3,'  Following François Laruelle\'s nonstandard philosophy and the work of Judith Butler, Drucilla Cornell, Luce Irigaray, and Rosi Braidotti, Katerina Kolozova reclaims the relevance of categories traditionally rendered \"unthinkable\" by postmodern feminist philosophies, such as \"the real,\" \"the one,\" \"the limit,\" and \"finality,\" thus critically repositioning poststructuralist feminist philosophy and gender/queer studies.  Poststructuralist (feminist) theory sees the subject as a purely linguistic category, as always already multiple, as always already nonfixed and fluctuating, as limitless discursivity, and as constitutively detached from the instance of the real. This reconceptualization is based on the exclusion of and dichotomous opposition to notions of the real, the one (unity and continuity), and the stable. The non-philosophical reading of postructuralist philosophy engenders new forms of universalisms for global debate and action, expressed in a language the world can understand. It also liberates theory from ideological paralysis, recasting the real as an immediately experienced human condition determined by gender, race, and social and economic circumstance.  Columbia University Press.   Drucilla Cornell Cut of the Real is an important and original contribution to the complex discussions relating to subjectivity and identity. Through her nuanced reading of Lacan and Laruelle, Katerina Kolozova creates a powerful argument for a notion of democratic love that allows us to break through some of the ambiguities that have attended discussions of subjectivity, human nature, and the possibility of meaningful or radical social change. Her book will be a must-read in fields as diverse as philosophy, anthropology, and law. Jodi Dean Kolozova\'s important new book is a fascinating disruption of the assumptions of post-structuralist feminism. Her creative extension of the \'non-philosophy\' of Laruelle radicalizes feminist philosophy as it expands possibilities for theorizing the real as experienced. This is a major contribution to the new materialism. Levi R. Bryant Cut of the Real is destined to be an important contribution to ongoing debates in feminist, queer, gender, and race theory, as well as the newly emerging philosophical trend of speculative realism. It is my belief that Kolozova\'s book is the best introduction to Laruelle\'s thought to date and that it does an exceptional job discussing why it is valuable and what it can do.  ','/resources/img/books/cutofthereal.JPG',0,208,'Columbia University Press',0,3,14,'Cut of the Real: Subjectivity in Poststructuralist Philosophy',2014,7),(28,'Rainer Maria Rilke',8,8,8,' Overview  “Essential for all poetry collectionsOriginally published in 1907 and 1908 in two volumes, the 200 poems contained in this bilingual single volume represent a period of intense creativity in the poet’s career. Translator Joseph Cadora renders a beautiful new edition complete with commentary on each poem at the end, based on Rilke’s letters, numerous biographies, and related works as well as an introduction outlining his approach to the translation..”—Library Journal, starred review  “[The] renderings of the canonical poems, such as “The Panther,” “Orpheus, Eurydice, Hermes,” and “Archaic Torso of Apollo” (to name a few), are worthwhile additions to the enormous body of Rilke’s work already translated by others. Cadora’s willingness to translate nearly two hundred poems is admirable, since many themes recur and develop.”—Booklist  \"Through him resounds the music of the universe.\"—Herman Hesse  Rainer Maria Rilke is one of the world\'s best-selling poets, and New Poems contains many of his most iconic pieces. Throughout, Rilke he is obsessed with shapes and different layers of physical containment—from an image held in a panther\'s eye to a cathedral window. Translator Joseph Cadora has created the definitive English-language version through meticulous faithfulness to Rilke\'s German and insightful commentary on each of the four hundred-plus poems. As Cadora said in an interview, \"I tried to stay true to the vision of Rilke that would invite the reader into his world, not mine.\" Bilingual, with an introduction by Robert Hass.  The Panther:  From endless passing of the bars his gazehas wearied—there is no more it can hold. There seem to be a thousand bars always, and past those thousand bars there is no world.  The soft pad of his brawny, rippling paceturns itself in a tightening circle till, like a mighty dance around a tiny space, it centers a numb but still enormous will.  But at times the shades of his pupils rise,grasping an image he cannot resist; through his tense, unmoving limbs it flies, and within his heart it ceases to exist.  Rainer Maria Rilke (18751926) is one of the world\'s most beloved poets. Working at the cusp of the century, Rilke bridged the gap between traditional and modernist poetics. Born in Prague, Rilke traveled widely across Europe and Egypt, and lived for many years in Paris.  Joseph Cadora is a guitarist, writer, and translator. He lives in Berkeley, California.','/resources/img/books/rilke.JPG',0,410,'Bilingual',0,6,14,'Rilke: New Poems',2014,1),(29,'Harold Schechter',10,1,10,'Beekman Place, once one of the most exclusive addresses in Manhattan, had a curious way of making it into the tabloids in the 1930s: “SKYSCRAPER SLAYER,” “BEAUTY SLAIN IN BATHTUB” read the headlines. On Easter Sunday in 1937, the discovery of a grisly triple homicide at Beekman Place would rock the neighborhood yet again—and enthrall the nation. The young man who committed the murders would come to be known in the annals of American crime as the Mad Sculptor.   Caught up in the Easter Sunday slayings was a bizarre and sensationalistic cast of characters, seemingly cooked up in a tabloid editor’s overheated imagination. The charismatic perpetrator, Roger Irwin, was a brilliant young sculptor who had studied with some of the masters of the era. But with his genius also came a deeply disturbed psyche; Irwin was obsessed with sexual self-mutilation and was frequently overcome by outbursts of violent rage.   Irwin’s primary victim, Veronica Gedeon, was a figure from the world of pulp fantasy—a stunning photographer\'s model whose scandalous seminude pinups would titillate the public for weeks after her death. Irwin’s defense attorney, Samuel Leibowitz, was a courtroom celebrity with an unmatched record of acquittals and clients ranging from Al Capone to the Scottsboro Boys. And Dr. Fredric Wertham, psychiatrist and forensic scientist, befriended Irwin years before the murders and had predicted them in a public lecture months before the crime.   Based on extensive research and archival records, The Mad Sculptor recounts the chilling story of the Easter Sunday murders—a case that sparked a nationwide manhunt and endures as one of the most engrossing American crime dramas of the twentieth century. Harold Schechter’s masterful prose evokes the faded glory of post-depression New York and the singular madness of a brilliant mind turned against itself. It will keep you riveted until the very last page. Library Journal 02/15/2014 Schechter (American literature, City University New York) shares another rigorously researched true crime story in the latest in his series that delves into American serial killers (e.g., Fatal; Fiend; Bestial; et al.). Dusty court records and archived newspaper articles serve as the cornerstone for a disturbing retelling of three gruesome murders that occurred in Manhattan\'s ritzy Beekman Place over Easter weekend in 1937 and the subsequent cross-country manhunt, arrest, and trial of Robert Irwin, a twisted artistic genius whose homicidal rampage was predicted years before by forensic scientist and psychiatrist Fredric Wertham. Front-page coverage of the deaths of \"art beauty\" Veronica Gedeon, her mother, and a boarder in their home by a \"sex fiend killer\" gripped the nation as Irwin evaded arrest until his surrender to journalists from the same newspaper made famous in the play The Front Page. VERDICT Resurrecting a huge national tabloid sensation covered by the likes of Walter Winchell, this fascinating tale of a charismatic and savvy madman will thrill historical true crime fans.—Reba Kennedy, San Antonio ','/resources/img/books/madsculptor.JPG',0,368,'Houghton Mifflin Harcourt',0,1,14,'The Mad Sculptor: The Maniac, the Model, and the Murder That Shook the Nation ',2014,10),(30,'Nick Lloyd',10,10,10,'In the late summer of 1918, after four long years of senseless, stagnant fighting, the Western Front erupted. The bitter four-month struggle that ensued—known as the Hundred Days Campaign—saw some of the bloodiest and most ferocious combat of the Great War, as the Allies grimly worked to break the stalemate in the west and end the conflict that had decimated Europe.  In Hundred Days, acclaimed military historian Nick Lloyd leads readers into the endgame of World War I, showing how the timely arrival of American men and materiel—as well as the bravery of French, British, and Commonwealth soldiers—helped to turn the tide on the Western Front. Many of these battle-hardened troops had endured years of terror in the trenches, clinging to their resolve through poison-gas attacks and fruitless assaults across no man’s land. Finally, in July 1918, they and their American allies did the impossible: they returned movement to the western theater. Using surprise attacks, innovative artillery tactics, and swarms of tanks and aircraft, they pushed the Germans out of their trenches and forced them back to their final bastion: the Hindenburg Line, a formidable network of dugouts, barbed wire, and pillboxes. After a massive assault, the Allies broke through, racing toward the Rhine and forcing Kaiser Wilhelm II to sue for peace.  An epic tale ranging from the ravaged fields of Flanders to the revolutionary streets of Berlin, Hundred Days recalls the bravery and sacrifice that finally silenced the guns of Europe.  From the Publisher  “Brisk and thoroughly engrossing Far from being a pointless stalemate in the mud, the last hundred days of [World War I] saw the Allied armies push their adversaries back from the Paris commuter belt all the way to the German border itself.” —Evening Standard (London)  “Lloyd enters the upper tier of Great War historians with this admirable account of the war’s final campaign. Lloyd’s unfailing eye for telling anecdotes vitalize his narrative. The text brims with archival research.” —Publishers Weekly  “Lloyd effectively proves his thesis that Allied military might and leadership, with four hard years of strategic and tactical lessons learned, were what brought the war to a close. While most of the new books commemorating the 100th anniversary of the beginning of the war will focus on its causes and origin, Lloyd’s analysis of the final campaigns brings a new perspective to the terrible conflict.” —Library Journal  “This culmination of four years of bloodshed has been largely forgotten... [Lloyd] gives the reader an insight into the raw emotions of the period.” —The Oxford Times  “This is a powerful and moving book by a rising military historian. Lloyd’s depiction of the great battles of July-November provides compelling evidence of the scale of the Allies’ victories and the bitter reality of German defeat.” —Gary Sheffield, Professor of War Studies, University of Wolverhampton Library Journal 11/15/2013 Lloyd (defense studies, King\'s Coll. London; Loos 1915) turns his attention to the 1918 Allied offensive on the western front, which forced Germany\'s leaders to ask for an armistice in early November of that year. He describes a war of movement, one that featured smart tactical decisions by Allied planners, but ultimately a war whose conclusion was almost as tragic as its waging. The chaos experienced by German armies during the Allied offensive was kept hidden from most of the German population. Since the war\'s battles were not fought on German soil and the German army was able for the most part to retreat successfully, the myth of the \"stab in the back\" found quick reception with German nationalists who needed to explain their county\'s defeat. Lloyd effectively proves his thesis that Allied military might and leadership, with four hard years of strategic and tactical lessons learned, were what brought the war to a close. VERDICT While most of the new books commemorating the 100th anniversary of the beginning of the war will focus on its causes and origin, Lloyd\'s analysis of the final campaigns brings a new perspective to the terrible conflict. Recommended for general readers interested in learning more about the war; for most World War I collections.—Michael Farrell, Reformed Theological Seminary, Orlando, FL ','/resources/img/books/hundreddays.JPG',0,400,'Basic Books',0,10,14,'Hundred Days: The Campaign That Ended World War I',2014,5),(31,'George Marsden',7,7,7,'In the aftermath of World War II, the United States stood at a precipice. The forces of modernity unleashed by the war had led to astonishing advances in daily life, but technology and mass culture also threatened to erode the country’s traditional moral character. As award-winning historian George M. Marsden explains in The Twilight of the American Enlightenment, postwar Americans looked to the country’s secular, liberal elites for guidance in this precarious time, but these intellectuals proved unable to articulate a coherent common cause by which America could chart its course. Their failure lost them the faith of their constituents, paving the way for a Christian revival that offered America a firm new moral vision—one rooted in the Protestant values of the founders.  A groundbreaking reappraisal of the country’s spiritual reawakening, The Twilight of the American Enlightenment shows how America found new purpose at the dawn of the Cold War. Publishers Weekly 12/09/2013 Under the surface of a seemingly placid era roiled a cauldron of doubts and discontent, according to this penetrating study of post-war intellectual ferment. Bancroft Prize–winning historian Marsden (Fundamentalism and American Culture) surveys the social and cultural developments that made the 1950s an unsettling time for contemporary thinkers: the menace of nuclear war; an affluent but shallow consumer society; the displacement of traditional authority and community by individualism; a new creed of science and psychology that eclipsed conventional religious doctrine; television. He sets these trends against a reigning orthodoxy of pragmatic liberalism that, he argues, hewed to the Enlightenment ideals of America’s founders while abandoning their belief in a rational moral order, a theme that he explores through engaging, perceptive critical exegeses of the writings of contemporary public intellectuals, including Walter Lippmann, Betty Friedan, B. F. Skinner, and Reinhold Niebuhr. Through these critiques Marsden provocatively diagnoses the decay of a liberal ideology unmoored from philosophical foundations, a decay, he contends, that set the stage for the cultural revolution of the 1960s and a resurgent religious right in the 1970s. Marsden’s erudite, sophisticated, but very accessible study reveals the suppressed spiritual hunger of a secular age. (Feb.) ','/resources/img/books/twinlight.JPG',0,264,'Basic Books',0,7,14,'The Twilight of the American Enlightenment: The 1950s and the Crisis of Liberal Belief ',2014,10),(32,'Dave Itzkoff',4,4,4,'  The behind-the-scenes story of the making of the iconic movie Network, which transformed the way we think about television and the way television thinks about us  “I’m mad as hell, and I\'m not going to take this anymore!”  Those words, spoken by an unhinged anchorman named Howard Beale, “the mad prophet of the airwaves,” took America by storm in 1976, when Network became a sensation. With a superb cast (including Faye Dunaway, William Holden, Peter Finch, and Robert Duvall) directed by Sidney Lumet, the film won four Academy Awards and indelibly shaped how we think about corporate and media power.  In Mad As Hell, Dave Itzkoff of The New York Times recounts the surprising and dramatic story of how Network made it to the screen. Such a movie rarely gets made any more—one man’s vision of the world, independent of studio testing or market research. And that man was Paddy Chayefsky, the tough, driven, Oscar-winning screenwriter whose vision—outlandish for its time—is all too real today. Itzkoff uses interviews with the cast and crew, as well as Chayefsky’s notes, letters, and drafts to re-create the action in front of and behind the camera at a time of swirling cultural turmoil. The result is a riveting account that enriches our appreciation of this prophetic and still-startling film.  Itzkoff also speaks with today’s leading broadcasters and filmmakers to assess Network’s lasting impact on television and popular culture. They testify to the enduring genius of Paddy Chayefsky, who foresaw the future and whose life offers an unforgettable lesson about the true cost of self-expression.','/resources/img/books/mad as hell.JPG',0,304,'Holt, Henry & Company, Inc.',0,4,14,'Mad as Hell: The Making of Network and the Fateful Vision of the Angriest Man in Movies',2014,4),(33,'Steven Rybin (Editor), Will Scheibel (Editor), Will Scheibel (Introduction) ',3,3,3,'  A range of approaches to the director\'s life and work.  The director of such classic Hollywood films as In a Lonely Place, Johnny Guitar, and Rebel Without a Cause, Nicholas Ray nevertheless remained on the margins of the American studio system throughout his career, and despite his cult status among auteurist critics and cinephiles, he has also remained at the margins of film scholarship. Lonely Places, Dangerous Ground offers twenty new essays by international film historians and critics that explore the director’s place in the history of the Hollywood industry and in the larger institution of cinema, as well as a 1977 interview with Ray that has never before been published in its entirety in English. In addition to readings of Ray’s most celebrated films, the book provides a range of approaches to his life and work, engaging new questions of his cinematic authorship with areas that include history and culture, politics and society, gender and sexuality, style and genre, performance, technology, and popular music. The collection also looks at Ray’s lesser-known and underappreciated films, and devotes attention to the highly experimental We Can’t Go Home Again, his recently restored final film made in the 1970s with his students at Binghamton University, State University of New York. Rediscovering what Ray means to contemporary film studies, the essays show how his films continue to possess a vital power for film history and criticism, and for film culture.','/resources/img/books/lonely places.JPG',0,256,'State University of New York Press',0,3,14,'Lonely Places, Dangerous Ground: Nicholas Ray in American Cinema ',2014,8),(34,'Mary Ann McDonald Carolan',2,2,2,'  Tracks the influence of Italian cinema on American film from the postwar period to the present.  In The Transatlantic Gaze, Mary Ann McDonald Carolan documents the sustained and profound artistic impact of Italian directors, actors, and screenwriters on American film. Working across a variety of genres, including neorealism, comedy, the Western, and the art film, Carolan explores how and why American directors from Woody Allen to Quentin Tarantino have adapted certain Italian trademark techniques and motifs. Allen’s To Rome with Love (2012), for example, is an homage to the genius of Italian filmmakers, and to Federico Fellini in particular, whose Lo sceicco bianco/The White Sheik (1952) also resonates with Allen’s The Purple Rose of Cairo (1985) as well as with Neil LaBute’s Nurse Betty (2000). Tarantino’s Kill Bill saga (2003, 2004) plays off elements of Sergio Leone’s spaghetti Western C’era una volta il West/Once Upon a Time in the West (1968), a transatlantic conversation about the Western that continues in Tarantino’s Oscar-winning Django Unchained (2012). Lee Daniels’s Precious (2009) and Spike Lee’s Miracle at St. Anna (2008), meanwhile, demonstrate that the neorealism of Roberto Rossellini and Vittorio De Sica, which arose from the political and economic exigencies of postwar Italy, is an effective vehicle for critiquing social issues such as poverty and racism in a contemporary American context. The book concludes with an examination of American remakes of popular Italian films, a comparison that offers insight into the similarities and differences between the two cultures and the transformations in genre, both subtle and obvious, that underlie this form of cross-cultural exchange.','/resources/img/books/Mary Ann McDonald Carolan.JPG',0,160,'State University of New York Press',0,2,14,'The Transatlantic Gaze: Italian Cinema, American Film ',1999,4),(35,'Mark Bryant',10,1,10,'Whether producing strips, social comment in magazines like Punch or Lilliput, savage caricature of allies and enemies, or a daily chronicle of events at home or abroad, little escaped the cartoonists pen during World War II and they encapsulated the great dramas in a way impossible in prose.  This book is divided into chapters covering the war year-by-year, each chapter prefaced with a concise introduction that provides a historical framework for the cartoons of that year. Altogether some 300 cartoons, in color and black and white, have been skillfully blended to produce a unique record of World War II.','/resources/img/books/World War II in Cartoons.jpg',0,160,'Grub Street',0,10,14,'World War II in Cartoons ',2014,1);
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
  CONSTRAINT `FK_mo4ohii4g1k8433a35y64nkyb` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_5y0fa2guej2upnvrhyfhhfpc8` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booksinuse`
--

LOCK TABLES `booksinuse` WRITE;
/*!40000 ALTER TABLE `booksinuse` DISABLE KEYS */;
INSERT INTO `booksinuse` VALUES (1,'2014-02-25 13:00:00',0,'2014-03-07 13:00:00',15,4);
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
  CONSTRAINT `FK_jt6l0spe0lqan72u4l7mmkx3g` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_e8pbuw8wsub3xgcvnolf72jsm` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,NULL,'2014-02-25 13:59:23',0,15,4);
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
  `preOrdered` tinyint(1) DEFAULT '0',
  `term` int(11) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `person_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o2c3ohh0hof8wms3tubkh0v5s` (`book_id`),
  KEY `FK_iej2da8bimqjxwvdma0eq8qus` (`person_id`),
  CONSTRAINT `FK_iej2da8bimqjxwvdma0eq8qus` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_o2c3ohh0hof8wms3tubkh0v5s` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'2014-02-25 13:57:20','2014-02-25 17:00:00',0,14,2,4),(4,'2014-02-25 14:09:55','2014-02-25 17:00:00',0,14,15,22);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (1,'(099) 274-5624',1,'yurik.my@gmail.com',1,1,8,'Yurik','$2a$10$Gp.aP6XfF5mZRyzrL5FNTuiw2wyoTOjZFsrLPl9Tg8/qaICgK7qRK',0.889,'ROLE_LIBRARIAN',0,'Mikhaletskiy',24,2,'168be49084d0181543480732768535f521da7af3'),(3,'(097) 108-4550',1,'yurik_my@mail.ru',1,0,10,'Yurik','$2a$10$yS2y6mjHpHf5/TaHPDcYYuPN6Bv6vjdIt2Bmx.R7rpuE1oDaQBAme',1,'ROLE_USER',0,'Mykh',10,0,'310dcc42a9e9662dac0282e77251a8e18986c947'),(4,'(050) 555-5555',1,'yurik.my@yandex.ru',1,0,8,'John','$2a$10$Fzcrwk4TCHf9JqCQRDGu7.jfpMbwzB0SnDFkC95i1u9BT0NnpA1Ym',0.818,'ROLE_USER',0,'Terry',9,2,'b1d2661a53328efcd97ae4acf1fa5dbb153e97c1'),(5,'(050) 222-2222',1,'test@test.tst',1,0,10,'Joe','$2a$10$F1V1TRrhiDFeFnqaSj822OHKA5lm41f7u21ffIAUEcD1vQ44xSz8m',1,'ROLE_USER',0,'Cole',1,0,'cf926b2cb8bb29d2b189a5d482016aabfa8742af'),(13,'(099) 997-9797',0,'yuggy@gmfgsdil.cod',0,0,10,'Yurik','$2a$10$rUdVYiOZSTTsuJjfoaBt1.9imZC8Ko/l9fQir46eb69G.FSjOQaK.',1,'ROLE_USER',0,'My',0,0,'5df63bb4d53d4dc5f088adc1300ad06b4b971ca5'),(14,'0',1,'admin@admin.loc',1,0,10,'Admin','$2a$10$Gp.aP6XfF5mZRyzrL5FNTuiw2wyoTOjZFsrLPl9Tg8/qaICgK7qRK',1,'ROLE_ADMINISTRATOR',1,'Adminus',0,0,NULL),(15,NULL,0,'yurik.my@test.com',0,0,10,NULL,'$2a$10$wkzY67DtHljxtLjwWaoSEepB8i9Ggkavac1wqfjvkhJjCsrw/ap3C',1,'ROLE_USER',0,NULL,0,0,'733858e0c7d4fb09a608e98d87f50f8216be9b0c'),(16,'(099) 987-6543',0,'mike@test.com',0,0,10,'Mike','$2a$10$AA9oLc1eDo8i94Pdi0qiN.1y8h4WLzdYpJxC7gF/imVAc0v0mmg6y',1,'ROLE_USER',0,'Tyson',0,0,'b45969942d5b84962dea573a6748547499535256'),(17,'(123) 456-7897',1,'leo@test.com',1,1,5,'Leo','$2a$10$N41C9mPl6zrZDV8HCVC9H.mRgyuwbleCGXuAqUOD881dILfSdjzAi',0.5,'ROLE_USER',0,'Messi',2,1,'203c1c3fcad42f4474f71e7e509175b352793529'),(18,'(095) 191-6925',1,'grizlixx@gmail.com',1,0,10,'Pavel','$2a$10$y.dkdQz254ZcQpMqrXP2FOhJhe./rfmOG3x1dViJLKSCtWGkjv8ta',1,'ROLE_USER',1,'Mikhaletsky',0,0,'0680d3f8a54b0db50b1f1ea0158ac0987b934282'),(19,'(050) 279-1710',1,'alex9523@yandex.ru',1,0,10,'Kryvorotenko','$2a$10$XvYzkMRdVJGXCf2O/xB.HuMnz2p8Y0TmliddUoGy6giH/kTUJoI2K',1,'ROLE_USER',1,'Olexandr',0,0,'00daa92387b535cf69ad3c95d7708d18ad97183c'),(21,NULL,0,'345@h.h',0,0,10,NULL,'$2a$10$NoPs8jbElCSo4ebcfVp7Te4ANJvPG2Z9fk3mZ76LPcBo2Q4pzEh/6',1,'ROLE_USER',0,NULL,0,0,'fad1283763e8d7cf6c9cf733ea050c9d45880121'),(22,NULL,0,'test@test.loc',1,0,10,NULL,'$2a$10$SZ62BYXWkYIOxiwIThYjau3L/JgKp9nL9WZGZLA67lOBUd3vPLhdS',1,'ROLE_USER',0,NULL,0,0,'d7ba946a0f102796611025039ca73734e6302ab2');
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
  CONSTRAINT `FK_jbjdt125j4db1tyhyj3m9pn86` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`),
  CONSTRAINT `FK_fa5p777ket8urfn9vddi7vm4p` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (2,2,22);
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

-- Dump completed on 2014-02-25 14:13:11
