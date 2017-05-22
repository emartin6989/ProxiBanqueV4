package com.gtm.proxiv4.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.proxiv4.dao.ConseillerRepository;
import com.gtm.proxiv4.dao.GerantRepository;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;

@Transactional
@Service
public class ServiceImpl implements IServiceConseiller, IServiceGerant {

	@Autowired
	GerantRepository gerantRepo;
	@Autowired
	ConseillerRepository conseillerRepo;
	
	@Override
	public List<Conseiller> listerConseiller(long idGerant) {
		return conseillerRepo.findByGerantId(idGerant);
	}

	@Override
	public Map<Client, Integer> compterTransactionsParClient(Date dateDebut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> listerCientsDecouvert(long idGerant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> listerClients(long idConseiller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifierInfoClient(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> listerCompesClient(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void effectuerVirement(long idCompteDebiteur, long idCompteCrediteur, double montant) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ajouterClient(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> listerClientsDecouvert(long idConseiller) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gerant findGerantByEmail(String email) {
		return gerantRepo.findOneByEmail(email);
	}

}