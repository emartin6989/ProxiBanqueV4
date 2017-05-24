package com.gtm.proxiv4.mbeans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JSpinner.ListEditor;

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

@Controller
@RequestScope
public class AuditBean {

	@Autowired
	private ConnexionBean connexionBean;
	@Autowired
	private IServiceConseiller serviceConseiller;
	@Autowired
	private IServiceGerant serviceGerant;
	
	private PieChartModel pieModelLastThreeMonths;

	private List<Compte> comptesASurveiller;

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

	public void setComptesASurveiller(List<Compte> comptesASurveiller) {
		this.comptesASurveiller = comptesASurveiller;
	}

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

	public PieChartModel getPieModelLastThreeMonths() {
		Calendar c = new GregorianCalendar();
	    c.setTime(new Date());
	    c.add(Calendar.MONTH, -1);
		return pieModelLastThreeMonths = createPieModel(c.getTime(), "Transactions du mois dernier");
	}

	public void setPieModelLastThreeMonths(PieChartModel pieModelLastThreeMonths) {
		this.pieModelLastThreeMonths = pieModelLastThreeMonths;
	}

}
