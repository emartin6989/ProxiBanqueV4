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
    @Autowired
    private ConnexionBean connexionBean;
    @Autowired
	private List<Client> clients;

	public IServiceConseiller getServiceConseiller() {
		return serviceConseiller;
	}

	public void setServiceConseiller(IServiceConseiller serviceConseiller) {
		this.serviceConseiller = serviceConseiller;
	}

	public List<Client> getClients() {
		return serviceConseiller.listerClients((Conseiller) connexionBean.employeConnecte());
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

}
