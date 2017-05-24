package com.gtm.proxiv4.service;

import java.util.List;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.service.exceptions.ConseillerNonSpecifieException;
import com.gtm.proxiv4.service.exceptions.MontantNegatifException;
import com.gtm.proxiv4.service.exceptions.NombreMaxDeClientException;
import com.gtm.proxiv4.service.exceptions.SoldeInsuffisantException;

/**
 * Interface de service pour les conseillers
 */
public interface IServiceConseiller {

	/**
	 * Ajoute le client passe en argument a la base de donnees
	 * 
	 * @param client
	 *            client a ajouter
	 * @throws ConseillerNonSpecifieException
	 *             exception lancee si le conseille n'est pas specifie dans le
	 *             client a ajouter
	 * @throws NombreMaxDeClientException
	 *             exception lancee si le conseille a deja 10 clients
	 */
	public void ajouterClient(Client client) throws ConseillerNonSpecifieException, NombreMaxDeClientException;

	/**
	 * Effectue un virement entre deux comptes de la banque
	 * 
	 * @param compteDebiteur
	 *            compte a debiter
	 * @param compteCrediteur
	 *            compte a crediter
	 * @param montant
	 *            montant du virement
	 * @throws MontantNegatifException
	 *             exception lancee en cas de saisie d'un montant negatif
	 * @throws SoldeInsuffisantException
	 *             exception lancee si le solde du compte debieur est
	 *             insuffisant
	 */
	public void effectuerVirement(Compte compteDebiteur, Compte compteCrediteur, double montant)
			throws MontantNegatifException, SoldeInsuffisantException;

	/**
	 * Trouve un objet compte a partir de son identifiant
	 * 
	 * @param idCompte
	 *            identifiant du compte a trouver
	 * @return Compte compte dont l'id est passe en argument
	 */
	public Compte findCompteById(long idCompte);

	/**
	 * lister tous les comptes de la banque sauf celui passe en argument
	 * 
	 * @param idCompte
	 *            identifiant du compte a exclure
	 * @return List Compte tous les comptes de la baque sauf celui dont l'id
	 *         est passe en argument
	 */
	public List<Compte> listerAutresComptes(long idCompte);

	/**
	 * Liste les clients du conseiller
	 * 
	 * @param conseiller conseiller
	 * @return List Client clients du conseiller
	 */
	public List<Client> listerClients(Conseiller conseiller);

	/**
	 * Liste les compte d'un client du conseiller
	 * 
	 * @param client
	 *            client dont on veut lister les comptes
	 * @return List Compte comptes du client
	 */
	public List<Compte> listerComptesClient(Client client);

	/**
	 * liste tous les comptes de tous les clients du conseiller
	 * 
	 * @param conseiller
	 *            conseiller dont on veut lister les comptes geres
	 * @return List Compte comptes des clients du conseiller
	 */
	public List<Compte> listerComptesConseiller(Conseiller conseiller);

	/**
	 * liste les comptes courant d'un client donne
	 * 
	 * @param client
	 *            dont on desire liset les comptes courant
	 * @return List Compte comptes courant du client
	 */
	public List<Compte> listerComptesCourantClient(Client client);

	/**
	 * Liste tous les comptes dont le solde depasse le seuil d'alerte de
	 * secouvert pour les clients dependant du conseiller
	 * 
	 * @param conseiller conseiller
	 * @return List Compte comptes dont le solde depasse le seuil d'alerte de
	 *         decouvert
	 */
	public List<Compte> listerComptesDecouvert(Conseiller conseiller);

	/**
	 * liste les comptes epargne d'un client donne
	 * 
	 * @param client
	 *            dont on desire liset les comptes epargne
	 * @return List Compte comptes eparne du client
	 */
	public List<Compte> listerComptesEpargneClient(Client client);

	/**
	 * Remplace les informations (identite et adresse) d'un client
	 * 
	 * @param client
	 *            client modifie
	 */
	public void modifierInfoClient(Client client);

}
