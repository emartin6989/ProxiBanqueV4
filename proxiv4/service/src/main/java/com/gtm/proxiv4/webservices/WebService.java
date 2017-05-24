package com.gtm.proxiv4.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gtm.proxiv4.dao.ClientRepository;
import com.gtm.proxiv4.dao.ConseillerRepository;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Personne;

@Controller
@Path("/proxibanque")
public class WebService {

	@Autowired
	ConseillerRepository conseillerRepo;
	@Autowired
	ClientRepository clientRepo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/conseillers")
	public List<Conseiller> listerConseillers() {
	return conseillerRepo.findAll();
	
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/monconseiller/{email}")
	public Conseiller trouverParEmail(@PathParam("email") String email) {
		Client client = clientRepo.findByEmail(email);
		return client.getConseiller();
	}

	
}
