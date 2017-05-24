package com.gtm.proxiv4.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

	List<Transaction> findByDateAfterAndCompteDebiteurIn(Date dateDebut, List<Compte> comptes);


}
