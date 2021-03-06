package com.gtm.proxiv4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gtm.proxiv4.metier.Conseiller;

@Repository
public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {

	public List<Conseiller> findByGerantId(long id);

	public Conseiller findOneByEmail(String email);

	public Conseiller findWithClientsById(long id);
}
