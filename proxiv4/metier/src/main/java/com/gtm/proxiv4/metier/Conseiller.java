package com.gtm.proxiv4.metier;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe Conseiller servant a definir les conseillers clientele des
 * agences bancaires
 */
@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "Conseiller")
@JsonIgnoreProperties({"gerant","clients","password"})
public class Conseiller extends Employe {

	/**
	 * Le gerant responsable du conseiller
	 */
	@ManyToOne
	private Gerant gerant;

	/**
	 * Les clients du conseiller
	 */
	@OneToMany(mappedBy = "conseiller")
	private Collection<Client> clients = new ArrayList<Client>();

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public Collection<Client> getClients() {
		return clients;
	}

	public void setClients(Collection<Client> clients) {
		this.clients = clients;
	}

}
