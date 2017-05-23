package com.gtm.proxiv4.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;
 
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestConseillerRepository {
	
	@Autowired
	private ConseillerRepository conseillerRepo;
	@Autowired
	private GerantRepository gerantRepo;
	
	@Test
	@Transactional
	public void testAjouterConseiller() {
		Conseiller c = new Conseiller();
		c.setNom("NomDuTest");
		conseillerRepo.save(c);
		
		assertNotNull(conseillerRepo.findOne(c.getId()));
	}
	
	@Test
	@Transactional
	public void testSupprimerConseiller() {
		Conseiller c = new Conseiller();
		c.setId(999);
		c.setNom("NomTest");
		c.setPrenom("PrenomTest");
		conseillerRepo.save(c);
		
		conseillerRepo.delete(c);
			
		assertNull(conseillerRepo.findOne(999L));
	}
	
	@Test
	@Transactional
	public void testFindByGerantId(){
		List<Conseiller> conseillers = new ArrayList<Conseiller>();
		Gerant g = new Gerant();
		Conseiller c1 = new Conseiller();
		Conseiller c2 = new Conseiller();
		Conseiller c3 = new Conseiller();
		
		c1.setGerant(g);
		c2.setGerant(g);
		c3.setGerant(g);
		
		conseillers.add(c1);
		conseillers.add(c2);
		conseillers.add(c3);
		g.setConseillers(conseillers);
		
		gerantRepo.save(g);
		conseillerRepo.save(c1);
		conseillerRepo.save(c2);
		conseillerRepo.save(c3);
		
		assertEquals(3, conseillerRepo.findByGerantId(g.getId()).size());
	}
	//@Ignore
	@Test
	//@Transactional
	public void testFindOneByEmail(){
		Conseiller c = new Conseiller();
		c.setNom("TEST");
		c.setEmail("email@test.fr");
		conseillerRepo.save(c);
		
		assertEquals("TEST", conseillerRepo.findOneByEmail("email@test.fr").getNom());
		conseillerRepo.delete(c);
	}
	

}
