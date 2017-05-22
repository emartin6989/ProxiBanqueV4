package com.gtm.proxiv4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gtm.proxiv4.metier.Conseiller;

public interface ConseillerRepository extends JpaRepository<Conseiller,Long> {

	public List<Conseiller> findByGerantId(long id);
}
