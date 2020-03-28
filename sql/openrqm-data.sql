-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 28. Mrz 2020 um 13:40
-- Server-Version: 10.4.11-MariaDB
-- PHP-Version: 7.4.2

SET FOREIGN_KEY_CHECKS=0;
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
CREATE DATABASE IF NOT EXISTS `openrqm` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `openrqm`;

--
-- Daten für Tabelle `accessgroup`
--

INSERT INTO `accessgroup` (`id`, `name`) VALUES
(1, 'Accessgroup1'),
(2, 'Accessgroup2');

--
-- Daten für Tabelle `accessgroup_user`
--

INSERT INTO `accessgroup_user` (`accessgroup_id`, `user_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4);

--
-- Daten für Tabelle `document`
--

INSERT INTO `document` (`id`, `workspace_id`, `internal_identifier`, `external_identifier`, `name`, `short_name`, `description`, `confidentiality`, `author_id`, `language_id`, `approver_id`, `reviewer_text`, `last_modified_by_id`, `last_modified_on`, `baseline_major`, `baseline_minor`, `baseline_review`, `previous_baseline_id`) VALUES
(1, 1, 1, '123456789', 'Requirements for OpenRQM', 'OpenRQM_Req', 'The Requirements for the OpenRQM project.', 'Public', 1, 1, NULL, NULL, 1, '2019-09-23 19:00:00', 0, 0, 0, NULL),
(3, 6, 2, NULL, 'Document 2', 'doc2', NULL, NULL, 2, 1, NULL, NULL, 2, '2020-02-16 17:04:46', 0, 0, 0, NULL);

--
-- Daten für Tabelle `element`
--

INSERT INTO `element` (`id`, `document_id`, `element_type_id`, `content`, `rank`, `parent_element_id`) VALUES
(1, 1, 2, '<p>First element</p>', 'aaaaaaiavncmrfhswswm', NULL);

--
-- Daten für Tabelle `element_type`
--

INSERT INTO `element_type` (`id`, `name`) VALUES
(1, 'Requirement'),
(2, 'Prose');

--
-- Daten für Tabelle `export_template`
--

INSERT INTO `export_template` (`id`, `type`, `name`) VALUES
(1, 'pdf', 'template'),
(2, 'markdown', 'template');

--
-- Daten für Tabelle `link`
--

INSERT INTO `link` (`id`, `from_element_id`, `to_element_id`, `link_type_id`) VALUES
(2, 3, 11, 1);

--
-- Daten für Tabelle `link_type`
--

INSERT INTO `link_type` (`id`, `name`) VALUES
(1, 'realizes'),
(2, 'tests'),
(3, 'uses');

--
-- Daten für Tabelle `theme`
--

INSERT INTO `theme` (`id`, `link_from_color`, `link_to_color`) VALUES
(1, '#333333', '#777777'),
(2, '#abcdef', '#fedcba');

--
-- Daten für Tabelle `theme_element_type`
--

INSERT INTO `theme_element_type` (`theme_id`, `element_type_id`, `color`) VALUES
(1, 1, '#fdfdfd'),
(1, 2, '#dfdfdf'),
(2, 1, '#000000'),
(2, 2, '#ffffff');

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`id`, `email`, `name`, `surname`, `department`, `password_hash`, `token`) VALUES
(1, 'user1@organization.com', 'UserOne', 'One', 'DepartmentOne', '$2a$10$qSpRELdXOy/P9adWxSw3F.IUfXxBoJ/kmzq.wq30I7pEKvZeNNJ5W', ''),
(2, 'user2@organization.com', 'UserTwo', 'Two', 'DepartmentTwo', '$2a$10$qSpRELdXOy/P9adWxSw3F.IUfXxBoJ/kmzq.wq30I7pEKvZeNNJ5W', ''),
(3, 'user3@organization.com', 'UserThree', 'Three', 'DepartmentOne', '$2a$10$qSpRELdXOy/P9adWxSw3F.IUfXxBoJ/kmzq.wq30I7pEKvZeNNJ5W', ''),
(4, 'user4@organization2.com', 'UserFour', 'Four', 'DepartmentTwo', '$2a$10$qSpRELdXOy/P9adWxSw3F.IUfXxBoJ/kmzq.wq30I7pEKvZeNNJ5W', '');

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
(7, 'Subworkspace 2.3', 4),
(8, 'Subworkspace 2.2.1', 6),
(9, 'Subworkspace 2.2.2', 6),
(10, 'Subworkspace 2.2.1.1', 8),
(11, 'Subworkspace 2.2.1.2', 8);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
