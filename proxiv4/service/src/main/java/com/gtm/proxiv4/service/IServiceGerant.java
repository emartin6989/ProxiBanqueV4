package com.gtm.proxiv4.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Conseiller;

public interface IServiceGerant {
	
	public List<Conseiller> listerConseiller(long idGerant);
	
	public Map<Client, Integer> compterTransactionsParClient(Date dateDebut);

	public List<Client> listerCientsDecouvert(long idGerant);
}
