-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: craftycat
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `CartID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `ProductID` int DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  PRIMARY KEY (`CartID`),
  KEY `UserID` (`UserID`),
  KEY `ProductID` (`ProductID`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `CategoryID` int NOT NULL,
  `CategoryName` varchar(255) NOT NULL,
  `Description` text,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Art','Art related products'),(2,'Beads','Beads Related Products'),(3,'Pottery','Pottery related Products'),(4,'DIY','DIY related Products');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `userID` int DEFAULT NULL,
  `sellerID` int DEFAULT NULL,
  `productID` int DEFAULT NULL,
  `orderDateTime` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `isPaid` tinyint(1) DEFAULT NULL,
  `orderStatus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,10,1,5,'2023-10-27 12:30:47','Mohammadpur, Dhaka',1,'DELIVERED');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Description` text,
  `Price` decimal(10,2) NOT NULL,
  `StockQuantity` int NOT NULL,
  `CategoryID` int DEFAULT NULL,
  `SellerID` int DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ProductID`),
  KEY `CategoryID` (`CategoryID`),
  KEY `SellerID` (`SellerID`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`SellerID`) REFERENCES `sellers` (`SellerID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (4,'Starry night','The Starry Night (Dutch: De sterrennacht) is an oil-on-canvas painting by the Dutch Post-Impressionist painter Vincent van Gogh. But this one has been painted by picchi',2000.00,5,1,1,'art.png'),(5,'Monalisa','A masterpiece by Leonardo Da Vinci. Grab it as soon as you can...',500.00,4,1,1,'Ami.jpg'),(6,'dummy','dummy description',250.00,3,2,4,'art.png'),(7,'dummyProduct','Dummy description',1450.00,37,4,1,'art.png'),(8,'dummyProduct','Dummy description',1079.00,9,2,4,'art.png'),(9,'dummyProduct','Dummy description',1079.00,9,2,4,'art.png'),(10,'dummyProduct','Dummy description',1079.00,9,2,4,'art.png'),(11,'Polu Senior','Penguin er baba',1234.00,79,4,3,'art.png'),(12,'polu','Penguin',1234.00,85,4,3,'art.png'),(13,'polu','Penguin',1234.00,85,4,3,'art.png'),(14,'polu','Penguin',1234.00,85,4,3,'art.png'),(15,'polu','Penguin',1234.00,85,4,3,'art.png'),(16,'testingImage','testing description',1234.00,321,2,1,'art.png'),(17,'testImage2','test description 2',231.00,23,1,1,'art.png'),(18,'testingImage3','test description',1234.00,3212,2,1,'art.png'),(19,'testing image 3','testing description 3',3213.00,21,2,1,'art.png'),(20,'testing image 4','testing description 4',1234.00,321,2,3,'art.png'),(21,'testing image 5','test description 5',3214.00,2131,2,3,'art.png'),(23,'sample','sample description',1234.00,21,3,1,'366244259_825626305727728_941007905534483537_n.png');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellers`
--

DROP TABLE IF EXISTS `sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sellers` (
  `SellerID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `ShopName` varchar(255) NOT NULL,
  PRIMARY KEY (`SellerID`),
  UNIQUE KEY `UserID` (`UserID`),
  CONSTRAINT `sellers_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellers`
--

LOCK TABLES `sellers` WRITE;
/*!40000 ALTER TABLE `sellers` DISABLE KEYS */;
INSERT INTO `sellers` VALUES (1,11,'pixie_dust'),(2,12,'tute_makaan'),(3,14,'PoluKaPicchi'),(4,18,'dummy shop'),(5,36,'dummyshop2'),(6,40,'dummyshop3'),(7,42,'dummyshop4');
/*!40000 ALTER TABLE `sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `AddressLine1` varchar(255) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `RegistrationDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `HashedPassword` varchar(255) DEFAULT NULL,
  `Salt` varchar(255) DEFAULT NULL,
  `UserRole` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (10,'santo','santo@gmail.com','Samiul','Hossen','Dhaka Uddan, Mohammadpur, Dhaka','Dhaka','Bangladesh','123456789','2023-10-24 00:39:19','4548c41cb368d053615a54db2f778d4b917bbbd7f509b770e0775f30fb8f38f10217cdbad7c39dfad103e145f7e7e1005c57a9626c71aa52bcb138ebc72a2450','sqdz1tlYeFVrBN0DR54FGA==','Buyer'),(11,'mojid','mojid@gmail.com','Mridula','Mozid','Mohammadpur, Dhaka','Dhaka','Bangladesh','0123456789','2023-10-24 00:41:36','4548c41cb368d053615a54db2f778d4b917bbbd7f509b770e0775f30fb8f38f10217cdbad7c39dfad103e145f7e7e1005c57a9626c71aa52bcb138ebc72a2450','sqdz1tlYeFVrBN0DR54FGA==','Seller'),(12,'deewani','kichuekta@gmail.com','FirstDeewani','LastDeewani','Teri, Dil Stree','Dildar','Tu','0123456789','2023-10-24 14:57:50','88d68e59bfbddd80bd77b47903f41391a037cb72284041b1aeb5eda195b2c609f052a297e9bf0aeb4b00420c3999c136893ebd9407e66cdc56a3efc55410fd25','4KEktKZvYGXYeN5I8jfRWw==','Seller'),(13,'dummy','dummy@dummy.com','Dummy','Dummy','Dummy Address','Dummy City','Dummy State','123456789','2023-10-24 17:19:05','88d68e59bfbddd80bd77b47903f41391a037cb72284041b1aeb5eda195b2c609f052a297e9bf0aeb4b00420c3999c136893ebd9407e66cdc56a3efc55410fd25','4KEktKZvYGXYeN5I8jfRWw==','Buyer'),(14,'picchi','picchi@gmail.com','Nishat','Tasneem','Mirpur, Dhaka','Dhaka','Bangladesh','123456789','2023-10-25 00:21:45','dfeeab250cb0875f9432da8e27d37810d61fb29185feba5a8a990076e3e9900f97125028bdd849185eac12c29c8cc0ba568b1a87bbacd65e3d7360f7615b8739','TCvMa7zmi8cVEq/4FsDufg==','Seller'),(15,'dummyBuyer','dummy@gmail.com','Dummy','Buyer','Kichu Ekta','Oitoh','Are Oikhane','123456789','2023-10-25 00:27:33','dfeeab250cb0875f9432da8e27d37810d61fb29185feba5a8a990076e3e9900f97125028bdd849185eac12c29c8cc0ba568b1a87bbacd65e3d7360f7615b8739','TCvMa7zmi8cVEq/4FsDufg==','Buyer'),(18,'dummySeller','dummy@mail.com','dummy','seller','are dhur','eida bujhos na','oi elakatei, haate baame','123456798','2023-10-25 00:31:21','87b3c24c30e6800a76df5694486d1a9902bc2c1c74f1c140fe617df0bc3b28e5abe3ea10d18ceb8bb4b91ea0c42582ed050e0db3df33c7c7951830760a949b9e','bW86o8yYaRXvTQmLpnBqNQ==','Seller'),(36,'dummySeller2','dummy@dummy.comc','Dummy','Seller2','Dummy Adress','Dummy City','Dummy State','123456789','2023-10-25 01:33:49','0bfbf5c1779e86cc74f169657a4caa2dd928df893bc035d4da2847d67890e70be11ced9a97f644ee35732d8addd5c25e64c2cbb86f011a1477a71bd3199fba97','5E0nP2wtX2AcqyRARoEsHg==','Seller'),(37,'dummyBuyer2','dummy@gmail.xyz','Dummy','Buyer2','Dummy Adress','Dummy City','Dummy State','123456789','2023-10-25 01:38:01','1ce3bd83e4a28960a3d12298bb1247cff76fc72bb08540280d8807766d295efe8981cef07366e60324c68a2f991bc10c195a452e69cd201a3c3e8c42f4592571','wyQOeeWPzqwGYIQQsbViow==','Buyer'),(39,'dummyBuyer3','dummy@dummy.xyz','Dummy','Buyer3','Dummy Adress','Dummy City','Dummy State','123456789','2023-10-25 01:43:57','f302465d8ae5a1409bb743f049140e863ea04fa3dda06b67fe4e3f24e8d86e27e7eb9e603f6766ef1a441a1a0a5b1884016279cd7e92e82e300d32dcf8cf5d93','wyQOeeWPzqwGYIQQsbViow==','Buyer'),(40,'dummySeller3','dummy@dummy.comz','Dummy','Seller3','Dummy Adress','Dummy City','Dummy State','123456789','2023-10-25 01:46:25','e641d65d51c237d18f41111324f19d9f6fab5e736dc0dbaaa454154c64c9f68e98bd72a04c99e252c1469ed4f1e1f9959c5cd878b16ec7ddd6a7c443e34e8c48','wyQOeeWPzqwGYIQQsbViow==','Seller'),(41,'dummyBuyer4','dummy@yahoo.com','Dummy','Buyer4','Dummy Adress','Dummy City','Dummy State','123456789','2023-10-25 01:49:29','580b6cce9e45c7b56cde43551130f393f4fc8134b4878ba4c801fa11eee3c46b2ae1c619cc9e146ddd60df3eacfa5f659c6df05117a06aa5e3d2a16604e938e7','7MbcBit5YQZ/6nAE5ySYhw==','Buyer'),(42,'dummySeller4','dummy@yahoo.xyz','Dummy','Seller4','Dummy Adress','Dummy City','Dummy State','123456789','2023-10-25 01:50:24','57df2fbe4d694fc3eb08a0f81d5f6d9c6c139978bd7a32e98d0cb5e70c3901d78a31baf62ae016d8cb88d4f2ec8132953d98bdcaac74ffbf02699b03b92b7e54','7MbcBit5YQZ/6nAE5ySYhw==','Seller'),(43,'dummybuyer5','dummy@kaboom.com','Dummy','Buyer5','Dummy Address','Dummy City','Dummy State','123456789','2023-10-25 02:43:19','ae44bbde704ef1bd21ec8b50787ee5e6aaf78b6d698d2caeda021f51e5b834f347b07d8ab20cca8acf85f86b2aaba9c8fc4e03500ef37cde95f3f3ea23f0d546','RXg2pJ22Y6KspYJK3qyScQ==','Buyer');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-29  3:07:06
