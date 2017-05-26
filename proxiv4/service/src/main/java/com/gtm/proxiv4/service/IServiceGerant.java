package com.gtm.proxiv4.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.metier.Transaction;

/**
 * Interface de services pour le gerant
 */
public interface IServiceGerant {

	/**
	 * Liste tous les clients geres par les conseillers sous la responsabilite
	 * du gerant
	 * 
	 * @param gerant
	 *            dont on souhaite recuperer les clients
	 * @return List Client clients dependant du gerant
	 */
	public List<Client> listerClients(Gerant gerant);

	/**
	 * Donne la liste des conseillers sous la responsabilite du gerant
	 * 
	 * @param gerant
	 *            gerant dont on souhaite avoir les conseiller
	 * @return List Conseiller conseillers sous la responsabilite du gerant
	 */
	public List<Conseiller> listerConseiller(Gerant gerant);

	/**
	 * Liste tous les comptes dependant des conseillers du gerant
	 * 
	 * @param gerant gerant
	 * @return List Compte comptes dependant des conseillers du gerant
	 */
	public List<Compte> listerComptes(Gerant gerant);

	/**
	 * Liste tous les comptes dont le solde depasse le seuil d'alerte de
	 * secouvert pour les clients dependant des conseillers du gerant
	 * 
	 * @param gerant gerant
	 * @return List Compte comptes dont le solde depasse le seuil d'alerte de
	 *         decouvert
	 */
	public List<Compte> listerComptesDecouvert(Gerant gerant);

	/**
	 * Donne une map de (client, nombre de transaction realisees par le client)
	 * depuis une date de debut donnee pour une liste de clients donnee cette
	 * methode est utilisee pour construire les pieChart de primefaces
	 * 
	 * @param clients
	 *            liste des clients dont on souhaite compter les transactions
	 * @param dateDebut
	 *            date de debut pour le decompte des transactions
	 * @return Map (Client, Integer) Map (client, nombre de transaction realisees
	 *         par le client)
	 */
	public Map<Client, Integer> listerNbTransactionsParClients(List<Client> clients, Date dateDebut);

	/**
	 * Liste de l'ensemble des transactions de l'agence
	 * 
	 * @param gerant
	 *            Gerant de l'agence
	 * @return Une liste de transactions
	 */
	public List<Transaction> listerTransactions(Gerant gerant);

	public List<Transaction> listerTransactionsParGerantEtApresDate(Gerant gerant, Date dateDebut);
}
