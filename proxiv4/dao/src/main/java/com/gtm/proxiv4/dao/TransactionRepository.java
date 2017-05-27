package com.gtm.proxiv4.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

	List<Transaction> findByDateAfterAndCompteDebiteurIn(Date dateDebut, List<Compte> comptes);
	
	List<Transaction> findByDateAfterAndCompteDebiteurOrDateAfterAndCompteCrediteurOrderByDateDesc(Date dateDebut1, Compte compteDebiteur,Date dateDebut2, Compte compteCrediteur);

	List<Transaction> findByCompteDebiteurInOrderByDateDesc(List<Compte> comptes);

	@Query("SELECT t "
			+ "FROM Transaction t "
			+ "JOIN FETCH t.compteDebiteur cb "
			+ "JOIN FETCH cb.client cl "
			+ "JOIN FETCH cl.conseiller cs "
			+ "JOIN FETCH cs.gerant g "
			+ "WHERE g.id = ?1 "
			+ "AND t.date >= ?2 ")
	List<Transaction> findByGerantAndByDateAfter(long idGerant, Date dateDebut);
}
