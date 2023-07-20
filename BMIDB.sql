-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2023 at 10:56 AM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bmidb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbmiresult`
--

CREATE TABLE `tbmiresult` (
  `tUser_email` varchar(50) NOT NULL,
  `bmi` decimal(5,2) NOT NULL,
  `date_added` datetime NOT NULL,
  `height` decimal(5,2) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tuser`
--

CREATE TABLE `tuser` (
  `email` varchar(50) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `date_of_birth` datetime NOT NULL,
  `account_made` datetime NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `password_salt` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tuser`
--

INSERT INTO `tuser` (`email`, `first_name`, `last_name`, `gender`, `date_of_birth`, `account_made`, `password`, `password_salt`) VALUES
('user1', 'Anna', 'Deinhardt', 'F', '2023-07-01 00:00:00', '2023-07-20 15:53:36', '2a3f3aadd958e374036696bc7c2a04521b51d56b7be339740dbdc956441e2947eaade08dca0e593940536ced766a7e4085ce9de8a61aa6427e93574689f97a77', 'c1fc848e959204c30cecb97de9a43b3f'),
('user2', 'Monika', 'Felka', 'F', '2023-07-02 00:00:00', '2023-07-20 15:53:54', '5192346da94bc84b39b4153465ca62f8e4d00183ad9fc33e7c4731d3c453c9362511bb853479ca03e79f43b7537698e6e2bd4c9a3a69043280247ecccadb67bf', 'cea468940a6b4518e5a37b2a506770d7'),
('user3', 'Rijndael', 'Lijdorf', 'M', '2023-07-03 00:00:00', '2023-07-20 15:54:36', '589af0be65b4c30c32c7ac4aff9f36f76e903a3938472ace71cb745668b90ffc4824e5747084613fd6321465c682280fc1a5e1b4a5a29e4d9f96ef5c4e58d72d', 'e6bc9442a7e0d9dcf3ecb66c7989fb58'),
('user4', 'Dixie', 'Boy', 'M', '2023-07-04 00:00:00', '2023-07-20 15:54:54', '9581972ac597c6dbfc28e786e19978b0c083c18df5319c4ea895b1e3989df5a0cacb4bcd08cd4b040708cd835dc7d4e55ecd80271d22e123203519786f88c7ef', '9fe3c9443410052259be52c5086e3253');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbmiresult`
--
ALTER TABLE `tbmiresult`
  ADD KEY `fk_tBMIResult_tUser_idx` (`tUser_email`);

--
-- Indexes for table `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`email`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbmiresult`
--
ALTER TABLE `tbmiresult`
  ADD CONSTRAINT `fk_tBMIResult_tUser` FOREIGN KEY (`tUser_email`) REFERENCES `tuser` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
