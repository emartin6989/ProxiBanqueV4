package com.gtm.proxiv4.metier;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * represente un employe de la banque
 */
@Component
@Scope("prototype")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Employe extends Personne {

	/**
	 * mot de passe de l'employe (crypte en base de donnee)
	 */
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
