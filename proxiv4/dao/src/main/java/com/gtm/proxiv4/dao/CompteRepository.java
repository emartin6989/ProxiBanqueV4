package com.gtm.proxiv4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Conseiller;
import com.gtm.proxiv4.metier.Gerant;

public interface CompteRepository extends JpaRepository<Compte, Long> {

	public List<Compte> findByClientId(long id);

	public List<Compte> findByClientNotIn(List<Client> clients);

	public List<Compte> findByIdNot(long idCompte);
	
	public List<Compte> findByClientIn(List<Client> clients);

	public Compte findById(long idCompte);

	@Query("SELECT c FROM Compte c "
			+ "JOIN FETCH c.client cl "
			+ "JOIN FETCH cl.conseiller cs "
			+ "JOIN FETCH cs.gerant g "
			+ "WHERE g = ?1")
	public List<Compte> findByGerant(Gerant gerant);

	@Query("SELECT c FROM Compte c "
			+ "JOIN FETCH c.client cl "
			+ "JOIN FETCH cl.conseiller cs "
			+ "WHERE cs = ?1")
	public List<Compte> findByConseiller(Conseiller conseiller);
}
