package com.gtm.proxiv4.service;

import java.util.List;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.service.exceptions.ConseillerNonSpecifieException;
import com.gtm.proxiv4.service.exceptions.MontantNegatifException;
import com.gtm.proxiv4.service.exceptions.NombreMaxDeClientException;
import com.gtm.proxiv4.service.exceptions.SoldeInsuffisantException;

public interface IServiceConseiller {

	public List<Client> listerClients(Conseiller conseiller);

	public void modifierInfoClient(Client client);

	public List<Compte> listerComptesClient(Client client);

	public void effectuerVirement(Compte compteDebiteur, Compte compteCrediteur, double montant) throws MontantNegatifException, SoldeInsuffisantException;

	public void ajouterClient(Client client) throws ConseillerNonSpecifieException, NombreMaxDeClientException;

	public List<Client> listerClientsDecouvert(Conseiller conseiller);
	
	public Conseiller findConseillerByEmail(String email);
	
	public List<Compte> listerComptesEpargneClient(Client client);
	
	public List<Compte> listerComptesCourantClient(Client client);
	
	public List<Compte> listerComptesConseiller(Conseiller conseiller);
	
	public List<Compte> listerAutresComptes(long idCompte);

	public Compte findCompteById(long idCompte);
	
}
