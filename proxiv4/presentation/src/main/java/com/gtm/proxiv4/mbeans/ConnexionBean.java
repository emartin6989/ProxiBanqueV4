package com.gtm.proxiv4.mbeans;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gtm.proxiv4.metier.Employe;
import com.gtm.proxiv4.service.IServiceEmploye;

/**
 * Controller pour toutes les vues permet la recuperation de l'employe connecte
 */
@Controller
public class ConnexionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IServiceEmploye serviceEmploye;
	
	/**
	 * Recupere l'objet Employe correspondant a l'utilisateur connecte
	 * @return Employe connecte
	 */
	public Employe employeConnecte(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		String email = externalContext.getRemoteUser();
				
		return serviceEmploye.findEmployeByEmail(email);
	}
	
	/**
	 * Invalide la session de l'utilisateur connecte
	 * @return redirection sur la page d'index
	 */
	public String logout(){
		
		FacesContext context = FacesContext.getCurrentInstance();	
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.invalidate();
		
		return "/index.xhtml?faces-redirect=true";
	}

}
