package com.gtm.proxiv4.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe ClientEntreprise permet de d�finir un client de type entreprise
 */
@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value="ClientEntreprise")
public class ClientEntreprise extends Client {

	protected String nomEntreprise;

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}


}
