package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.service.IServiceConseiller;

/**
 * Controller pour les vues conseiller
 */
@Controller
@SessionScope
public class ConseillerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
