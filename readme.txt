
Ce document a pour but de vous expliquer les démarches à effectuer afin de pouvoir déployer le fichier ProxiBanqueV4.war en toute simplicité, et ainsi installer et exécuter l’application dans un environnement Windows ayant pour serveur d'application TomCat.

Le fichier Web archive (.war) contient tous les composants d'une application web de manière organisée et hiérarchisée.Il a pour but de déployer l'application web sur l'environnement dans lequel va évoluer l'application.

REMARQUE PRELIMINAIRE :le programme d'installation requiert au moins la version 1.5 du kit de développement (JRE ou JDK) de la plate-forme Java Standard Edition. Si vous utilisez une version antérieure, la procédure d'installation ne configurera pas correctement le fichier WAR de l'application utilisateur. L'installation semblera réussir, mais vous rencontrerez des erreurs lorsque vous tenterez de démarrer l'application utilisateur. Les étapes 0 à 3 vous indiquent la procédure à effectuer pour installer la JRE de la plateforme JAVA.


Etape 0 : Système d'exploitation et type de système

=> Pour connaitre votre système d'exploitation, vous devez vous rendre dans votre "Poste de travail" ou "Ordinateur".
=> Faire un clic droit dans la fenêtre à côté de votre disque dur et cliquez sur "Propriétés".
=> Vous pouvez maintenant lire dans "type de système" la configuration de votre système ( Exemple : Système d'exploitation 64 Bits)
=> Mémorisez votre système d'exploitation et votre nombre de bits et passez à l’étape suivante.

Etape 1 : Téléchargement du JRE 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
=> Maintenant clicker sous download sur la version qui vous intéresse et qui dépend de votre système d'exploitation et de votre nombre de bits que vous avez trouvés à l’étape précédente.
=> Une fois le téléchargement terminé passer à l’étape 2.

Etape 2 : Installation du JRE

=> Lancer l'exécutable et suivre l'assistant d'installation jusqu’à la fin.

Etape 3 : Ajouter les variables d'environnement 

=> Aller dans Ordinateur
=> Clic droit "propriétés
=> Paramètre système avancé
=> Cliquez sur "variable d'environnement"
=> Dans les variables d'utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du répertoire d'installation du JRE.
=> Si la variable n'existe pas, la créer, et ajouter le chemin comme précedemment.  

Vous pouvez vérifier si les variables d'environnements ont été correctement paramétrées en lançant la commande "java -version" dans l'invite de commande. 
L'invite de commande doit indiquer le numéro de version de votre JRE. Sinon veuillez refaire les étapes précédentes.

Etape 4 : installer la base de données fournie

=> Utilisez le Système de Gestion de Base de Données MySQL via phpMyAdmin
=> Le login utilisateur par défaut doit être ‘root’
=> Il ne doit pas y avoir de mot de passe
=> Le port utilisé doit être 3306
=> Créez une nouvelle base appelÈe proxibanque
=> Cliquez sur import
=> Sélectionnez le fichier proxibanque.sql puis ouvrir
=> Cliquez sur exécuter
=> Votre base de données est installée

NOTE : le fichier DATA_Demo.sql fourni contient des données de démonstration qui peuvent être insérées en base via le même mécanisme d'import.

Etape 5 : installer un serveur TomCat 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://tomcat.apache.org/download-90.cgi
=> Maintenant cliquer sur la version Core qui vous intéresse et qui dépend de votre système d'exploitation et du nombre de bits que vous avez trouvés à l’étape 0.
=> Cliquez sur "variable d'environnement"
=> Dans les variables d'environnement utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du répertoire d'installation du JRE.
=> Si la variable n'existe pas, la créer, et ajouter le chemin comme précédemment.
=> Par ailleurs, il faudra remplacer le fichier context.xml du dossier \conf  se situant dans le répertoire d’installation de TomCat par le fichier context.xml fourni avec l’application


Etape 6 : déployer l'application dans TomCat

=> Démarrer Tomcat (bin/startup.bat)
=> Les fichiers de configuration sont déjà configurés pour le paramétre du port 8080. 
=> Vous devez vérifier si votre système hôte est spécifié dans les fichiers de configuration et laisser le numéro de port tel quel. 
=> Copiez le fichier ProxiBanqueV4.war fourni dans le dossier \webapps du repertoire d'installation de TomCat
=> Redémarrez Tomcat

Etape 6 : utiliser l'application

=> Allez sur l'url suivante : localhost:8080/ProxiBanqueV4/ 
=> Maintenant votre programme est lancé.

NOTE : si vous avez installé les données de démo à l’étape 4, vous pouvez tester le logiciel :

- en vous connectant aux fonctionnalités du conseiller via les identifiants suivants, email : conseiller@test.com et Mot de passe : conseiller

- en vous connectant aux fonctionnalités du gérant via les identifiants suivants, email : conseiller@test.com et Mot de passe : conseiller


=> Pour consulter la documentation (javadoc) de l'application, double-cliquez sur le fichier index.html se trouvant dans le repertoire "doc" 
livré avec le dossier ProxiBanque.  

