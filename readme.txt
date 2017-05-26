
Ce document a pour but de vous expliquer les demarches a effectuer afin de pouvoir deployer le fichier ProxiBanque.war en toute simplicite, et ainsi installer et executer l'application dans un environnement Windows ayant pour serveur d'application TomCat.

Le fichier Web archive (.war) contient tous les composants d'une application web de manire organisee et hierarchisee.Il a pour but de deployer l'application web sur l'environnement dans lequel va evoluer l'application.

REMARQUE PRELIMINAIRE :le programme d'installation requiert au moins la version 1.5 du kit de developpement (JRE ou JDK) de la plate-forme Java Standard Edition. Si vous utilisez une version anterieure, la procedure d'installation ne configurera pas correctement le fichier WAR de l'application utilisateur. L'installation semblera reussir, mais vous rencontrerez des erreurs lorsque vous tenterez de demarrer l'application utilisateur. Les etapes 0 a 3 vous indiquent la procedure a effectuer pour installer la JRE de la plateforme JAVA.


Etape 0 : Systeme d'exploitation et type de systeme

=> Pour connaitre votre systeme d'exploitation, vous devez vous rendre dans votre "Poste de travail" ou "Ordinateur".
=> Faire un clic droit dans la fentre a cote de votre disque dur et cliquez sur "Proprietes".
=> Vous pouvez maintenant lire dans "type de systeme" la configuration de votre systeme ( Exemple : Systeme d'exploitation 64 Bits)
=> Memorisez votre systeme d'exploitation et votre nombre de bits et passez a l'etape suivante.

Etape 1 : Telechargement du JRE 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
=> Maintenant cliquer sous download sur la version qui vous interesse et qui depend de votre systme d'exploitation et de votre nombre de bits que vous avez trouves a l'etape precedente.
=> Une fois le telechargement termine passer a l'etape 2.

Etape 2 : Installation du JRE

=> Lancer l'executable et suivre l'assistant d'installation jusqu'a la fin.

Etape 3 : Ajouter les variables d'environnement 

=> Aller dans Ordinateur
=> Clic droit "proprietes
=> Parametre systeme avance
=> Cliquer sur "variable d'environnement"
=> Dans les variables d'utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du repertoire d'installation du JRE.
=> Si la variable n'existe pas, la creer, et ajouter le chemin comme precedemment.  

Vous pouvez verifier si les variables d'environnements ont ete correctement parametrees en lancant la commande "java -version" dans l'invite de commande. 
L'invite de commande doit indiquer le numero de version de votre JRE. Sinon veuillez refaire les etapes precedentes.

Etape 4 : installer la base de donnees fournie

=> Utilisez le Systme de Gestion de Base de Donnees MySQL via phpMyAdmin
=> Le login utilisateur par defaut doit etre 'root'
=> Il ne doit pas y avoir de mot de passe
=> Le port utilise doit etre 3306
=> Creez une nouvelle base appelée proxibanque
=> Cliquer sur import
=> Selectionnez le fichier proxibanque.sql dans le dossier dans le dossier fichiersConfig puis ouvrir
=> Cliquer sur executer
=> Votre base de donnees est installee

NOTE : le fichier proxibanque_demo.sql fourni dans le dossier fichiersConfig contient des donnees de demonstration qui peuvent etre inserees en base via le meme mecanisme d'import.

Etape 5 : installer un serveur TomCat 

=> Ouvrir un navigateur internet et coller l'URL suivante => http://tomcat.apache.org/download-90.cgi
=> Maintenant cliquer sur la version Core qui vous interesse et qui depend de votre systeme d'exploitation et du nombre de bits que vous avez trouves a l'etape 0.
=> Cliquer sur "variable d'environnement"
=> Dans les variables d'environnement utilisateur, si une variable "Path" existe, ajouter le chemin vers le dossier "bin" du repertoire d'installation du JRE.
=> Si la variable n'existe pas, la creer, et ajouter le chemin comme precedemment.

=> Par ailleurs, il faudra copier le texte fourni dans le fichier context.xml dans le dossier fichiersConfig fourni avec l'application
 à l'intérieur des balises <Context> </Context> dans le fichier context.xml du dossier \conf se situant dans le repertoire d'installation de TomCat, 

Etape 6 : deployer l'application dans TomCat

=> Demarrer Tomcat (bin/startup.bat)
=> Les fichiers de configuration sont deja configures pour le parametre du port 8080. 
=> Vous devez verifier si votre systme hote est specifie dans les fichiers de configuration et laisser le numero de port tel quel. 
=> Copier le fichier ProxiBanque.war fourni dans le dossier \webapps du repertoire d'installation de TomCat
=> Redemarrez Tomcat

Etape 7 : utiliser l'application

=> Allez sur l'url suivante : localhost:8080/ProxiBanque/ 
=> Maintenant votre programme est lance.
=> Le WebService pour acceder a la liste des conseillers  est accessisble à l'adresse url suivante : localhost:8080/ProxiBanque/rest/conseillers
=> Le WebService pour accéder aux coordonnees de son conseiller est accessisble à l'aide de l'adresse mail du client, par exemple : localhost:8080/ProxiBanque/rest/monconseiller/arthur.emin@test.fr (si vous avez installe la base de donnees de démonstration)

NOTE : si vous avez installe les donnees de demo a l'etape 4, vous pouvez tester le logiciel à l'aide des informations contenues dans le fichier info_utilisation_proxibanque_demo.txt du dossier fichiersConfig


=> Pour consulter la documentation (javadoc) de l'application, double-cliquez sur le fichier index.html se trouvant dans le repertoire "doc" 
livre avec le dossier ProxiBanque.  


