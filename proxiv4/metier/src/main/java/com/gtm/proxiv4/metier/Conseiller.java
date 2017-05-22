package com.gtm.proxiv4.metier;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe ConseillerClientele servant � d�finir les conseillers client�le des
 * agences bancaires
 */
@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value = "Conseiller")
public class Conseiller extends Employe {

	/**
	 * Le g�rant responsable du conseiller client�le
	 */
	@ManyToOne
	private Gerant gerant;

	/**
	 * Les clients affect�s � un conseiller client�le
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "conseiller")
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
