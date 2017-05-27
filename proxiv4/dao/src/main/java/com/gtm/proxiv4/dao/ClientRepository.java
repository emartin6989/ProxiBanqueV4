package com.gtm.proxiv4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Gerant;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
	
	public List<Client> findByConseillerId(long id);
	
	public Client findByEmail(String email);

	@Query("SELECT c FROM Client c "
			+ "JOIN FETCH c.conseiller cs "
			+ "JOIN FETCH cs.gerant g "
			+ "WHERE g = ?1")
	public List<Client> findByGerant(Gerant gerant);

}
