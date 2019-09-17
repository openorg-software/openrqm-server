-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 17. Sep 2019 um 21:26
-- Server-Version: 10.4.6-MariaDB
-- PHP-Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `openrqm`
--

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `surname`, `department`, `password_hash`, `password_salt`) VALUES
(1, 'user1@email.com', 'UserOne', 'One', 'DepartmentOne', 'thisisarandompaasworhashbutnotreallyrandom', 'thisisarandompaasworhashbutnotreallyrandom'),
(2, 'user2@email.com', 'UserTwo', 'Two', 'DepartmentTwo', 'thisisarandompaasworhashbutnotreallyrandom', 'thisisarandompaasworhashbutnotreallyrandom');

--
-- Daten für Tabelle `workspace`
--

INSERT INTO `workspace` (`id`, `name`, `workspace_id`) VALUES
(1, 'Workspace 1', NULL),
(2, 'Subworkspace 1.1', 1),
(3, 'Subworkspace 1.2', 1),
(4, 'Workspace 2', NULL),
(5, 'Subworkspace 2.1', 4),
(6, 'Subworkspace 2.2', 4),
(7, 'Subworkspace 2.3', 4);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
