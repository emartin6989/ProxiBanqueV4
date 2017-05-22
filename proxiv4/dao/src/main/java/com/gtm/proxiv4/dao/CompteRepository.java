package com.gtm.proxiv4.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtm.proxiv4.metier.Compte;

public interface CompteRepository extends JpaRepository<Compte,Long>  {

}
