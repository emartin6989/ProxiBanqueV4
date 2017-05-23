package com.gtm.proxiv4.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;

public interface IServiceGerant {
	
	public List<Conseiller> listerConseiller(Gerant gerant);
	
	public Map<Client, Integer> compterTransactionsParClient(Date dateDebut);

	public List<Client> listerClientsDecouvert(Gerant gerant);
	
	public Gerant findGerantByEmail(String email);
}
