
Ce document a pour but de vous expliquer les d�marches � effectuer afin de pouvoir d�ployer le fichier ProxiBanqueV4.war en toute simplicit�, et ainsi installer et ex�cuter l�application dans un environnement Windows ayant pour serveur d'application TomCat.

Le fichier Web archive (.war) contient tous les composants d'une application web de mani�re organis�e et hi�rarchis�e.Il a pour but de d�ployer l'application web sur l'environnement dans lequel va �voluer l'application.

REMARQUE PRELIMINAIRE :le programme d'installation requiert au moins la version 1.5 du kit de d�veloppement (JRE ou JDK) de la plate-forme Java Standard Edition. Si vous utilisez une version ant�rieure, la proc�dure d'installation ne configurera pas correctement le fichier WAR de l'application utilisateur. L'installation semblera r�ussir, mais vous rencontrerez des erreurs lorsque vous tenterez de d�marrer l'application utilisateur. Les �tapes 0 � 3 vous indiquent la proc�dure � effectuer pour installer la JRE de la plateforme JAVA.


Etape 0 : Syst�me d'exploitation et type de syst�me

=> Pour connaitre votre syst�me d'exploitation, vous devez vous rendre dans votre "Poste de travail" ou "Ordinateur".
=> Faire un clic droit dans la fen�tre � c�t� de votre disque dur et cliquez sur "Propri�t�s".
=> Vous pouvez maintenant lire dans "type de syst�me" la configuration de votre syst�me ( Exemple : Syst�me d'exploitation 64 Bits)
=> M�morisez votre syst�me d'exploitation et votre nombre de bits et passez � lՎtape suivante.

Etape 1 : T�l�chargement du JRE 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
=> Maintenant clicker sous download sur la version qui vous int�resse et qui d�pend de votre syst�me d'exploitation et de votre nombre de bits que vous avez trouv�s � lՎtape pr�c�dente.
=> Une fois le t�l�chargement termin� passer � lՎtape 2.

Etape 2 : Installation du JRE

=> Lancer l'ex�cutable et suivre l'assistant d'installation jusquՈ la fin.

Etape 3 : Ajouter les variables d'environnement 

=> Aller dans Ordinateur
=> Clic droit "propri�t�s
=> Param�tre syst�me avanc�
=> Cliquez sur "variable d'environnement"
=> Dans les variables d'utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du r�pertoire d'installation du JRE.
=> Si la variable n'existe pas, la cr�er, et ajouter le chemin comme pr�cedemment.  

Vous pouvez v�rifier si les variables d'environnements ont �t� correctement param�tr�es en lan�ant la commande "java -version" dans l'invite de commande. 
L'invite de commande doit indiquer le num�ro de version de votre JRE. Sinon veuillez refaire les �tapes pr�c�dentes.

Etape 4 : installer la base de donn�es fournie

=> Utilisez le Syst�me de Gestion de Base de Donn�es MySQL via phpMyAdmin
=> Le login utilisateur par d�faut doit �tre �root�
=> Il ne doit pas y avoir de mot de passe
=> Le port utilis� doit �tre 3306
=> Cr�ez une nouvelle base appel�e proxibanque
=> Cliquez sur import
=> S�lectionnez le fichier proxibanque.sql puis ouvrir
=> Cliquez sur ex�cuter
=> Votre base de donn�es est install�e

NOTE : le fichier DATA_Demo.sql fourni contient des donn�es de d�monstration qui peuvent �tre ins�r�es en base via le m�me m�canisme d'import.

Etape 5 : installer un serveur TomCat 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://tomcat.apache.org/download-90.cgi
=> Maintenant cliquer sur la version Core qui vous int�resse et qui d�pend de votre syst�me d'exploitation et du nombre de bits que vous avez trouv�s � lՎtape 0.
=> Cliquez sur "variable d'environnement"
=> Dans les variables d'environnement utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du r�pertoire d'installation du JRE.
=> Si la variable n'existe pas, la cr�er, et ajouter le chemin comme pr�c�demment.
=> Par ailleurs, il faudra remplacer le fichier context.xml du dossier \conf  se situant dans le r�pertoire d�installation de TomCat par le fichier context.xml fourni avec l�application


Etape 6 : d�ployer l'application dans TomCat

=> D�marrer Tomcat (bin/startup.bat)
=> Les fichiers de configuration sont d�j� configur�s pour le param�tre du port 8080. 
=> Vous devez v�rifier si votre syst�me h�te est sp�cifi� dans les fichiers de configuration et laisser le num�ro de port tel quel. 
=> Copiez le fichier ProxiBanqueV4.war fourni dans le dossier \webapps du repertoire d'installation de TomCat
=> Red�marrez Tomcat

Etape 6 : utiliser l'application

=> Allez sur l'url suivante : localhost:8080/ProxiBanqueV4/ 
=> Maintenant votre programme est lanc�.

NOTE : si vous avez install� les donn�es de d�mo � lՎtape 4, vous pouvez tester le logiciel :

- en vous connectant aux fonctionnalit�s du conseiller via les identifiants suivants, email : conseiller@test.com et Mot de passe : conseiller

- en vous connectant aux fonctionnalit�s du g�rant via les identifiants suivants, email : conseiller@test.com et Mot de passe : conseiller


=> Pour consulter la documentation (javadoc) de l'application, double-cliquez sur le fichier index.html se trouvant dans le repertoire "doc" 
livr� avec le dossier ProxiBanque.  

