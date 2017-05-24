package com.gtm.proxiv4.service;

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
}
