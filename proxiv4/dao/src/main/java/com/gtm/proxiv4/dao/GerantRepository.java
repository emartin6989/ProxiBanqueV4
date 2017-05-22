package com.gtm.proxiv4.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtm.proxiv4.metier.Gerant;

public interface GerantRepository extends JpaRepository<Gerant,Long> {

	public Gerant findOneByEmail(String email);
}
