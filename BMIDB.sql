-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2023 at 03:56 PM
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
  `password` varchar(45) DEFAULT NULL,
  `password_salt` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tuser`
--

INSERT INTO `tuser` (`email`, `first_name`, `last_name`, `gender`, `date_of_birth`, `account_made`, `password`, `password_salt`) VALUES
('admin', '', '', 'M', '2003-10-30 00:00:00', '2023-07-07 19:38:52', NULL, NULL);

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
