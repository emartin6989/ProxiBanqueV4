package com.gtm.proxiv4.mbeans;

import javax.annotation.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.catalina.realm.RealmBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gtm.proxiv4.dao.ConseillerRepository;
import com.gtm.proxiv4.dao.GerantRepository;
import com.gtm.proxiv4.dao.RoleRepository;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.metier.Role;

@Controller
@ManagedBean
@RequestScoped
public class PopulateBean {
	
	@Autowired
	ConseillerRepository cr;
	@Autowired
	RoleRepository rr;
	@Autowired
	GerantRepository gr;
	@Autowired
	Gerant gerant;
	@Autowired
	Conseiller conseiller;
	@Autowired
	Role role;

	public ConseillerRepository getCr() {
		return cr;
	}

	public void setCr(ConseillerRepository cr) {
		this.cr = cr;
	}

	public RoleRepository getRr() {
		return rr;
	}

	public void setRr(RoleRepository rr) {
		this.rr = rr;
	}

	public GerantRepository getGr() {
		return gr;
	}

	public void setGr(GerantRepository gr) {
		this.gr = gr;
	}

	public Gerant getGerant() {
		return gerant;
	}

	public void setGerant(Gerant gerant) {
		this.gerant = gerant;
	}

	public Conseiller getConseiller() {
		return conseiller;
	}

	public void setConseiller(Conseiller conseiller) {
		this.conseiller = conseiller;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void ajouterConseiller() {
		
		conseiller.setPassword(RealmBase.Digest("conseiller", "SHA-1", "UTF-8"));
		role.setEmail(conseiller.getEmail());
		role.setRole("CONSEILLER");

		cr.save(conseiller);
		rr.save(role);
		
	}
	
	public void ajouterGerant() {
		
		gerant.setPassword(RealmBase.Digest("conseiller", "SHA-1", "UTF-8"));
		
		role.setEmail(gerant.getEmail());
		role.setRole("GERANT");

		gr.save(gerant);
		rr.save(role);
		
	}
	
	


}
