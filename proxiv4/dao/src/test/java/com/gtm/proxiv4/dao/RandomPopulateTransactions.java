package com.gtm.proxiv4.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Transaction;

/**
 * pour test population de la base de données
 */
public class RandomPopulateTransactions {
	// PARAMETRES
	private static int DEBUT_DES_TRANSACTION_EN_MOIS = 6;
	private static int HEURES_MAX_ENTRE_DEUX_TRANSACTIONS = 5;


	//reminders
	private static List<Compte> existingAccounts;
	private static Date lastTransactionDate;

	public static void main(String[] args) {

		ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		CompteRepository compteRep = (CompteRepository) appContext.getBean("compteRepository");
		TransactionRepository transactionRep = (TransactionRepository) appContext.getBean("transactionRepository");

		// stockage de tous les comptes générés pour transactions
		existingAccounts = compteRep.findAll();

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
		
		appContext.close();

	}


	public static Transaction randomTransaction() {

		Transaction t = new Transaction();

		Compte cDebit = randomExistingCompte();
		Compte cCredit = randomExistingCompte();

		Double montant = Math.abs(Math.random() * cDebit.getSolde());

		Calendar c = new GregorianCalendar();
		c.setTime(lastTransactionDate);
		c.add(Calendar.MINUTE, (int) (Math.random() * 60 * HEURES_MAX_ENTRE_DEUX_TRANSACTIONS));

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
