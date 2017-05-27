package com.gtm.proxiv4.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Employe;

/**
 * Interface de service pour les employes
 */
public interface IServiceEmploye {

	/**
	 * Trouve un objet Employe par son email
	 * 
	 * @param email
	 *            email de l'employer a trouver
	 * @return Employe
	 */
	public Employe findEmployeByEmail(String email);

	/**
	 * renvoie la liste des anciens soldes d'un compte depuis une date donnee
	 * 
	 * @param compte
	 *            compte dont on desire connaitre l'historique des soldes
	 * @param dateDebut
	 *            date de debut de la liste
	 * @return historiques du solde du compte
	 */
	public Map<Date, Double> getPreviousSoldes(Compte compte, Date dateDebut);
}
