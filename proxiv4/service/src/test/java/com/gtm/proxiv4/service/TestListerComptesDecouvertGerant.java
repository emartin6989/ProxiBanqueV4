package com.gtm.proxiv4.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.CompteCourant;
import com.gtm.proxiv4.metier.CompteEpargne;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;

public class TestListerComptesDecouvertGerant {

	private IServiceGerant service = new ServiceImpl();
	
	
	@Test
	public void TestListerComptesDecouvertGerantAucunConseiller() {

		Gerant g = new Gerant();
		
		List<Compte> expected = new ArrayList<Compte>();

		Assert.assertEquals(expected, service.listerComptesDecouvert(g));

	}
	
	@Test
	public void TestListerComptesDecouvertGerantAucunClient() {

		Conseiller c = new Conseiller();
		Gerant g = new Gerant();
		g.getConseillers().add(c);
		
		List<Compte> expected = new ArrayList<Compte>();

		Assert.assertEquals(expected, service.listerComptesDecouvert(g));

	}

	@Test
	public void testListerComptesDecouvertGerantCasNominalClientsParticuliers() {
		
		//Comptes devant apparaitre dans la liste
		CompteCourant cc1 =new CompteCourant();
		CompteCourant cc2 =new CompteCourant();
		CompteCourant cc3 =new CompteCourant();
		CompteEpargne ce1 =new CompteEpargne();
		
		cc1.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER);
		cc2.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER-0.1);
		cc3.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER-1);
		ce1.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER-10);
		
		//Comptes ne devant pas apparaitre dans la liste
		CompteCourant ccok1 =new CompteCourant();
		CompteCourant ccok2 =new CompteCourant();
		CompteCourant ccok3 =new CompteCourant();
		CompteEpargne ceok1 =new CompteEpargne();
		
		ccok1.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER+0.01);
		ccok2.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER+0.1);
		ccok3.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER+1);
		ceok1.setSolde(-ServiceImpl.DECOUVERT_MAX_PARTICULIER+10);
		
		Client c1 = new Client();
		Client c2 = new Client();
		Client c3 = new Client();
		
		c1.getComptes().add(cc1);
		c1.getComptes().add(ccok1);
		c1.getComptes().add(ce1);
		c1.getComptes().add(ceok1);
		c2.getComptes().add(cc2);
		c2.getComptes().add(ccok2);
		c3.getComptes().add(cc3);
		c3.getComptes().add(ccok3);
		
		Conseiller co1 = new Conseiller();
		Conseiller co2 = new Conseiller();
		
		co1.getClients().add(c1);
		co1.getClients().add(c2);
		
		co2.getClients().add(c3);
		
		Gerant g = new Gerant();
		
		g.getConseillers().add(co1);
		g.getConseillers().add(co2);
		
		//reponse esperée
		List<Compte> expected =new ArrayList<Compte>();
		expected.add(cc1);
		expected.add(ce1);
		expected.add(cc2);
		expected.add(cc3);
				
		System.out.println(service.listerComptesDecouvert(g).size());
		Assert.assertEquals(expected, service.listerComptesDecouvert(g));
		
	}

	@Test
	public void testListerComptesDecouvertGerantCasNominalClientsEntreprises() {
		
		//Comptes devant apparaitre dans la liste
		CompteCourant cc1 =new CompteCourant();
		CompteCourant cc2 =new CompteCourant();
		CompteCourant cc3 =new CompteCourant();
		CompteEpargne ce1 =new CompteEpargne();
		
		cc1.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE);
		cc2.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE-0.1);
		cc3.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE-1);
		ce1.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE-10);
		
		//Comptes ne devant pas apparaitre dans la liste
		CompteCourant ccok1 =new CompteCourant();
		CompteCourant ccok2 =new CompteCourant();
		CompteCourant ccok3 =new CompteCourant();
		CompteEpargne ceok1 =new CompteEpargne();
		
		ccok1.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE+0.01);
		ccok2.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE+0.1);
		ccok3.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE+1);
		ceok1.setSolde(-ServiceImpl.DECOUVERT_MAX_ENTREPRISE+10);
		
		Client c1 = new Client();
		c1.setEntreprise(true);
		Client c2 = new Client();
		c2.setEntreprise(true);
		Client c3 = new Client();
		c3.setEntreprise(true);
		
		c1.getComptes().add(cc1);
		c1.getComptes().add(ccok1);
		c1.getComptes().add(ce1);
		c1.getComptes().add(ceok1);
		c2.getComptes().add(cc2);
		c2.getComptes().add(ccok2);
		c3.getComptes().add(cc3);
		c3.getComptes().add(ccok3);
		
		Conseiller co1 = new Conseiller();
		Conseiller co2 = new Conseiller();
		
		co1.getClients().add(c1);
		co1.getClients().add(c2);
		
		co2.getClients().add(c3);
		
		Gerant g = new Gerant();
		
		g.getConseillers().add(co1);
		g.getConseillers().add(co2);
		
		//reponse esperée
		List<Compte> expected =new ArrayList<Compte>();
		expected.add(cc1);
		expected.add(ce1);
		expected.add(cc2);
		expected.add(cc3);
				
		System.out.println(service.listerComptesDecouvert(g).size());
		Assert.assertEquals(expected, service.listerComptesDecouvert(g));
		
	}

}
