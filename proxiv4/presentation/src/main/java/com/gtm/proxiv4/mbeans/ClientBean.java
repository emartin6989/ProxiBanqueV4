package com.gtm.proxiv4.mbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Adresse;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Personne;
import com.gtm.proxiv4.service.IServiceConseiller;

@Controller
//@SessionScope
public class ClientBean {
	
	@Autowired
	private IServiceConseiller serviceConseiller;
	@Autowired
	private Client client;
	@Autowired
	private Adresse adresse;
	@Autowired
	private List<Compte> comptesCourants;
	@Autowired
	private List<Compte> comptesEpargnes;

	@Autowired
	private ConseillerBean conseillerBean;

	public IServiceConseiller getServiceConseiller() {
		return serviceConseiller;
	}

	public void setServiceConseiller(IServiceConseiller serviceConseiller) {
		this.serviceConseiller = serviceConseiller;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ConseillerBean getConseillerBean() {
		return conseillerBean;
	}

	public void setConseillerBean(ConseillerBean conseillerBean) {
		this.conseillerBean = conseillerBean;
	}

	public List<Compte> getComptesCourants() {
		return serviceConseiller.listerComptesCourantClient(client);
	}

	public void setComptesCourants(List<Compte> comptesCourants) {
		this.comptesCourants = comptesCourants;
	}

	public List<Compte> getComptesEpargnes() {
		return serviceConseiller.listerComptesEpargneClient(client);
	}

	public void setComptesEpargnes(List<Compte> comptesEpargnes) {
		this.comptesEpargnes = comptesEpargnes;
	}

	public void rowSelect(SelectEvent event) {
	}

	public void rowUnSelect(SelectEvent event) {
	}

	public String modifierClient() {
		try {
			serviceConseiller.modifierInfoClient(client);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Client modifié", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} finally {
			return "modifierClient";
		}
	}
    
}
