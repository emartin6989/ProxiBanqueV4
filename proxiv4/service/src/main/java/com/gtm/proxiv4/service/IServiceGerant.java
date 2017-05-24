package com.gtm.proxiv4.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;

public interface IServiceGerant {
	
	public List<Conseiller> listerConseiller(Gerant gerant);
	
	public List<Client> listerClients(Gerant gerant);
	
	public Map<Client, Integer> listerNbTransactionsParClients(List<Client> clients, Date dateDebut);

	public List<Compte> listerComptesDecouvert(Gerant gerant);
	
	public Gerant findGerantByEmail(String email);
}
