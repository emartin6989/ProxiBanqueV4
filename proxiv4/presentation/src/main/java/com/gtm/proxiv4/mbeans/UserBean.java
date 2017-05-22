package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gtm.proxiv4.metier.User;
import com.gtm.proxiv4.service.IService;


@Controller
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
	
	@Autowired
	private IService service;
	
	@Autowired
	private User user;

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void ajouterUser(){
		service.ajouterUser(user);
	}

	public List<User> listerUser(){
		return service.listerUser();
	}

	public void supprimerUser(long id){
		service.supprimerUser(id);
	}

	public User trouverUser(long id){
		return service.trouverUser(id);
	}

	public List<User> listerUserParMc(String nom){
		return service.listerUserParMc(nom);
	}

	public User listerUserParNom(String nom){
		return service.listerUserParNom(nom);
	}
	
	
	

}
