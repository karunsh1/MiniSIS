-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 17, 2016 at 07:34 AM
-- Server version: 5.5.47-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `msis`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(25) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `address` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `first_name`, `last_name`, `email`, `mobile`, `emp_id`, `address`) VALUES
(1, 'Halina', 'Monkiewicz', 'halina@cs.concordia.ca', '514-848-2424 ext 3043', 70000012, 'S-EV 3152, 1455 De Maisonneuve Blvd. W.\nMontreal, QC H3G 1M8\nCanada');

-- --------------------------------------------------------

--
-- Table structure for table `building`
--

CREATE TABLE IF NOT EXISTS `building` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `address` text NOT NULL,
  `campus_short` varchar(10) NOT NULL,
  `campus` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `building`
--

INSERT INTO `building` (`id`, `name`, `full_name`, `address`, `campus_short`, `campus`) VALUES
(1, 'EV', 'Engineering, Computer Science and Visual Arts Integrated Complex', '1515 Rue Ste-Catherine O, Montréal, QC H3G 2W1', 'SGW', 'Sir George Williams campus'),
(2, 'H', 'Henry F. Hall Building', '1455 Boulevard de Maisonneuve O, Montréal, QC H3G 1M8', 'SGW', 'Sir George Williams campus'),
(3, 'MB', 'John Molson Building', '1450 Guy Street, Montreal, Quebec, H3H 0A1', 'SGW', 'Sir George Williams campus');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `course_code` varchar(20) NOT NULL,
  `description` mediumtext NOT NULL,
  `level` varchar(5) DEFAULT NULL,
  `units` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `subject_id`, `title`, `course_code`, `description`, `level`, `units`) VALUES
(1, 2, 'Software Measurement', '6611', 'Role of measurement in Software Engineering, theoretical, technical and managerial views on software measurement. Representational theory of measurement. Theoretical validation of software measurement. Measurement program: goal-driven approach. Collecting and analyzing software engineering data. Software quality modelling and measuring. Testing and measurement. Reliability models. Functional size measurement methods. Effort estimation models and their usage in project management. Software measurement standards. Tool support. A project.', 'G', 4),
(2, 2, 'Software Systems Requirements Specification', '6481', 'The requirements engineering (RE) process. Requirements engineering in different software lifecycle models. Problem analysis. Requirements elicitation. Requirements evaluation. Inconsistency management. Risk analysis. Requirements prioritization and negotiation. Requirements specification: natural language documentation, IEEE and ISO standards. Use cases. Agile processes and user stories. Introduction to formal specification: logics, formal languages. Requirements quality assurance. RE tools. Requirements evolution. Traceability. Domain modelling: UML, ontologies, domain-specific languages. Modelling behaviour. Acceptance criteria. Test cases. Cost models. A project.', 'G', 4),
(3, 2, 'Software Project Management', '6841', 'Fundamental concepts of management activities, demonstrating how it can relate to software engineering and how the two can be mutually supportive throughout software development and maintenance. Overview of object-oriented development; software development processes; quality considerations; activity planning; risk management; monitoring and control; maintenance and evolution; issues of professional ethics and practice, and legal issues. A project.', 'G', 4),
(4, 1, 'Compiler Design', '6421', 'Compiler organization and implementation: lexical analysis and parsing, syntax-directed translation, code optimization. Run-time systems. A project.', 'G', 4),
(5, 1, 'Artificial Intelligence', '6721', 'Scope of AI. First order logic. Automated reasoning. Search and heuristic search. Game-playing. Planning. Knowledge representation. Probabilistic reasoning. Introduction to machine learning. Introduction to natural language processing. A project.', 'G', 4),
(6, 1, 'Algorithm Design Techniques', '6651', 'Mathematical preliminaries; Empirical and theoretical measures of algorithm efficiencies; Optimization and combinatorial techniques and algorithms including greedy algorithms, dynamic programming, branch-and-bound techniques and graph network algorithms; Amortized complexity analysis; String matching algorithms; NP-complete problems and approximate solutions; Probabilistic algorithms. A project.', 'G', 4),
(7, 1, 'Image Processing', '6771', 'Digital image fundamentals; image enhancement: histogram processing, filtering in the spatial domain, filtering in the frequency domain; image restoration and reconstruction; image segmentation: line detection, Hough transform, edge detection and linking, thresholding, region splitting and merging; image compression; introduction to wavelet transform and multi-resolution processing. A project. Laboratory: two hours per week.', 'G', 4),
(8, 1, 'Distributed System Design', '6231', 'Principles of distributed computing: scalability, transparency, concurrency, consistency, fault tolerance. Client-server interaction technologies: interprocess communication, sockets, group communication, remote procedure call, remote method invocation, object request broker, CORBA, web services. Distributed server design techniques: process replication, fault tolerance through passive replication, high availability through active replication, coordination and agreement transactions and concurrency control. Designing software fault-tolerant highly available distributed systems using process replication. Laboratory: two hours per week.', 'G', 4),
(9, 1, 'Computer Networks and Protocols', '6461', 'Direct link networks: encoding, framing, error detection, flow control, example networks. Packet switching and forwarding: bridges, switches. Internetworking: Internet Protocol, routing, addressing, IPv6, multicasting, mobile IP. End-to-end protocols: UDP, TCP. Network security concepts. Application-level protocols. Laboratory: two hours per week.', 'G', 4),
(10, 1, 'Advanced Database Technology and Applications', '6521', 'Review of standard relational databases, query languages. Query processing and optimization. Parallel and distributed databases. Information integration. Data warehouse systems. Data mining and OLAP. Web databases and XML Active and logical databases, spatial and multimedia data management. Laboratory: Two hours per week.', 'G', 4),
(11, 2, 'Advanced Programming Practices', '6441', 'Problems of writing and managing code. Managing code complexity and quality through a programming process. Coding conventions. Inline software documentation. Software configuration management. Tools and techniques for testing software. Multithreading concurrency. Code reuse in software development. Quality in coding, fault tolerance. A project. Laboratory: two hours per week.', 'G', 4),
(12, 2, 'Advanced Software Architectures', '6471', 'Study of architectural view models, architectural styles and frameworks; Architectural Description Languages (ADLs) and Architectural Specification Languages (ASLs); Architectural design patterns; advanced topics such as Model Driven Architecture (MDA), Service Oriented Architecture (SOA), extensible frameworks (like the OSGi specification and framework), Software Product Line Engineering (SPLE), and architectures in support of cloud computing. A project.', 'G', 4),
(13, 2, 'Software Comprehension and Maintenance', '6431', 'The course addresses both technical and managerial views of software comprehension and software maintenance issues. Topics covered in this course include: cognitive models, software visualization, CASE tools, reverse engineering, static and dynamic source code analysis, software configuration management, and introduction to current research topics in software maintenance and program comprehension. A project.', 'G', 4),
(14, 2, 'Software Design Methodologies', '6461', 'Introduction to software design processes and their models. Representations of design/architecture. Software architectures and design plans. Design methods, object-oriented application frameworks, design patterns, design quality and assurance, coupling and cohesion measurements, design verification and documentation. A design project.', 'G', 4),
(15, 3, 'Software Quality Assurance ', '6260', 'Quality assurance, quality factors, components of a software quality assurance system, contract review, software development and quality plans, activities and alternatives, integration of quality activities in a project lifecycle, reviews, software inspection, software verification, testing processes, static analysis, control-flow analysis, data-flow analysis, control-flow testing, loop testing, data-flow testing, transaction-flow testing, domain testing, type-based analysis, dynamic analysis, usage models, operational profiles, result and defect analysis, reliability, performance analysis, maintenance and reverse engineering, case tools and software quality assurance. A project.', 'G', 4),
(16, 3, 'Total Quality Project Management', '6230', 'Role of a project manager, learning and applications, project management processes, project management in quality initiatives, intellectual property protection, customer project management and critical-to-quality requirements, project planning and execution, team formation, goals, roles, procedures and interpersonal relationship, types of teams, creating a project plan, project tracking, project compliance requirements, sourcing and supplier qualification, government contract data rights, government property, risk management process, action planning, project communication, customer, team and stakeholder communications, communication planning and strategy, web-based collaboration, project management software tools. A project.', 'G', 4),
(17, 3, 'Wireless Network Security', '6190', 'Introduction to wireless network security; security issues in cellular networks; authentication/key management in wireless LAN; secure handover; security in mobile IP; security issues in mobile ad-hoc networks: trust establishment, secure routing, anonymity; anonymous sensory data collection; privacy for smartphone applications. A project.', 'G', 4),
(18, 3, 'Database Security and Privacy', '6160', 'Access control in relational databases; grant/revoke model; security by views; query modification; Oracle VPD; auditing in databases; information warfare in databases; multi-level database security; polyinstantiation and covert channel; statistical database security; inference control; security by auditing; microdata security; random perturbation; outsourced database security, encrypted databases; SQL injection attack; anomaly detection in databases; data privacy, P3P; Hippocratic databases; perfect secrecy-based privacy; k-anonymity model; l-diversity; data utility measure, data release with public algorithms, multi-party privacy preserving computation; privacy in OLAP. A project.', 'G', 4),
(19, 3, 'Foundations of Cryptography', '6110', 'Introduction to cryptography and cryptanalysis, classical ciphers, number-theoretic reference problems, the integer factorization problem, the RSA problem, the quadratic residuosity problem, computing square roots in Zn, the discrete logarithmic problem, the diffie-hellman problem, pseudorandom bits and sequences, stream ciphers: feedback shift registers, LFSRs, RC4. Block Ciphers: SPN and Fiestel structures, DES, AES, linear cryptanalysis, differential cryptanalysis, side channel attacks, ciphertext indistinguishability, attack analysis, IND-CPA, IND-CCA, IND-CCA2, public key encryption: RSA, Rabin, ElGamal, elliptic curves cryptography, hash functions: Un-keyed hash functions, MACs, Attacks, Digital signatures: RSA, Fiat-Shamir, DSA, public key infrastructure, key management, efficient implementation of ciphers, zero-knowledge proof. A project.', 'G', 4),
(20, 3, 'Total Quality Methodologies in Engineering', '6210', 'Methodologies for quality engineering: six sigma, ACE (Achieving Competitive Excellence), Lean engineering, ISO9000 series; comparative study, quality clinic process charts, relentless root cause analysis, mistake proofing, market feedback analysis, process improvement and waste elimination, visual control, standard work and process management, process certification, setup reduction, total productive maintenance, DMAIC and DMADV processes, define phase, project charter, project scoping and planning, measure phase, critical to quality requirements, quality functional deployment, analyze phase, functional and process requirements, design requirements, design concepts, high-level design capability elaboration and evaluation, design phase, detailed design capability elaboration and evaluation, failure mode and effects analysis, control and verification plans, verify phase, pilot-scale processes, pilot testing and evaluation, implementation planning, full-scale processes, start-up and testing, performance evaluation, turnover to operations and maintenance, transition to process management, project closure. A project.', 'G', 4),
(21, 1, 'Mathematics for Computer Science', '232', '', 'U', 3),
(22, 1, 'Object-Oriented Programming I', '248', '', 'U', 3),
(23, 1, 'System Hardware', '228', '', 'U', 3),
(24, 1, 'Probability and Statistics for Computer Science', '233', '', 'U', 3),
(25, 2, 'Distributed Systems', '423', '', 'U', 4),
(26, 2, 'Web Services and Applications', '487', '', 'U', 4),
(27, 2, 'Embedded Systems and Software', '422', '', 'U', 4),
(28, 2, 'Web Programming', '287', '', 'U', 4);

-- --------------------------------------------------------

--
-- Table structure for table `course_details`
--

CREATE TABLE IF NOT EXISTS `course_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `instructor_id` int(11) NOT NULL,
  `term_id` int(11) NOT NULL,
  `duration` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `class_capacity` int(11) NOT NULL,
  `waiting_capacity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `course_details`
--

INSERT INTO `course_details` (`id`, `course_id`, `instructor_id`, `term_id`, `duration`, `room_id`, `class_capacity`, `waiting_capacity`) VALUES
(1, 15, 11, 1, 6, 1, 50, 10),
(2, 16, 10, 1, 6, 10, 90, 25),
(3, 3, 3, 2, 13, 1, 50, 10);

-- --------------------------------------------------------

--
-- Table structure for table `course_schedule`
--

CREATE TABLE IF NOT EXISTS `course_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_detail_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `course_schedule`
--

INSERT INTO `course_schedule` (`id`, `course_detail_id`, `schedule_id`) VALUES
(1, 1, 27),
(2, 1, 29),
(3, 2, 26),
(4, 2, 28);

-- --------------------------------------------------------

--
-- Table structure for table `due`
--

CREATE TABLE IF NOT EXISTS `due` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(8) NOT NULL,
  `term_id` int(11) NOT NULL,
  `due_type` varchar(20) DEFAULT NULL,
  `due_amount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `due`
--

INSERT INTO `due` (`id`, `student_id`, `term_id`, `due_type`, `due_amount`) VALUES
(2, 1, 1, NULL, 7000),
(3, 1, 1, NULL, 7000);

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE IF NOT EXISTS `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `term_id` int(11) NOT NULL,
  `gpa` double(10,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `grading_points`
--

CREATE TABLE IF NOT EXISTS `grading_points` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `grade_scale` varchar(10) NOT NULL,
  `points` double NOT NULL,
  `mark_rang` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `grading_points`
--

INSERT INTO `grading_points` (`id`, `grade_scale`, `points`, `mark_rang`) VALUES
(1, 'A+', 4.3, '90-00'),
(2, 'A', 4, '85-89'),
(3, 'A-', 3.7, '80-84'),
(4, 'B+', 3.3, '77-79'),
(5, 'B', 3, '73-76'),
(6, 'B-', 2.7, '70-72'),
(7, 'C', 2, '60-69'),
(8, 'F', 0, '0-59'),
(9, 'in progres', -1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE IF NOT EXISTS `instructor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(50) NOT NULL DEFAULT 'NULL',
  `emp_id` int(8) NOT NULL,
  `address` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`id`, `first_name`, `last_name`, `email`, `emp_id`, `address`) VALUES
(1, 'Dr. Sabine', 'Bergler', 'bergler@cse.concordia.ca', 700001, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(2, 'Dr. Lata ', 'Narayanan', 'lata@cs.concordia.ca', 700002, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(3, 'Dr. Tien ', 'Bui', 'bui@cse.concordia.ca', 700003, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(4, 'Dr. Rajagopalan ', 'Jayakumar', 'Rajagopalan@encs.concordia.ca', 700004, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(5, 'Dr. Chadi ', 'Assi', 'assi@encs.concordia.ca', 700005, '1455 de Maisonneuve Blvd West\nMontreal, Quebec, Canada\nH3G 1M8 '),
(6, 'Dr. Gosta ', 'Grahne', 'gosta@encs.concordia.ca', 700006, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(7, 'Dr. Juergen ', 'Rilling', 'Rilling@encs.concordia.ca', 700007, '1455 de Maisonneuve Blvd West\nMontreal, Quebec, Canada\nH3G 1M8 '),
(8, 'Prof. Pankaj ', 'Kamthan', 'kamthan@encs.concordia.ca', 700008, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(9, 'Dr. Rene ', 'Witte ', 'rene@encs.concordia.ca', 700009, '1455 de Maisonneuve Blvd West\nMontreal, Quebec, Canada\nH3G 1M8 '),
(10, 'Prof. Fereshteh ', 'Mafakheri', 'fereshteh@encs.concordia.ca', 700010, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(11, 'Prof. Rachida ', 'Dssouli', 'Dssouli@encs.concordia.ca', 700011, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(12, 'Prof. Ayda ', 'Basyouni', '@encs.concordia.ca', 700012, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(13, 'Prof. Jeremy ', 'Clark', 'Clark@encs.concordia.ca', 700013, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(14, 'Prof. Nizar ', 'Bouguila', 'Bouguila@encs.concordia.ca', 700014, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(15, 'Prof. Lingyu ', 'Wang', 'Wang@encs.concordia.ca', 700015, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(16, 'Prof. Olga ', 'Ormandjieva', 'Olga@encs.concordia.ca', 700016, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(17, 'Prof. Constantinos ', 'Constantinides', 'Constantinos@encs.concordia.ca', 700017, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(18, 'Prof. Terrill ', 'Fancott ', 'Fancott@encs.concordia.ca', 700018, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(19, 'Prof. Yuhong ', 'Yan', 'Yan@encs.concordia.ca', 700019, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(20, 'Prof. Sudhir ', 'Mudur', 'Mudur@encs.concordia.ca', 700020, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 '),
(21, 'Prof. Eusebius ', 'Doedel', 'Doedel@encs.concordia.ca', 700021, '1455 de Maisonneuve Blvd West\r\nMontreal, Quebec, Canada\r\nH3G 1M8 ');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `paid_amount` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `student_id`, `paid_amount`) VALUES
(1, 1, 7000);

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE IF NOT EXISTS `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_details_id` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- --------------------------------------------------------

--
-- Table structure for table `registration_cart`
--

CREATE TABLE IF NOT EXISTS `registration_cart` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_details_id` int(11) NOT NULL,
  `status` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `registration_cart`
--

INSERT INTO `registration_cart` (`id`, `student_id`, `course_details_id`, `status`) VALUES
(7, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_no` varchar(10) NOT NULL,
  `building_id` int(11) NOT NULL,
  `capacity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`id`, `room_no`, `building_id`, `capacity`) VALUES
(1, '633', 2, 30),
(2, '407', 2, 50),
(3, '2.445', 3, 50),
(4, '2.210', 3, 100),
(5, '409', 2, 75),
(6, '410', 2, 50),
(7, '505', 2, 60),
(8, '507', 2, 50),
(9, '509', 2, 70),
(10, '520', 2, 100),
(11, '3.121', 3, 60),
(12, '3.101', 3, 80),
(13, '3.212', 3, 90),
(14, '4.220', 3, 100),
(15, '4.113', 3, 70);

-- --------------------------------------------------------

--
-- Table structure for table `schedule`
--

CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `day` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `schedule`
--

INSERT INTO `schedule` (`id`, `start_time`, `end_time`, `day`) VALUES
(1, '10:45:00', '13:15:00', 'MON'),
(2, '10:45:00', '13:15:00', 'TUE'),
(3, '10:45:00', '13:15:00', 'WED'),
(4, '10:45:00', '13:15:00', 'THU'),
(5, '10:45:00', '13:15:00', 'FRI'),
(6, '13:30:00', '16:00:00', 'MON'),
(7, '13:30:00', '16:00:00', 'TUE'),
(8, '13:30:00', '16:00:00', 'WED'),
(9, '13:30:00', '16:00:00', 'THU'),
(10, '13:30:00', '16:00:00', 'FRI'),
(11, '14:45:00', '17:15:00', 'MON'),
(12, '14:45:00', '17:15:00', 'TUE'),
(13, '14:45:00', '17:15:00', 'WED'),
(14, '14:45:00', '17:15:00', 'THU'),
(15, '14:45:00', '17:15:00', 'FRI'),
(16, '15:30:00', '18:00:00', 'MON'),
(17, '15:30:00', '18:00:00', 'TUE'),
(18, '15:30:00', '18:00:00', 'WED'),
(19, '15:30:00', '18:00:00', 'THU'),
(20, '15:30:00', '18:00:00', 'FRI'),
(21, '17:45:00', '20:15:00', 'MON'),
(22, '17:45:00', '20:15:00', 'TUE'),
(23, '17:45:00', '20:15:00', 'WED'),
(24, '17:45:00', '20:15:00', 'THU'),
(25, '17:45:00', '20:15:00', 'FRI'),
(26, '18:30:00', '21:00:00', 'MON'),
(27, '18:30:00', '21:00:00', 'TUE'),
(28, '18:30:00', '21:00:00', 'WED'),
(29, '18:30:00', '21:00:00', 'THU'),
(30, '18:30:00', '21:00:00', 'FRI');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `address` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`, `mobile`, `address`) VALUES
(1, 'nafiz', 'islam', 'na_islam@concordia.ca', '5144735006', 'Montreal, Quebec, Canada');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_code` varchar(10) NOT NULL,
  `details` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `subject_code`, `details`) VALUES
(1, 'COMP', 'COMPUTER SCIENCE'),
(2, 'SOEN', 'SOFTWARE ENGINEERING'),
(3, 'INSE', 'I.N.S.E'),
(4, 'COEN', 'COMPUTER ENGINEERING');

-- --------------------------------------------------------

--
-- Table structure for table `term_info`
--

CREATE TABLE IF NOT EXISTS `term_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `term` varchar(50) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `registration_start` date NOT NULL,
  `dne_date` date NOT NULL,
  `disc_date` date NOT NULL,
  `max_credit` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `term_info`
--

INSERT INTO `term_info` (`id`, `term`, `start_date`, `end_date`, `registration_start`, `dne_date`, `disc_date`, `max_credit`) VALUES
(1, 'Fall 2016', '2016-08-02', '2016-12-31', '2016-05-02', '2016-08-18', '2016-09-02', 12),
(2, 'Winter 2017', '2017-01-07', '2016-04-30', '2016-05-02', '2017-02-19', '2017-02-03', 12);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `type` enum('1','2') DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `type`) VALUES
(1, 'na_islam@concordia.ca', 'c4ca4238a0b923820dcc509a6f75849b', '1'),
(2, 'halina@cs.concordia.ca', '21232f297a57a5a743894a0e4a801fc3', '2'),
(3, 'omarfaruk.sust@gmail.com', 'e10adc3949ba59abbe56e057f20f883e', '1');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
