package com.gtm.proxiv4.service;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.gtm.proxiv4.metier.CompteCourant;
import com.gtm.proxiv4.metier.CompteEpargne;
import com.gtm.proxiv4.service.exceptions.MontantNegatifException;
import com.gtm.proxiv4.service.exceptions.SoldeInsuffisantException;

public class TestEffectuerVirement {

	private IServiceConseiller service = new ServiceImpl();
	
	@Test(expected = MontantNegatifException.class)
	public void testVirementMontantNegatif() throws MontantNegatifException, SoldeInsuffisantException {
		CompteCourant cc = new CompteCourant();
		CompteEpargne ce = new CompteEpargne();
			
		service.effectuerVirement(cc, ce,-500);
	}

	@Test(expected = SoldeInsuffisantException.class)
	public void testVirementCompteCourantSoldeInsuffisant() throws MontantNegatifException, SoldeInsuffisantException {
		CompteCourant cc = new CompteCourant();
		CompteEpargne ce = new CompteEpargne();
		
		cc.setSolde(1000);
		cc.setDecouvert(500);
		
		service.effectuerVirement(cc, ce,2000);
	}
	
	@Test(expected = SoldeInsuffisantException.class)
	public void testVirementCompteEpargneSoldeInsuffisant() throws MontantNegatifException, SoldeInsuffisantException {
		CompteCourant cc = new CompteCourant();
		CompteEpargne ce = new CompteEpargne();
		
		ce.setSolde(1000);
		
		service.effectuerVirement(cc, ce,2000);
	}
	
	@Ignore // il faudrait un mock de la dao car un compte est rattaché a un client
	@Test
	public void testEffectuerVirementSoldeEmetteurEstDebite() throws SoldeInsuffisantException, MontantNegatifException {
		CompteEpargne c1 = new CompteEpargne();
		CompteCourant c2 = new CompteCourant();
	
		c1.setSolde(1000);
		c2.setSolde(300);
		service.effectuerVirement(c1, c2, 500);
	
		Assert.assertEquals(500, c1.getSolde(), 0);
	}

	@Ignore // il faudrait un mock de la dao car un compte est rattaché a un client
	@Test
	public void testEffectuerVirementSoldeRecepteurEstCredite() throws SoldeInsuffisantException, MontantNegatifException {
		CompteEpargne c1 = new CompteEpargne();
		CompteCourant c2 = new CompteCourant();
	
		c1.setSolde(1000);
		c2.setSolde(300);
		service.effectuerVirement(c1, c2, 500);
	
		Assert.assertEquals(800, c2.getSolde(), 0);
	}
}
