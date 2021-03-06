package com.gtm.proxiv4.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe Role est utilisee par JAAS pour gerer les authorisations des utilisateurs de l'application
 */
@Component
@Scope("prototype")
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	/**
	 * email (login) de l'utilisateur
	 */
	private String email;
	/**
	 * role de l'utilisateur CONSEILLER / GERANT
	 */
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
