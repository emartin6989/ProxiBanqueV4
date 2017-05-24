package com.gtm.proxiv4.metier;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe transaction represente un enregistrement de mouvement de fonds
 * entre deux comptes
 */
@Component
@Scope("prototype")
@Entity
public class Transaction {

	/**
	 * identifiant de la transaction
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/**
	 * date de la transaction
	 */
	private Date date = new Date();

	/**
	 * compte debiteur concerne par la transaction
	 */
	@ManyToOne
	private Compte compteDebiteur;
	
	/**
	 * compte crediteur concerne par la transaction
	 */
	@ManyToOne
	private Compte compteCrediteur;
	
	/**
	 * montant de la transaction
	 */
	@Column(columnDefinition = "Decimal(10,2)") // bride à 2 décimales la BDD
	private double montant;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Compte getCompteDebiteur() {
		return compteDebiteur;
	}

	public void setCompteDebiteur(Compte compteDebiteur) {
		this.compteDebiteur = compteDebiteur;
	}

	public Compte getCompteCrediteur() {
		return compteCrediteur;
	}

	public void setCompteCrediteur(Compte compteCrediteur) {
		this.compteCrediteur = compteCrediteur;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

}
