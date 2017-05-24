package com.gtm.proxiv4.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.catalina.realm.RealmBase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gtm.proxiv4.metier.Adresse;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.CompteCourant;
import com.gtm.proxiv4.metier.CompteEpargne;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.metier.Role;
import com.gtm.proxiv4.metier.Transaction;

/**
 * pour test population de la base de données
 */
public class RandomPopulateBdd {
	// PARAMETRES
	private static int NB_GERANTS = 3;
	private static int NB_MAX_CONSEILLERS_PAR_GERANT = 20;
	private static int NB_MAX_CLIENT_PAR_CONSEILLER = 10;
	private static int DEBUT_DES_TRANSACTION_EN_MOIS = 6;

	
	//pool de données pour random
	private static String[] NOMS = { "DUPOND", "DURAND", "DOE", "MARTIN", "MICHEL", "OTHMANE", "IZARD", "JEANJACQUOT",
			"BONHOMME", "ESPUCHE", "EMIN", "SALOMON", "AYRAUT", "LEGAL", "VILLAR", "MARIN" };
	private static String[] PRENOMS = { "Paul", "Jean", "Arthur", "Guy", "Claude", "Claudine", "Marie", "Leo", "Thomas",
			"Guillaume", "Jerome", "Emmanuel", "Pierre", "Manon", "Perrine", "Marine", "Stephane", "Vincent", "Florent",
			"Theo", "Eddy", "Douglas", "Nassur", "Amel" };
	private static String[] RUES = { "Rue de l'Église", "Place de l'Église", "Grande Rue", "Rue du Moulin",
			"Place de la Mairie", "Rue du Château", "Rue des Écoles", "Rue de la Gare", "Rue de la Mairie",
			"Rue Principale", "Rue du Stade", "Rue de la Fontaine", "Rue Pasteur", "Rue des Jardins", "Rue Victor-Hugo",
			"Rue du chat qui tousse", "Rue de la soif", "Place des Quinconces", "Place de la Concorde", "Cours Léopold",
			"Place Bellecour", "Place Charles-de-Gaulle", "Place du Pâtis", "Place de la Comédie", "Place de Jaude",
			"Place de la République", "Place Denfert-Rochereau", "Place des Palmistes", "Place Saint-Nicolas",
			"Place Jean-Jaurès", "Grand-Place", "Place des Vosges", "Parc Salvator", "Place de la Liberté",
			"Place Stanislas", "Place du Capitole", "Place Ducale", "Place du Général-de-Gaulle",
			"Place du Ralliement" };
	private static String[] CODEPOSTALS = { "69000", "69001", "69002", "69003", "69004", "69005", "69006", "69007",
			"69008", "69009" };
	private static String[] NOMS_ENTREPRISES = { "Accor", "Air Liquide", "Alstom", "Arcelor-Mittal", "AXA",
			"BNP Paribas", "Bouygues", "Cap Gemini", "Carrefour", "Crédit agricole", "Danone", "EADS", "EDF", "Essilor",
			"France Télécom", "GDF Suez", "Gemalto", "L'Oréal", "Lafarge", "Legrand", "LVMH", "Michelin",
			"Pernod Ricard", "Kering", "Publicis", "Renault", "Safran", "Saint-Gobain", "Sanofi", "Schneider Electric",
			"Société Générale", "Solvay", "STMicroelectronics", "Technip", "Total", "Unibail-Rodamco", "Vallourec",
			"Véolia Environnement", "Vinci", "Vivendi" };

	//reminders
	private static List<String> usedEmail;
	private static List<Compte> existingAccounts;
	private static Date lastTransactionDate;

	public static void main(String[] args) {

		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		AdresseRepository adresseRep = (AdresseRepository) appContext.getBean("adresseRepository");
		CompteRepository compteRep = (CompteRepository) appContext.getBean("compteRepository");
		ConseillerRepository conseillerRep = (ConseillerRepository) appContext.getBean("conseillerRepository");
		GerantRepository gerantRep = (GerantRepository) appContext.getBean("gerantRepository");
		ClientRepository clientRep = (ClientRepository) appContext.getBean("clientRepository");
		RoleRepository roleRep = (RoleRepository) appContext.getBean("roleRepository");
		TransactionRepository transactionRep = (TransactionRepository) appContext.getBean("transactionRepository");

		// stockage de tous les comptes générés pour transactions
		existingAccounts = new ArrayList<Compte>();
		// liste des emails utilisés pour éviter les doublons de login
		usedEmail = new ArrayList<String>();

		for (int iGerant = 0; iGerant < NB_GERANTS; iGerant++) {

			// géneration etpersistance d'un gérant aléatoire
			Gerant gerant = randomGerant();
			Role rg = new Role();
			rg.setRole("GERANT");
			rg.setEmail(gerant.getEmail());

			gerantRep.save(gerant);
			roleRep.save(rg);

			// ajout des emails utilisés
			usedEmail.add(gerant.getEmail());

			// nombre aléatoire de conseillers
			int nbConseiller = 1 + (int) (Math.random() * NB_MAX_CONSEILLERS_PAR_GERANT);

			// géneration des conseillers
			for (int iConseiller = 0; iConseiller < nbConseiller; iConseiller++) {

				Conseiller conseiller = randomConseiller();
				conseiller.setGerant(gerant);

				Role rc = new Role();
				rc.setRole("CONSEILLER");
				rc.setEmail(conseiller.getEmail());

				conseillerRep.save(conseiller);
				roleRep.save(rc);

				// ajout des emails utilisés
				usedEmail.add(conseiller.getEmail());

				int nbClients = (int) (Math.random() * NB_MAX_CLIENT_PAR_CONSEILLER + 1);

				for (int iClient = 0; iClient < nbClients; iClient++) {

					Client client = randomClient();
					client.setConseiller(conseiller);

					clientRep.save(client);

					// ajout des comptes à la liste des éligibles pour
					// transaction
					existingAccounts.addAll(client.getComptes());

				}

			}

		}

		// Generation des transactions
		Date now = new Date();
		lastTransactionDate = new Date();

		// recul de la date de début
		Calendar c = new GregorianCalendar();
		c.setTime(lastTransactionDate);
		c.add(Calendar.MONTH, -DEBUT_DES_TRANSACTION_EN_MOIS);
		lastTransactionDate = c.getTime();

		boolean continueTransaction = true;
		while (continueTransaction) {

			Transaction t = randomTransaction();

			if (t.getDate().after(now)) {
				continueTransaction = false;
			} else {
				transactionRep.save(t);
			}

		}

	}

	public static String randomNom() {
		int index = (int) (Math.random() * NOMS.length);
		return NOMS[index];
	}

	public static String randomPrenom() {
		int index = (int) (Math.random() * PRENOMS.length);
		return PRENOMS[index];
	}

	public static String randomRue() {

		int numero = (int) (Math.random() * 100);
		int index = (int) (Math.random() * RUES.length);
		return numero + " " + RUES[index];
	}

	public static String randomCP() {
		int index = (int) (Math.random() * CODEPOSTALS.length);
		return CODEPOSTALS[index];
	}

	public static int randomInt() {
		return (int) (Math.random() * 10);
	}

	public static String randomTel() {

		return "06" + randomInt() + randomInt() + randomInt() + randomInt() + randomInt() + randomInt() + randomInt()
				+ randomInt();
	}

	public static Date randomDate() {

		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -3 - (int) (Math.random() * 48));
		c.add(Calendar.SECOND, -(int) (Math.random() * 60 * 60 * 24));

		Date date = c.getTime();

		return date;
	}

	public static String randomNomEntreprise() {
		int index = (int) (Math.random() * NOMS_ENTREPRISES.length);
		return NOMS_ENTREPRISES[index];
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
		c.setEmail(c.getPrenom().toLowerCase() + "." + c.getNom().toLowerCase() + "@test.fr");
		c.setTelephone(randomTel());
		c.setEntreprise(Math.random() > 0.9 ? true : false);
		if (c.isEntreprise()) {
			c.setNomEntreprise(randomNomEntreprise());
		}

		// comptes
		if (Math.random() < 0.9) {
			CompteCourant cc = randomCompteCourant();
			c.getComptes().add(cc);
			cc.setClient(c);

		}
		if (Math.random() < 0.5) {
			CompteEpargne ce = randomCompteEpargne();
			c.getComptes().add(ce);
			ce.setClient(c);
		}

		return c;
	}

	public static Conseiller randomConseiller() {

		Conseiller c = new Conseiller();
		c.setAdresse(randomAdresse());

		boolean findNewIdentity = true;
		while (findNewIdentity) {
			c.setNom(randomNom());
			c.setPrenom(randomPrenom());
			c.setEmail(c.getPrenom().toLowerCase() + "." + c.getNom().toLowerCase() + "@proxibanque.fr");
			findNewIdentity = usedEmail.contains(c.getEmail());
		}
		c.setTelephone(randomTel());
		c.setPassword(RealmBase.Digest(c.getPrenom(), "SHA-1", "UTF-8"));

		return c;
	}

	public static Gerant randomGerant() {

		Gerant g = new Gerant();
		g.setAdresse(randomAdresse());

		boolean findNewIdentity = true;
		while (findNewIdentity) {
			g.setNom(randomNom());
			g.setPrenom(randomPrenom());
			g.setEmail(g.getPrenom().toLowerCase() + "." + g.getNom().toLowerCase() + "@proxibanque.fr");
			findNewIdentity = usedEmail.contains(g.getEmail());
		}

		g.setTelephone(randomTel());
		g.setPassword(RealmBase.Digest(g.getPrenom(), "SHA-1", "UTF-8"));

		return g;
	}

	public static Transaction randomTransaction() {

		Transaction t = new Transaction();

		Compte cDebit = randomExistingCompte();
		Compte cCredit = randomExistingCompte();

		Double montant = Math.random() * cDebit.getSolde();

		Calendar c = new GregorianCalendar();
		c.setTime(lastTransactionDate);
		c.add(Calendar.MINUTE, (int) (Math.random() * 60 * 24 * 2));

		Date date = c.getTime();

		lastTransactionDate = date;

		t.setCompteDebiteur(cDebit);
		t.setCompteCrediteur(cCredit);
		t.setDate(date);
		t.setMontant(montant);

		return t;

	}

	public static Compte randomExistingCompte() {

		int index = (int) (Math.random() * existingAccounts.size());
		return existingAccounts.get(index);

	}
}
