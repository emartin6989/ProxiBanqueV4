package com.gtm.proxiv4.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe Gerant permet de d�finir un g�rant
 */
@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "Gerant")
public class Gerant extends Employe {

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "gerant")
	private List<Conseiller> conseillers = new ArrayList<Conseiller>();

	public List<Conseiller> getConseillers() {
		return conseillers;
	}

	public void setConseillers(List<Conseiller> conseillers) {
		this.conseillers = conseillers;
	}

}
