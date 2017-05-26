package com.gtm.proxiv4.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe Client permettant de definir les clients
 */
@Component
@Scope("prototype")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "Client")
public class Client extends Personne {

	/**
	 * Represente le type de client (true si le client est une entreprise)
	 */
	private boolean entreprise;
	
	/**
	 * Nom de l'entreprise du client dans le cas où le client est une entreprise, null sinon
	 */
	private String nomEntreprise;

	public boolean isEntreprise() {
		return entreprise;
	}

	public void setEntreprise(boolean entreprise) {
		this.entreprise = entreprise;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	/**
	 * Les comptes du client
	 */
	// cascade ALL car la suppression d'un client entraine la suppression de ses
	// comptes
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	protected List<Compte> comptes = new ArrayList<Compte>();

	/**
	 * conseiller en charge du client
	 */
	@ManyToOne
	protected Conseiller conseiller;

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

}
