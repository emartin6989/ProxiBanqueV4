-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Mar 23 Mai 2017 à 09:23
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `proxibanque`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id` bigint(20) NOT NULL,
  `codePostal` varchar(255) DEFAULT NULL,
  `rue` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `adresse`
--

INSERT INTO `adresse` (`id`, `codePostal`, `rue`, `ville`) VALUES
(8, '69006', '10 rue Brotteaux', 'LYON'),
(10, '69000', '1 rue Massena', 'LYON'),
(14, '01103', '742 Evergreen Terrace', 'SPRINGFIELD'),
(16, '01103', '742 Evergreen Terrace', 'SPRINGFIELD');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `TypeCompte` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `dateOuverture` datetime DEFAULT NULL,
  `solde` decimal(10,2) DEFAULT NULL,
  `decouvert` double DEFAULT NULL,
  `taux` float DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`TypeCompte`, `id`, `dateOuverture`, `solde`, `decouvert`, `taux`, `client_id`) VALUES
('CompteCourant', 11, '2017-05-23 11:23:11', '1000.00', 500, NULL, 9),
('CompteEpargne', 12, '2017-05-23 11:23:11', '3500.00', NULL, 3, 9);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(17),
(17),
(17),
(17),
(17),
(17);

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

CREATE TABLE `personne` (
  `TypePersonne` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `entreprise` bit(1) DEFAULT NULL,
  `nomEntreprise` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `adresse_id` bigint(20) DEFAULT NULL,
  `conseiller_id` bigint(20) DEFAULT NULL,
  `gerant_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `personne`
--

INSERT INTO `personne` (`TypePersonne`, `id`, `email`, `nom`, `prenom`, `telephone`, `entreprise`, `nomEntreprise`, `password`, `adresse_id`, `conseiller_id`, `gerant_id`) VALUES
('Gerant', 1, 'gerant@test.fr', 'GOSLING', 'James', '0678974518', NULL, NULL, 'e3c07fbecbf87a40d2e44e140f1faf1d9de256fe', NULL, NULL, NULL),
('Conseiller', 3, 'conseiller@test.fr', 'JAFFRE', 'Guy', '0678974518', NULL, NULL, 'e98d3ab2a856a616ce60a89fe7710b348a86c69c', NULL, NULL, 1),
('Conseiller', 5, 'conseiller2@test.fr', 'TOURNESOL', 'Tryphon', '0678974546', NULL, NULL, '9005bb5dfbbd5bbb2bfa4bd5d8ce7f22a39e6a85', NULL, NULL, 1),
('Client', 7, 'marie.durand@gmail.net', 'DURAND', 'Marie', '0678974518', b'1', 'GTM Ingénierie', NULL, 8, 3, NULL),
('Client', 9, 'laurent.dupond@free.net', 'DUPOND', 'Laurent', '0678569874', b'0', NULL, NULL, 10, 3, NULL),
('Client', 13, 'BGdeSpringfield@free.net', 'SIMPSOM', 'Homer', '0682731655', b'0', NULL, NULL, 14, 3, NULL),
('Client', 15, 'marge.simpson@free.net', 'SIMPSOM', 'Marge', '0682731678', b'0', NULL, NULL, 16, 3, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `email`, `role`) VALUES
(2, 'gerant@test.fr', 'GERANT'),
(4, 'conseiller@test.fr', 'CONSEILLER'),
(6, 'conseiller2@test.fr', 'CONSEILLER');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `montant` double NOT NULL,
  `compteCrediteur_id` bigint(20) DEFAULT NULL,
  `compteDebiteur_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmh0qrgi04rq9l7xlwlqbd0i8` (`client_id`);

--
-- Index pour la table `personne`
--
ALTER TABLE `personne`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5rytjhlr4eaj4tdbnsqp88064` (`adresse_id`),
  ADD KEY `FKacdxhvjl2obfks97yet4h02va` (`conseiller_id`),
  ADD KEY `FK8yty5oj9qksj7bkuecg3m5xy3` (`gerant_id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKornr8nel8y8rweoatqq2ycqvu` (`compteCrediteur_id`),
  ADD KEY `FKcnadat5yi7xhipjj8sgxn6svr` (`compteDebiteur_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
