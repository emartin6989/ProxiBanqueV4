package com.gtm.proxiv4.mbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Employe;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.metier.Transaction;
import com.gtm.proxiv4.service.IServiceConseiller;
import com.gtm.proxiv4.service.IServiceEmploye;
import com.gtm.proxiv4.service.IServiceGerant;

/**
 * Controller de la vue Auditer du conseiller et du gerant
 *
 */
@Controller
@RequestScope
public class AuditBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ConnexionBean connexionBean;
	@Autowired
	private IServiceConseiller serviceConseiller;
	@Autowired
	private IServiceGerant serviceGerant;
	@Autowired
	private IServiceEmploye serviceEmploye;

	private PieChartModel pieModelLastThreeMonths;
	private PieChartModel pieModelLastWeek;
	private List<Transaction> transactions;

	private Compte compte;

	private LineChartModel courbeSolde3mois;

	private PieChartModel createPieModel(Date dateDebut, String titre) {
		PieChartModel pieModel = new PieChartModel();

		Gerant gerant = (Gerant) connexionBean.employeConnecte();
		List<Client> clients = serviceGerant.listerClients(gerant);

		Map<Client, Integer> liste = serviceGerant.listerNbTransactionsParClients(clients, dateDebut);
		if (liste.size() > 0) {
			for (Entry<Client, Integer> l : liste.entrySet()) {
				pieModel.set(l.getKey().getPrenom() + " " + l.getKey().getNom(), l.getValue());
			}

			pieModel.setTitle(titre);
			pieModel.setLegendPosition("w");
		} else {
			pieModel.setFill(false);
		}
		return pieModel;
	}

	private PieChartModel createPieModel2(Date dateDebut, String titre) {
		PieChartModel pieModel = new PieChartModel();
		Gerant gerant = (Gerant) connexionBean.employeConnecte();
		List<Transaction> transactions = serviceGerant.listerTransactionsParGerantEtApresDate(gerant, dateDebut);

		Map<String, Long> counts = transactions.stream().collect(Collectors.groupingBy(
				c -> c.getCompteDebiteur().getClient().getPrenom() + " " + c.getCompteDebiteur().getClient().getNom(),
				Collectors.counting()));

		if (counts.size() > 0) {
			for (Map.Entry<String, Long> c : counts.entrySet()) {
				pieModel.set(c.getKey(), c.getValue());
			}
			pieModel.setTitle(titre);
			pieModel.setLegendPosition("w");
		} else {
			pieModel.setFill(false);
		}

		return pieModel;
	}

	/**
	 * Donne la liste des comptes dépassant les seuils d'alerte de decouvert
	 * dont le titulaire est sous la responsabilite de l'employe connecte
	 * 
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
	 * 
	 * @return PieChartModel pour primefaces
	 */
	public PieChartModel getPieModelLastThreeMonths() {
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.add(Calendar.MONTH, -3);
		pieModelLastThreeMonths = createPieModel2(c.getTime(), "Trois derniers mois");
		return pieModelLastThreeMonths;
	}

	public void setPieModelLastThreeMonths(PieChartModel pieModelLastThreeMonths) {
		this.pieModelLastThreeMonths = pieModelLastThreeMonths;
	}

	/**
	 * Cree le modele d'affichage pour le camembert des transactions a une
	 * semaine
	 * 
	 * @return PieChartModel pour primefaces
	 */
	public PieChartModel getPieModelLastWeek() {
		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_YEAR, -7);
		pieModelLastWeek = createPieModel2(c.getTime(), "Semaine dernière");
		return pieModelLastWeek;
	}

	public void setPieModelLastWeek(PieChartModel pieModelLastWeek) {
		this.pieModelLastWeek = pieModelLastWeek;
	}

	public List<Transaction> getTransactions() {
		// transactions = serviceGerant.listerTransactions((Gerant)
		// connexionBean.employeConnecte()) ;
		Gerant gerant = (Gerant) connexionBean.employeConnecte();
		return serviceGerant.listerTransactions(gerant);
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	//
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {

		this.compte = compte;

	}

	public LineChartModel getCourbeSolde3mois() {
		return courbeSolde3mois;
	}

	public void rowSelect(SelectEvent event) {
		// update du chart
		createLineModels3months();
	}

	public void rowUnSelect(SelectEvent event) {
	}

	public void initSoldChart() {
		System.out.println("init");
		createLineModels3months();

	}

	

	private void createLineModels3months() {

		courbeSolde3mois = new LineChartModel();

		LineChartSeries soldeADateDonne = new LineChartSeries();
		soldeADateDonne.setLabel("Solde");

		//position de la legende (est)
		courbeSolde3mois.setLegendPosition("e");

		//définition d'un axe te temps
	    DateAxis axis = new DateAxis("Dates");
	    //donne l'angle des notes de l'echelle
	    axis.setTickAngle(-50);
	    //formate l'echelle du graphe dd mm yyyy
	    axis.setTickFormat("%#d %m %y");
	    //
	    courbeSolde3mois.getAxes().put(AxisType.X, axis);
	    
		//remplissge des data
		if (compte != null) {
			
			//titre du chart
			courbeSolde3mois.setTitle("Compte n° " + compte.getId() + " - Detail du solde sur les trois derniers mois");
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Calendar c = new GregorianCalendar();
			c.setTime(new Date());
			c.add(Calendar.MONTH,-3);
			Date firstDay = c.getTime();
			
			//TODO getPreviousSoldes(compte,beginingDate)
			
			Map<Date,Double> previousSoldes = serviceEmploye.getPreviousSoldes(compte, firstDay);
		
			//on parcours la liste
			for (Date mapKey : previousSoldes.keySet()) {
				
				soldeADateDonne.set(format.format(mapKey),previousSoldes.get(mapKey));
				
			}
			
			soldeADateDonne.set(format.format(new Date()), compte.getSolde());

			courbeSolde3mois.addSeries(soldeADateDonne);
		}
		
	}

}
