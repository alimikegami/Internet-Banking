-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2021 at 03:10 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) NOT NULL,
  `NIK` varchar(16) NOT NULL,
  `street_address` varchar(75) NOT NULL,
  `sub_district` varchar(50) NOT NULL,
  `regency` varchar(50) NOT NULL,
  `province` varchar(50) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `first_name`, `middle_name`, `last_name`, `NIK`, `street_address`, `sub_district`, `regency`, `province`, `gender`, `email`) VALUES
(1, 'Alim', NULL, 'Ikegami', '5104051601010001', 'Br.Tengkulak Kaja Kauh', 'Kemenuh', 'Ubud', 'Bali', 'male', 'alimikegami1@gmail.com'),
(2, 'Komang', NULL, 'Aditya', '1908561046', 'Penestanan Kaja', 'Ubud', 'Gianyar', 'Bali', 'male', 'komangaditya@gmail.com'),
(3, 'Dika', 'Rahayu', 'Arthari', '1908561028', 'Jalan Raya Mas', 'Ubud', 'Gianyar', 'Bali', 'female', 'dikaterawan@gmail.com'),
(4, 'I', 'Nyoman', 'Gunayhana', '1908561944', 'Penestanan Kaja', 'Ubud', 'Gianyar', 'Bali', 'male', 'gunayhana@gmail.com'),
(5, 'Miki', NULL, 'Ikegami', '19085619049', 'Br. Tengkulak Kaja Kauh', 'Kemenuh', 'Sukawati', 'Gianyar', 'female', 'mikiikegami@gmail.com'),
(6, 'Komang', 'Bram', 'Krisnanendra', '1908561040', 'Jln. Rambutan', 'Gianyar', 'Gianyar', 'Bali', 'female', 'komangbram@gmail.com'),
(7, 'Tom', NULL, 'Brady', '1908561092', 'Tampa Bay', 'Florida', 'USA', 'America', 'male', 'tombrady@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `customer_account`
--

CREATE TABLE `customer_account` (
  `account_id` int(11) NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `balance` int(20) NOT NULL,
  `validation` enum('valid','not valid','blocked') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_account`
--

INSERT INTO `customer_account` (`account_id`, `customer_id`, `username`, `password`, `balance`, `validation`) VALUES
(1, 1, 'alimikegami1', 'alim123keren', 0, 'valid'),
(2, 2, 'komangaditya', 'komang1234', 95000, 'valid'),
(3, 3, 'dikaterawan', 'dika1234', 30000, 'valid'),
(4, 4, 'gunayhana', 'komang1234', 0, 'not valid'),
(5, 5, 'miki', 'miki1234', 100000, 'valid'),
(7, 1, 'alimikegami2', 'alim123keren', 500000, 'valid'),
(8, 7, 'tombrady', 'tom1234', 312000, 'valid');

-- --------------------------------------------------------

--
-- Table structure for table `employee_account`
--

CREATE TABLE `employee_account` (
  `employee_id` int(11) NOT NULL,
  `employee_username` varchar(50) NOT NULL,
  `employee_password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee_account`
--

INSERT INTO `employee_account` (`employee_id`, `employee_username`, `employee_password`) VALUES
(1, 'admin', 'admin2020');

-- --------------------------------------------------------

--
-- Table structure for table `transfer_history`
--

CREATE TABLE `transfer_history` (
  `transfer_id` int(11) NOT NULL,
  `transferFrom` varchar(50) NOT NULL,
  `transferTo` varchar(50) NOT NULL,
  `amount` int(11) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `voucher_code` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transfer_history`
--

INSERT INTO `transfer_history` (`transfer_id`, `transferFrom`, `transferTo`, `amount`, `time`, `voucher_code`) VALUES
(1, 'alimikegami1', 'komangaditya', 10000, '2020-11-22 16:00:10', NULL),
(2, 'alimikegami1', 'komangaditya', 30000, '2020-11-22 16:01:00', NULL),
(3, 'komangaditya', 'alimikegami1', 10000, '2020-11-22 16:07:21', NULL),
(4, 'alimikegami1', 'komangaditya', 10000, '2020-11-24 08:29:02', NULL),
(5, 'alimikegami1', 'komangaditya', 10000, '2020-11-24 08:29:13', NULL),
(6, 'alimikegami1', 'komangaditya', 10000, '2020-11-24 08:29:29', NULL),
(7, 'alimikegami1', 'komangaditya', 20000, '2020-11-24 08:29:40', NULL),
(8, 'alimikegami1', 'komangaditya', 5000, '2020-11-24 08:29:50', NULL),
(9, 'dikaterawan', 'alimikegami1', 5000, '2020-11-24 10:19:42', NULL),
(18, 'alimikegami1', 'Google Play Voucher IDR 5.000,00', 5000, '2021-01-09 05:51:41', '3vapv32xeR'),
(19, 'alimikegami1', 'Pulsa Telkomsel IDR 5.000', 7000, '2021-01-09 07:08:29', 'uZsoNIK8S5'),
(20, 'alimikegami1', 'Token Listrik PLN IDR 47.000', 50000, '2021-01-09 07:09:22', 'EIaBT5gZXP'),
(21, 'alimikegami1', 'dikaterawan', 10000, '2021-01-09 07:42:44', NULL),
(22, 'alimikegami1', 'dikaterawan', 5000, '2021-01-09 07:46:03', NULL),
(23, 'alimikegami1', 'Pulsa Telkomsel IDR 5.000', 7000, '2021-01-09 07:46:14', '8tmXKmdKxf'),
(24, 'alimikegami1', 'dikaterawan', 10000, '2021-01-09 07:58:40', NULL),
(25, 'alimikegami1', 'Token Listrik PLN IDR 17.000', 20000, '2021-01-09 07:58:48', 'rylkYaiZzq'),
(26, 'tombrady', 'alimikegami1', 1000, '2021-01-10 01:55:27', NULL),
(27, 'tombrady', 'Pulsa Telkomsel IDR 5.000', 7000, '2021-01-10 01:55:37', '5EPLZ2nmo6'),
(28, 'alimikegami1', 'tombrady', 10000, '2021-01-10 01:56:06', NULL),
(29, 'tombrady', 'Pulsa Indosat IDR 10.000', 12000, '2021-01-10 02:00:07', 'sFXZWIazKS'),
(30, 'alimikegami1', 'tombrady', 312000, '2021-01-10 02:00:31', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `customer_account`
--
ALTER TABLE `customer_account`
  ADD PRIMARY KEY (`account_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `employee_account`
--
ALTER TABLE `employee_account`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `transfer_history`
--
ALTER TABLE `transfer_history`
  ADD PRIMARY KEY (`transfer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `customer_account`
--
ALTER TABLE `customer_account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `employee_account`
--
ALTER TABLE `employee_account`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `transfer_history`
--
ALTER TABLE `transfer_history`
  MODIFY `transfer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customer_account`
--
ALTER TABLE `customer_account`
  ADD CONSTRAINT `customer_account_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
