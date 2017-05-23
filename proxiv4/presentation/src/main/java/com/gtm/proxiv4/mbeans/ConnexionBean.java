package com.gtm.proxiv4.mbeans;


import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gtm.proxiv4.metier.Employe;
import com.gtm.proxiv4.service.IServiceConseiller;
import com.gtm.proxiv4.service.IServiceEmploye;

@Controller
//@ManagedBean
public class ConnexionBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IServiceEmploye serviceEmploye;
	
	public Employe employeConnecte(){
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		String email = externalContext.getRemoteUser();
		
		
		return serviceEmploye.findEmployeByEmail(email);
	}
	
	
	public String logout(){
		
//		FacesContext facesContext = FacesContext.getCurrentInstance();
//		ExternalContext externalContext = facesContext.getExternalContext();
//		final HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//		request.getSession(false).invalidate(); 
		
//		FacesContext context = FacesContext.getCurrentInstance();
//		ExternalContext ec = context.getExternalContext();
//		final HttpServletRequest request = (HttpServletRequest) ec.getRequest();
//		request.getSession(false).invalidate(); 
		
		
		FacesContext context = FacesContext.getCurrentInstance();	
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.invalidate();
		
		return "/index.xhtml?faces-redirect=true";
	}

}
