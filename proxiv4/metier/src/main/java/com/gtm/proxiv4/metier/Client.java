package com.gtm.proxiv4.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Classe Client permettant de d�finir les clients 
 */
@Component
@Scope("prototype")
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Client extends Personne {
	
	/**
	 * Les comptes du client
	 */
	//cascade ALL car la suppression d'un client entraine la suppression de ses comptes
	@OneToMany(fetch = FetchType.EAGER , mappedBy="client" , cascade=CascadeType.ALL)
	protected List<Compte> comptes = new ArrayList<Compte>();
	
	@ManyToOne
	protected Conseiller conseiller;

	
}
