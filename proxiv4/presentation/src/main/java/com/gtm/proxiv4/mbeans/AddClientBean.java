package com.gtm.proxiv4.mbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Adresse;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.service.IServiceConseiller;
import com.gtm.proxiv4.service.exceptions.ConseillerNonSpecifieException;
import com.gtm.proxiv4.service.exceptions.NombreMaxDeClientException;

@Controller
// @SessionScope
@RequestScope
public class AddClientBean {

	@Autowired
	private IServiceConseiller serviceConseiller;
	@Autowired
	private Client client;
	@Autowired
	private Adresse adresse;
	@Autowired
	private ConnexionBean connexionBean;
	
	private String entreprise = "false"; //give the default value

	public IServiceConseiller getServiceConseiller() {
		return serviceConseiller;
	}

	public void setServiceConseiller(IServiceConseiller serviceConseiller) {
		this.serviceConseiller = serviceConseiller;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public ConnexionBean getConnexionBean() {
		return connexionBean;
	}

	public void setConnexionBean(ConnexionBean connexionBean) {
		this.connexionBean = connexionBean;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String ajouterClient() {
		client.setAdresse(adresse);
		client.setConseiller((Conseiller) connexionBean.employeConnecte());

		try {
			serviceConseiller.ajouterClient(client);
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Client ajouté", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			client = null;
			adresse = null;
			entreprise = "false";
			
		} catch (ConseillerNonSpecifieException e1) {

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ajout impossible : conseiller non identifié", null);
			FacesContext.getCurrentInstance().addMessage(null, message);

		} catch (NombreMaxDeClientException e1) {
			
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ajout impossible : Nombre maximal de clients atteint", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
			
		}

		
		
		return "ajouterClient";
	}

}
