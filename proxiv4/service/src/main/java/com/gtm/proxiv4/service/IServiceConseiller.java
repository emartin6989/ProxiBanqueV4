package com.gtm.proxiv4.service;

import java.util.List;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;

public interface IServiceConseiller {

	public List<Client> listerClients(Conseiller conseiller);

	public void modifierInfoClient(Client client);

	public List<Compte> listerComptesClient(Client client);

	public void effectuerVirement(Compte compteDebiteur, Compte compteCrediteur, double montant) throws MontantNegatifException, SoldeInsuffisantException;

	public void ajouterClient(Client client);

	public List<Client> listerClientsDecouvert(Conseiller conseiller);
	
	public Conseiller findConseillerByEmail(String email);
	
}
