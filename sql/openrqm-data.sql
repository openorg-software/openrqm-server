-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 15. Apr 2020 um 19:38
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
(1, 1, 2, '<h2>OpenRQM Desktop Client</h2><p>This OpenRQM Desktop Client is a <a href=\"https://nwjs.io/\">NW.js</a> project using <a href=\"https://cli.angular.io/\">angular-cli</a>.</p><figure class=\"image\"><img src=\"data:image/png;base64,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\" alt=\"OpenRQM\"><figcaption>OpenRQM</figcaption></figure>', 'aaaaaaiavncmrfhswswm', NULL),
(12, 3, 1, '<p>First element</p>', 'aaaaaaiavncmrfhswswm', NULL),
(20, 1, 2, '<h3>Content</h3><ul><li>OpenRQM Desktop Client<ul><li>Content</li><li>Hints fo reading the OpenRQM Client documentation</li><li>How to run / build<ul><li>Development server</li><li>Build</li></ul></li><li>Design &amp; Architecture</li><li>Features</li><li>License</li><li>Copyright</li></ul></li></ul><h3>Hints for reading the OpenRQM Client documentation</h3><p>The documents can be read best using <a href=\"https://code.visualstudio.com/\">Visual Studio Code</a> or <a href=\"https://atom.io/\">Atom</a> using the <a href=\"https://shd101wyy.github.io/markdown-preview-enhanced/#/\">Markdown Preview Enhanced</a> extension since all drawings are created using <a href=\"http://plantuml.com/\">PlantUML</a>.</p><h3>How to run / build</h3><p>This project was generated with <a href=\"https://github.com/angular/angular-cli\">Angular CLI</a> version 8.3.5.</p><h4>Build</h4><p>Run <i>ng build</i> to build the project. The build artifacts will be stored in the <i>dist/</i> directory. Use the <i>--prod</i> flag for a production build.</p><p>Then the nw-package.json has to be copied to the <i>dist/openrqm-client-desktop-nwjs/</i> directory.</p><p>Afterwards running <i>&lt;Path to nw.js&gt;\\nw.exe dist/openrqm-client-desktop-nwjs/.</i> on Windows or <i>&lt;Path to nw&gt;/nw dist/openrqm-client-desktop-nwjs/.</i> on Linux can be used to run the application.</p><p>To package the software for release please follow the guildelines of <a href=\"http://docs.nwjs.io/en/latest/For%20Users/Package%20and%20Distribute/\">nw.js</a>.</p><h4>Development server</h4><p>Run <i>ng serve</i> for a dev server. Navigate to <i>http://localhost:4200/</i>. The app will automatically reload if you change any of the source files.</p><h3>Design &amp; Architecture</h3><p>The design and architecture is described in the documents in the <i>doc</i> directory.</p><h3>Features</h3><figure class=\"table\"><table><thead><tr><th>Feature</th><th>Status</th><th>Release</th></tr></thead><tbody><tr><td>Basic workspace explorer &amp; document viewer</td><td>done</td><td>MVP</td></tr><tr><td>User management</td><td>done</td><td>MVP</td></tr><tr><td>Linking</td><td>done</td><td>MVP</td></tr><tr><td>PDF export</td><td>done</td><td>MVP</td></tr><tr><td>Multiple views per document</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>Baselining</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>Shared edit</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>Multimedia content (figures, tables)</td><td>done</td><td>&nbsp;</td></tr><tr><td>Tracing Graphs</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table></figure><h3>License</h3><p>SPDX-License-Identifier: GPL-2.0-only</p>', 'aaaaaambggqszuypichs', NULL),
(21, 1, 1, '<blockquote><p>Requirements Management has to be easy.</p><p>- OpenRQM</p></blockquote>', 'aaaaaakbawwpvndeckpc', NULL);

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

INSERT INTO `link` (`id`, `from_element_id`, `from_document_id`, `to_element_id`, `to_document_id`, `link_type_id`) VALUES
(3, 21, 1, 12, 3, 1),
(4, 20, 1, 1, 1, 2),
(5, 12, 3, 1, 1, 1),
(6, 12, 3, 12, 3, 1),
(7, 1, 1, 1, 1, 1),
(8, 1, 1, 12, 3, 1),
(9, 21, 1, 12, 3, 1),
(10, 21, 1, 12, 3, 1),
(11, 21, 1, 12, 3, 1),
(12, 21, 1, 20, 1, 1);

--
-- Daten für Tabelle `link_type`
--

INSERT INTO `link_type` (`id`, `name_from`, `name_to`) VALUES
(1, 'realized by', 'realizes'),
(2, 'tested by', 'tests'),
(3, 'used by', 'uses');

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
(4, 'user4@organization2.com', 'UserFour', 'Four', 'DepartmentTwo', '$2a$10$qSpRELdXOy/P9adWxSw3F.IUfXxBoJ/kmzq.wq30I7pEKvZeNNJ5W', ''),
(5, 'hunter@openrqm.org', 'Hunter', 'Dev', 'Developers', '$2a$12$Y2iS8.0OUASrvqvdekRSHeDxXg0rIdfis4A1LTjCXPZLNRdrpAps6', 'lLhlPNrXERAXfBFfVTg3S3c8yFtr2Zt9');

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

--
-- Daten für Tabelle `workspace_user`
--

INSERT INTO `workspace_user` (`workspace_id`, `user_id`, `permissions`) VALUES
(1, 1, 0),
(1, 2, 0),
(2, 3, 0),
(2, 4, 0);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
