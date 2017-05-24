package com.gtm.proxiv4.metier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * La classe CompteEpargne permet de d�finir les comptes bancaires d'�pargne.
 */
@Component
@Scope("prototype")
@Entity
@DiscriminatorValue(value="CompteEpargne")
public class CompteEpargne extends Compte {
	/**
	 * Le taux de  remuneration du compte epargne en pourcentage
	 */
	@Column(columnDefinition = "Decimal(10,2)") // bride à 2 décimales la BDD
	private float taux;

	public float getTaux() {
		return taux;
	}

	public void setTaux(float taux) {
		this.taux = taux;
	}

	
}
