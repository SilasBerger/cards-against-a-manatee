-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: localhost    Database: cards_against_a_manatee
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS cards_against_a_manatee;

USE cards_against_a_manatee;

--
-- Table structure for table `black_cards`
--

DROP TABLE IF EXISTS `black_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `black_cards` (
  `id` int NOT NULL,
  `value` text NOT NULL,
  `pick` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `black_cards`
--

LOCK TABLES `black_cards` WRITE;
/*!40000 ALTER TABLE `black_cards` DISABLE KEYS */;
INSERT INTO `black_cards` VALUES (2,'Why can’t I sleep at night?',1),(3,'What’s that smell?',1),(4,'I got 99 problems but ______ ain’t one.',1),(5,'Maybe she’s born with it. Maybe it’s ______.',1),(6,'What’s the next Happy Meal® toy?',1),(7,'Here is the church Here is the steeple Open the doors And there is ______.',1),(8,'It’s a pity that kids these days are all getting involved with.',1),(9,'Today on Maury: “Help! My son is ______.',1),(10,'Alternative medicine is now embracing the curative powers of ______.',1),(11,'And the Academy Award for ______ goes to ______.',2),(12,'What’s that sound?',1),(13,'What ended my last relationship?',1),(14,'MTV’s new reality show features eight washed-up celebrities living with ______.',1),(16,'I’m sorry, Professor, but I couldn’t complete my homework because of ______.',1),(17,'What is Batman’s guilty pleasure?',1),(19,'That\'s the way the world ends \\ This is the way the world ends \\ Not with a bang but with ______.',1),(21,'TSA guidelines now prohibit on airplanes.',1),(25,'In the new Disney Channel Original Movie, Hannah Montana struggles with ______ for the first time.',1),(26,'______ is a slippery slope that leads to ______.',2),(27,'I get by with a little help from ______.',1),(28,'Dear Abby, I’m having some trouble with ______ and would like your advice.',1),(29,'Instead of coal, Santa now gives the bad children ______.',1),(31,'In 1,000 years, when paper money is a distant memory, how will we pay for goods and services?',1),(32,'Introducing the amazing superhero/sidekick duo! lt’s ______ and ______!',2),(33,'In M. Night Shyamalan’s new movie, Bruce Willis discovers that ______ had really been ______ all along.',2),(34,'A romantic, candlelit dinner would be incomplete without ______.',1),(35,'Betcha can’t have just one!',1),(37,'______. High five, bro.',1),(38,'Next from J.K. Rowling: Harry Potter and the Chamber of ______.',1),(39,'Introducing Xtreme Baseball! lt’s like baseball, but with ______!',1),(40,'In a world ravaged by ______ our only solace is ______.',2),(41,'War! What is it good for?',1),(44,'What are my parents hiding from me?',1),(48,'What don’t you want to find in your Kung Pao chicken?',1),(49,'What will I bring back in time to convince people that I am a powerful wizard?',1),(50,'How am I maintaining my relationship status?',1),(52,'Coming to Broadway this season, ______: The Musical.',1),(55,'Next on ESPN2, the World Series of ______.',1),(56,'Step 1: ______. Step 2: ______. Step 3: Profit.',2),(57,'They said we were crazy. They said we couldn’t put ______ inside of ______. They were wrong.',2),(58,'But before I kill you, Mr. Bond, I must show you ______.',1),(60,'The new Chevy Tahoe. With the power and space to take ______ everywhere you go.',1),(61,'The class field trip was completely ruined by ______.',1),(62,'When Pharaoh remained unmoved, Moses called down a Plague of ______.',1),(63,'What’s my secret power?',1),(65,'What’s there a ton of in heaven?',1),(67,'I never truly understood ______ until I encountered ______.',2),(70,'What did Vin Diesel eat for dinner?',1),(73,'What gets better with age?',1),(74,'______: kid-tested, mother-approved.',1),(76,'What’s Teach for America using to inspire inner city students to succeed?',1),(80,'I do not know with what weapons World War III will be fought, but World War IV will be fought with ______.',1),(82,'What am I giving up for Lent?',1),(84,'The Smithsonian Museum of Natural History has just opened an interactive exhibit on ______.',1),(86,'When I am President of the United States, I will create the Department of ______.',1),(87,'When I am a billionaire, I shall erect a 50-foot statue to commemorate ______.',1),(91,'______+______=______.',2),(92,'What never fails to liven up the party?',1),(93,'What’s the new fad diet?',1);
/*!40000 ALTER TABLE `black_cards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `white_cards`
--

DROP TABLE IF EXISTS `white_cards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `white_cards` (
  `id` int NOT NULL,
  `value` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `white_cards`
--

LOCK TABLES `white_cards` WRITE;
/*!40000 ALTER TABLE `white_cards` DISABLE KEYS */;
INSERT INTO `white_cards` VALUES (1,'Being on fire.'),(5,'Women in yogurt commercials.'),(12,'An oversized lollipop.'),(17,'An Oedipus complex.'),(18,'A tiny horse.'),(21,'Barack Obama.'),(25,'Darth Vader.'),(28,'Five-Dollar Footlongs.'),(30,'Free samples.'),(31,'Estrogen.'),(34,'Men.'),(37,'A bag of magic beans.'),(38,'Repression.'),(39,'Prancing.'),(40,'My relationship status.'),(41,'Overcompensation.'),(44,'Forever.'),(47,'The Devil himself.'),(48,'The World of Warcraft.'),(50,'Being fabulous.'),(54,'The rhythms of Africa.'),(57,'The Pope.'),(62,'Cybernetic enhancements.'),(64,'Jobs.'),(67,'The Boy Scouts of America.'),(69,'Finger painting.'),(70,'The Care Bear Stare.'),(77,'Mr. Clean, right behind you.'),(78,'Magnets.'),(80,'Agriculture.'),(81,'Judge Judy.'),(84,'Robert Downey, Jr.'),(86,'An M. Night Shyamalan plot twist.'),(87,'Funky fresh rhymes.'),(88,'The light of a billion suns.'),(92,'Explosions.'),(94,'Destroying the evidence.'),(96,'Catapults.'),(97,'One trillion dollars.'),(99,'Dying.'),(100,'Silence.'),(102,'YOU MUST CONSTRUCT ADDITIONAL PYLONS.'),(109,'The invisible hand.'),(110,'My inner demons.'),(116,'Teaching a robot to love.'),(119,'All-you-can-eat shrimp for $4.99.'),(121,'Michael Jackson.'),(122,'A really cool hat.'),(125,'Shapeshifters.'),(127,'A disappointing birthday party.'),(129,'My soul.'),(132,'Synergistic management solutions.'),(133,'RoboCop.'),(141,'Object permanence.'),(142,'Lockjaw.'),(146,'Hip hop jewels.'),(155,'A monkey smoking a cigar.'),(157,'A live studio audience.'),(158,'Making a pouty face.'),(160,'Unfathomable stupidity.'),(161,'Sunshine and rainbows.'),(165,'The Three-Fifths compromise.'),(168,'The Great Depression.'),(169,'Emotions.'),(175,'A foul mouth.'),(176,'Flightless birds.'),(177,'Doing the right thing.'),(183,'Raptor attacks.'),(187,'Vigorous jazz hands.'),(199,'The true meaning of Christmas.'),(203,'New Age music.'),(205,'Geese.'),(212,'The American Dream.'),(213,'Puberty.'),(214,'Sweet, sweet vengeance.'),(215,'Winking at old people.'),(216,'The wonders of the Orient.'),(217,'Oompa-Loompas.'),(218,'Authentic Mexican cuisine.'),(219,'Preteens.'),(220,'The Little Engine That Could.'),(225,'Saxophone solos.'),(230,'Vigilante justice.'),(232,'Opposable thumbs.'),(233,'Ghosts.'),(236,'Inappropriate yodeling.'),(238,'Exactly what you’d expect.'),(239,'A time travel paradox.'),(240,'AXE Body Spray.'),(241,'The pirate’s life.'),(242,'Saying “l love you.”'),(246,'A murder most foul.'),(247,'A falcon with a cap on its head.'),(251,'Friction.'),(253,'Fear itself.'),(255,'Yeast.'),(257,'Vikings.'),(258,'The Kool-Aid Man.'),(259,'Hot cheese.'),(262,'The inevitable heat death of the universe.'),(264,'William Shatner.'),(267,'Lady Gaga.'),(278,'Giving 110%.'),(282,'Puppies!'),(285,'Dropping a chandelier on your enemies and riding the rope up.'),(286,'Soup that is too hot.'),(290,'The Big Bang.'),(291,'Switching to Geico®.'),(293,'Dark and mysterious forces beyond our control.'),(294,'Christopher Walken.'),(295,'Count Chocula.'),(296,'The Hamburglar.'),(306,'The glass ceiling.'),(307,'The Hustle.'),(308,'Miley Cyrus at 55.'),(309,'Our first chimpanzee president.'),(310,'Breaking out into song and dance.'),(312,'The Underground Railroad.'),(313,'The Rev. Dr. Martin Luther King, Jr.'),(318,'The cool, refreshing taste of Pepsi®.'),(320,'Hope.'),(327,'Natural selection.'),(330,'Arnold Schwarzenegger.'),(332,'Ronald Reagan.'),(336,'BATMAN.'),(339,'Centaurs.'),(346,'Genuine human connection.'),(354,'Riding off into the sunset.'),(355,'Goblins.'),(356,'Eating the last known bison.'),(357,'Shiny objects.'),(358,'Being rich.'),(360,'World peace.'),(365,'The folly of man.'),(367,'Grandma.'),(371,'Active listening.'),(388,'Passive-aggressive Post-it notes.'),(391,'Your weird brother.'),(394,'Sean Penn.'),(395,'Sean Connery.'),(399,'Genghis Khan.'),(402,'A subscription to Men’s Fitness.'),(403,'The milk man.'),(404,'Friendly fire.'),(405,'Women’s suffrage.'),(412,'The Force.'),(413,'Bees?'),(416,'A micropig wearing a tiny raincoat and booties.'),(419,'Tom Cruise.'),(420,'A balanced breakfast.'),(423,'Humanity.'),(428,'Domino’s Oreo Dessert Pizza.'),(430,'Morgan Freeman’s voice.'),(431,'A middle-aged man on roller skates.'),(432,'Gandhi.'),(433,'The penny whistle solo from “My Heart Will Go On.”'),(434,'Spectacular abs.'),(435,'Keanu Reeves.'),(436,'Child beauty pageants.'),(438,'Bill Nye the Science Guy.'),(439,'Science.'),(442,'Her Majesty, Queen Elizabeth ll.'),(446,'Take-backsies.');
/*!40000 ALTER TABLE `white_cards` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-20 18:18:23
