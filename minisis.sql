-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: minisis
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(25) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `address` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=70003 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (70001,'Program','Director','dir@gmail.com','514-5135384',70000013,'S-EV 3152, 1455 De Maisonneuve Blvd. W.'),(70002,'Halina','Monkiewicz','halina@cs.concordia.ca','514-848-2424 ext 3043',70000012,'S-EV 3153, 1455 De Maisonneuve Blvd. W.\r Montreal, QC H3G 1M8\r Canada');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `building` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `campus_short` varchar(10) NOT NULL,
  `campus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,'EV','Engineering, Computer Science and Visual Arts Integrated Complex','1515 Rue Ste-Catherine O, Montréal, QC H3G 2W1','SGW','Sir George Williams campus'),(2,'H','Henry F. Hall Building','1455 Boulevard de Maisonneuve O, Montréal, QC H3G 1M8','SGW','Sir George Williams campus'),(3,'MB','John Molson Building','1450 Guy Street, Montreal, Quebec, H3H 0A1','SGW','Sir George Williams campus');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `course_code` varchar(20) NOT NULL,
  `description` mediumtext NOT NULL,
  `level` varchar(5) DEFAULT NULL,
  `units` tinyint(4) NOT NULL,
  `program` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,2,'Software Measurement','6611','Role of measurement in Software Engineering, theoretical, technical and managerial views on software measurement. Representational theory of measurement. Theoretical validation of software measurement. Measurement program: goal-driven approach. Collecting and analyzing software engineering data. Software quality modelling and measuring. Testing and measurement. Reliability models. Functional size measurement methods. Effort estimation models and their usage in project management. Software measurement standards. Tool support. A project.','G',4,'SOEN'),(2,2,'Software Systems Requirements Specification','6481','The requirements engineering (RE) process. Requirements engineering in different software lifecycle models. Problem analysis. Requirements elicitation. Requirements evaluation. Inconsistency management. Risk analysis. Requirements prioritization and negotiation. Requirements specification: natural language documentation, IEEE and ISO standards. Use cases. Agile processes and user stories. Introduction to formal specification: logics, formal languages. Requirements quality assurance. RE tools. Requirements evolution. Traceability. Domain modelling: UML, ontologies, domain-specific languages. Modelling behaviour. Acceptance criteria. Test cases. Cost models. A project.','G',4,'SOEN'),(3,2,'Software Project Management','6841','Fundamental concepts of management activities, demonstrating how it can relate to software engineering and how the two can be mutually supportive throughout software development and maintenance. Overview of object-oriented development; software development processes; quality considerations; activity planning; risk management; monitoring and control; maintenance and evolution; issues of professional ethics and practice, and legal issues. A project.','G',4,'SOEN'),(4,1,'Compiler Design','6421','Compiler organization and implementation: lexical analysis and parsing, syntax-directed translation, code optimization. Run-time systems. A project.','G',4,'COMP'),(5,1,'Artificial Intelligence','6721','Scope of AI. First order logic. Automated reasoning. Search and heuristic search. Game-playing. Planning. Knowledge representation. Probabilistic reasoning. Introduction to machine learning. Introduction to natural language processing. A project.','G',4,'COMP'),(6,1,'Algorithm Design Techniques','6651','Mathematical preliminaries; Empirical and theoretical measures of algorithm efficiencies; Optimization and combinatorial techniques and algorithms including greedy algorithms, dynamic programming, branch-and-bound techniques and graph network algorithms; Amortized complexity analysis; String matching algorithms; NP-complete problems and approximate solutions; Probabilistic algorithms. A project.','G',4,'COMP'),(7,1,'Image Processing','6771','Digital image fundamentals; image enhancement: histogram processing, filtering in the spatial domain, filtering in the frequency domain; image restoration and reconstruction; image segmentation: line detection, Hough transform, edge detection and linking, thresholding, region splitting and merging; image compression; introduction to wavelet transform and multi-resolution processing. A project. Laboratory: two hours per week.','G',4,'COMP'),(8,1,'Distributed System Design','6231','Principles of distributed computing: scalability, transparency, concurrency, consistency, fault tolerance. Client-server interaction technologies: interprocess communication, sockets, group communication, remote procedure call, remote method invocation, object request broker, CORBA, web services. Distributed server design techniques: process replication, fault tolerance through passive replication, high availability through active replication, coordination and agreement transactions and concurrency control. Designing software fault-tolerant highly available distributed systems using process replication. Laboratory: two hours per week.','G',4,'COMP'),(9,1,'Computer Networks and Protocols','6461','Direct link networks: encoding, framing, error detection, flow control, example networks. Packet switching and forwarding: bridges, switches. Internetworking: Internet Protocol, routing, addressing, IPv6, multicasting, mobile IP. End-to-end protocols: UDP, TCP. Network security concepts. Application-level protocols. Laboratory: two hours per week.','G',4,'COMP'),(10,1,'Advanced Database Technology and Applications','6521','Review of standard relational databases, query languages. Query processing and optimization. Parallel and distributed databases. Information integration. Data warehouse systems. Data mining and OLAP. Web databases and XML Active and logical databases, spatial and multimedia data management. Laboratory: Two hours per week.','G',4,'COMP'),(11,2,'Advanced Programming Practices','6441','Problems of writing and managing code. Managing code complexity and quality through a programming process. Coding conventions. Inline software documentation. Software configuration management. Tools and techniques for testing software. Multithreading concurrency. Code reuse in software development. Quality in coding, fault tolerance. A project. Laboratory: two hours per week.','G',4,'SOEN'),(12,2,'Advanced Software Architectures','6471','Study of architectural view models, architectural styles and frameworks; Architectural Description Languages (ADLs) and Architectural Specification Languages (ASLs); Architectural design patterns; advanced topics such as Model Driven Architecture (MDA), Service Oriented Architecture (SOA), extensible frameworks (like the OSGi specification and framework), Software Product Line Engineering (SPLE), and architectures in support of cloud computing. A project.','G',4,'SOEN'),(13,2,'Software Comprehension and Maintenance','6431','The course addresses both technical and managerial views of software comprehension and software maintenance issues. Topics covered in this course include: cognitive models, software visualization, CASE tools, reverse engineering, static and dynamic source code analysis, software configuration management, and introduction to current research topics in software maintenance and program comprehension. A project.','G',4,'SOEN'),(14,2,'Software Design Methodologies','6461','Introduction to software design processes and their models. Representations of design/architecture. Software architectures and design plans. Design methods, object-oriented application frameworks, design patterns, design quality and assurance, coupling and cohesion measurements, design verification and documentation. A design project.','G',4,'SOEN'),(15,3,'Software Quality Assurance ','6260','Quality assurance, quality factors, components of a software quality assurance system, contract review, software development and quality plans, activities and alternatives, integration of quality activities in a project lifecycle, reviews, software inspection, software verification, testing processes, static analysis, control-flow analysis, data-flow analysis, control-flow testing, loop testing, data-flow testing, transaction-flow testing, domain testing, type-based analysis, dynamic analysis, usage models, operational profiles, result and defect analysis, reliability, performance analysis, maintenance and reverse engineering, case tools and software quality assurance. A project.','G',4,'INSE'),(16,3,'Total Quality Project Management','6230','Role of a project manager, learning and applications, project management processes, project management in quality initiatives, intellectual property protection, customer project management and critical-to-quality requirements, project planning and execution, team formation, goals, roles, procedures and interpersonal relationship, types of teams, creating a project plan, project tracking, project compliance requirements, sourcing and supplier qualification, government contract data rights, government property, risk management process, action planning, project communication, customer, team and stakeholder communications, communication planning and strategy, web-based collaboration, project management software tools. A project.','G',4,'INSE'),(17,3,'Wireless Network Security','6190','Introduction to wireless network security; security issues in cellular networks; authentication/key management in wireless LAN; secure handover; security in mobile IP; security issues in mobile ad-hoc networks: trust establishment, secure routing, anonymity; anonymous sensory data collection; privacy for smartphone applications. A project.','G',4,'INSE'),(18,3,'Database Security and Privacy','6160','Access control in relational databases; grant/revoke model; security by views; query modification; Oracle VPD; auditing in databases; information warfare in databases; multi-level database security; polyinstantiation and covert channel; statistical database security; inference control; security by auditing; microdata security; random perturbation; outsourced database security, encrypted databases; SQL injection attack; anomaly detection in databases; data privacy, P3P; Hippocratic databases; perfect secrecy-based privacy; k-anonymity model; l-diversity; data utility measure, data release with public algorithms, multi-party privacy preserving computation; privacy in OLAP. A project.','G',4,'INSE'),(19,3,'Foundations of Cryptography','6110','Introduction to cryptography and cryptanalysis, classical ciphers, number-theoretic reference problems, the integer factorization problem, the RSA problem, the quadratic residuosity problem, computing square roots in Zn, the discrete logarithmic problem, the diffie-hellman problem, pseudorandom bits and sequences, stream ciphers: feedback shift registers, LFSRs, RC4. Block Ciphers: SPN and Fiestel structures, DES, AES, linear cryptanalysis, differential cryptanalysis, side channel attacks, ciphertext indistinguishability, attack analysis, IND-CPA, IND-CCA, IND-CCA2, public key encryption: RSA, Rabin, ElGamal, elliptic curves cryptography, hash functions: Un-keyed hash functions, MACs, Attacks, Digital signatures: RSA, Fiat-Shamir, DSA, public key infrastructure, key management, efficient implementation of ciphers, zero-knowledge proof. A project.','G',4,'INSE'),(20,3,'Total Quality Methodologies in Engineering','6210','Methodologies for quality engineering: six sigma, ACE (Achieving Competitive Excellence), Lean engineering, ISO9000 series; comparative study, quality clinic process charts, relentless root cause analysis, mistake proofing, market feedback analysis, process improvement and waste elimination, visual control, standard work and process management, process certification, setup reduction, total productive maintenance, DMAIC and DMADV processes, define phase, project charter, project scoping and planning, measure phase, critical to quality requirements, quality functional deployment, analyze phase, functional and process requirements, design requirements, design concepts, high-level design capability elaboration and evaluation, design phase, detailed design capability elaboration and evaluation, failure mode and effects analysis, control and verification plans, verify phase, pilot-scale processes, pilot testing and evaluation, implementation planning, full-scale processes, start-up and testing, performance evaluation, turnover to operations and maintenance, transition to process management, project closure. A project.','G',4,'INSE'),(21,1,'Mathematics for Computer Science','232','','U',3,'COMP'),(22,1,'Object-Oriented Programming I','248','','U',3,'COMP'),(23,1,'System Hardware','228','','U',3,'COMP'),(24,1,'Probability and Statistics for Computer Science','233','','U',3,'COMP'),(25,2,'Distributed Systems','423','','U',4,'SOEN'),(26,2,'Web Services and Applications','487','','U',4,'SOEN'),(27,2,'Embedded Systems and Software','422','','U',4,'SOEN'),(28,2,'Web Programming','287','','U',4,'SOEN');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_details`
--

DROP TABLE IF EXISTS `course_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `instructor_id` int(11) NOT NULL,
  `term_id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `class_capacity` int(11) NOT NULL,
  `class_availability` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=56 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_details`
--

LOCK TABLES `course_details` WRITE;
/*!40000 ALTER TABLE `course_details` DISABLE KEYS */;
INSERT INTO `course_details` VALUES (10,1,7,1003,13,7,60,60),(15,4,1,1006,13,8,50,50),(8,16,12,1006,13,5,75,74),(7,18,14,1006,13,2,50,50),(6,15,11,1006,13,2,50,49),(22,1,7,1001,6,8,50,50),(19,12,8,1002,13,5,75,75),(21,1,8,1005,6,5,75,75),(20,1,8,1006,6,5,75,73),(23,2,9,1001,13,9,70,70),(24,2,7,1003,13,10,100,100),(25,11,9,1001,13,10,100,100),(26,14,7,1001,13,10,100,100),(27,15,11,1007,13,10,100,100),(28,15,11,1003,13,10,100,100),(29,15,14,1003,13,10,100,100),(30,22,17,1003,13,10,100,100),(31,22,17,1007,13,8,50,50),(32,23,14,1007,13,4,100,100),(33,23,20,1007,13,12,80,80),(34,3,16,1007,13,6,50,48),(35,25,19,1007,13,6,50,50),(36,1,19,1007,13,6,50,49),(37,17,12,1007,13,9,70,70),(38,12,7,1007,13,9,70,68),(39,10,2,1007,13,6,50,50),(40,10,2,1005,13,6,50,50),(41,10,2,1003,13,6,50,50),(42,7,1,1003,13,8,50,50),(43,7,4,1006,13,8,50,50),(44,22,4,1006,13,8,50,50),(45,5,3,1006,13,8,50,50),(46,5,3,1004,13,8,50,50),(47,5,3,1007,13,8,50,50),(48,21,6,1007,13,8,50,50),(54,2,2,1007,13,2,50,48),(50,5,6,1003,13,8,50,50),(51,5,1,1003,13,8,50,50),(52,23,18,1005,13,8,50,50),(53,14,18,1005,13,8,50,50),(55,13,5,1007,13,7,60,60);
/*!40000 ALTER TABLE `course_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_schedule`
--

DROP TABLE IF EXISTS `course_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_detail_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_schedule`
--

LOCK TABLES `course_schedule` WRITE;
/*!40000 ALTER TABLE `course_schedule` DISABLE KEYS */;
INSERT INTO `course_schedule` VALUES (5,27,11),(6,31,12),(7,32,13),(8,33,21),(9,34,22),(10,35,23),(11,36,24),(12,37,25),(13,38,13),(14,39,14),(15,47,15),(16,48,24),(17,6,13),(18,20,20),(19,44,21),(20,7,20),(21,8,25),(22,45,23),(23,54,24),(24,10,24),(25,34,24),(26,19,24);
/*!40000 ALTER TABLE `course_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `term_id` int(11) NOT NULL,
  `gpa` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (13,14,1000004,1001,4),(12,11,1000004,1001,3.3),(20,16,1000005,1006,NULL),(23,15,1000005,1006,NULL);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL DEFAULT 'NULL',
  `emp_id` int(8) NOT NULL,
  `address` mediumtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=80001 DEFAULT CHARSET=latin1 PACK_KEYS=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES (1,'Dr. Sabine','Bergler','bergler@cse.concordia.ca',700001,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(2,'Dr. Lata ','Narayanan','lata@cs.concordia.ca',700002,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(3,'Dr. Tien ','Bui','bui@cse.concordia.ca',700003,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(4,'Dr. Rajagopalan ','Jayakumar','Rajagopalan@encs.concordia.ca',700004,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(5,'Dr. Chadi ','Assi','assi@encs.concordia.ca',700005,'1455 de Maisonneuve Blvd West\nMontreal, Quebec, Canada\nH3G 1M8 '),(6,'Dr. Gosta ','Grahne','gosta@encs.concordia.ca',700006,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(7,'Dr. Juergen ','Rilling','Rilling@encs.concordia.ca',700007,'1455 de Maisonneuve Blvd West\nMontreal, Quebec, Canada\nH3G 1M8 '),(8,'Prof. Pankaj ','Kamthan','kamthan@encs.concordia.ca',700008,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(9,'Dr. Rene ','Witte ','rene@encs.concordia.ca',700009,'1455 de Maisonneuve Blvd West\nMontreal, Quebec, Canada\nH3G 1M8 '),(10,'Prof. Fereshteh ','Mafakheri','fereshteh@encs.concordia.ca',700010,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(11,'Prof. Rachida ','Dssouli','Dssouli@encs.concordia.ca',700011,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(12,'Prof. Ayda ','Basyouni','avdabas@encs.concordia.ca',700012,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(13,'Prof. Jeremy ','Clark','Clark@encs.concordia.ca',700013,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(14,'Prof. Nizar ','Bouguila','Bouguila@encs.concordia.ca',700014,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(15,'Prof. Lingyu ','Wang','Wang@encs.concordia.ca',700015,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(16,'Prof. Olga ','Ormandjieva','Olga@encs.concordia.ca',700016,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(17,'Prof. Constantinos ','Constantinides','Constantinos@encs.concordia.ca',700017,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(18,'Prof. Terrill ','Fancott ','Fancott@encs.concordia.ca',700018,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(19,'Prof. Yuhong ','Yan','Yan@encs.concordia.ca',700019,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(20,'Prof. Sudhir ','Mudur','Mudur@encs.concordia.ca',700020,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),(21,'Prof. Eusebius ','Doedel','Doedel@encs.concordia.ca',700021,'1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 ');
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `amount_due` double NOT NULL,
  `semester_fee` double DEFAULT NULL,
  `term` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (3,1000004,0,4000,'Winter 2016'),(4,1000004,0,4000,'Summer 2016'),(5,1000004,0,4000,'Fall 2016'),(6,1000004,0,4000,'Winter 2017'),(7,1000004,0,4000,'Fall 2017'),(8,1000004,0,4000,'Summer 2017'),(10,1000005,0,4000,'Winter 2017'),(11,1000005,0,4000,'Fall 2017'),(12,1000005,0,4000,'Summer 2017'),(13,1000005,0,4000,'Winter 2018'),(30,1000003,0,4000,'Fall 2016'),(29,1000003,0,5000,'Summer 2016'),(28,1000003,4000,6000,'Winter 2018'),(27,1000003,0,6000,'Summer 2017'),(26,1000003,0,6000,'Fall 2017'),(21,1000006,0,4000,'Fall 2017'),(22,1000006,0,4000,'Summer 2017'),(23,1000006,4000,4000,'Winter 2018'),(24,1000006,0,4000,'Summer 2016'),(25,1000006,0,4000,'Fall 2016');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pre_requisite`
--

DROP TABLE IF EXISTS `pre_requisite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pre_requisite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL,
  `program` varchar(45) DEFAULT NULL,
  `course_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pre_requisite`
--

LOCK TABLES `pre_requisite` WRITE;
/*!40000 ALTER TABLE `pre_requisite` DISABLE KEYS */;
INSERT INTO `pre_requisite` VALUES (1002,1000006,'SOEN',487),(1003,1000006,'INSE',6260);
/*!40000 ALTER TABLE `pre_requisite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_details_id` int(11) NOT NULL,
  `status` enum('enrolled','dropped','completed','disc') NOT NULL DEFAULT 'enrolled',
  `student_course` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `student_course_UNIQUE` (`student_course`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (14,1000004,10,'completed','1000004-10'),(16,1000004,19,'completed','1000004-19'),(19,1000004,23,'completed','1000004-23'),(21,1000004,25,'completed','1000004-25'),(22,1000004,26,'completed','1000004-26'),(23,1000004,38,'dropped','1000004-38'),(24,1000004,45,'dropped','1000004-45'),(30,1000005,20,'enrolled','1000005-20'),(27,1000004,6,'dropped','1000004-6'),(29,1000005,6,'enrolled','1000005-6'),(31,1000005,8,'enrolled','1000005-8'),(32,1000004,34,'dropped','1000004-34'),(33,1000004,36,'dropped','1000004-36'),(34,1000004,20,'dropped','1000004-20'),(35,1000004,54,'dropped','1000004-54'),(36,1000005,38,'dropped','1000005-38'),(37,1000005,54,'dropped','1000005-54');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_no` varchar(10) NOT NULL,
  `building_id` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'633',2,30),(2,'407',2,50),(3,'2.445',3,50),(4,'2.210',3,100),(5,'409',2,75),(6,'410',2,50),(7,'505',2,60),(8,'507',2,50),(9,'509',2,70),(10,'520',2,100),(11,'3.121',3,60),(12,'3.101',3,80),(13,'3.212',3,90),(14,'4.220',3,100),(15,'4.113',3,70);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `day` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,'10:45:00','13:15:00','MON'),(2,'10:45:00','13:15:00','TUE'),(3,'10:45:00','13:15:00','WED'),(4,'10:45:00','13:15:00','THU'),(5,'10:45:00','13:15:00','FRI'),(6,'13:30:00','16:00:00','MON'),(7,'13:30:00','16:00:00','TUE'),(8,'13:30:00','16:00:00','WED'),(9,'13:30:00','16:00:00','THU'),(10,'13:30:00','16:00:00','FRI'),(11,'14:45:00','17:15:00','MON'),(12,'14:45:00','17:15:00','TUE'),(13,'14:45:00','17:15:00','WED'),(14,'14:45:00','17:15:00','THU'),(15,'14:45:00','17:15:00','FRI'),(16,'15:30:00','18:00:00','MON'),(17,'15:30:00','18:00:00','TUE'),(18,'15:30:00','18:00:00','WED'),(19,'15:30:00','18:00:00','THU'),(20,'15:30:00','18:00:00','FRI'),(21,'17:45:00','20:15:00','MON'),(22,'17:45:00','20:15:00','TUE'),(23,'17:45:00','20:15:00','WED'),(24,'17:45:00','20:15:00','THU'),(25,'17:45:00','20:15:00','FRI'),(26,'18:30:00','21:00:00','MON'),(27,'18:30:00','21:00:00','TUE'),(28,'18:30:00','21:00:00','WED'),(29,'18:30:00','21:00:00','THU'),(30,'18:30:00','21:00:00','FRI');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_question`
--

DROP TABLE IF EXISTS `security_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_question`
--

LOCK TABLES `security_question` WRITE;
/*!40000 ALTER TABLE `security_question` DISABLE KEYS */;
INSERT INTO `security_question` VALUES (1,'What is your favourite pet?'),(2,'What is the name of your hometown?'),(3,'What is your favourite color?'),(4,'What is your favourite place?');
/*!40000 ALTER TABLE `security_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` mediumtext,
  `level` enum('G','UG') DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000007 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1000003,'John','Smith','jsmith@yahoo.com','5124878890','Montreal, Quebec, Canada','UG',3),(1000004,'Karun','Sharma','karunsh1@gmail.com','5145135394','Montreal, Quebec, Canada','G',2),(1000005,'Nidhi','Arora','nidhi.arora@gmail.com','5142671890','Montreal, Quebec, Canada','G',2),(1000006,'Azida','Mujtaba','azimujtaba@in.com','5167890368','Montreal, Quebec, Canada','G',1);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_code` varchar(10) NOT NULL,
  `details` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'COMP','COMPUTER SCIENCE'),(2,'SOEN','SOFTWARE ENGINEERING'),(3,'INSE','I.N.S.E'),(4,'COEN','COMPUTER ENGINEERING');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `term_info`
--

DROP TABLE IF EXISTS `term_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `term_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term` varchar(50) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `registration_start` date NOT NULL,
  `dne_date` date DEFAULT NULL,
  `disc_date` date DEFAULT NULL,
  `max_credit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `term_UNIQUE` (`term`)
) ENGINE=MyISAM AUTO_INCREMENT=1008 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term_info`
--

LOCK TABLES `term_info` WRITE;
/*!40000 ALTER TABLE `term_info` DISABLE KEYS */;
INSERT INTO `term_info` VALUES (1001,'Winter 2016','2016-01-06','2016-04-25','2015-05-12',NULL,NULL,NULL),(1002,'Summer 2016','2016-05-06','2016-08-25','2016-02-12',NULL,NULL,NULL),(1003,'Fall 2016','2016-09-06','2016-12-20','2016-02-12',NULL,NULL,NULL),(1004,'Winter 2017','2017-01-06','2017-04-25','2016-05-12',NULL,NULL,NULL),(1005,'Summer 2017','2017-05-06','2017-08-25','2017-02-12',NULL,NULL,NULL),(1006,'Fall 2017','2017-09-06','2017-12-20','2017-02-12',NULL,NULL,NULL),(1007,'Winter 2018','2018-01-06','2018-04-25','2017-05-12',NULL,NULL,NULL);
/*!40000 ALTER TABLE `term_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_security_question`
--

DROP TABLE IF EXISTS `user_security_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_security_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `sq_id` int(11) DEFAULT NULL,
  `answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_security_question`
--

LOCK TABLES `user_security_question` WRITE;
/*!40000 ALTER TABLE `user_security_question` DISABLE KEYS */;
INSERT INTO `user_security_question` VALUES (2,'karunsh1@gmail.com',1,'dog');
/*!40000 ALTER TABLE `user_security_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `type` enum('1','2','3','4','5') DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10023 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (10015,'azimujtaba@in.com','187ef4436122d1cc2f40dc2b92f0eba0','1'),(10014,'nidhi.arora@gmail.com','187ef4436122d1cc2f40dc2b92f0eba0','1'),(10013,'karunsh1@gmail.com','187ef4436122d1cc2f40dc2b92f0eba0','1'),(10012,'jsmith@yahoo.com','187ef4436122d1cc2f40dc2b92f0eba0','1'),(10011,'tanya.khan@gmail.com','187ef4436122d1cc2f40dc2b92f0eba0','1'),(10010,'na_islam@concordia.ca','187ef4436122d1cc2f40dc2b92f0eba0','1'),(10016,'dir@gmail.com','187ef4436122d1cc2f40dc2b92f0eba0','4'),(10017,'halina@cs.concordia.ca','187ef4436122d1cc2f40dc2b92f0eba0','2'),(10018,'Dssouli@encs.concordia.ca','187ef4436122d1cc2f40dc2b92f0eba0','3'),(10019,'lata@cs.concordia.ca','187ef4436122d1cc2f40dc2b92f0eba0','3'),(10020,'Rajagopalan@encs.concordia.ca','187ef4436122d1cc2f40dc2b92f0eba0','3'),(10021,'Rilling@encs.concordia.ca','187ef4436122d1cc2f40dc2b92f0eba0','3'),(10022,'Yan@encs.concordia.ca','187ef4436122d1cc2f40dc2b92f0eba0','3');
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

-- Dump completed on 2017-12-16 14:24:13
