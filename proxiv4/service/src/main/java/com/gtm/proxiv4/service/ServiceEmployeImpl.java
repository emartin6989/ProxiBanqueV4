package com.gtm.proxiv4.service;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtm.proxiv4.dao.ClientRepository;
import com.gtm.proxiv4.dao.CompteRepository;
import com.gtm.proxiv4.dao.ConseillerRepository;
import com.gtm.proxiv4.dao.EmployeRepository;
import com.gtm.proxiv4.dao.GerantRepository;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Employe;
import com.gtm.proxiv4.metier.Gerant;

@Service
public class ServiceEmployeImpl implements IServiceEmploye {

	@Autowired
	GerantRepository gerantRepo;
	@Autowired
	ConseillerRepository conseillerRepo;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	CompteRepository compteRepo;
	@Autowired
	EmployeRepository employeRepo;

	@Override
	public Employe findEmployeByEmail(String email) {

		Employe e = employeRepo.findOneByEmail(email);

		if (e instanceof Conseiller) {
			e = (Conseiller) e;
		}
		if (e instanceof Gerant) {
			e = (Gerant) e;
		}

		return e;
	}

}
