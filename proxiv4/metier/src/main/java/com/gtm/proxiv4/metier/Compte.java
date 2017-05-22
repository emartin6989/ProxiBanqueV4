package com.gtm.proxiv4.metier;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe Compte permet de d�finir les comptes bancaires. Classe abstraite
 * n'ayant pas d'existence logique .
 */
@Component
@Scope("prototype")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TypeCompte")
public abstract class Compte {

	/**
	 * L'identifiant du compte.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;

	/**
	 * Le solde du compte
	 */
	@Column(columnDefinition = "Decimal(10,2)") // bride à 2 décimales la BDD
	protected double solde;

	/**
	 * La date d'ouverture du compte
	 */
	protected Date dateOuverture;

	/**
	 * Le Client propri�taire du compte
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Client client;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Date getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
