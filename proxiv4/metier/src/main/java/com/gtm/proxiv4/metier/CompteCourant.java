package com.gtm.proxiv4.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe CompteCourant permet de d�finir les comptes bancaires courants.
 */
@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value="CompteCourant")
public class CompteCourant extends Compte {
	/**
	 * Le d�couvert autoris�.
	 */
	private double decouvert;

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}


}
