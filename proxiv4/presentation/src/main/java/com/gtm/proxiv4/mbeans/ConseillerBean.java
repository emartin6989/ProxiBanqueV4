package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.service.IServiceConseiller;

@Controller
@SessionScope
public class ConseillerBean implements Serializable {
	
	@Autowired
	private IServiceConseiller serviceConseiller;
	
	private Conseiller conseiller;
	
	private List<Client> clients = new ArrayList<Client>();
	
	private Client client;

	public IServiceConseiller getServiceConseiller() {
		return serviceConseiller;
	}

	public void setServiceConseiller(IServiceConseiller serviceConseiller) {
		this.serviceConseiller = serviceConseiller;
	}

	public Conseiller getConseiller() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		String email = externalContext.getRemoteUser();
		return conseiller = serviceConseiller.findConseillerByEmail(email);
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public List<Client> getClients() {
		return serviceConseiller.listerClients(conseiller);
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}


}
