package com.gtm.proxiv4.mbeans;


import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
public class ConnexionBean {
	
	
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
