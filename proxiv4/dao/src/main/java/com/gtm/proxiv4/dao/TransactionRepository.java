package com.gtm.proxiv4.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gtm.proxiv4.metier.Client;
import com.gtm.proxiv4.metier.Compte;
import com.gtm.proxiv4.metier.Gerant;
import com.gtm.proxiv4.metier.Transaction;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction,Long> {

	List<Transaction> findByDateAfterAndCompteDebiteurIn(Date dateDebut, List<Compte> comptes);

	List<Transaction> findByCompteDebiteurIn(List<Compte> comptes);
}
