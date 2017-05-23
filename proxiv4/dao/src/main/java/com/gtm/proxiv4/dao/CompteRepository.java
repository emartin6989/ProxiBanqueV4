package com.gtm.proxiv4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gtm.proxiv4.metier.Compte;

public interface CompteRepository extends JpaRepository<Compte,Long>  {

	public List<Compte> findByClientId(long id);
	

	
}
