package com.gtm.proxiv4.dao;

import java.util.Date;

import org.apache.catalina.realm.RealmBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.gtm.proxiv4.metier.Adresse;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.CompteCourant;
import com.gtm.proxiv4.metier.CompteEpargne;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.metier.Role;

/**
 * pour test population de la base de données
 */
// @ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class RandomPopulateBdd {
	// PARAMETRES
	private static int NB_GERANTS = 1;
	private static int NB_MAX_CONSEILLERS_PAR_GERANT = 15;
	private static int NB_MAX_CLIENT_PAR_CONSEILLER = 15;

	private static String[] NOMS = { "DUPOND", "DURAND", "DOE" };
	private static String[] PRENOMS = { "Paul", "Jean", "Arthur", "Guy", "André", "Claudine", "Marie", "Léa" };
	private static String[] RUES = { "aaa", "bb", "cc" };
	private static String[] CODEPOSTALS = { "69000", "69001", "69002", "69003", "69004" };

	public static void main(String[] args) {

		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		AdresseRepository adresseRep = (AdresseRepository) appContext.getBean("adresseRepository");
		CompteRepository compteRep = (CompteRepository) appContext.getBean("compteRepository");
		ConseillerRepository conseillerRep = (ConseillerRepository) appContext.getBean("conseillerRepository");
		GerantRepository gerantRep = (GerantRepository) appContext.getBean("gerantRepository");
		ClientRepository clientRep = (ClientRepository) appContext.getBean("clientRepository");
		RoleRepository roleRep = (RoleRepository) appContext.getBean("roleRepository");

		for (int iGerant = 0; iGerant < NB_GERANTS; iGerant++) {

			// géneration etpersistance d'un gérant aléatoire
			Gerant gerant = randomGerant();
			gerantRep.save(gerant);

			// nombre aléatoire de conseillers
			int nbConseiller = 1 + (int) (Math.random() * NB_MAX_CONSEILLERS_PAR_GERANT);

			for (int iConseiller = 0; iConseiller > nbConseiller; iConseiller++) {

				Conseiller conseiller = randomConseiller();
				conseiller.setGerant(gerant);
				
				int nbClients = 1 + (int) (Math.random() * NB_MAX_CLIENT_PAR_CONSEILLER);
				
				
				//TODO
				
				
			}

		}

	}

	public static String randomNom() {
		int index = (int) Math.random() * NOMS.length;
		return NOMS[index];
	}

	public static String randomPrenom() {
		int index = (int) Math.random() * PRENOMS.length;
		return PRENOMS[index];
	}

	public static String randomRue() {
		int index = (int) Math.random() * RUES.length;
		return RUES[index];
	}

	public static String randomCP() {
		int index = (int) Math.random() * CODEPOSTALS.length;
		return CODEPOSTALS[index];
	}

	public static String randomTel() {

		return "0600000000";
	}

	public static Date randomDate() {

		return new Date();
	}

	public static String randomNomEntreprise() {
		int index = (int) Math.random() * NOMS.length;
		return NOMS[index];
	}

	public static Adresse randomAdresse() {

		Adresse a = new Adresse();
		a.setRue(randomRue());
		a.setCodePostal(randomCP());
		a.setVille("LYON");
		return a;
	}

	public static CompteEpargne randomCompteEpargne() {

		CompteEpargne c = new CompteEpargne();
		c.setDateOuverture(randomDate());
		c.setSolde(Math.random() * 300_000);
		c.setTaux((float) Math.random() * 3);

		return c;
	}

	public static CompteCourant randomCompteCourant() {

		CompteCourant c = new CompteCourant();
		c.setDateOuverture(randomDate());
		c.setSolde(Math.random() * 300_000 - Math.random() * 100_000);
		c.setDecouvert(500);

		return c;
	}

	public static Client randomClient() {

		Client c = new Client();
		c.setAdresse(randomAdresse());
		c.setNom(randomNom());
		c.setPrenom(randomPrenom());
		c.setEmail(c.getPrenom() + "." + c.getNom().toLowerCase() + "@test.fr");
		c.setTelephone(randomTel());
		c.setEntreprise(Math.random() > 0.9 ? true : false);
		if (c.isEntreprise()) {
			c.setNomEntreprise(randomNomEntreprise());
		}

		// comptes
		if (Math.random() < 0.9) {
			c.getComptes().add(randomCompteCourant());
		}
		if (Math.random() < 0.5) {
			c.getComptes().add(randomCompteEpargne());
		}

		return c;
	}

	public static Conseiller randomConseiller() {

		Conseiller c = new Conseiller();
		c.setAdresse(randomAdresse());
		c.setNom(randomNom());
		c.setPrenom(randomPrenom());
		c.setEmail(c.getPrenom() + "." + c.getNom().toLowerCase() + "@test.fr");
		c.setTelephone(randomTel());
		c.setPassword(RealmBase.Digest(c.getPrenom(), "SHA-1", "UTF-8"));

		return c;
	}

	public static Gerant randomGerant() {

		Gerant g = new Gerant();
		g.setAdresse(randomAdresse());
		g.setNom(randomNom());
		g.setPrenom(randomPrenom());
		g.setEmail(g.getPrenom() + "." + g.getNom().toLowerCase() + "@test.fr");
		g.setTelephone(randomTel());
		g.setPassword(RealmBase.Digest(g.getPrenom(), "SHA-1", "UTF-8"));

		return g;
	}
}
