-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 01. Okt 2019 um 19:30
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
-- Daten für Tabelle `document`
--

INSERT INTO `document` (`id`, `workspace_id`, `internal_identifier`, `external_identifier`, `name`, `short_name`, `description`, `confidentiality`, `author_id`, `language_id`, `approver_id`, `reviewer_text`, `last_modified_by_id`, `last_modified_on`, `baseline_major`, `baseline_minor`, `baseline_review`, `previous_baseline_id`) VALUES
(1, 1, 1, '123456789', 'Requirements for OpenRQM', 'OpenRQM_Req', 'The Requirements for the OpenRQM project.', 'Public', 1, 1, NULL, NULL, 1, '2019-09-23 19:00:00', 0, 0, 0, NULL);

--
-- Daten für Tabelle `element`
--

INSERT INTO `element` (`id`, `document_id`, `element_type_id`, `content`, `rank`, `parent_element_id`) VALUES
(1, 1, 2, 'This is chapter 1', 'aaaaaaaaaaaaaaaaaaaa', NULL),
(2, 1, 2, 'The next sentence is a requirement:', 'aaaaaaaaabaaaaaaaaaa', 1),
(3, 1, 1, 'This shall be a requirement.', 'aaaaaaaaacaaaaaaaaaa', 1),
(4, 1, 2, 'This is chapter 2', 'aaaaaaaaadaaaaaaaaaa', NULL),
(5, 1, 1, 'This shall be the second requirement', 'aaaaaaaaaeaaaaaaaaaa', 4),
(6, 1, 2, 'This is chapter 2.1', 'aaaaaaaaafaaaaaaaaaa', 4),
(7, 1, 1, 'This shall be the third requirement.', 'aaaaaaaaagaaaaaaaaaa', 6),
(8, 1, 2, 'This is only a prose.', 'aaaaaaaaahaaaaaaaaaa', 6),
(9, 1, 2, 'This is chapter 2.2', 'aaaaaaaaaiaaaaaaaaaa', 4),
(10, 1, 2, 'Yet another prose.', 'aaaaaaaaajaaaaaaaaaa', 9);

--
-- Daten für Tabelle `element_type`
--

INSERT INTO `element_type` (`id`, `name`) VALUES
(1, 'Requirement'),
(2, 'Prose');

--
-- Daten für Tabelle `link_type`
--

INSERT INTO `link_type` (`id`, `name`) VALUES
(1, 'realizes'),
(2, 'tests'),
(3, 'uses');

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
