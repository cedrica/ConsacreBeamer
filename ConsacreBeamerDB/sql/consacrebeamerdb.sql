CREATE DATABASE  IF NOT EXISTS `consacrebeamerdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `consacrebeamerdb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: consacrebeamerdb
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `attachement` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'Sinach'),(3,'Feiert Jesus'),(4,'River Valley Worship'),(5,'Youthfull Praise'),(10,'Elevation worship'),(11,'Hillsong'),(12,'Free Book'),(13,'Planetshakers'),(14,'All sons & Daughters'),(15,'Jawe Thomas'),(16,'Outsbreak band'),(17,'Agatha moses'),(18,'Jaye Thomas'),(19,'Albert Frey'),(20,'Christ Tomlin'),(21,'Island Medley'),(22,'Cantiques africain'),(23,'Phil Thompson'),(24,'Louange congolaise'),(25,'Eben'),(26,'Anja Lehman');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_song`
--

DROP TABLE IF EXISTS `schedule_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_song` (
  `scheduleId` bigint(20) NOT NULL,
  `songId` bigint(20) NOT NULL,
  UNIQUE KEY `UK_3hl4l3162lidop7oypwxx6w2l` (`songId`),
  KEY `FK7o7w0fxk4c6fwy7biapyjp7hf` (`scheduleId`),
  CONSTRAINT `FK3wtn4e1wbm509pkuqc052xb32` FOREIGN KEY (`songId`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FK5a08lksckfe2xgcc9pcdf1vrq` FOREIGN KEY (`scheduleId`) REFERENCES `schedule` (`id`),
  CONSTRAINT `FK7o7w0fxk4c6fwy7biapyjp7hf` FOREIGN KEY (`scheduleId`) REFERENCES `song` (`id`),
  CONSTRAINT `FKa69dpjp8uh3o1j4gmis36yfav` FOREIGN KEY (`songId`) REFERENCES `song` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_song`
--

LOCK TABLES `schedule_song` WRITE;
/*!40000 ALTER TABLE `schedule_song` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `additionalInfo` varchar(255) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `bibleVerse` varchar(255) DEFAULT NULL,
  `ccliNumber` varchar(255) DEFAULT NULL,
  `copyRight` varchar(255) DEFAULT NULL,
  `copyRightTitle` varchar(255) DEFAULT NULL,
  `musik` varchar(255) DEFAULT NULL,
  `nationalCopy` varchar(255) DEFAULT NULL,
  `originalTitle` varchar(255) DEFAULT NULL,
  `rights` varchar(255) DEFAULT NULL,
  `songKey` varchar(255) DEFAULT NULL,
  `songTitle` varchar(255) DEFAULT NULL,
  `tempo` varchar(255) DEFAULT NULL,
  `textFileReference` varchar(255) DEFAULT NULL,
  `traduction` varchar(255) DEFAULT NULL,
  `bookId` bigint(20) NOT NULL,
  `songCategoryName` varchar(255) DEFAULT NULL,
  `songCategoryId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27693599FD7FA5` (`bookId`),
  CONSTRAINT `FK27693599FD7FA5` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`),
  CONSTRAINT `FK4luev5h879sge9b0eovin30hp` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (7,'','','','','reserved','Feiert Jesus','','','','','','Vater des Lichts','','VaterdesLichts.txt','',3,NULL,1),(8,'','','','','reserved','River Valley Worship','','','','','','No wonder','','Nowonder.txt','',4,NULL,1),(9,'','','','','reserved','Sinach','','','','','','The name of Jesus','','ThenameofJesus.txt','',2,NULL,1),(10,'','','','','reserved','Youthfull Praise','','','','','','My hallelujah belongs to You','','MyhallelujahbelongstoYou.txt','',5,NULL,1),(11,'','','','','reserved','I know who i am','','','','','','I know who i am','','Iknowwhoiam.txt','',2,NULL,1),(13,'','','','','gg','gg','','','','','','Deine Liebe trägt mich','','DeineLiebeträgtmich.txt','',3,NULL,1),(14,'','','','','reserved','Elevation worship','','','','','','Here as in heaven','','Hereasinheaven.txt','',10,NULL,1),(15,'','','','','reserved','Hillsong','','','','','','All i need is you','','Allineedisyou.txt','',11,NULL,1),(16,'','','','','reserved','Free Book','','','','','','Holy Gost fire','','HolyGostfire.txt','',12,NULL,1),(17,'','','','','Fullness','Elevation worship','','','','','','Fullness','','Fullness.txt','',10,NULL,1),(18,'','','','','n. reserved','Free book','','','','','','Emmanuel du bist Emmanuel','','EmmanueldubistEmmanuel.txt','',12,NULL,1),(19,'','','','','free','Free book','','','','','','Ayaya Jésus est bon','','AyayaJésusestbon.txt','',12,NULL,1),(20,'','','','','reserved','Turn it up','','','','','','Turn it up','','Turnitup.txt','',13,NULL,1),(21,'','','','','reserved','Great are you Lord','','','','','','Great are you lord','','Greatareyoulord.txt','',14,NULL,1),(22,'','','','','reserved','Eeh my lod is good','','','','','','Eeeh my lord is good','','Eeehmylordisgood.txt','',12,NULL,1),(23,'','','','','reserved','Dir gebührt die Ehre','','','','','','Dir gebührt die Ehre','','DirgebührtdieEhre.txt','',3,'Adoration',2),(24,'','','','','unknow','Open the floorgates of heaven','','','','','','Open the floorgates of heaven','','Openthefloorgatesofheaven.txt','',12,NULL,1),(26,'','','','','reserved','Herr deine Gnade','','','','','','Herr deine Gnade','','HerrdeineGnade.txt','',3,NULL,1),(27,'','','','','reserverd','Herr deine Gnade','','','','','','Tu es Emmanuel','','TuesEmmanuel.txt','',12,NULL,1),(28,'','','','','reserved','Everyday','','','','','','Every day','','Everyday.txt','',11,NULL,1),(29,'','','','','reserved','We love your name','','','','','','We love your name','','Weloveyourname.txt','',15,NULL,1),(30,'','','','','reserved','We loveyou forever Jesus','','','','','','We love you forever','','Weloveyouforever.txt','',12,NULL,1),(31,'','','','','reserverd','Die Freude des Herrn an alle','','','','','','Die Friede des Herrn an alle','','DieFriededesHerrnanalle.txt','',12,NULL,1),(32,'','','','','ok','Gott und König','','','','','','Gott und König','','GottundKönig.txt','',16,NULL,1),(33,'','','','','ok','Let it rain','','','','','','Let it rain','','Letitrain.txt','',12,NULL,1),(34,'','','','','ok','Unser Gott ist ein mächtiger Gott','','','','','','Unser Gott ist ein mächtiger Gott','','UnserGottisteinmächtigerGott.txt','',12,NULL,1),(35,'','','','','ok','I will enter His gates','','','','','','I will enter his gates','','Iwillenterhisgates.txt','',12,NULL,1),(36,'','','','','reserverde','Appel il te répondra','','','','','','Appel il te répondra','','Appelilterépondra.txt','',12,NULL,1),(37,'','','','','reserved','Compile','','','','','','Compile','','Compile.txt','',12,NULL,1),(38,'','','','','reserved','Je reconnais','','','','','','Je reconnais','','Jereconnais.txt','',12,NULL,1),(39,'','','','','ok','Parfum','','','','','','Parfum','','Parfum.txt','',12,NULL,1),(40,'','','','','reserved','Anthem','','','','','','Anthem','','Anthem.txt','',13,NULL,1),(41,'','','','','reserved','Immer mehr von dir','','','','','','Immer mehr von dir','','Immermehrvondir.txt','',3,NULL,1),(42,'','','','','reserved','Mit allem was ich bin','','','','','','Mit allem was ich bin','','Mitallemwasichbin.txt','',16,NULL,1),(43,'','','','','reserved','Ruf zu dem Herrn','','','','','','Ruf zu dem Herrn','','RufzudemHerrn.txt','',3,NULL,1),(44,'','','','','reserved','Agatha Compile','','','','','','Agatha Compile','','AgathaCompile.txt','',17,NULL,1),(45,'','','','','reserved','For your glory','','','','','','For your glory','','Foryourglory.txt','',15,NULL,1),(46,'','','','','reserved','Halleluja Jesus ist Herr','','','','','','Halleluja Jesus ist Herr','','HallelujaJesusistHerr.txt','',12,NULL,1),(47,'','','','','reserved','Comme un torrent','','','','','','Comme un torrent','','Commeuntorrent.txt','',12,NULL,1),(48,'','','','','reserved','Dieu tout-puissant','','','','','','Dieu tout-puissant','','Dieutout-puissant.txt','',12,NULL,1),(49,'','','','','reserved','Was für ein Mensch','','','','','','Was für ein Mensch','','WasfüreinMensch.txt','',19,NULL,1),(50,'','','','','reserved','Que ton nom est merveilleux','','','','','','Que ton nom merveilleux','','Quetonnommerveilleux.txt','',12,NULL,1),(51,'','','','','reserved','Licht dieser Welt','','','','','','Licht dieser Welt','','LichtdieserWelt.txt','',3,NULL,1),(52,'','','','','reserved','So groß ist der Herr','','','','','','So groß ist der Herr','','SogroßistderHerr.txt','',3,NULL,1),(53,'','','','','reserved','Herr, dein Name sei erhöht','','','','','','Herr, dein Name sei erhöht','','Herr,deinNameseierhöht.txt','',3,NULL,1),(54,'','','','','reserved','I believe','','','','','','I believe','','Ibelieve.txt','',21,NULL,1),(55,'','','','','reserved','Nur du allein jesus (Il n´ y a que toi)','','','','','','Nur du allein Jesus (Il n\'y a que Jesus)','','NurdualleinJesus(Iln\'yaqueJesus).txt','',12,NULL,1),(56,'','','','','reserved','Dein Name ist wunderbar','','','','','','Dein name is wunderbar Jesus','','DeinnameiswunderbarJesus.txt','',12,NULL,1),(57,'','','','','reserved','Gloire a l´ agneau (Ehre sei dem Lamm)','','','','','','Gloire a l´ agneau (Ehre sei dem Lamm)','','Gloireal´agneau(EhreseidemLamm).txt','',12,NULL,1),(58,'','','','','reserved','Jesus tu es présent','','','','','','Jesus tu es présent','','Jesustuesprésent.txt','',12,NULL,1),(59,'','','','','reserved','Jesus Jesus heilig und gesalbt','','','','','','Jesus Jesus heilig und gesalbt','','JesusJesusheiligundgesalbt.txt','',3,NULL,1),(60,'','','','','reserved','you are good','','','','','','you are good','','youaregood.txt','',12,NULL,1),(61,'','','','','reserved','what shall i render to the vater','','','','','','What shall i render','','Whatshallirender.txt','',12,NULL,1),(62,'','','','','Reserve','The King of my heart','','','','','','The King of my heart','','TheKingofmyheart.txt','',12,NULL,1),(63,'','','','','reserved','compile maman ndola','','','','','','compile maman ndola','','compilemamanndola.txt','',12,NULL,1),(64,'','','','','xxx','Car ta bonté','','','','','','Car ta bonté','','Cartabonté.txt','',12,NULL,1),(65,'','','','','xxx','Dieu tout puissant DE','','','','','','Dieu tout puissant DE','','DieutoutpuissantDE.txt','',12,NULL,1),(66,'','','','','xxx','Jéhovah est son nom','','','','','','Jéhovah est son nom','','Jéhovahestsonnom.txt','',12,NULL,1),(67,'','','','','xxxx','na yoka yo yesu','','','','','','na yoka yo yesu','','nayokayoyesu.txt','',12,NULL,1),(68,'','','','','xxx','Je chanterai de tout coeur','','','','','','Je chanterai de tout coeur','','Jechanteraidetoutcoeur.txt','',12,NULL,1),(69,'','','','','xxx','aie mon Dieu est bon','','','','','','aie mon Dieu est bon','','aiemonDieuestbon.txt','',12,NULL,1),(70,'','','','','xxx','Onise Iyanu','','','','','','Onise Iyanu','','OniseIyanu.txt','',12,NULL,1),(71,'','','','','xxx','Wir haben Jesus','','','','','','Wir haben Jesus','','WirhabenJesus.txt','',12,NULL,1),(72,'','','','','xxx','Du bist größer als das, was man denkt','','','','','','Du bist größer als das, was man denkt','','Dubistgrößeralsdas,wasmandenkt.txt','',12,NULL,1),(73,'','','','','xxx','Ehre sei dem Lamm','','','','','','Ehre sei dem Lamm','','EhreseidemLamm.txt','',12,NULL,1),(74,'','','','','xxxx','Hallelua eeeh','','','','','','Halleluja eeeh','','Hallelujaeeeh.txt','',12,NULL,1),(75,'','','','','xxx','Oh Jesus seid gelobt','','','','','','Oh Jesus seid gelobt','','OhJesusseidgelobt.txt','',12,NULL,1),(76,'','','','','xxx','Jesus est ici present','','','','','','Jesus est ici present','','Jesusesticipresent.txt','',12,NULL,1),(77,'','','','','xxx','My lord is good','','','','','','My lord is good','','Mylordisgood.txt','',12,NULL,1),(78,'','','','','xxx','Il est bon de louer Dieu','','','','','','Il est bon de louer Dieu','','IlestbondelouerDieu.txt','',12,NULL,1),(79,'','','','','xxx','Wami lele','','','','','','Wami lele','','Wamilele.txt','',12,NULL,1),(80,'','','','','reserved','Tu es le meme / Du bist der selbe','','','','','','Tu es le meme Du bist der selbe','','TueslememeDubistderselbe.txt','',12,NULL,1),(81,'','','','','reserved','je lèves mes mains','','','','','','je lèves mes mains','','jelèvesmesmains.txt','',12,NULL,1),(82,'','','','','reserved','Je veus t´ adorer en Esprit et en verité','','','','','','Je veus t´ adorer en Esprit et en verité','','Jeveust´adorerenEspritetenverité.txt','',12,NULL,1),(83,'','','','','reserved','j aime ta présence','','','','','','j aime ta présence','','jaimetaprésence.txt','',12,NULL,1),(84,'','','','','reserved','je veus t´ adoré','','','','','','je veus t´ adoré','','jeveust´adoré.txt','',12,NULL,1),(85,'','','','','reserved','Halleluja Hosana','','','','','','Halleluja Hosana','','HallelujaHosana.txt','',12,NULL,1),(86,'','','','','reserved','Saint Esprit je t´ adore','','','','','','Saint Esprit je t´ adore','','SaintEspritjet´adore.txt','',12,NULL,1),(87,'','','','','reserved','je sais qu´ il vie','','','','','','je sais qu´ il vie','','jesaisqu´ilvie.txt','',12,NULL,1),(88,'','','','','reserved','Digne tu es digne roi des rois','','','','','','Digne tu es digne roi des rois','','Dignetuesdigneroidesrois.txt','',12,NULL,1),(89,'','','','','reserved','Digne  digne la  terre dit que tu es digne','','','','','','Digne  digne la  terre dit que tu es digne','','Dignedignelaterreditquetuesdigne.txt','',12,NULL,1),(90,'','','','','reserved','Esprit descend sur nous','','','','','','Esprit descend sur nous','','Espritdescendsurnous.txt','',12,NULL,1),(91,'','','','','xxx','Leer','','','','','','Leer','','Leer.txt','',12,NULL,1),(92,'','','','','reserved','Hosana a toi oh mon Dieu','','','','','','Hosana a toi oh mon Dieu','','HosanaatoiohmonDieu.txt','',12,NULL,1),(93,'','','','','reserved','il est le meme','','','','','','il est le meme','','ilestlememe.txt','',12,NULL,2),(94,'','','','','reserved','Siyahamba','','','','','','Siyahamba','','Siyahamba.txt','',12,NULL,1),(95,'','','','','reserved','We love Your Nam','','','','','','We love Your Nam','','WeloveYourNam.txt','',15,NULL,2),(96,'','','','','reserved',' My worship','','','','','','My worship','','Myworship.txt','',23,NULL,2),(97,'','','','','reserved','Narekele mo','','','','','','Narekele mo','','Narekelemo.txt','',12,NULL,2),(98,'','','','','reserved','Nzambe na nguya','','','','','','Nzambe na nguya A kumama Yesu','','NzambenanguyaAkumamaYesu.txt','',24,NULL,1),(99,'','','','','reserved','Nkembo na yo','','','','','','Nkembo na yo','','Nkembonayo.txt','',24,NULL,1),(100,'','','','','reserved','Papa na lola yo kumama','','','','','','Papa na lola yo kumama','','Papanalolayokumama.txt','',24,NULL,1),(101,'','','','','reserved','You are the reason','','','','','','You are the reason','','Youarethereason.txt','',12,NULL,2),(102,'','','','','ALLELUYA AMEN','ALLELUYA AMEN','','','','','','ALLELUYA AMEN','','ALLELUYAAMEN.txt','',12,NULL,2),(103,'','','','','xxxxscv','Alleluya Jesus ist Herr','','','','','','Alleluya Jesus ist Herr','','AlleluyaJesusistHerr.txt','',12,NULL,1),(104,'','','','','Denn du bist Gott','Denn du bist Gott','','','','','','Denn du bist Gott','','DenndubistGott.txt','',12,NULL,1),(105,'','','','','reserved','Moyo wangu','','','','','','Moyo wangu - shanguilia','','Moyowangu-shanguilia.txt','',12,NULL,1),(106,'','','','','reserved','Heilig bist du Herr ','','','','','','Heilig bist du Herr','','HeiligbistduHerr.txt','',16,NULL,2),(107,'','','','','reserved','Segura','','','','','','Segura','','Segura.txt','',12,NULL,1),(108,'','','','','reserved','Der Himmel zeugt ','','','','','','Der Himmel zeugt ','','DerHimmelzeugt.txt','',3,NULL,2),(109,'','','','','reserved','Hakuna Mungu kama wewe','','','','','','Hakuna Mungu kama wewe','','HakunaMungukamawewe.txt','',24,NULL,1),(110,'','','','','reserved','Jesus, du allein bist genug,','','','','','','Jesus, du allein bist genug,','','Jesus,dualleinbistgenug,.txt','',26,NULL,2),(111,'','','','','reserve','Mon Dieu est bon (Mein Gott ist gut)','','','','','','Mon Dieu est bon (Mein Gott ist gut)','','MonDieuestbon(MeinGottistgut).txt','',22,NULL,1),(112,'','','','','reserved','Elevons Jesus (Erhebe Jesus)','','','','','','Elevons Jesus (Erhebe Jesus)','','ElevonsJesus(ErhebeJesus).txt','',22,NULL,1),(113,'','','','','reserved','Zambe napesayo nini','','','','','','Zambe napesayo nini','','Zambenapesayonini.txt','',12,NULL,2),(114,'','','','','reserved','Na pesa yo nini','','','','','','Na pesa yo nini','','Napesayonini.txt','',12,NULL,2),(115,'','','','','reserved','Na yoka yo','','','','','','Na yoka yo','','Nayokayo.txt','',12,NULL,2),(116,'','','','','reserved','Nothing but the blood of Jesus','','','','','','Nothing but the blood of Jesus','','NothingbutthebloodofJesus.txt','',12,NULL,2),(117,'','','','','German Song','Über alle Welt','','','','','','Über alle Welt','','ÜberalleWelt.txt','',3,NULL,1),(118,'','','','','Todd Dulaney','The anthem','','','','','','The anthem ','','Theanthem.txt','',5,NULL,2),(119,'','','','','Africa ','Tu es l\'Eternel le seul vrai Dieu','','','','','','Tu es l\'Eternel le seul vrai Dieu','','Tuesl\'EternelleseulvraiDieu.txt','',12,NULL,1),(120,'','','','','Africa','Seigneur Jesus','','','','','','Seigneur Jesus, toi seul es mon Dieu','','SeigneurJesus,toiseulesmonDieu.txt','',12,NULL,1),(121,'','','','','Africa','Jesus est plus haut','','','','','','Jesus est plus haut !','','Jesusestplushaut!.txt','',12,NULL,1),(122,'','','','','Africa','En ce moment c\'est Jesus qui regne','','','','','','En ce moment c\'est Jesus qui regne','','Encemomentc\'estJesusquiregne.txt','',12,NULL,1),(123,'','','','','Africa','Lass uns Gott preisen, er ist lebendig','','','','','','Lass und Gott preisen, er ist lebendig','','LassundGottpreisen,eristlebendig.txt','',12,NULL,1),(124,'','','','','reserved','Dieu n´ est pas mort','','','','','','Dieu n\'est pas mort','','Dieun\'estpasmort.txt','',12,NULL,1),(125,'','','','','reserved','Jésus il t´ appel','','','','','','Jésus il t\'appel','','Jésusilt\'appel.txt','',12,NULL,1),(126,'','','','','reserved','Qui sera mon témoin','','','','','','Qui sera mon témoin','','Quiseramontémoin.txt','',12,NULL,1),(127,'','Mercy Chinwo','','','Jesus','Excess Love','','','','','','Excess Love','','ExcessLove.txt','',10,NULL,1),(128,'','William McDowell','','','Jesus','I give myself away','','','','','','I give myself away','','Igivemyselfaway.txt','',10,NULL,2),(129,'','Donnie McClurkin','','','Jesus','Create in me a clean heart','','','','','','Create in me a clean heart','','Createinmeacleanheart.txt','',3,NULL,2),(130,'','','','','Jésus m´ a délivré','Jésus m´ a délivré','','','','','','Jésus m\'a délivré','','Jésusm\'adélivré.txt','',12,NULL,1);
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song_attachment`
--

DROP TABLE IF EXISTS `song_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song_attachment` (
  `songId` bigint(20) NOT NULL,
  `attachmentId` bigint(20) NOT NULL,
  UNIQUE KEY `attachmentId` (`attachmentId`),
  UNIQUE KEY `UK_90x9b4djd1fcnp1iua1ic67y5` (`attachmentId`),
  KEY `FKE8BBBE8D4DF4FB99` (`attachmentId`),
  KEY `FKE8BBBE8DB70718FD` (`songId`),
  CONSTRAINT `FK93col205i68dsjxiqcc8ggli9` FOREIGN KEY (`songId`) REFERENCES `song` (`id`),
  CONSTRAINT `FKE8BBBE8D4DF4FB99` FOREIGN KEY (`attachmentId`) REFERENCES `attachment` (`id`),
  CONSTRAINT `FKE8BBBE8DB70718FD` FOREIGN KEY (`songId`) REFERENCES `song` (`id`),
  CONSTRAINT `FKpck08umfa2kbrkx9mpjicvbb5` FOREIGN KEY (`attachmentId`) REFERENCES `attachment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song_attachment`
--

LOCK TABLES `song_attachment` WRITE;
/*!40000 ALTER TABLE `song_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `song_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'consacrebeamerdb'
--

--
-- Dumping routines for database 'consacrebeamerdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-15 23:03:04
