package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.service.IServiceGerant;

/**
 * Controller pour les vues gerant
 */
@Controller
@SessionScope
public class GerantBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IServiceGerant service;
    @Autowired
    private ConnexionBean connexionBean;

	private Gerant gerant;
	
	private List<Conseiller> conseillers = new ArrayList<Conseiller>();
	
	
	public IServiceGerant getService() {
		return service;
	}
	public void setService(IServiceGerant service) {
		this.service = service;
	}
	public Gerant getGerant() {
		return gerant = (Gerant) connexionBean.employeConnecte();
	}
	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}
	public List<Conseiller> getConseillers() {
		return service.listerConseiller(getGerant());
	}

	public void setConseillers(List<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}
}
