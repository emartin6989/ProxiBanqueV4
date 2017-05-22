package com.gtm.proxiv4.mbeans;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.service.IServiceConseiller;

@Controller
@SessionScope
public class ConseillerBean {
	
	@Autowired
	private IServiceConseiller serviceConseiller;
	@Autowired
	private Conseiller conseiller;
	
	private List<Client> clients;

	public IServiceConseiller getServiceConseiller() {
		return serviceConseiller;
	}

	public void setServiceConseiller(IServiceConseiller serviceConseiller) {
		this.serviceConseiller = serviceConseiller;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public List<Client> getClients() {
		return serviceConseiller.listerClients(conseiller.getId());
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}
