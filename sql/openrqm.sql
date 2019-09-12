-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Sep 2019 um 21:35
-- Server-Version: 10.4.6-MariaDB
-- PHP-Version: 7.3.9
-- SPDX-License-Identifier: GPL-2.0-only

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

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `accessgroup`
--

CREATE TABLE `accessgroup` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `accessgroup_user`
--

CREATE TABLE `accessgroup_user` (
  `accessgroup_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `document`
--

CREATE TABLE `document` (
  `id` int(10) UNSIGNED NOT NULL,
  `workspace_id` int(10) UNSIGNED NOT NULL,
  `internal_identifier` int(10) UNSIGNED NOT NULL,
  `external_identifier` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT 'NULL',
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `short_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `confidentiality` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'NULL',
  `author_id` int(10) UNSIGNED NOT NULL,
  `language_id` int(10) UNSIGNED NOT NULL,
  `approver_id` int(10) UNSIGNED DEFAULT NULL,
  `reviewer_text` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_modified_by_id` int(10) UNSIGNED NOT NULL,
  `last_modified_on` date NOT NULL,
  `baseline_major` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `baseline_minor` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `baseline_review` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `previous_baseline_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `element`
--

CREATE TABLE `element` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `document_id` int(10) UNSIGNED NOT NULL,
  `element_type_id` int(10) UNSIGNED NOT NULL,
  `content` varchar(2000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rank` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `parent_element_id` bigint(20) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `element_type`
--

CREATE TABLE `element_type` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `link`
--

CREATE TABLE `link` (
  `id` int(10) UNSIGNED NOT NULL,
  `from_element_id` bigint(20) UNSIGNED NOT NULL,
  `to_element_id` bigint(20) UNSIGNED NOT NULL,
  `link_type_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `link_type`
--

CREATE TABLE `link_type` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reviewer`
--

CREATE TABLE `reviewer` (
  `document_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `id` int(10) UNSIGNED NOT NULL,
  `email` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `surname` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `department` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_hash` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password_salt` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `workspace`
--

CREATE TABLE `workspace` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `workspace_id` int(10) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `workspace_accessgroup`
--

CREATE TABLE `workspace_accessgroup` (
  `workspace_id` int(10) UNSIGNED NOT NULL,
  `accessgroup_id` int(10) UNSIGNED NOT NULL,
  `permissions` int(10) UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `workspace_user`
--

CREATE TABLE `workspace_user` (
  `workspace_id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `permissions` int(10) UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `accessgroup`
--
ALTER TABLE `accessgroup`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name_unique` (`name`);

--
-- Indizes für die Tabelle `accessgroup_user`
--
ALTER TABLE `accessgroup_user`
  ADD PRIMARY KEY (`accessgroup_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `document`
--
ALTER TABLE `document`
  ADD PRIMARY KEY (`id`),
  ADD KEY `workspace_id` (`workspace_id`),
  ADD KEY `approver_id` (`approver_id`),
  ADD KEY `author_id` (`author_id`),
  ADD KEY `last_modified_by_id` (`last_modified_by_id`),
  ADD KEY `previous_baseline_id` (`previous_baseline_id`);

--
-- Indizes für die Tabelle `element`
--
ALTER TABLE `element`
  ADD PRIMARY KEY (`id`),
  ADD KEY `rank_index` (`rank`),
  ADD KEY `document_id` (`document_id`),
  ADD KEY `parent_element_id` (`parent_element_id`),
  ADD KEY `element_type_id` (`element_type_id`);

--
-- Indizes für die Tabelle `element_type`
--
ALTER TABLE `element_type`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `link`
--
ALTER TABLE `link`
  ADD PRIMARY KEY (`id`),
  ADD KEY `from_element_id` (`from_element_id`),
  ADD KEY `to_element_id` (`to_element_id`),
  ADD KEY `link_type_id` (`link_type_id`);

--
-- Indizes für die Tabelle `link_type`
--
ALTER TABLE `link_type`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `reviewer`
--
ALTER TABLE `reviewer`
  ADD PRIMARY KEY (`document_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_email` (`email`);

--
-- Indizes für die Tabelle `workspace`
--
ALTER TABLE `workspace`
  ADD PRIMARY KEY (`id`),
  ADD KEY `workspace_id` (`workspace_id`);

--
-- Indizes für die Tabelle `workspace_accessgroup`
--
ALTER TABLE `workspace_accessgroup`
  ADD PRIMARY KEY (`workspace_id`,`accessgroup_id`),
  ADD KEY `accessgroup_id` (`accessgroup_id`);

--
-- Indizes für die Tabelle `workspace_user`
--
ALTER TABLE `workspace_user`
  ADD PRIMARY KEY (`workspace_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `accessgroup`
--
ALTER TABLE `accessgroup`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `document`
--
ALTER TABLE `document`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `element`
--
ALTER TABLE `element`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `element_type`
--
ALTER TABLE `element_type`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `link`
--
ALTER TABLE `link`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `link_type`
--
ALTER TABLE `link_type`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `workspace`
--
ALTER TABLE `workspace`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `accessgroup_user`
--
ALTER TABLE `accessgroup_user`
  ADD CONSTRAINT `accessgroup_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `accessgroup_user_ibfk_2` FOREIGN KEY (`accessgroup_id`) REFERENCES `accessgroup` (`id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `document`
--
ALTER TABLE `document`
  ADD CONSTRAINT `document_ibfk_1` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `document_ibfk_2` FOREIGN KEY (`author_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `document_ibfk_3` FOREIGN KEY (`approver_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `document_ibfk_4` FOREIGN KEY (`last_modified_by_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `document_ibfk_5` FOREIGN KEY (`previous_baseline_id`) REFERENCES `document` (`id`);

--
-- Constraints der Tabelle `element`
--
ALTER TABLE `element`
  ADD CONSTRAINT `element_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `element_ibfk_2` FOREIGN KEY (`parent_element_id`) REFERENCES `element` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `element_ibfk_3` FOREIGN KEY (`element_type_id`) REFERENCES `element_type` (`id`);

--
-- Constraints der Tabelle `link`
--
ALTER TABLE `link`
  ADD CONSTRAINT `link_ibfk_1` FOREIGN KEY (`from_element_id`) REFERENCES `element` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `link_ibfk_2` FOREIGN KEY (`to_element_id`) REFERENCES `element` (`id`),
  ADD CONSTRAINT `link_ibfk_3` FOREIGN KEY (`link_type_id`) REFERENCES `link_type` (`id`);

--
-- Constraints der Tabelle `reviewer`
--
ALTER TABLE `reviewer`
  ADD CONSTRAINT `reviewer_ibfk_1` FOREIGN KEY (`document_id`) REFERENCES `document` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `reviewer_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints der Tabelle `workspace`
--
ALTER TABLE `workspace`
  ADD CONSTRAINT `workspace_ibfk_1` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `workspace_accessgroup`
--
ALTER TABLE `workspace_accessgroup`
  ADD CONSTRAINT `workspace_accessgroup_ibfk_1` FOREIGN KEY (`accessgroup_id`) REFERENCES `accessgroup` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `workspace_accessgroup_ibfk_2` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `workspace_user`
--
ALTER TABLE `workspace_user`
  ADD CONSTRAINT `workspace_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `workspace_user_ibfk_2` FOREIGN KEY (`workspace_id`) REFERENCES `workspace` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
