package com.gtm.proxiv4.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;

@ContextConfiguration(locations = "classpath:/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCompteRepository {

	@Autowired
	private ConseillerRepository conseillerRepo;
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private CompteRepository compteRepo;
	
	@Test
	public void testFindByClientNotIn() {
		List<Compte> allComptes = compteRepo.findAll();
		Compte c = allComptes.get(0);
		Client client = c.getClient();
		
		int nbComptesClient = 0;
		for (Compte compte : allComptes) {
			if(compte.getClient().equals(client)){
				nbComptesClient++;
			}
		}
		
		List<Client> clients = new ArrayList<Client>();
		clients.add(client);
		
		List<Compte> otherComptes = compteRepo.findByClientNotIn(clients);
		
		int nbComptesTotal = allComptes.size();
		int nbAutresComptes = otherComptes.size();
		
		Assert.assertEquals(nbComptesTotal-nbComptesClient, nbAutresComptes);
	}
	
	@Test
	public void testfindByIdNot() {
		List<Compte> allComptes = compteRepo.findAll();
		Compte c = allComptes.get(0);

		List<Compte> otherComptes = compteRepo.findByIdNot(c.getId());
		Assert.assertEquals(allComptes.size()-1, otherComptes.size());
	}
	
	@Test
	public void testFindByClientIn() {
		List<Compte> allComptes = compteRepo.findAll();
		Compte c = allComptes.get(0);
		Client client = c.getClient();
		
		int nbAutresComptes  = 0;
		for (Compte compte : allComptes) {
			if(!compte.getClient().equals(client)){
				nbAutresComptes++;
			}
		}
		
		List<Client> clients = new ArrayList<Client>();
		clients.add(client);
		List<Compte> conseillersComptes = compteRepo.findByClientIn(clients);
		
		int nbComptesClient = conseillersComptes.size();
		int nbComptesTotal = allComptes.size();
		
		Assert.assertEquals(nbComptesTotal-nbAutresComptes, nbComptesClient);
	}

}
