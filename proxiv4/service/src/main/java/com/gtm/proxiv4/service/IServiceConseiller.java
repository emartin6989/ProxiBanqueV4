package com.gtm.proxiv4.service;

import java.util.List;

import com.gtm.proxiv4.metier.Client;

public interface IServiceConseiller {

	public List<Client> listerClients(long idConseiller);

	public void modifierInfoClient(Client client);

	public List<Client> listerCompesClient(Client client);

	public void effectuerVirement(long idCompteDebiteur, long idCompteCrediteur, double montant);

	public void ajouterClient(Client client);

	public List<Client> listerClientsDecouvert(long idConseiller);
}
