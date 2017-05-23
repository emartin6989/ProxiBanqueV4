package com.gtm.proxiv4.dao;

import java.util.Date;

import org.apache.catalina.realm.RealmBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.gtm.proxiv4.metier.Adresse;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.CompteCourant;
import com.gtm.proxiv4.metier.CompteEpargne;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.metier.Role;

/**
 * pour test population de la base de données
 */
// @ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class PopulateBdd {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		AdresseRepository adresseRep = (AdresseRepository) appContext.getBean("adresseRepository");
		CompteRepository compteRep = (CompteRepository) appContext.getBean("compteRepository");
		ConseillerRepository conseillerRep = (ConseillerRepository) appContext.getBean("conseillerRepository");
		GerantRepository gerantRep = (GerantRepository) appContext.getBean("gerantRepository");
		ClientRepository clientRep = (ClientRepository) appContext.getBean("clientRepository");
		RoleRepository roleRep = (RoleRepository) appContext.getBean("roleRepository");

		
		
		/** ajout clients **/
		
		Adresse a1 = new Adresse();
		a1.setRue("1 rue Massena");
		a1.setCodePostal("69000");
		a1.setVille("LYON");


		Client clientPart1 = new Client();
		clientPart1.setAdresse(a1);
		clientPart1.setNom("DUPOND");
		clientPart1.setEntreprise(false);
		clientPart1.setPrenom("Laurent");
		clientPart1.setEmail("laurent.dupond@free.net");
		clientPart1.setTelephone("0678569874");

		Adresse a2 = new Adresse();
		a2.setRue("742 Evergreen Terrace");
		a2.setCodePostal("01103");
		a2.setVille("SPRINGFIELD");
		
		Client clientPart2 = new Client();
		clientPart2.setAdresse(a2);
		clientPart2.setNom("SIMPSOM");
		clientPart2.setEntreprise(false);
		clientPart2.setPrenom("Homer");
		clientPart2.setEmail("BGdeSpringfield@free.net");
		clientPart2.setTelephone("0682731655");
		
		Adresse a3 = new Adresse();
		a3.setRue("742 Evergreen Terrace");
		a3.setCodePostal("01103");
		a3.setVille("SPRINGFIELD");
		
		Client clientPart3 = new Client();
		clientPart3.setAdresse(a3);
		clientPart3.setNom("SIMPSOM");
		clientPart3.setEntreprise(false);
		clientPart3.setPrenom("Marge");
		clientPart3.setEmail("marge.simpson@free.net");
		clientPart3.setTelephone("0682731678");

		Adresse ae1 = new Adresse();
		ae1.setRue("10 rue Brotteaux");
		ae1.setCodePostal("69006");
		ae1.setVille("LYON");

		Client clientEntr1 = new Client();
		clientEntr1.setAdresse(ae1);
		clientEntr1.setNom("DURAND");
		clientEntr1.setPrenom("Marie");
		clientEntr1.setEntreprise(true);
		clientEntr1.setEmail("marie.durand@gmail.net");
		clientEntr1.setTelephone("0678974518");
		clientEntr1.setNomEntreprise("GTM Ingénierie");
		
		Adresse ae2 = new Adresse();
		ae2.setRue("10 rue du port");
		ae2.setCodePostal("13000");
		ae2.setVille("MARSEILLE");
		
		Client clientEntr2 = new Client();
		clientEntr2.setAdresse(ae2);
		clientEntr2.setNom("MENSOIF");
		clientEntr2.setPrenom("Gérard");
		clientEntr2.setEntreprise(true);
		clientEntr2.setEmail("ggdu13@hotmel.net");
		clientEntr2.setTelephone("0678977589");
		clientEntr2.setNomEntreprise("La Sardinerie du port");
		
		
		/** ajout Conseillers clientèle */

		Conseiller conseiller1 = new Conseiller();
		conseiller1.setNom("JAFFRE");
		conseiller1.setPrenom("Guy");
		conseiller1.setEmail("conseiller@test.fr");
		conseiller1.setTelephone("0678974518");
		conseiller1.setPassword("conseiller");
		conseiller1.setPassword(RealmBase.Digest(conseiller1.getPassword(), "SHA-1", "UTF-8"));

		conseiller1.getClients().add(clientPart1);
		conseiller1.getClients().add(clientPart2);
		conseiller1.getClients().add(clientPart3);
		conseiller1.getClients().add(clientEntr1);

		clientPart1.setConseiller(conseiller1);
		clientPart2.setConseiller(conseiller1);
		clientPart3.setConseiller(conseiller1);
		clientEntr1.setConseiller(conseiller1);

		Role roleConseiller1 = new Role();
		roleConseiller1.setEmail(conseiller1.getEmail());
		roleConseiller1.setRole("CONSEILLER");
		
		
		Conseiller conseiller2 = new Conseiller();
		conseiller2.setNom("TOURNESOL");
		conseiller2.setPrenom("Tryphon");
		conseiller2.setEmail("conseiller2@test.fr");
		conseiller2.setTelephone("0678974546");
		conseiller2.setPassword("conseiller2");
		conseiller2.setPassword(RealmBase.Digest(conseiller2.getPassword(), "SHA-1", "UTF-8"));
		
		Role roleConseiller2 = new Role();
		roleConseiller2.setEmail(conseiller2.getEmail());
		roleConseiller2.setRole("CONSEILLER");
		

		/** ajout gerant **/
		Gerant gerant1 = new Gerant();
		gerant1.setNom("GOSLING");
		gerant1.setPrenom("James");
		gerant1.setEmail("gerant@test.fr");
		gerant1.setTelephone("0678974518");
		gerant1.setPassword("gerant");
		gerant1.setPassword(RealmBase.Digest(gerant1.getPassword(), "SHA-1", "UTF-8"));

		gerant1.getConseillers().add(conseiller1);
		conseiller1.setGerant(gerant1);
		gerant1.getConseillers().add(conseiller2);
		conseiller2.setGerant(gerant1);

		Role roleGerant1 = new Role();
		roleGerant1.setEmail(gerant1.getEmail());
		roleGerant1.setRole("GERANT");

		/** création des comptes **/
		
		CompteCourant cc1 = new CompteCourant();
		cc1.setDecouvert(500);
		cc1.setSolde(4799.88);
		cc1.setDateOuverture(new Date());
		cc1.setClient(clientPart1);
		clientPart1.getComptes().add(cc1);
		
		CompteCourant cc2 = new CompteCourant();
		cc2.setDecouvert(6000);
		cc2.setSolde(-5099.45);
		cc2.setDateOuverture(new Date());
		cc2.setClient(clientPart2);
		clientPart2.getComptes().add(cc2);
		
		CompteCourant cc3 = new CompteCourant();
		cc3.setDecouvert(500);
		cc3.setSolde(1086.25);
		cc3.setDateOuverture(new Date());
		cc3.setClient(clientPart3);
		clientPart3.getComptes().add(cc3);
		
		CompteCourant cc4 = new CompteCourant();
		cc4.setDecouvert(5000);
		cc4.setSolde(58007.78);
		cc4.setDateOuverture(new Date());
		cc4.setClient(clientEntr1);
		clientEntr1.getComptes().add(cc4);
		
		CompteCourant cc5 = new CompteCourant();
		cc5.setDecouvert(60000);
		cc5.setSolde(-59317.78);
		cc5.setDateOuverture(new Date());
		cc5.setClient(clientEntr2);
		clientEntr2.getComptes().add(cc5);
		
		CompteEpargne ce1 = new CompteEpargne();
		ce1.setTaux(1.75f);
		ce1.setSolde(3012.42);
		ce1.setDateOuverture(new Date());
		ce1.setClient(clientPart1);
		clientPart1.getComptes().add(ce1);
		
		CompteEpargne ce3 = new CompteEpargne();
		ce3.setTaux(0.75f);
		ce3.setSolde(1000);
		ce3.setDateOuverture(new Date());
		ce3.setClient(clientPart3);
		clientPart3.getComptes().add(ce3);
		
		/** persitance **/

		gerantRep.save(gerant1);
		roleRep.save(roleGerant1);

		conseillerRep.save(conseiller1);
		roleRep.save(roleConseiller1);
		conseillerRep.save(conseiller2);
		roleRep.save(roleConseiller2);

		clientRep.save(clientEntr1);
		clientRep.save(clientPart1);
		clientRep.save(clientPart2);
		clientRep.save(clientPart3);


	}

}
