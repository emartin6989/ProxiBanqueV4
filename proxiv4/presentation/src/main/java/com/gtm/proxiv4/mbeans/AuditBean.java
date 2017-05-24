package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Employe;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.service.IServiceConseiller;
import com.gtm.proxiv4.service.IServiceGerant;

/**
 * Controller de la vue Auditer du conseiller et du gerant
 *
 */
@Controller
@RequestScope
public class AuditBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ConnexionBean connexionBean;
	@Autowired
	private IServiceConseiller serviceConseiller;
	@Autowired
	private IServiceGerant serviceGerant;
	
	private PieChartModel pieModelLastThreeMonths;
	private PieChartModel pieModelLastWeek;

	private PieChartModel createPieModel(Date dateDebut, String titre) {
	    PieChartModel pieModel = new PieChartModel();
	    
	    Gerant gerant = (Gerant) connexionBean.employeConnecte();
	    List<Client> clients = serviceGerant.listerClients(gerant);
	
	    Map<Client, Integer> liste = serviceGerant.listerNbTransactionsParClients(clients, dateDebut);
	    if(liste.size() > 0) {
		    for (Entry<Client, Integer> l : liste.entrySet()) {
		    	pieModel.set(l.getKey().getPrenom() + " " + l.getKey().getNom(), l.getValue());
		    }
		     
		    pieModel.setTitle(titre);
		    pieModel.setLegendPosition("w");
	    }else{
	    	pieModel.setFill(false);
	    }
	    return pieModel; 
	}

	/**
	 * Donne la liste des comptes dépassant les seuils d'alerte de decouvert dont le titulaire est sous la responsabilite de l'employe connecte
	 * @return Liste de comptes à surveiller
	 */
	public List<Compte> getComptesASurveiller() {

		Employe employe = connexionBean.employeConnecte();

		if (employe instanceof Conseiller) {
			return serviceConseiller.listerComptesDecouvert((Conseiller) employe);
		}
		if (employe instanceof Gerant) {
			return serviceGerant.listerComptesDecouvert((Gerant) employe);
		}

		return null;
	}


	/**
	 * Cree le modele d'affichage pour le camembert des transactions a 3 mois
	 * @return PieChartModel pour primefaces
	 */
	public PieChartModel getPieModelLastThreeMonths() {
		Calendar c = new GregorianCalendar();
	    c.setTime(new Date());
	    c.add(Calendar.MONTH, -3);
		pieModelLastThreeMonths = createPieModel(c.getTime(), "Trois derniers mois");
		return pieModelLastThreeMonths;
	}

	public void setPieModelLastThreeMonths(PieChartModel pieModelLastThreeMonths) {
		this.pieModelLastThreeMonths = pieModelLastThreeMonths;
	}

	/**
	 * Cree le modele d'affichage pour le camembert des transactions a une semaine
	 * @return PieChartModel pour primefaces
	 */
	public PieChartModel getPieModelLastWeek() {
		Calendar c = new GregorianCalendar();
	    c.setTime(new Date());
	    c.add(Calendar.DAY_OF_YEAR, -7);
		pieModelLastWeek = createPieModel(c.getTime(), "Semaine dernière");
		return pieModelLastWeek;
	}

	public void setPieModelLastWeek(PieChartModel pieModelLastWeek) {
		this.pieModelLastWeek = pieModelLastWeek;
	}

}
