package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.service.IServiceConseiller;

import javassist.expr.NewArray;

@Controller
@SessionScope
public class VirementBean implements Serializable {

	@Autowired
	IServiceConseiller service;
	@Autowired
	ConseillerBean conseillerBean;

	private Compte compteDebiteur;
	private Compte compteCrediteur;
	private long idCompteDebiteur;
	private long idCompteCrediteur;
	private double montant;
	private List<Compte> comptesBanque;
	private List<Compte> comptesConseiller;

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

	public long getIdCompteDebiteur() {
		return idCompteDebiteur;
	}

	public void setIdCompteDebiteur(long idCompteDebiteur) {
		this.idCompteDebiteur = idCompteDebiteur;
	}

	public long getIdCompteCrediteur() {
		return idCompteCrediteur;
	}

	public void setIdCompteCrediteur(long idCompteCrediteur) {
		this.idCompteCrediteur = idCompteCrediteur;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public Collection<Compte> getComptesBanque() {
		return comptesBanque;
	}

	public void setComptesBanque(List<Compte> comptesBanque) {
		this.comptesBanque = comptesBanque;
	}
	
	public String effectuerVirement(){
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Solde insuffisant, virement non effectué", null));
		montant = 0;
		return "listerConseillers";
	}
	
	public void onCompteDebiteurChange(){
		 if(idCompteCrediteur != 0)
			 comptesBanque = service.listerAutresComptes(idCompteCrediteur);
		else
			comptesBanque = new ArrayList<Compte>();
	}

	public List<Compte> getComptesConseiller() {
		Conseiller c = service.findConseillerByEmail("conseiller@test.fr");
		comptesConseiller = service.listerComptesConseiller(c);
		return comptesConseiller;
	}

	public void setComptesConseiller(List<Compte> comptesConseiller) {
		this.comptesConseiller = comptesConseiller;
	}
}
