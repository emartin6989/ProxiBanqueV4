package com.gtm.proxiv4.mbeans;

import javax.faces.bean.ManagedProperty;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

import com.gtm.proxiv4.metier.Adresse;
import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.service.IServiceConseiller;

@Controller
@SessionScope
public class ClientBean {
	
	@Autowired
	private IServiceConseiller serviceConseiller;
	@Autowired
	private Client client;
	@Autowired
	private	Adresse adresse;
	
	@Autowired
    private ConseillerBean conseillerBean;

	public IServiceConseiller getServiceConseiller() {
		return serviceConseiller;
	}

	public void setServiceConseiller(IServiceConseiller serviceConseiller) {
		this.serviceConseiller = serviceConseiller;
	}



	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Client getClient() {
		//if(client == null){
          //  client = new Client();
        //}
        return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public ConseillerBean getConseillerBean() {
		return conseillerBean;
	}

	public void setConseillerBean(ConseillerBean conseillerBean) {
		this.conseillerBean = conseillerBean;
	}

	public void onUserSelect(SelectEvent event){ 
    	this.client =  (Client)event.getObject();
    	System.out.println("client = "+client.getId());
    }
    public void onUserUnselect(UnselectEvent event)
    {
    	client =  null;
    }
    
    public void rowSelect(SelectEvent event){
    	client =  (Client)event.getObject();
    	System.out.println("client = "+client.getPrenom());	
}
    
    public String ajouterClient(){
    	client.setAdresse(adresse);
    	serviceConseiller.ajouterClient(client);
    	return "ajouterClient";
    }
    
    public String modifierClient(){
    	client.setAdresse(adresse);
    	serviceConseiller.modifierInfoClient(client);
    	return "listerClients";
    }

}
