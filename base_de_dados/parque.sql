-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 15, 2021 at 02:31 AM
-- Server version: 5.7.24
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parque`
--
CREATE DATABASE IF NOT EXISTS `parque` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `parque`;

-- --------------------------------------------------------

--
-- Table structure for table `clientes`
--
-- Creation: May 25, 2021 at 02:51 PM
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) DEFAULT NULL,
  `tipo` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clientes`
--

INSERT INTO `clientes` (`id`, `nome`, `sobrenome`, `tipo`) VALUES
(1, 'Misael', 'Lopes', 'Estudante'),
(5, 'Ernesto', 'Rafael', 'Visitante'),
(10, 'Ely', 'Cleófas', 'Funcionário'),
(11, 'Homem', 'Aranha', 'Visitante'),
(12, 'Erikson', 'Morenaile', 'Funcionário');

-- --------------------------------------------------------

--
-- Table structure for table `estacionamento`
--
-- Creation: May 25, 2021 at 02:45 PM
--

CREATE TABLE `estacionamento` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estacionamento`
--

INSERT INTO `estacionamento` (`id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20);

-- --------------------------------------------------------

--
-- Table structure for table `fluxo`
--
-- Creation: May 25, 2021 at 03:16 PM
-- Last update: Jun 15, 2021 at 01:39 AM
--

CREATE TABLE `fluxo` (
  `id` int(11) NOT NULL,
  `veiculo_id` int(11) NOT NULL,
  `estacionamento_id` int(11) NOT NULL,
  `data_entrada` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_saida` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fluxo`
--

INSERT INTO `fluxo` (`id`, `veiculo_id`, `estacionamento_id`, `data_entrada`, `data_saida`) VALUES
(1, 1, 2, '2021-05-25 15:19:57', '2021-06-14 00:07:10'),
(2, 6, 3, '2021-06-13 17:57:55', '2021-06-14 01:37:08'),
(4, 7, 1, '2021-06-14 01:04:26', '2021-06-14 03:42:43'),
(5, 8, 6, '2021-06-14 01:15:44', '2021-06-15 00:45:46'),
(6, 10, 1, '2021-06-15 00:28:32', NULL),
(7, 9, 3, '2021-06-15 00:41:47', '2021-06-15 00:42:02'),
(8, 1, 4, '2021-06-15 00:51:47', '2021-06-15 00:52:20'),
(9, 6, 4, '2021-06-15 01:39:49', '2021-06-15 01:39:54');

-- --------------------------------------------------------

--
-- Table structure for table `funcionarios`
--
-- Creation: Jun 07, 2021 at 12:44 AM
--

CREATE TABLE `funcionarios` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(16) NOT NULL,
  `fullname` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `funcionarios`
--

INSERT INTO `funcionarios` (`id`, `username`, `password`, `fullname`) VALUES
(1, 'admin', 'qwerty1234', 'Misael  Lopes'),
(4, 'ernesto', '1234', 'Ernesto Rafael'),
(5, 'wilza', '1234', 'Wilza Coelho');

-- --------------------------------------------------------

--
-- Table structure for table `veiculos`
--
-- Creation: Jun 14, 2021 at 06:49 PM
--

CREATE TABLE `veiculos` (
  `id` int(11) NOT NULL,
  `matricula` varchar(25) NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `veiculos`
--

INSERT INTO `veiculos` (`id`, `matricula`, `client_id`) VALUES
(1, 'ML-LD-45', 1),
(6, 'BL-ML-25', 1),
(7, 'BL-HB-45', 5),
(8, 'AB-L4-16-C', 11),
(9, 'AB-L45-LD', 5),
(10, 'AB-LD-2014', 12);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `estacionamento`
--
ALTER TABLE `estacionamento`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `fluxo`
--
ALTER TABLE `fluxo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `veiculo_id` (`veiculo_id`),
  ADD KEY `estacionamento_id` (`estacionamento_id`);

--
-- Indexes for table `funcionarios`
--
ALTER TABLE `funcionarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `veiculos`
--
ALTER TABLE `veiculos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_id` (`client_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `estacionamento`
--
ALTER TABLE `estacionamento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `fluxo`
--
ALTER TABLE `fluxo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `funcionarios`
--
ALTER TABLE `funcionarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `veiculos`
--
ALTER TABLE `veiculos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fluxo`
--
ALTER TABLE `fluxo`
  ADD CONSTRAINT `fluxo_ibfk_1` FOREIGN KEY (`estacionamento_id`) REFERENCES `estacionamento` (`id`),
  ADD CONSTRAINT `fluxo_ibfk_2` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculos` (`id`);

--
-- Constraints for table `veiculos`
--
ALTER TABLE `veiculos`
  ADD CONSTRAINT `veiculos_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clientes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
