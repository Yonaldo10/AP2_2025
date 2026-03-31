-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 17 mars 2026 à 18:23
-- Version du serveur : 11.8.3-MariaDB-log
-- Version de PHP : 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `u482683110_26ONAMB_BDD`
--

-- --------------------------------------------------------

--
-- Structure de la table `commentaires`
--

CREATE TABLE `commentaires` (
  `id` int(11) NOT NULL,
  `id_cr` bigint(20) NOT NULL,
  `id_prof` int(10) NOT NULL,
  `texte` text NOT NULL,
  `date` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `cr`
--

CREATE TABLE `cr` (
  `num` bigint(20) NOT NULL,
  `num_utilisateur` int(10) NOT NULL,
  `date` date NOT NULL,
  `description` text NOT NULL,
  `vu` tinyint(1) DEFAULT 0,
  `datetime` datetime DEFAULT current_timestamp(),
  `valide` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `cr`
--

INSERT INTO `cr` (`num`, `num_utilisateur`, `date`, `description`, `vu`, `datetime`, `valide`) VALUES
(33, 1, '2025-12-02', 'fefefefefefefe', 0, '2025-12-02 16:37:31', 1),
(34, 1, '2025-12-01', 'gggraezgazrgrzg', 0, '2025-12-02 16:37:45', 1),
(35, 0, '2025-11-13', 'dvdd', 0, '2025-12-02 16:38:41', 1),
(36, 1, '2025-12-15', 'ekieeeee', 0, '2025-12-15 08:05:56', 1),
(37, 1, '2025-12-11', 'ùùpùopùpùpùpùùùpùpùùpùpùpùpùppùpùp', 0, '2025-12-15 08:48:41', 1);

-- --------------------------------------------------------

--
-- Structure de la table `stage`
--

CREATE TABLE `stage` (
  `num` int(10) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `CP` int(10) DEFAULT NULL,
  `ville` varchar(40) DEFAULT NULL,
  `tel` int(30) DEFAULT NULL,
  `libelleStage` varchar(500) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `num_tuteur` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `tuteur`
--

CREATE TABLE `tuteur` (
  `num` int(10) NOT NULL,
  `nom` varchar(40) NOT NULL,
  `prenom` varchar(40) NOT NULL,
  `tel` int(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `num` int(10) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `tel` int(20) DEFAULT NULL,
  `login` varchar(100) NOT NULL,
  `motdepasse` varchar(100) NOT NULL,
  `type` int(1) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `option` int(1) DEFAULT NULL,
  `num_stage` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`num`, `nom`, `prenom`, `tel`, `login`, `motdepasse`, `type`, `email`, `option`, `num_stage`) VALUES
(0, 'Bazile', 'Noah', NULL, 'Noah Bazile', '$2y$10$AoOkCuICemn2PMrYqBWgW.Je92zUL2VcLlzD3Kh8UElnnt1MQTnOS', 2, 'noah.bazile92@gmail.com', NULL, NULL),
(1, 'Onambele', 'Yohann', NULL, 'Yohann Onambele', '$2y$10$AoOkCuICemn2PMrYqBWgW.Je92zUL2VcLlzD3Kh8UElnnt1MQTnOS', 2, 'yohann.onambele@gmail.com', NULL, NULL),
(2, 'Grimaud', 'Richard', NULL, 'Richard Grimaud', '$2y$10$AoOkCuICemn2PMrYqBWgW.Je92zUL2VcLlzD3Kh8UElnnt1MQTnOS', 1, 'richard.grimaud@gmail.com', NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cr` (`id_cr`),
  ADD KEY `id_prof` (`id_prof`);

--
-- Index pour la table `cr`
--
ALTER TABLE `cr`
  ADD PRIMARY KEY (`num`),
  ADD KEY `num_utilisateur` (`num_utilisateur`);

--
-- Index pour la table `stage`
--
ALTER TABLE `stage`
  ADD PRIMARY KEY (`num`),
  ADD KEY `num_tuteur` (`num_tuteur`);

--
-- Index pour la table `tuteur`
--
ALTER TABLE `tuteur`
  ADD PRIMARY KEY (`num`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`num`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `num_stage` (`num_stage`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `commentaires`
--
ALTER TABLE `commentaires`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `cr`
--
ALTER TABLE `cr`
  MODIFY `num` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `commentaires_ibfk_1` FOREIGN KEY (`id_cr`) REFERENCES `cr` (`num`) ON DELETE CASCADE,
  ADD CONSTRAINT `commentaires_ibfk_2` FOREIGN KEY (`id_prof`) REFERENCES `utilisateur` (`num`) ON DELETE CASCADE;

--
-- Contraintes pour la table `cr`
--
ALTER TABLE `cr`
  ADD CONSTRAINT `cr_ibfk_1` FOREIGN KEY (`num_utilisateur`) REFERENCES `utilisateur` (`num`);

--
-- Contraintes pour la table `stage`
--
ALTER TABLE `stage`
  ADD CONSTRAINT `stage_ibfk_1` FOREIGN KEY (`num_tuteur`) REFERENCES `tuteur` (`num`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`num_stage`) REFERENCES `stage` (`num`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
