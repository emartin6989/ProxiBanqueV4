package com.gtm.proxiv4.metier;

import javax.persistence.CascadeType;
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
 * La classe Personne permet de d�finir une personne. Classe abstraite.
 */
@Component
@Scope("prototype")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TypePersonne")
public abstract class Personne {

	/**
	 * id de la personne
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	/**
	 * Le nom de la personne.
	 */
	protected String nom;

	/**
	 * Le pr�nom de la personne.
	 */
	protected String prenom;

	/**
	 * L'adresse de la personne
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	protected Adresse adresse;

	/**
	 * Le num�ro de t�l�phone de la personne.
	 */
	protected String telephone;

	/**
	 * L'adresse mail de la personne.
	 */
	protected String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
