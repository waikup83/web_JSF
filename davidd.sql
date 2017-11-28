-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Ven 10 Novembre 2017 à 02:18
-- Version du serveur :  10.1.9-MariaDB
-- Version de PHP :  5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `davidd`
--

-- --------------------------------------------------------

DROP TABLE utilisateurs;
DROP TABLE commentaires;
DROP TABLE images;

--
-- Structure de la table `commentaires`
--

CREATE TABLE `commentaires` (
  `ID_Comm` int(11) NOT NULL,
  `ID_Util` int(11) NOT NULL,
  `ID_Image` int(11) NOT NULL,
  `Date_Comm` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Commentaire` text CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `commentaires`
--

INSERT INTO `commentaires` (`ID_Comm`, `ID_Util`, `ID_Image`, `Date_Comm`, `Commentaire`) VALUES
(1, 1, 2, '2017-10-19 14:44:55', 'Commentaire constructif !!!');

-- --------------------------------------------------------

--
-- Structure de la table `images`
--

CREATE TABLE `images` (
  `ID_Image` int(11) NOT NULL,
  `ID_Util` int(11) NOT NULL,
  `Image` text CHARACTER SET utf8,
  `Titre_Image` text CHARACTER SET utf8,
  `Desc_Image` text CHARACTER SET utf8,
  `Date_Image` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `images`
--

INSERT INTO `images` (`ID_Image`, `ID_Util`, `Image`, `Titre_Image`, `Desc_Image`, `Date_Image`) VALUES
(2, 1, '1.jpg', 'Img1', 'Image #1', '2017-09-15 20:34:11'),
(3, 1, '2.jpg', 'Img2', 'Image #2', '2017-09-15 20:38:11'),
(4, 1, '3.jpg', 'Img3', 'Image #3', '2017-09-15 20:59:11'),
(5, 1, '4.jpg', 'Img4', 'Image #4', '2017-09-16 20:59:11');

-- --------------------------------------------------------

--
-- Structure de la table `tp1`
--

CREATE TABLE `tp1` (
  `ID` int(11) NOT NULL,
  `Image` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `Titre` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `Description` text CHARACTER SET utf8 COLLATE utf8_unicode_ci,
  `Date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `tp1`
--

INSERT INTO `tp1` (`ID`, `Image`, `Titre`, `Description`, `Date`) VALUES
(2, '1.jpg', 'Img1', 'Image #1', '2017-09-15 20:34:11'),
(3, '2.jpg', 'Img2', 'Image #2', '2017-09-15 20:38:11'),
(4, '3.jpg', 'Img3', 'Image #3', '2017-09-15 20:59:11'),
(5, '4.jpg', 'Img4', 'Image #4', '2017-09-16 20:59:11');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `ID_Util` int(11) NOT NULL,
  `Utilisateur` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `MotDePasse` text CHARACTER SET utf8 NOT NULL,
  `Type` char(1) CHARACTER SET utf8 NOT NULL DEFAULT 'U'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD PRIMARY KEY (`ID_Comm`);

--
-- Index pour la table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`ID_Image`);

--
-- Index pour la table `tp1`
--
ALTER TABLE `tp1`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`ID_Util`),
  ADD UNIQUE KEY `Utilisateur` (`Utilisateur`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `commentaires`
--
ALTER TABLE `commentaires`
  MODIFY `ID_Comm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `images`
--
ALTER TABLE `images`
  MODIFY `ID_Image` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `tp1`
--
ALTER TABLE `tp1`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `ID_Util` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
