package com.gtm.proxiv4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Conseiller;

public interface ClientRepository extends JpaRepository<Client,Long> {
	
	public List<Client> findByConseillerId(long id);

}
