package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
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
import com.gtm.proxiv4.service.exceptions.MontantNegatifException;
import com.gtm.proxiv4.service.exceptions.SoldeInsuffisantException;

/**
 * Controller de la vue virement
 */
@Controller
@SessionScope
public class VirementBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	IServiceConseiller service;
	@Autowired
	ConseillerBean conseillerBean;
	@Autowired
	ConnexionBean connexionBean;

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
		
		compteDebiteur = service.findCompteById(idCompteDebiteur);
		compteCrediteur = service.findCompteById(idCompteCrediteur);
		
		try {
			service.effectuerVirement(compteDebiteur, compteCrediteur, montant);
			idCompteDebiteur = 0;
			idCompteCrediteur = 0;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Virement effectué avec succés", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (MontantNegatifException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Impossible de virer un montant négatif ou nul", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (SoldeInsuffisantException e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Solde du compte créditeur insuffisant", null);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		montant = 0;
		return "virement";
	}
	
	public void onCompteDebiteurChange(){
		//idCompteDebiteur = 19;
		 if(idCompteDebiteur != 0)
			 comptesBanque = service.listerAutresComptes(idCompteDebiteur);
		else
			comptesBanque = new ArrayList<Compte>();
	}

	public List<Compte> getComptesConseiller() {
		Conseiller c = (Conseiller) connexionBean.employeConnecte();
		comptesConseiller = service.listerComptesConseiller(c);
		return comptesConseiller;
	}

	public void setComptesConseiller(List<Compte> comptesConseiller) {
		this.comptesConseiller = comptesConseiller;
	}
}
