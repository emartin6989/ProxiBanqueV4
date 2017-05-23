package com.gtm.proxiv4.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.proxiv4.dao.ClientRepository;
import com.gtm.proxiv4.dao.CompteRepository;
import com.gtm.proxiv4.dao.ConseillerRepository;
import com.gtm.proxiv4.dao.EmployeRepository;
import com.gtm.proxiv4.dao.GerantRepository;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.CompteCourant;
import com.gtm.proxiv4.metier.CompteEpargne;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Employe;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.service.exceptions.ConseillerNonSpecifieException;
import com.gtm.proxiv4.service.exceptions.MontantNegatifException;
import com.gtm.proxiv4.service.exceptions.NombreMaxDeClientException;
import com.gtm.proxiv4.service.exceptions.SoldeInsuffisantException;

@Transactional
@Service
public class ServiceImpl implements IServiceConseiller, IServiceGerant, IServiceEmploye {

	@Autowired
	GerantRepository gerantRepo;
	@Autowired
	ConseillerRepository conseillerRepo;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	CompteRepository compteRepo;
	@Autowired
	EmployeRepository employeRepo;

	final static double DECOUVERT_MAX_ENTREPRISE = 50000;
	final static double DECOUVERT_MAX_PARTICULIER = 5000;
	
	@Override
	public List<Conseiller> listerConseiller(Gerant gerant) {
		return conseillerRepo.findByGerantId(gerant.getId());
	}

	@Override
	public Map<Client, Integer> compterTransactionsParClient(Date dateDebut) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compte> listerComptesDecouvert(Gerant gerant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> listerClients(Conseiller conseiller) {
		return clientRepo.findByConseillerId(conseiller.getId());
	}

	@Override
	public void modifierInfoClient(Client client) {
		clientRepo.save(client);

	}

	@Override
	public List<Compte> listerComptesClient(Client client) {
		// TODO Auto-generated method stub
		return compteRepo.findByClientId(client.getId());
	}

	@Override
	public List<Compte> listerComptesEpargneClient(Client client) {
		List<Compte> comptesE = new ArrayList<Compte>();
		List<Compte> comptes = compteRepo.findByClientId(client.getId());
		for (Compte c : comptes) {
			if (c instanceof CompteEpargne) {
				comptesE.add(c);
			}
		}
		return comptesE;
	}

	@Override
	public List<Compte> listerComptesCourantClient(Client client) {
		List<Compte> comptesC = new ArrayList<Compte>();
		List<Compte> comptes = compteRepo.findByClientId(client.getId());
		for (Compte c : comptes) {
			if (c instanceof CompteCourant) {
				comptesC.add(c);
			}
		}
		return comptesC;
	}

	@Override
	public void effectuerVirement(Compte compteDebiteur, Compte compteCrediteur, double montant)
			throws SoldeInsuffisantException, MontantNegatifException {
		// montant doit etre positif
		if (montant <= 0)
			throw new MontantNegatifException();
		// le montant ne doit pas depasser le solde d'un CompteEpargne
		if (compteDebiteur instanceof CompteEpargne) {
			if (montant > compteDebiteur.getSolde())
				throw new SoldeInsuffisantException();
		}
		// le montant ne pas pas depasser le solde (avec son decouvert) d'un
		// CompteCourant
		if (compteDebiteur instanceof CompteCourant) {
			double decouvert = ((CompteCourant) compteDebiteur).getDecouvert();
			if (montant > compteDebiteur.getSolde() + decouvert)
				throw new SoldeInsuffisantException();
		}

		compteCrediteur.setSolde(compteCrediteur.getSolde() + montant);
		compteDebiteur.setSolde(compteDebiteur.getSolde() - montant);

		 compteRepo.save(compteCrediteur);
		 compteRepo.save(compteDebiteur);

	}

	@Override
	public void ajouterClient(Client client) throws ConseillerNonSpecifieException, NombreMaxDeClientException {

		Conseiller conseiller = client.getConseiller();
		

		// on verifie que le conseiller existe
		if (conseiller == null) {
			throw new ConseillerNonSpecifieException();
		} else {
			
			System.out.println(conseiller.getClients().size());
			
			//on recupère le conseiller de la base
			//conseiller = conseillerRepo.findOne(conseiller.getId());

			int nbClients = conseiller.getClients().size();
			
			if (nbClients >= 10){
				throw new NombreMaxDeClientException();
			}
		}
		
		clientRepo.save(client);
	}

	@Override
	public List<Compte> listerComptesDecouvert(Conseiller conseiller) {
	
		//préparation de la réponse
		List<Compte> comptesADecouvert = new ArrayList<Compte>();
		
		//recherche de tous les clients du conseiller
		for(Client client : conseiller.getClients()){
			
			//détermination du seuil d'alerte en fonction du client
			double seuilAlerte = client.isEntreprise() ? DECOUVERT_MAX_ENTREPRISE :DECOUVERT_MAX_PARTICULIER;
			
			//pour chaque compte du client on compare son solde au seuil d'alerte
			for (Compte compte : client.getComptes()){

				if(compte.getSolde()<=seuilAlerte){
					comptesADecouvert.add(compte);
				}
			}			
		}		
		
		return comptesADecouvert;
	}

	@Override
	public Gerant findGerantByEmail(String email) {
		return gerantRepo.findOneByEmail(email);
	}

	@Override
	public Conseiller findConseillerByEmail(String email) {
		return conseillerRepo.findOneByEmail(email);
	}

	@Override
	public List<Compte> listerComptesConseiller(Conseiller conseiller) {
		List<Client> clients = clientRepo.findByConseillerId(conseiller.getId());
		return compteRepo.findByClientIn(clients);
	}

	@Override
	public List<Compte> listerAutresComptes(long idCompte) {
		return compteRepo.findByIdNot(idCompte);
	}

	@Override
	public Compte findCompteById(long idCompte) {
		return compteRepo.findById(idCompte);
	}
	
	@Override
	public Employe findEmployeByEmail(String email) {

		Employe e = employeRepo.findOneByEmail(email);

		if (e instanceof Conseiller) {
			e = (Conseiller) e;
		}
		if (e instanceof Gerant) {
			e = (Gerant) e;
		}

		return e;
	}

}
