package com.gtm.proxiv4.service;

import com.gtm.proxiv4.metier.Employe;

public interface IServiceEmploye {

	public Employe findEmployeByEmail(String email);
}
